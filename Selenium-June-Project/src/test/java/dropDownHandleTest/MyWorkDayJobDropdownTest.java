package dropDownHandleTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyWorkDayJobDropdownTest {
	WebDriver driver;
	@Test
	public void MyWorkdayJobDropdownMethodTest() throws Throwable {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://alliancedata.wd5.myworkdayjobs.com/breadfinancial_India/job/Bangalore-India/QA-Manual-Tester--3_R1008271?source=Linkedin");
		driver.findElement(By.xpath("//a[text()='Apply']")).click();
		driver.findElement(By.xpath("//a[text()='Apply Manually']")).click();
		JavascriptExecutor jscriptVeriable = (JavascriptExecutor)driver;
		WebElement signinButton = driver.findElement(By.xpath("//div[@class='css-n9fnp8']"));
		//jscriptVeriable.executeScript("window.scrollBy(0,2000)");
		jscriptVeriable.executeScript("arguments[0].scrollIntoView();", signinButton);
		driver.findElement(By.xpath("//input[@data-automation-id=\"email\"]")).sendKeys("pitleshubham14@gmail.com");
		driver.findElement(By.xpath("//input[@data-automation-id=\"password\"]")).sendKeys("Arpita@2019");
		signinButton.click();
		driver.findElement(By.xpath("//button[@aria-label=\"How Did You Hear About Us? select one required\"]")).click();
		WebElement linkedInOption = driver.findElement(By.xpath("//div[text()='Indeed.com']"));
		MyWorkDayJobDropdownTest.clickOptionMethodTest(driver, linkedInOption);
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	public static void clickOptionMethodTest(WebDriver driver,WebElement ele) {
		JavascriptExecutor jscriptVeriable = (JavascriptExecutor)driver;
		jscriptVeriable.executeScript("arguments[0].click();", ele);
	}
}
