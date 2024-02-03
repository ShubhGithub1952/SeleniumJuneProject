package takeScreenShot;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenshotOfGoibiboTest {
	@Test
	public void takeScreenshotMethodTest() throws Throwable {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.goibibo.com/");
		JavascriptExecutor jScript = (JavascriptExecutor)driver;
		WebElement pnrStatusElement = driver.findElement(By.xpath("//a[@href='/trains/check-pnr-status/']"));
		jScript.executeScript("arguments[0].scrollIntoView();", pnrStatusElement);
		jScript.executeScript("window.scrollBy(0,-100);");
		pnrStatusElement.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains("https://www.goibibo.com/"));
		jScript.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		LocalDateTime localTime = LocalDateTime.now();
		String localTimeDate = localTime.toString();
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshot"+localTimeDate+".png");
		FileUtils.copyFile(srcFile, destFile);
		driver.quit();
	}
}
