package notificationHandling;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusNotificationPopUpTest {
	@Test
	public void redBusNotificationMethodTest() throws Throwable {
		ChromeOptions options = new ChromeOptions();
		
		HashMap<String, Integer> contentSettings = new HashMap<String, Integer>();
		contentSettings.put("notifications",1);
		
		HashMap<String, Object> profile = new HashMap<String, Object>();
		profile.put("managed_default_content_settings", contentSettings);
		
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile", profile);
		
		options.setExperimentalOption("prefs", prefs);
		
		//Setup the Chromedriver executable path
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		
		//Maximize the window 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.redbus.com/");
		
		//Create the New Window and handle the Alert Popups
		driver.switchTo().newWindow(WindowType.TAB);
		
		//Switch to the new window and trigger the URL
		driver.get("https://nxtgenaiacademy.com/alertandpopup/");
		driver.findElement(By.cssSelector("button[name='alertbox']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		
		//Launch new Window trigger the URL and handle the Confirmation alert Pop Up
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://nxtgenaiacademy.com/alertandpopup/");
		driver.findElement(By.cssSelector("button[name='confirmalertbox']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		
		//Launch new tab trigger the URl and handle the prompt Popup
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://nxtgenaiacademy.com/alertandpopup/");
		driver.findElement(By.cssSelector("button[name='promptalertbox1234']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().sendKeys("No");
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		driver.quit();
	}

}
