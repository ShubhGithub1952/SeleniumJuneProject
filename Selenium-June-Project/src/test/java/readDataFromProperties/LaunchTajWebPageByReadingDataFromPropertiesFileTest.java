package readDataFromProperties;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchTajWebPageByReadingDataFromPropertiesFileTest {
	@Test
	public void readDataFromPropertiesFileMethodTest() throws Throwable {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Read Data from properties file 
		FileInputStream fileInput = new FileInputStream(".\\src\\test\\resources\\tajCredentials.properties");
		
		//Create object of Properties file 
		Properties properties = new Properties();
		properties.load(fileInput);
		String URL = properties.getProperty("tajurl");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		System.out.println(URL+" "+username+" "+password);
		
		driver.get(URL);
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		driver.findElement(By.id("phoneInputLogin-phone")).sendKeys(username);
		driver.findElement(By.xpath("//span[contains(text(),'I agree to the ')]/parent::label[@class='checkbox-label-wrapper-label']//span[@class='checkmark']")).click();
		driver.findElement(By.id("confirmButton")).click();
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.id("passwordButton")).click();
		
		Thread.sleep(2000);
		String expectedTitle="Luxury Hotels & Resorts in India & the World | Taj Hotels";
		
		String actualTitle = driver.getTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("Successfully Read data from Properties File !");
		} else {
			System.out.println("Not Read data from Properties File !");
		}
		
		driver.findElement(By.xpath("//div[@class='navbar-nav']//span[text()='Hi, ']")).click();
		Actions actions = new Actions(driver);
		WebElement signoutButton = driver.findElement(By.xpath("//div[@class='navbar-nav']//div[@id='logout-btn']"));
		actions.moveToElement(signoutButton).click().perform();
		Thread.sleep(2000);
		driver.quit();
	}
}
