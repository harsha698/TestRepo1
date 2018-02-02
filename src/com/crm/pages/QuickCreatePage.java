package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class QuickCreatePage extends TestBase{
	
//1. to create a constructor to load all the elements of this page
	public QuickCreatePage() throws IOException{
		PageFactory.initElements(driver, this);
	}

//2. to find all the elements: a Page Repository
	@FindBy(how=How.XPATH, using="//a[contains(text(), 'Quick Create»')]")
	private WebElement lnk_Qkcreate;
	
	@FindBy(how=How.XPATH, using="//input[@name='company_name']")
	private WebElement lnk_CompName;
	
	
	@FindBy(how=How.XPATH, using="//input[@name='contact_first_name']")
	private WebElement lnk_FName;
	
	@FindBy(how=How.XPATH, using="//input[@name='contact_surname']")
	private WebElement lnk_LName;
	
	@FindBy(how=How.XPATH, using="//input[@value='Create' and @class='button']")
	private WebElement lnk_btnCrt;
	
	public void quickCreate(String compName, String fname, String lname){
		
		lnk_Qkcreate.click();
		lnk_CompName.sendKeys(compName);
		lnk_FName.sendKeys(fname);
		lnk_LName.sendKeys(lname);
		lnk_btnCrt.click();
	}
	
	
	
	
	
}
