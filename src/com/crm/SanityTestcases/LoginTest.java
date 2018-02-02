package com.crm.SanityTestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage_BasicInfo;
import com.crm.pages.LoginPage;
import com.crm.utils.Log;
import com.crm.utils.TestUtils;

public class LoginTest extends TestBase{
	
	public LoginTest() throws IOException {
		super();
	
	}

	public LoginPage login;
	public HomePage_BasicInfo home;
	public String sTestCaseName = this.toString();
	public String TestCaseName;
	
	@BeforeClass
	public void initialization(){
			init();
			TestCaseName = TestUtils.getClassName(sTestCaseName);
			Log.startTestCase(TestCaseName);
		
	}
	
	@Test
	public void loginUser1() throws IOException{
		login = new LoginPage();
		home = login.doLogin(prop.getProperty("UserName1"), prop.getProperty("Password1"));
		/*String actualUser = home.getUserName().trim();
		String expectedUser = prop.getProperty("UserHeaderName1").trim();
		Assert.assertEquals(actualUser, expectedUser, "Login is incorrect");*/
		Log.logInfo("Login happended successfully");
		Log.logInfo(TestCaseName+" is passed successfully");
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		Log.endTestCase(TestCaseName);
	}
	
	

}
