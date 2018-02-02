package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class HomePage_CompanyTab extends TestBase{
	
	public static Actions act;

	public HomePage_CompanyTab() throws IOException{
		//super(); this is implicit
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//a[text()='Companies']")
	private WebElement lnk_Companies;
	
	@FindBy(how=How.XPATH, using="//a[text()='New Company']")
	private WebElement lnk_NewCompany;
	
	@FindBy(how=How.XPATH, using="//a[text()='New Company']//parent::li//following-sibling::li//a[@title='Full Search Form']")
	private WebElement lnk_SearchCompany;
	

	
	public NewCompany createCompany()throws IOException{
		driver.switchTo().frame("mainpanel");
		act = new Actions(driver);
		act.moveToElement(lnk_Companies).perform();
		lnk_NewCompany.click();
		return new NewCompany();
		
	}
	
	public SearchCompany srchCompany()throws IOException{
		driver.switchTo().frame("mainpanel");
		act = new Actions(driver);
		act.moveToElement(lnk_Companies).perform();
		lnk_NewCompany.click();
		return new SearchCompany();
	}
		
	
	
}
