package listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class MethodInterceptor implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods,ITestContext context) {

        List<IMethodInstance> filteredMethods =
                new ArrayList<>();

        for (IMethodInstance method : methods) {

            String methodName =
                    method.getMethod().getMethodName();

            System.out.println(
                    "INTERCEPTED METHOD: " + methodName
            );

            if (!methodName.equals(
                    "productCardNavigatesToProductPage")) {

                filteredMethods.add(method);
            }
        }

        return filteredMethods;
    }
}