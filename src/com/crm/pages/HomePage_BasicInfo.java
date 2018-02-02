package com.crm.pages;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;

public class HomePage_BasicInfo extends TestBase{
	
	public static Actions act;

	public HomePage_BasicInfo() throws IOException{
		//super(); this is implicit
		PageFactory.initElements(driver, this);
	}
	
	/*@FindBy(how=How.XPATH, using="//td[contains(text(), 'User')]")
	private WebElement header_UserName;*/
	
	@FindBy(how=How.XPATH, using="//td[contains(text(), 'CRMPRO')]//following-sibling::td")
	private List<WebElement> lnk_lstHeaders;
	
	@FindBy(how=How.XPATH, using="//td[contains(text(), 'User')]//following-sibling::td[2]")
	private WebElement lnk_TimeStamp;
	
	
	@FindBy(how=How.XPATH, using="//div[@id='navmenu']/ul[@class='mlddm']/li")
	private List<WebElement> lnk_lstOfMenusAndSubMenus;
	
	@FindBy(how=How.XPATH, using="//a[contains(@href, 'logout')]")
	private WebElement lnk_LogOut;
		

	public void swtichToActualFrame(){
		driver.switchTo().frame("mainpanel");		
	}
	
	public void swtichToSideFrame(){
		driver.switchTo().frame("leftpanel");		
	}
	
	public String getApplicationName(){
		
		return driver.getTitle();
	}
	
	/*public String getUserName(){
		
		return header_UserName.getText().trim();
		}*/
	
	public List<String> getHeaderLinks(){
		
		List<String> lst_Headers = new ArrayList<String>();
		lst_Headers.add(lnk_lstHeaders.get(0).getText().trim());
		lst_Headers.add(lnk_lstHeaders.get(1).getText().trim());
		return lst_Headers;
		}
	
	public List<String> getAllMenus(){
		
		List<String> lst_TabNames = new ArrayList<String>();
		for(WebElement w: lnk_lstOfMenusAndSubMenus){
			lst_TabNames.add(w.findElement(By.tagName("a")).getText().trim());
		}
		return lst_TabNames;
	}
	
	public List<String> getAllSubMenus(){
		
		List<String> lst_SubMenuNames = new ArrayList<String>();		
		Actions act = new Actions(driver);
		for(int i=1; i<(lnk_lstOfMenusAndSubMenus.size())-1; i++){
			
			act.moveToElement(lnk_lstOfMenusAndSubMenus.get(i)).perform();
			List<WebElement> subMenus = lnk_lstOfMenusAndSubMenus.get(i).findElements(By.tagName("li"));
				for(WebElement w:subMenus){
					lst_SubMenuNames.add(w.findElement(By.tagName("a")).getText().trim());
					}			
		}
		return lst_SubMenuNames;
		
	}	
	
	
	public String getLoggedInTimeStamp(){
		
		return lnk_TimeStamp.getText();		
			
	}
	
	public void logout(){
		
		lnk_LogOut.click();
		}

		
	
}
