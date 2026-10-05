package listeners;

import org.testng.IDataProviderInterceptor;
import org.testng.IDataProviderMethod;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderInterceptor
        implements IDataProviderInterceptor {

    @Override
    public Iterator<Object[]> intercept(
            Iterator<Object[]> original,
            IDataProviderMethod dataProviderMethod,
            ITestNGMethod method,
            ITestContext context) {

        List<Object[]> filteredData =
                new ArrayList<>();

        while (original.hasNext()) {

            Object[] row = original.next();

            if (!row[0].equals("3")) {
                filteredData.add(row);
            }
        }

        return filteredData.iterator();
    }
}