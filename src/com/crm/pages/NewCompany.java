package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

import com.crm.base.TestBase;
import com.crm.utils.ExcelHandler;
import com.crm.utils.TestUtils;

public class NewCompany extends TestBase{
	
	public ExcelHandler excel;
	
	public NewCompany()throws IOException{
		//super(); this is implicit
		PageFactory.initElements(driver, this);
		try {
			excel = new ExcelHandler("D:\\selenium imp projects\\FreeCRM\\TestData.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FindBy(how=How.NAME, using="company_name")
	private WebElement txt_CompanyName;
	
	@FindBy(how=How.NAME, using="industry")
	private WebElement txt_Industry;
	
	@FindBy(how=How.NAME, using="annual_revenue")
	private WebElement txt_AnnualRevenue;
	
	@FindBy(how=How.NAME, using="num_of_employees")
	private WebElement txt_Employees;
	
	@FindBy(how=How.NAME, using="status")
	private WebElement drp_Status;
	
	@FindBy(how=How.NAME, using="category")
	private WebElement drp_Category;
		
	@FindBy(how=How.NAME, using="priority")
	private WebElement drp_Priority;
	
	@FindBy(how=How.NAME, using="source")
	private WebElement drp_Source;
	
	@FindBy(how=How.NAME, using="identifier")
	private WebElement txt_Iden;
	
	@FindBy(how=How.NAME, using="vat_number")
	private WebElement txt_vat_taxNum;
	
	@FindBy(how=How.NAME, using="phone")
	private WebElement txt_Phone;
	
	@FindBy(how=How.NAME, using="fax")
	private WebElement txt_Fax;
	
	@FindBy(how=How.NAME, using="website")
	private WebElement txt_Website;
	
	@FindBy(how=How.NAME, using="email")
	private WebElement txt_Email;
	
	@FindBy(how=How.NAME, using="symbol")
	private WebElement txt_Symbol;
	
	@FindBy(how=How.NAME, using="address_title")
	private WebElement txt_AddTitle;
	
	@FindBy(how=How.NAME, using="city")
	private WebElement txt_City;
	
	@FindBy(how=How.NAME, using="state")
	private WebElement txt_State;
	
	@FindBy(how=How.NAME, using="postcode")
	private WebElement txt_Zip;
	
	@FindBy(how=How.NAME, using="country")
	private WebElement txt_Country;
	
	@FindBy(how=How.XPATH, using="//input[@value='Save']")
	private List<WebElement> btns_Save;
	
	@FindBy(how=How.XPATH, using="//td[contains(text(), ' Company:')]")
	private WebElement new_Company;
	
	
	@SuppressWarnings("finally")
	public String createNewCompany(String sheetName, int rowNum){
		try {
		String timeStamp = TestUtils.generateTimeStamp();
		excel.setCellData(sheetName, rowNum, "CompanyName", excel.getCellData(sheetName, rowNum, "Company")+timeStamp);
		excel.setCellData(sheetName, rowNum, "Identifier", excel.getCellData(sheetName, rowNum, "Iden")+timeStamp);
		
		txt_CompanyName.sendKeys(excel.getCellData(sheetName, rowNum, "CompanyName"));
		txt_Industry.sendKeys(excel.getCellData(sheetName, rowNum, "Industry"));
		txt_AnnualRevenue.sendKeys(excel.getCellData(sheetName, rowNum, "Revenue"));
		txt_Employees.sendKeys(excel.getCellData(sheetName, rowNum, "Employees"));
		TestUtils.dropDownHandle(drp_Status, excel.getCellData(sheetName, rowNum, "Status"));
		TestUtils.dropDownHandle(drp_Category, excel.getCellData(sheetName, rowNum, "Category"));
		TestUtils.dropDownHandle(drp_Priority, excel.getCellData(sheetName, rowNum, "Priority"));
		TestUtils.dropDownHandle(drp_Source, excel.getCellData(sheetName, rowNum, "Source"));
		txt_Iden.sendKeys(excel.getCellData(sheetName, rowNum, "Identifier"));
		txt_vat_taxNum.sendKeys(excel.getCellData(sheetName, rowNum, "VAT_TAX_Number"));
		txt_Phone.sendKeys(excel.getCellData(sheetName, rowNum, "Phone"));
		txt_Fax.sendKeys(excel.getCellData(sheetName, rowNum, "Fax"));
		txt_Website.sendKeys(excel.getCellData(sheetName, rowNum, "Website"));
		txt_Email.sendKeys(excel.getCellData(sheetName, rowNum, "Email"));
		txt_Symbol.sendKeys(excel.getCellData(sheetName, rowNum, "Symbol"));
		txt_AddTitle.sendKeys(excel.getCellData(sheetName, rowNum, "Address"));
		txt_City.sendKeys(excel.getCellData(sheetName, rowNum, "City"));
		txt_State.sendKeys(excel.getCellData(sheetName, rowNum, "State"));
		txt_Zip.sendKeys(excel.getCellData(sheetName, rowNum, "Zip"));
		txt_Country.sendKeys(excel.getCellData(sheetName, rowNum, "Country"));
		btns_Save.get(1).click();
				
		}//end of try		
		catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			return excel.getCellData(sheetName, rowNum, "CompanyName");
		}
	}//end of method
	
	public String newCompanyName(){
		String company = new_Company.getText();
		String[] arr_new_CompanyName = company.split(":");
		return arr_new_CompanyName[1].trim();
		
	}
	
	
}
