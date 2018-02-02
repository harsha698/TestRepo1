package com.crm.RegressionTestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage_BasicInfo;
import com.crm.pages.HomePage_CompanyTab;
import com.crm.pages.LoginPage;
import com.crm.pages.NewCompany;
import com.crm.utils.Log;
import com.crm.utils.TestUtils;

public class NewCompanyTest extends TestBase{

	public NewCompanyTest() throws IOException {
		super();
		}

	public LoginPage login;
	public HomePage_CompanyTab compTab;
	public HomePage_BasicInfo home;
	public NewCompany new_Comp;
	public String sTestCaseName = this.toString();
	public String TestCaseName;
	
	@BeforeClass
	public void initialization(){
			init();
			TestCaseName = TestUtils.getClassName(sTestCaseName);
			Log.startTestCase(TestCaseName);
		
	}
	
	@Test(priority=1)
	public void loginUser1() throws IOException{
		login = new LoginPage();
		home = login.doLogin(prop.getProperty("UserName1"), prop.getProperty("Password1"));
		Log.logInfo("Logged in successfully");
		}
	
	@Test(priority=2)
	public void createNewCompanyTest(){
		try {
		new_Comp = compTab.createCompany();
		String comp_Name = new_Comp.createNewCompany("Companies", 2);
		Thread.sleep(Long.parseLong(prop.getProperty("SleepTime")));
		String actualCompanyName = new_Comp.newCompanyName();
		Assert.assertEquals(actualCompanyName, comp_Name, "Company did not got created");
		Log.logInfo("New company "+actualCompanyName +" got created successfully");
		}
		catch (NumberFormatException | InterruptedException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		Log.endTestCase(TestCaseName);
	}
	
	
}
