package com.crm.RegressionTestcases;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.QuickCreatePage;
import com.crm.utils.ExcelHandler;
import com.crm.utils.Log;
import com.crm.utils.TestUtils;

public class QuickCreateTest extends TestBase{
	public QuickCreatePage qkpage = null;
	public ExcelHandler excel = null;
	public String name = this.toString();
	public String testCaseName=null;
	
	public QuickCreateTest()throws IOException {
		super();
		
	}

	@BeforeClass
	public void start() throws IOException{
		excel = new ExcelHandler(System.getProperty("user.dir")+"\\src\\com\\crm\\testData\\TestData.xlsx");
		testCaseName=TestUtils.getClassName(name);
		Log.startTestCase(testCaseName);
		driver.switchTo().frame("leftpanel");
				
	}
	
	
	@Test
	public void validateQuickCreate() throws IOException{
				
		String compName = excel.getCellData("Companies", 3, "Company")+TestUtils.getIST_TimeStamp();
		excel.setCellData("Companies", 3, "CompanyName", compName);
		
		String fname = excel.getCellData("Companies", 3, "First_Name")+TestUtils.getIST_TimeStamp();
		excel.setCellData("Companies", 3, "UpdatedFName", fname);
		
		String lname = excel.getCellData("Companies", 3, "Last_Name")+TestUtils.getIST_TimeStamp();
		excel.setCellData("Companies", 3, "UpdatedLName", lname);
				
		qkpage = new QuickCreatePage();
		qkpage.quickCreate(compName, fname, lname);	
		
		Log.logInfo("New company: "+compName +"got created successfully");
		
	}
	
	@AfterClass
	public void end(){
		driver.switchTo().parentFrame();
		Log.endTestCase(testCaseName);
	}
	
	

}
