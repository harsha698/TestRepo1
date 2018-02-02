package com.crm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {
	
	public static File f;
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook workbook;
	
	//***********************************************************************************//
	public ExcelHandler(String excelFilePath)throws IOException{
		f = new File(excelFilePath);
		fis = new FileInputStream(f);
		workbook = new XSSFWorkbook(fis);
	}
	
	//***********************************************************************************//
	public int getRowCount(String sheetName){
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getPhysicalNumberOfRows();
		return noOfRows;
		}
	
	//***********************************************************************************//
	public short getColCount(String sheetName, int rowNum){
		XSSFSheet sheet = workbook.getSheet(sheetName);
		short noOfCols = sheet.getRow(rowNum).getLastCellNum();
		return noOfCols;
		}
	
	//this method will hold the entire sheetName data inside this list
	public List<HashMap<String, String>> excelDataHolder(String sheetName){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int noOfRows = sheet.getPhysicalNumberOfRows();
			for(int r=1; r<noOfRows; r++){
				HashMap<String, String> map = new HashMap<String, String>();
					for(int c=0; c<sheet.getRow(r).getPhysicalNumberOfCells(); c++){
						String key = sheet.getRow(0).getCell(c).getStringCellValue().toString();
						String value = sheet.getRow(r).getCell(c).getStringCellValue().toString();
						map.put(key, value);
						}
					list.add(map);
			}
		return list;
	}
	
	//***********************************************************************************//
	
	//use this list to fetch the value of 1 particular cell based on colName
	public String getCellData(String sheetName, int rowNum, String colName){
		int colNum = 0;
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		for(int c=0; c<cols; c++){
			if(sheet.getRow(0).getCell(c).getStringCellValue().toString().equals(colName)) {
				colNum=c+1;
				break;
				}
			}
		
		String cellData = sheet.getRow(rowNum-1).getCell(colNum-1).getStringCellValue().toString();
		return cellData;
	}
	
	//***********************************************************************************//	
	//use this list to fetch the value of 1 particular cell based on colNum
		public String getCellData(String sheetName, int rowNum, int colNum){
			
			XSSFSheet sheet = workbook.getSheet(sheetName);
			String cellData = sheet.getRow(rowNum-1).getCell(colNum-1).getStringCellValue().toString();
			return cellData;
	}
	//***********************************************************************************//
	//writing data into an excel cell
		
		public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException{
			
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			if(sheet.getRow(rowNum-1).getCell(colNum-1).getStringCellValue().toString().equals(null)){
				sheet.getRow(rowNum-1).getCell(colNum-1).setCellValue(data);
				
			}
			else if(sheet.getRow(rowNum-1).getCell(colNum-1).getStringCellValue().toString()!=null)//existing value will be over ridden if cell is not blank
				{sheet.getRow(rowNum-1).getCell(colNum-1).setCellValue(data);
				}
			
			fos = new FileOutputStream(f);
			workbook.write(fos);
			fos.close();	
			
			}
	
		public int getColNumFromHeader(String sheetName, String colName) {
			int colNum = 0;
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int cols = sheet.getRow(0).getPhysicalNumberOfCells();
			for(int c=0; c<cols; c++){
				if(sheet.getRow(0).getCell(c).getStringCellValue().toString().equals(colName)) {
					colNum=c+1;
					break;
					}
				}
			return colNum;
			
		}
		//*************************************************************//
		public void setCellData(String sheetName, int rowNum, String colName, String data) throws IOException{
			int colNum = -1;
					
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			for(int c=0; c<sheet.getRow(0).getPhysicalNumberOfCells(); c++){
				if(sheet.getRow(0).getCell(c).getStringCellValue().equals(colName)) {
					colNum=c;
					break;
				}
				}
			System.out.println(colNum);
			sheet.getRow(rowNum-1).getCell(colNum).setCellValue(data);
			
			fos = new FileOutputStream(f);
			workbook.write(fos);
			fos.close();
			
		}
		
		public static void setDataIntoExcelWithTimeStamp(String filePath, String sheetName, int rowNum, int colNameToGet, int colNameToSet){
			try {
				ExcelHandler excel = new ExcelHandler(filePath);
				String cellData = excel.getCellData(sheetName, rowNum, colNameToGet);
				String timeStamp = TestUtils.getIST_TimeStamp();
				excel.setCellData(sheetName, rowNum, colNameToSet, cellData+timeStamp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
