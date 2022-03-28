package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	
	Workbook wb;
	
	public  ExcelFileUtil(String excelpath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		 wb = WorkbookFactory.create(fi);
	}

	//Get Rowcount
	public int rowcount(String sheetName)
	{
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		return rowcount;
	}
	
	//Get data from cell
	public String getCellData(String sheetName,int row,int column)
	{		
		String data = " ";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			
			// convert integer type into string
			data = String.valueOf(celldata);
		}
		else 
		{
			data = wb.getSheet(data).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	
	//Method for set CellData
	public void setCellData(String sheetName,int row,int column,String status,String writeexcel) throws Throwable
	{
		//get sheet from workbook
		Sheet ws = wb.getSheet(sheetName);
		//get row from sheet
		Row rownum = ws.getRow(row);
		//get cell from row
		Cell cell = rownum.createCell(column);
		//write status
		cell.setCellValue(status);

		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		else if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}

		else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
	FileOutputStream fo = new FileOutputStream(writeexcel);
	wb.write(fo);
	}
}
