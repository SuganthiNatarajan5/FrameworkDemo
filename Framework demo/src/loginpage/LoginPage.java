package loginpage;

import java.io.FileInputStream;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginPage {
	
	String[][] data=null;
	
	@DataProvider(name="LoginData")
	public String[][] loginDataProvider() throws BiffException, IOException {
		
		data=getExcelData();
		return data;
		
	}
	
	public String[][] getExcelData() throws BiffException, IOException	
	{
		FileInputStream excel= new FileInputStream("C:\\Users\\sugannat\\Desktop\\LoginWebsite\\login.xls");
		Workbook workbook=Workbook.getWorkbook(excel);
		Sheet sheet=workbook.getSheet(0);
		int rowCount=sheet.getRows();
		int columnCount=sheet.getColumns();
		
		String testData[][]=new String[rowCount-1] [columnCount];
		
		for(int i=1; i< rowCount; i++) {
			for(int j=0; j<columnCount; j++) {
				
				testData[i-1][j]= sheet.getCell(j,i).getContents();
			}
				
			
			
		}
		return testData;
	}
	@Test(dataProvider = "LoginData")
	public void BothCorrect1(String uname, String pword) {
		
		System.setProperty("Webdriver.Chrome.driver", "C:\\Users\\sugannat\\Desktop\\chromedriver.exe\\");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		WebElement correctUsername= driver.findElement(By.id("txtUsername"));
		correctUsername.sendKeys(uname);
		WebElement wrongPw= driver.findElement(By.id("txtPassword"));
		wrongPw.sendKeys(pword);
		WebElement enterLogin=driver.findElement(By.id("btnLogin"));
		enterLogin.click();
		driver.quit();
	}

}
