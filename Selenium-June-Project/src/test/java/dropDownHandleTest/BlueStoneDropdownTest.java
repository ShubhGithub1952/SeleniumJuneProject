package dropDownHandleTest;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BlueStoneDropdownTest {
	WebDriver driver;
	
	//Launch the Chrome ,Maximize the Window and Trigger the URL
	@BeforeMethod
	public void launchBrowserMethod() {
		
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get("https://www.bluestone.com/");
				driver.findElement(By.id("denyBtn")).click();
	}
	
	
	public static Actions elementActionsMethodTest(WebDriver driver) {
		Actions actions = new Actions(driver);
		return actions;
	}
	
	//Closeup the Browser and stop the server
	@AfterMethod
	public void quiteBrowserMethod() {
		driver.quit();
	}
	
	//Identify element and Capture all Options of watchJewellery dropdown
	@Test(priority = 1)
	public void bluestoneWatchJewelleryOptionMethodTest() throws Throwable {
		
		WebElement watchJewelleryHeaderLinkText = driver.findElement(By.cssSelector("a[title='Watch Jewellery']"));
		
		//Navigate to element using action class
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(watchJewelleryHeaderLinkText).perform();
		
		List<WebElement> watchJewelleyOptions = driver.findElements(By.xpath("//div[@class='wh-submenu single-column-submenu width-auto']/ul/li/a[@href]"));
		
		TreeSet<String> watchJewelleryList = new TreeSet<String>();
		
		//Add all options in List And Print on Console
		for ( WebElement ele : watchJewelleyOptions) {
			watchJewelleryList.add(ele.getText());
		}
		System.out.println("WatchJewellery Options are :- "+watchJewelleryList);
	}
	
	//Capture all Rings Options webelemnts ans Store into a List and Print
	@Test(priority = 2)
	public void bluestoneRingsOptionMethodTest() throws Throwable {
		
		WebElement ringHeaderOptions = driver.findElement(By.xpath("//li[@class='menuparent repb']/a[@title='Rings']"));
		WebElement popularRingTypesEle = driver.findElement(By.xpath("//div[text()='Popular Ring Types ']"));

		//Navigate to element using action class
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(ringHeaderOptions).perform();
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(popularRingTypesEle).perform();
		
		List<WebElement> ringsElements1 = driver.findElements(By.xpath("//li[@class='menu-col-1 active']//ul/li/span[@class='prcs-d']"));
		
		List<WebElement> ringsElements2 = driver.findElements(By.xpath("//li[@class='menu-col-1 active']//ul/li/a"));
		
		//add elements into the treeset
		TreeSet<String> ringsOptionsList = new TreeSet<String>();
		
		for ( WebElement ele1 : ringsElements1) {
			
			ringsOptionsList.add(ele1.getText());
		}
		for ( WebElement ele2 : ringsElements2) {
			
			ringsOptionsList.add(ele2.getText());
		}
		System.out.println("Rings Options are :- "+ringsOptionsList);
	}
	
	//Create Method to capture all options of Earrings and Print it.
	@Test(priority = 3)
	public void bluestoneEarringsOptionsMethodTest() throws InterruptedException {
		
		//Navigate to element using action class
		WebElement earringsHeaderLinkText = driver.findElement(By.xpath("//li[@class='menuparent repb']/a[@title='Earrings']"));
			WebElement poupularEarringsEle = driver.findElement(By.xpath("//div[text()='Popular Earring Types']"));
			
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(earringsHeaderLinkText).perform();
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(poupularEarringsEle).perform();
		
		List<WebElement> earringsWebElements1 = driver.findElements(By.xpath("//div[text()='Popular Earring Types']/ancestor::div[@class='col-inner']//span[@class='prcs-d']"));
		List<WebElement> earringsWebElements2 = driver.findElements(By.xpath("//div[text()='Popular Earring Types']/ancestor::div[@class='col-inner']//a"));
		
		//Create the List add all webelements as Strings
		TreeSet<String> earringsOptions = new TreeSet<String>();
	
		//create foreach loop and add elements into the List collections
		for (WebElement ele3 : earringsWebElements1) {
			
			earringsOptions.add(ele3.getText());
		}
		
		for (WebElement ele4 : earringsWebElements2) {
			
			earringsOptions.add(ele4.getText());
		}
		System.out.println("Earrings Options are :- "+earringsOptions);
	}
	
	//Click on Earrings Header module and Capture all Options to filter the Earrings
	@Test(priority = 4)
	public void earringsFilterOptionsMethodTest() {
		//identify the element EarRings and click on it
		driver.findElement(By.xpath("//li[@class='menuparent repb']/a[@title='Earrings']")).click();
		
		//identify each Dropdown element and move to each element and Capture all Options
		WebElement priceOptionElements = driver.findElement(By.xpath("//span[text()='Price']"));
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(priceOptionElements).perform();
		
		//Capture all Webelements Options of the Price tag 
		List<WebElement> priceWebelements = driver.findElements(By.xpath("//form[@id='price']//span[@data-tagcategory='Price']"));
		
		//Store into the List and Print
		LinkedList<String> priceListOptions = new LinkedList<String>();
		
		for (WebElement ele5 : priceWebelements) {
			priceListOptions.add(ele5.getText());
		}
		System.out.println("Price Options are :- "+priceListOptions); 
		
		//Move to Metal Filter and Capture all Options and Print 
		WebElement metalOptionElements = driver.findElement(By.xpath("//span[text()='Metal']"));
		BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(metalOptionElements).perform();
		
		//Capture all Webelements Options of the Metal tag 
		List<WebElement> metalWebelements = driver.findElements(By.xpath("//form[@id='metal']//span[@data-tagcategory='Metal']"));
		
		//Store into the List and Print
		LinkedList<String> metalListOptions = new LinkedList<String>();
		
		for (WebElement ele6 : metalWebelements) {
			metalListOptions.add(ele6.getText());
		}
		System.out.println("Metal Options are :- "+metalListOptions); 
		
		//Move to Gold Purity Filter and Capture all Options and Print 
				WebElement goldPurityOptionElements = driver.findElement(By.xpath("//span[text()='Gold Purity']"));
				BlueStoneDropdownTest.elementActionsMethodTest(driver).moveToElement(goldPurityOptionElements).perform();
				
				//Capture all Webelements Options of the Gold Purity tag 
				List<WebElement> goldPurityWebelements = driver.findElements(By.xpath("//form[@id='gold purity']//span[@data-tagcategory='Gold Purity']"));
				
				//Store into the List and Print
				LinkedList<String> goldPurityListOptions = new LinkedList<String>();
				
				for (WebElement ele7 : goldPurityWebelements) {
					goldPurityListOptions.add(ele7.getText());
				}
				System.out.println("GoldPurity Options are :- "+goldPurityListOptions); 
	}
	
}
