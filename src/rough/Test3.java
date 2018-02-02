package rough;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test3 {
	
	@BeforeSuite
	public void meth1(){
		System.out.println("1");
		
	}
	@AfterSuite
	public void meth2(){
		System.out.println("12");
	}
	@BeforeTest
public void meth3(){
		System.out.println("2");
	}
	@AfterTest
public void meth4(){
		System.out.println("11");
	}
	
	@BeforeClass
	public void meth9(){
			System.out.println("3");
		}
		@AfterClass
	public void meth10(){
			System.out.println("10");
		}
	@BeforeMethod
public void meth5(){
		System.out.println("4/7");
	}	
	@AfterMethod
public void meth6(){
		System.out.println("6/9");
	}
	@Test(priority=1)
public void Test1(){
		System.out.println("5");
	}
	@Test(priority=2)
public void Test2(){
		System.out.println("8");
	}

}
