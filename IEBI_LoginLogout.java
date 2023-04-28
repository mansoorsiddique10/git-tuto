package com.test;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IEBI_LoginLogout {
	 
	WebDriver driver;
	
	Logger logger = Logger.getLogger(IEBI_LoginLogout.class);
	 
	  @BeforeTest	
	  @Parameters({ "browsername", "url" })
	  public void setup(@Optional String browsername, @Optional String url) {	
	  	switch (browsername) {
	  	case "ChromeDriver":
	  	
	  //BasicConfigurator.configure();
	//PropertyConfigurator.configure("log4j.properties");
	DOMConfigurator.configure("log4j.xml");
	  		
	  		
	  		//Code to initialize webdriver instance
	  		System.setProperty("webdriver.ie.driver","D:\\chromedriver.exe");
	  		
	  		//System.setProperty("webdriver.ie.driver","D:\\IEDriverServer.exe");
			//Open browser instance
	  		driver = new ChromeDriver();
	 		driver.get(url);
	 		logger.info("Opening IEBI application");
	 		break;
	  		
	  	}	 }
	  	
	  	@Test
		public void login() throws InterruptedException 
		{

			//code to enter credentials
	  		
	  
	  		driver.findElement(By.name("ctl00$body$txtUserID")).sendKeys("donhere");
	  		logger.info("Entering Username");
			
	  		driver.findElement(By.name("ctl00$body$txtPassword")).sendKeys("don@123");
			logger.info("Entering Password");
			
			driver.findElement(By.name("ctl00$body$btnLogin")).click();
			logger.info("Click on Login button");
			Thread.sleep(10000);
			
			driver.findElement(By.id("lbLoginOut")).click();
			logger.info("Logout from the application");
		
			logger.info("Logout from the application1");
			logger.info("Logout from the application2");
			logger.info("Logout from the application3");
			logger.info("Logout from the application4");
			
						
		}

			/*@Test(dependsOnMethods="login")
			public void dashboardTest()
			{

		       //logic to hover over My dashBoard link and choose My details link
				Actions action= new Actions(driver);
				WebElement mydashboardLink=driver.findElement(By.linkText("My Dashboard"));
				action.moveToElement(mydashboardLink).build().perform();
				new WebDriverWait(driver, 5000);
				driver.findElement(By.linkText("My Details")).click();

		      //verification of welcome message 
				if(driver.findElement(By.id("lblLoginUser")).getText().toString()
		         .contains(driver.findElement(By.id("cphMainContent_tcMyDetails_tpUpdatePersonalDetails_lblFirstname")).getText()))
				{
					System.out.println("welcome message is accurate");
					
				}
				else
				{
					System.out.println("error in welcome message");
				}	
				
			}
				@Test(dependsOnMethods="dashboardTest",enabled=false)
				public void logOut()
				{

					driver.findElement(By.linkText("Logout")).click();
				}*/
				
	
	  	
		@AfterTest
				public void tearDown() {
					//Close the browser
					driver.quit();
				}

}
			

	  	
	  

