package reporting;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomReporter implements IReporter {

    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites,
            List<ISuite> suites,
            String outputDirectory) {

        StringBuilder html = new StringBuilder();

        html.append("""
                <html>
                <head>
                    <title>Custom TestNG Report</title>
                </head>
                <body>
                    <h1>Automation Execution Report</h1>
                """);

        for (ISuite suite : suites) {

            html.append("<h2>Suite: ")
                    .append(suite.getName())
                    .append("</h2>");

            for (ISuiteResult suiteResult
                    : suite.getResults().values()) {

                ITestContext context =
                        suiteResult.getTestContext();

                html.append("<h3>XML Test: ")
                        .append(context.getName())
                        .append("</h3>");

                html.append("<p>Passed: ")
                        .append(context.getPassedTests().size())
                        .append("</p>");

                html.append("<p>Failed: ")
                        .append(context.getFailedTests().size())
                        .append("</p>");

                html.append("<p>Skipped: ")
                        .append(context.getSkippedTests().size())
                        .append("</p>");

                for (ITestResult failed
                        : context.getFailedTests()
                        .getAllResults()) {

                    html.append("<p><b>Failed Method:</b> ")
                            .append(
                                    failed.getMethod()
                                            .getMethodName()
                            )
                            .append("</p>");

                    html.append("<p><b>Reason:</b> ")
                            .append(failed.getThrowable())
                            .append("</p>");
                }
            }
        }

        html.append("""
                </body>
                </html>
                """);

        try {

            Path reportDirectory =
                    Path.of(outputDirectory);

            Files.createDirectories(reportDirectory);

            Path reportPath =
                    reportDirectory.resolve(
                            "custom-report.html"
                    );

            Files.writeString(
                    reportPath,
                    html.toString()
            );

            System.out.println(
                    "Custom HTML report created at: "
                            + reportPath.toAbsolutePath()
            );

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to create custom report",
                    e
            );
        }
    }
}