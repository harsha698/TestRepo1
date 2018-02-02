package com.crm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.listeners.WebDriverListener;
import com.crm.utils.Log;

public class TestBase {
	
	public static FileInputStream fis;
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Log log;
	public static WebDriverListener listener = null;
	public static EventFiringWebDriver fire = null;
	
	
	public TestBase()throws IOException{
		//loading log4j.xml file
				DOMConfigurator.configure("log4j.xml");
				
		//loading properties file
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\crm\\config\\Configuration.properties");
				prop.load(fis);
	}
	
	
	public static void init() {
		//Launching browser
		if(prop.getProperty("BrowserName").equals("Firefox")){
			System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir")+"geckodriver.exe");
			driver = new FirefoxDriver();
			Log.logInfo("Firefox is launched");
		}
		else if(prop.getProperty("BrowserName").equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"chromedriver.exe");
			driver = new ChromeDriver();
			Log.logInfo("Chrome is launched");
		}
		else if(prop.getProperty("BrowserName").equals("IE")){
			DesiredCapabilities des = new DesiredCapabilities();
			des.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"iedriver.exe");
			driver = new InternetExplorerDriver(des);
			Log.logInfo("IE is launched");
		}
		
		
		//registering WebDriver listener
		fire = new EventFiringWebDriver(driver);
		listener = new WebDriverListener();
		fire.register(listener);
		driver=fire;		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("ImplicitWait")), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("PageLoadTimeOut")), TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("Url"));
		Log.logInfo("CRM PRO url is launched");
		
	}

}
