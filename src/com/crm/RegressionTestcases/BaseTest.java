package com.crm.RegressionTestcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.crm.base.TestBase;

public class BaseTest extends TestBase{
	
	public BaseTest() throws IOException {
		super();
	}

	@BeforeSuite
	public void initialization(){
		init();
	}
	
	@AfterSuite
	public void tearDown(){
		driver.quit();
	}

}
