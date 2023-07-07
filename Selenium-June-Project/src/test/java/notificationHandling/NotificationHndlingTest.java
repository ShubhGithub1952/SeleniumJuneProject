package notificationHandling;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NotificationHndlingTest {
	@Test
	public void NotificationMethodTest() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Integer> contentSettings = new HashMap<String, Integer>();
		contentSettings.put("media_stream", 2);
		
		HashMap<String, Object> profile = new HashMap<String,Object>();
		profile.put("managed_default_content_settings", contentSettings);
		
		HashMap<String, Object> prefs = new HashMap<String,Object>();
		prefs.put("profile", profile);
		
		options.setExperimentalOption("prefs", prefs);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://webcamtests.com/");

	}
}
