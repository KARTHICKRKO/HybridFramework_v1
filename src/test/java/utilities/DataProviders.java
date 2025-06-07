package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\Opencart_LoginData.xlsx"; // Path to Excel file

		ExcelUtils xlutil = new ExcelUtils(path); // Excel utility instance

		int totalrows = xlutil.getRowcount("Sheet1");
		int totalcols = xlutil.getCellcount("Sheet1", 1);

		String loginData[][] = new String[totalrows][totalcols];//created for 2Dim array which can store

		// Start from i = 1 to skip header row
		for (int i = 1; i <= totalrows; i++)// 1 //read the data from xcel and storing in 2 Dime array
		{
			for (int j = 0; j < totalcols; j++) //0 i is row and j is col
			{
				loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}

		return loginData; // returning 2D array
	}
}
