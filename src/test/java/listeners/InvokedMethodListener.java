package listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener
        implements IInvokedMethodListener {

    @Override
    public void beforeInvocation(
            IInvokedMethod method,
            ITestResult testResult) {

        if (method.isTestMethod()) {

            System.out.println(
                    "BEFORE TEST METHOD: "
                            + method.getTestMethod()
                            .getMethodName()
            );

        } else if (method.isConfigurationMethod()) {

            System.out.println(
                    "BEFORE CONFIG METHOD: "
                            + method.getTestMethod()
                            .getMethodName()
            );
        }
    }

    @Override
    public void afterInvocation(
            IInvokedMethod method,
            ITestResult testResult) {

        if (method.isTestMethod()) {

            System.out.println(
                    "AFTER TEST METHOD: "
                            + method.getTestMethod()
                            .getMethodName()
            );

        } else if (method.isConfigurationMethod()) {

            System.out.println(
                    "AFTER CONFIG METHOD: "
                            + method.getTestMethod()
                            .getMethodName()
            );
        }
    }
}