import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;

public class ExcelDataProviderTest {

    @DataProvider(name = "users")
    public Object[][] users() {

        String filePath =
                "src/test/resources/testdata/users.xlsx";

        try (
                FileInputStream input =
                        new FileInputStream(filePath);

                Workbook workbook =
                        WorkbookFactory.create(input)
        ) {

            Sheet sheet =
                    workbook.getSheet("Users");

            Object[][] data =
                    new Object[sheet.getLastRowNum()][2];

            for (int i = 1;
                 i <= sheet.getLastRowNum();
                 i++) {

                Row row = sheet.getRow(i);

                data[i - 1][0] =
                        row.getCell(0)
                                .getStringCellValue();

                data[i - 1][1] =
                        row.getCell(1)
                                .getStringCellValue();
            }

            return data;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to read Excel test data",
                    e
            );
        }
    }

    @Test(dataProvider = "users")
    public void loginWithExcelData(
            String username,
            String password) {

        System.out.println(
                username + " | " + password
        );

        if (username.equals("user2")) {

            Assert.fail(
                    "Intentional failure for DataProvider reporting demo"
            );
        }
    }
}