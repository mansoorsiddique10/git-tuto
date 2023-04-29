package sel_1112577;

import static org.testng.Assert.assertEquals;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Module2_capstone {
	WebDriver driver;

	@Test /* (dataProvider = "dataProvider") */
	public void login() throws InterruptedException, IOException {

		String FilePath ="C:\\Users\\mansoor.hussain\\Desktop\\EsseneceBank.xlsx";
		FileInputStream inputFile = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
		XSSFSheet sheet = workbook.getSheet("Credentials");
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 1; i <= rowCount; i++) {
			String userName = sheet.getRow(i).getCell(0).getStringCellValue();
			String password = sheet.getRow(i).getCell(1).getStringCellValue();

			// Thread.sleep(2000);
			driver.findElement(By.name("ctl00$body$txtUserID")).clear();
			driver.findElement(By.id("body_txtUserID")).sendKeys(userName);
			// Thread.sleep(2000);
			driver.findElement(By.name("ctl00$body$txtPassword")).clear();

			driver.findElement(By.id("body_txtPassword")).sendKeys(password);

			driver.findElement(By.xpath("//*[@id=\"body_btnLogin\"]")).click();

			// Thread.sleep(2000);

			String message = driver.findElement(By.xpath("//*[@id=\"divWelcome\"]")).getText();
			System.out.println(message);
			String expectedmes = "Welcome donhere";

			if (expectedmes.equals(message)) {
				System.out.println("Login sucessfull  " + message);

			} else {
				System.out.println("Login failed");

				String messg = driver.findElement(By.xpath("//*[@id=\"body_lblStatus\"]")).getText();

				System.out.println(messg);

			}

		}

	}

	
	@Test(dependsOnMethods = "login")

	public void viewtransaction() throws IOException, InterruptedException {

		driver.findElement(By.xpath("//*[@id=\"GeneralTabMenu_Accounts_li_Cust\"]/span")).click();

		driver.findElement(By.xpath("//*[@id=\"AccountCustomer_ul\"]/li[4]/a")).click();
		for (int i = 1; i <= 3; i++) {

			WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_ddlAccountNo\"]"));

			Select select1 = new Select(dropdown);

			select1.selectByIndex(i);

			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_rblTransactions\"]/tbody/tr/td[1]")).click();
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_btnViewTrancastions\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_btnReset\"]")).click();
		}


		for (int j = 0; j <= 3; j++) {
			WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_ddlAccountNo\"]"));
			Select sel = new Select(dropdown);
			sel.selectByIndex(j);
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_rblTransactions\"]/tbody/tr/td[2]/label"))
					.click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_txtFromDate\"]")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_txtFromDate\"]")).sendKeys("06/21/2018");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_txtToDate\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_txtToDate\"]")).sendKeys("06/21/2021");
			 Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_btnViewTrancastions\"]")).click();
			 Thread.sleep(5000);

			driver.findElement(By.xpath("//*[@id=\"body_cph_MyAccount_btnReset\"]")).click();
			 Thread.sleep(5000);
		}
	}
		



	@Test(dependsOnMethods = "viewtransaction")
	public void services() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"GeneralTabMenu_Service_li_Cust\"]/span")).click();
		
		Thread.sleep(2000);
		
		String expectedmessage = "ENJOY OUR SERVICES";
		String actualmessage = driver.findElement(By.xpath("//*[@id=\"body_cph_Services_header_divHeader\"]"))
				.getText();
		Assert.assertEquals(expectedmessage, actualmessage);
		System.out.println(expectedmessage);

	}

	/*
	 * @DataProvider public Object dataProvider() {
	 * 
	 * Object[][] data= new Object[3][2]; data[0][0]= "IamDon";
	 * data[0][1]="IamMansoor"; data[1][0]= "Donhere"; data[1][1]= "don@321";
	 * data[2][0]= "donhere"; data[2][1]= "don@123"; return data;
	 * 
	 * }
	 */

	@BeforeSuite
	@Parameters({ "browserName", "url" })
	public void setUp(@Optional("FireFox") String browserName, @Optional String url) throws InterruptedException {
		switch (browserName) {
		case "ChromeDriver":
			System.setProperty("webdriver.chrome.driver", "C:\\Chromedriver104 (1)\\Chromedriver104\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println(driver.getTitle());

			System.out.println("Opened the IEBI ");
			break;
		}

		driver.findElement(By.xpath("//*[@id=\"body_lbtSignUp\"]")).click();
	String	expectedMess= "REGISTER WITH IN ESSENCE BANK";
	 String	actualMess= driver.findElement(By.xpath("//*[@id=\"body_divHeader\"]")).getText();
		
		Assert.assertEquals(expectedMess, actualMess);
		System.out.println(actualMess);
		
	
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@id=\"body_lbtForgotPassword\"]")).click();
		 String expmsg="FORGOT PASSWORD/UNLOCK ACCOUNT";
		 String actmsg= driver.findElement(By.xpath("//*[@id=\"body_header_divHeader\"]")).getText();
		 Assert.assertEquals(actmsg, expmsg);
		 System.out.println(actmsg);
	
		driver.navigate().back();

	}

	
	  @AfterSuite public void logout()  throws IOException{


	
		
	  driver.findElement(By.xpath("//*[@id=\"lbLoginOut\"]")).click();
	  
	  
	  }
	 

}
