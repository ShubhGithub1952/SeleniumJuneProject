package dropDownHandleTest;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TajDropDownAllOptionsTest {
	WebDriver driver;
	@Test
	public void takeLocationNames() throws Throwable {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.get("https://www.tajhotels.com/");
		 JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		 
		 //write locator to capture all Header Names
		List<WebElement> headerElement = driver.findElements(By.xpath("//a[@class='nav-link text-caps']"));
		
		//Create the list Add into it and Print
		ArrayList<String> headerList = new ArrayList<String>();
		
		//adding element into list using foreach loop
		for ( WebElement ele : headerElement) {
			headerList.add(ele.getText());
		}

		//Verify element and Print
		System.out.println(headerList);
		
		//Click  on Membership Program Options and Print all option
		driver.findElement(By.xpath("//div[contains(text(),'Membership Programs')]")).click();
		
		List<WebElement> dropdownElement = driver.findElements(By.xpath("//ul[@class='cm-header-dd-list-con NonloyalCustomerData']//a"));
		
		//Print the Options
		for (WebElement options : dropdownElement) {
			//System.out.println(options.getText());
			if (options.getText().equalsIgnoreCase("Epicure")) {
				options.click();
				break;
			}
			
		}
		Thread.sleep(3000);
		driver.quit();
	}
	
}