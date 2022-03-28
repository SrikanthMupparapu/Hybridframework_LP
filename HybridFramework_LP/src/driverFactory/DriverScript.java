package driverFactory;

import utilities.ExcelFileUtil;

public class DriverScript {
	
	String inputpath = "C:\\Users\\Srikanth\\git\\Hybridframework_LP\\HybridFramework_LP\\TestInPut\\HybridTest.xlsx";
	String outputpath = "C:\\Users\\Srikanth\\git\\Hybridframework_LP\\HybridFramework_LP\\TestOutPut\\HybridResults.xlsx";
	
	public void startTest() throws Throwable
	{
		//access excel file util methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	}
}
