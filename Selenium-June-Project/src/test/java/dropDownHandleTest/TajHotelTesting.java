package dropDownHandleTest;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TajHotelTesting {
	public static void main(String[] args) throws Throwable {

//		ChromeOptions options = new ChromeOptions();
//		//		ChromeOptions options = new ChromeOptions();
//		HashMap<String, Integer> contentsettings = new HashMap<String, Integer>();
//		//		HashMap<String, Integer> contentsettings = new HashMap<String, Integer>();
//		HashMap<String, Object> profile = new HashMap<String, Object>();
//		//		HashMap<String, Object> profile = new HashMap<String, Object>();
//		HashMap<String, Object> prefs = new HashMap<String, Object>();
//		//		HashMap<String, Object> prefs = new HashMap<String,Object>();
//		contentsettings.put("notifications",2);
//		//		contentsettings.put("notifications",2);
//		profile.put("managed_default_content_settings", contentsettings);
//		//		profile.put("managed_default_content_settings", contentsettings);
//		prefs.put("profile", profile);
//		//		prefs.put("profile", profile);
//		options.setExperimentalOption("prefs", prefs);
//		//		options.setExperimentalOption("prefs", prefs);



		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//WebDriverWait expliciteWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.tajhotels.com/");
		WebElement popularDestinationTag = driver.findElement(By.xpath("//h1[text()='POPULAR DESTINATIONS']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView();", popularDestinationTag);
		List<WebElement> popularDestinationPlaceNameScroll1 = driver.findElements(By.xpath("//div[@class='popularDest-details-name']"));
		
		ArrayList<String> popularLocationListName = new ArrayList<String>();
		
		//LinkedHashSet<String> locationList = new LinkedHashSet<String>();
		
		for (WebElement ele1 : popularDestinationPlaceNameScroll1) {
			popularLocationListName.add(ele1.getText());
		}
		//expliciteWait.until(ExpectedConditions.urlContains("https://www.tajhotels.com/"));
		
		System.out.println(popularLocationListName);					
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://www.tajhotels.com/");
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
//		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight);");
//		Thread.sleep(3000);
//		jsExecutor.executeScript("window.scrollTo(0,0);");
		WebElement popularDestinationTagChildWindow = driver.findElement(By.xpath("//h1[text()='POPULAR DESTINATIONS']"));
		jsExecutor.executeScript("arguments[0].scrollIntoView();", popularDestinationTagChildWindow);
		List<WebElement> destinationLocationEle = driver.findElements(By.xpath("//div[@class='popularDest-details-name']"));
		ArrayList<String> childLocationList = new ArrayList<String>();
		for ( WebElement ele : destinationLocationEle) {
			childLocationList.add(ele.getText());
		}
		System.out.println(childLocationList);

		int childSize = childLocationList.size();
		int parentSize = popularLocationListName.size();
		int size=parentSize;
		int count=0;
		for (int i = 0; i < size; i++) {
			if (popularLocationListName.get(i).equals(childLocationList.get(i))) {
				count++;
			}
		}
		if (size==count) {
			System.out.println("Pass :- both webpage tab have same Location !");
		}else {
			System.out.println("Fail......");
		}
		driver.quit();
	}
}
