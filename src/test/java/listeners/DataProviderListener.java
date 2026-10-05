package listeners;

import org.testng.IDataProviderListener;
import org.testng.IDataProviderMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

public class DataProviderListener
        implements IDataProviderListener {

    @Override
    public void beforeDataProviderExecution(
            IDataProviderMethod dataProviderMethod,
            ITestNGMethod method,
            ITestContext context) {

        System.out.println(
                "DATA PROVIDER STARTED: "
                        + dataProviderMethod.getName()
        );

        System.out.println(
                "For test: "
                        + method.getMethodName()
        );
    }

    @Override
    public void afterDataProviderExecution(
            IDataProviderMethod dataProviderMethod,
            ITestNGMethod method,
            ITestContext context) {

        System.out.println(
                "DATA PROVIDER COMPLETED: "
                        + dataProviderMethod.getName()
        );
    }

    @Override
    public void onDataProviderFailure(
            ITestNGMethod method,
            ITestContext context,
            RuntimeException exception) {

        System.out.println(
                "DATA PROVIDER FAILED for test: "
                        + method.getMethodName()
        );

        System.out.println(
                "Reason: "
                        + exception.getCause()
        );
    }
}