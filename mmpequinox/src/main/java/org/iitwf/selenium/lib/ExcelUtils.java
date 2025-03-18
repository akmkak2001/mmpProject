package org.iitwf.selenium.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
  

public class ExcelUtils {

	public static String[][] getCellData(String fileName) throws IOException {
	//public static void main(String[] args) throws IOException  {
		
	
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		String data[][] = new String[rows][cols];

		for(int i =0 ; i < rows;i++) 
		{
			XSSFRow row = sheet.getRow(i);

			for(int j =0 ; j < cols;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType ctype = cell.getCellType();
				switch(ctype)
				{
				case STRING:
					data[i][j] = cell.getStringCellValue();
					System.out.println("Printing the data in the excel::" + data[i][j]);
					break;

				case NUMERIC:
					data[i][j] = cell.getNumericCellValue()+"";
					System.out.println("Fetching the numeric cell value");
					System.out.println("Printing the data in the excel::" + data[i][j]);
					break;
				}

			}

		}

		wb.close();
		return data;
	}

}
