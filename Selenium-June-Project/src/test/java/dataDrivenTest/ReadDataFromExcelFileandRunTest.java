package dataDrivenTest;

import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadDataFromExcelFileandRunTest {
	
	//create the method to fetch data from DataProvider Method
	@Test(dataProvider ="dataDrivenMethod" )
	public void readDataFromExcelMethodTest(String FirstName, String LastName, String emailid, String MobileNumber, String CompanyName) throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://only-testing-blog.blogspot.com/");
		WebElement companyFormElement = driver.findElement(By.xpath("//h3[@itemprop=\"name\"]//a[@href='https://only-testing-blog.blogspot.com/2014/05/form.html']"));
		JavascriptExecutor jScript = (JavascriptExecutor)driver;
		jScript.executeScript("arguments[0].scrollIntoView()", companyFormElement);
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@name='EmailID']")).sendKeys(emailid);
		driver.findElement(By.xpath("//input[@name='MobNo']")).sendKeys(MobileNumber);
		driver.findElement(By.xpath("//input[@name='Company']")).sendKeys(CompanyName);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='post-body-8228718889842861683']//input[@type='submit']")).submit();
		driver.switchTo().alert().accept();
		
		//Verify with the URl that contains the Paramters has been Passed to this method
		String expectedURL=CompanyName;
		String actualURL = driver.getCurrentUrl();
		wait.until(ExpectedConditions.urlContains(actualURL));
		Thread.sleep(3000);
		if (actualURL.contains(expectedURL)) {
			System.out.println("Test Case Pass :- Data has been read from Excel");
		}else {
			System.out.println("Test Case Fail :- Data not been read from Excel");
		}
		
		//Quite the Browser
		driver.quit();
	}
	
	//Create the Excel Utility method to fetch data from excle
	public static String getDataFromExcleMethodTest(int rowNum,int cellNum) throws Throwable {
		
		Workbook workbook = WorkbookFactory.create(new FileInputStream(".\\src\\test\\resources\\DDT data.xlsx"));
		Sheet sheetname = workbook.getSheet("student");
		Row row = sheetname.getRow(rowNum);
		Cell cellValue = row.getCell(cellNum);
		DataFormatter dataFormat = new DataFormatter();
		String value = dataFormat.formatCellValue(cellValue);
		return value;
	}
	
	//Create Data Provider Method and pass the Excel co-ordinates
	@DataProvider
	public Object[][] dataDrivenMethod() throws Throwable{
		Object[][] dataObject = new Object[1][5];
		dataObject[0][0]=getDataFromExcleMethodTest(1, 1);
		dataObject[0][1]=getDataFromExcleMethodTest(1, 2);
		dataObject[0][2]=getDataFromExcleMethodTest(1, 3);
		dataObject[0][3]=getDataFromExcleMethodTest(1, 4);
		dataObject[0][4]=getDataFromExcleMethodTest(1, 5);
		return dataObject;
	}
}
