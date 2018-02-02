package rough;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.utils.TestUtils;

public class Test1 {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.firefox.marionette", "D:\\Firefox_geckodriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.get("https://www.freecrm.com/index.html");
		driver.findElement(By.name("username")).sendKeys("hemant01");
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
		driver.switchTo().frame(1);
		WebElement tasks = driver.findElement(By.xpath("//a[text()='Tasks']"));
		Actions act = new Actions(driver);
		
		act.moveToElement(tasks).perform();
		driver.findElement(By.xpath("//a[text()='New Task']")).click();
		driver.findElement(By.xpath("//input[@id='title' and @name='title']")).sendKeys("Test12");
		
		driver.findElement(By.xpath("//*[@id='f_trigger_c_deadline']")).click();
		try {
			Thread.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String xpathOfCalMonthyear = "//td[@class='title']";		
		WebElement left_arrow = driver.findElement(By.xpath("//td[@class='title']//parent::*//following-sibling::tr[@class='headrow']/td[2]/div"));
		WebElement right_arrow = driver.findElement(By.xpath("//td[@class='title']//parent::*//following-sibling::tr[@class='headrow']/td[4]/div"));
		List<WebElement> days_list = driver.findElements(By.xpath("//td[@class='title']//ancestor::thead//following-sibling::tbody/tr/td"));
		
		TestUtils.handleCalendar(driver, 12, 2018, 25, xpathOfCalMonthyear, left_arrow, right_arrow, days_list);
		
		String originalWindowHandle = driver.getWindowHandle();
		System.out.println(originalWindowHandle);
		driver.findElement(By.xpath("//input[@name='contact_lookup']//following-sibling::input[@value='Lookup']")).click();
		try {
			Thread.sleep(3L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		java.util.Set<String> windowHandles = driver.getWindowHandles();
		
		for (String window: windowHandles) {

		    //skip if parent window
		    if(!window.equals(originalWindowHandle)){
		        //if not parent switch to window
		        driver.switchTo().window(window);
		    	driver.findElement(By.xpath("//input[@id='search' and @name='search']")).sendKeys("hemant");
				driver.findElement(By.xpath("//input[@id='search' and @name='search']")).sendKeys(Keys.ENTER);
				driver.findElement(By.xpath("//a[contains(text(), 'Hemant')]")).click();       
		    }  
		    // if you are done switch back to parent`   
		    driver.switchTo().window(originalWindowHandle);     
		}		
		 driver.switchTo().window(originalWindowHandle); 
		 System.out.println(originalWindowHandle);
		
		
	}

}
