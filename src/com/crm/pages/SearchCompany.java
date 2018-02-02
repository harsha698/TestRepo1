package com.crm.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;
import com.crm.utils.ExcelHandler;

public class SearchCompany extends TestBase{

	public ExcelHandler excel;
	
	public SearchCompany()throws IOException{
		PageFactory.initElements(driver, this);
		excel = new ExcelHandler("D:\\selenium imp projects\\FreeCRM\\TestData.xlsx");
		
	}
	
	@FindBy(how=How.NAME, using="cs_name")
	private WebElement txt_CompanyName;
	
	@FindBy(how=How.NAME, using="cs_identifier")
	private WebElement txt_Identifier;
	
	@FindBy(how=How.XPATH, using="//input[@value='Search Companies']")
	private List<WebElement> btns_Search;
	private WebElement btn_Search = btns_Search.get(1);
	
	@FindBy(how=How.XPATH, using="//input[@value='Save']")
	private List<WebElement> btns_Save;
	private WebElement btn_Save = btns_Save.get(1);

	
	public void searchCompany(String sheetName, int rowNum){
		txt_CompanyName.sendKeys(excel.getCellData(sheetName, rowNum, "CompanyName"));
		txt_Identifier.sendKeys(excel.getCellData(sheetName, rowNum, "Identifier"));
		btn_Search.click();
	}
	
	public void amendSearchedCompany(String sheetName, int rowNum){
		searchCompany(sheetName, rowNum);
		driver.findElement(By.xpath("//a[text()='"+excel.getCellData(sheetName, rowNum, "CompanyName")+"' and contains(@_name, '"+excel.getCellData(sheetName, rowNum, "CompanyName")+"')]")).click();
		driver.findElement(By.xpath("//input[@class='button' and @value='Edit']"));
		driver.findElement(By.name("service")).sendKeys("Amended Company");
		btn_Save.click();
		
		
	}
	
	
	
}
