package com.crm.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class LoginPage extends TestBase{
	
	public LoginPage() throws IOException{
		//super(); this is implicit
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.NAME, using="username")
	private WebElement txt_username;
	
	@FindBy(how=How.NAME, using="password")
	private WebElement txt_password;
	
	@FindBy(how=How.XPATH, using="//input[@value='Login']")
	private WebElement btn_login;
	
	public HomePage_BasicInfo doLogin(String userName, String password) throws IOException{
		txt_username.sendKeys(userName);
		txt_password.sendKeys(password);
		btn_login.submit();
		return new HomePage_BasicInfo();
	}
}
