package com.crm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestUtils {
	
	//****************************************************Utility 1********************************************************
	public static void handleCalendar(WebDriver driver, int month, int year, int date, String xpathOfcalmonthYear, WebElement leftArrow, WebElement rghtArrow, List<WebElement> daysList){
		List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
			
		boolean dateNotFound = true;
		
				while(dateNotFound){
					
					String cal_monthYear = driver.findElement(By.xpath(xpathOfcalmonthYear)).getText();
					String[] arr_calMonthyear = cal_monthYear.split(",");
					String cal_month = arr_calMonthyear[0].trim();
					String cal_Year = arr_calMonthyear[1].trim();
					
					if((months.indexOf(cal_month))+1 == month && Integer.parseInt(cal_Year) == year){
						selectDateFromCalendar(date, daysList);
						dateNotFound = false;
					}
					else if( ((months.indexOf(cal_month))+1 > month &&  Integer.parseInt(cal_Year) == year) || (Integer.parseInt(cal_Year) > year))
						leftArrow.click();
					
					else if( ((months.indexOf(cal_month))+1 < month &&  Integer.parseInt(cal_Year) == year) || (Integer.parseInt(cal_Year) < year))
						rghtArrow.click();
				}
	}//end of method
	
	public static void selectDateFromCalendar(int date, List<WebElement> daysList){
		
		for(WebElement we:daysList){
			
			if((we.getAttribute("class").equals("day") || we.getAttribute("class").equals("day weekend")) && we.getText().equals(String.valueOf(date))){
				we.click();
				break;
			}
		}
		
	}//end of method
	
	//****************************************************Utility 2***********************to handle multiple windows*********************************
	public static void handleMultipleWindows(WebDriver driver, int noOfWindows){
		
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iter = handles.iterator();
		int n=1;
		while(iter.hasNext() && noOfWindows>=n){
			String handle = iter.next();
			driver.switchTo().window(handle);
			n++;
		}
				
	}
	
	//****************************************************Utility 3***********************to generate TimeStamp*********************************
	
	public static String generateTimeStamp(){
		Calendar cal = Calendar.getInstance();
		List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		String month = months.get(cal.get(Calendar.MONTH));
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
				
		SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm:ss a");
		String formattedDate = formatDate.format(new Date()).toString();
		
		String timeStamp = month+" "+date +", " +year +", " +formattedDate;
		return timeStamp;
	}
	
	public static String getIST_TimeStamp(){
		Calendar cal = Calendar.getInstance();
		String month = String.valueOf(cal.get((Calendar.MONTH)+1));
		String year = String.valueOf(cal.get(Calendar.YEAR));
		String date = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		String hour = String.valueOf(cal.get(Calendar.HOUR));
		String min = String.valueOf(cal.get(Calendar.MINUTE));
		String sec = String.valueOf(cal.get(Calendar.SECOND));
		
		String timeStamp = date+month+year+"_"+hour+min+sec;
		return timeStamp;
	}
	
	//****************************************************Utility 4***********************to select a value from drop down*********************************
	
	public static void dropDownHandle(WebElement selectElement, String selectValue){
		Select sel = new Select(selectElement);
		sel.selectByVisibleText(selectValue);
	}
	
	public static String getClassName(String sTestCaseName){
		int start_posi = sTestCaseName.indexOf(".")+1;
		int end_posi = sTestCaseName.indexOf("@");
		return sTestCaseName.substring(start_posi, end_posi); 
		
	}
	
	//****************************************************Utility 5***********************to capture a screenshot*********************************
	public static void captureScreenshot(WebDriver driver) throws IOException{
		TakesScreenshot sr = (TakesScreenshot) driver;
		File source = sr.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\screenshots"+TestUtils.generateTimeStamp() +".png");
		FileUtils.copyFile(source, dest);
			}
	
	
}//end of class
