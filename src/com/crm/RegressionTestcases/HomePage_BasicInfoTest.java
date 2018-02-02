package com.crm.RegressionTestcases;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.HomePage_BasicInfo;
import com.crm.utils.Log;
import com.crm.utils.TestUtils;

public class HomePage_BasicInfoTest extends TestBase{
	
	public HomePage_BasicInfoTest() throws IOException {
		super();
	}

	public HomePage_BasicInfo home = null;
	public String sTestCaseName = this.toString();
	public String TestCaseName = null;
	
	@BeforeClass
	public void start() throws InterruptedException{
		TestCaseName = TestUtils.getClassName(sTestCaseName);
		Log.startTestCase(TestCaseName);
		home.swtichToActualFrame();
		Log.logInfo("Switched to frame of execution");
		}
	
	@Test(priority=1)
	public void validateApplicationName(){
		Assert.assertEquals(home.getApplicationName(), "CRMPRO", "Incorrect application name");
		Log.logInfo("Logged into correct Application: " +home.getApplicationName());
	}
	/*
	@Test(priority=2)
	public void validateLoggedInUserName(){
		Assert.assertEquals(home.getUserName(), "User: Hemant H Kumar", "Incorrect Username");
		Log.logInfo("logged in user is: " +prop.getProperty("UserName1"));	
			
	}*/
	
	@Test(priority=3)
	public void validateLoggedInTimeStamp(){
		Assert.assertEquals(home.getLoggedInTimeStamp(), TestUtils.generateTimeStamp(), "Incorrect timestamp");
		Log.logInfo("System date time is displayed correctly");
		
	}
	
	@Test(priority=4)
	public void validateHeaderLinks(){
		List<String> headers = home.getHeaderLinks();	
		Assert.assertEquals(headers.get(0), "Setup", "Incorrect header");
		Assert.assertEquals(headers.get(1), "Help", "Incorrect header");
		Log.logInfo("Headers are correct");
	}
	
	@Test(priority=5)
	public void displayHomePageMenus(){
		List<String> menus = home.getAllMenus();
		for(int i=0; i<menus.size(); i++){
			System.out.println(menus.get(i));
		}
		Log.logInfo("Menu names displayed");
	}
	
	@Test(priority=6)
	public void displayHomePageSubMenus(){
		List<String> subMenus = home.getAllSubMenus();
		for(int i=0; i<subMenus.size(); i++){
			System.out.println(subMenus.get(i));
		}
		Log.logInfo("SubMenu names displayed");
	}
	
	/*	
	@Test(priority=6)
	public void validateFooter(){
		
	}
	*/
	
	@AfterClass
	public void end(){
		driver.switchTo().parentFrame();
		Log.endTestCase(TestCaseName);
	}
	
	
}
