package commonFunctionsLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary {
	
	public static WebDriver driver;	
	//method for launching browser	
	public WebDriver startBrowser(String key) throws Throwable
	{
		if(PropertyFileUtil.getValueForKey(key).equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(key);
		}
		else if(PropertyFileUtil.getValueForKey(key).equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(key);
		} 
		else
		{
			System.out.println("Browser value not Matching.");
		}
		return driver;
	}
	
	//method for launch url
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
	}
	
	//method for textboxes
		public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
		{
			if(locatortype.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorvalue)).clear();
				driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
			}
			else if(locatortype.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(locatorvalue)).clear();
				driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
			}
			else if(locatortype.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorvalue)).clear();
				driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
			}
			else 
			{
				System.out.println("Unable to execut type Action method");
			}
		}
		
		//method for wait for element
		public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,String waittime)
		{
			WebDriverWait myWait  = new WebDriverWait(driver, Integer.parseInt(waittime));
			if(locatortype.equalsIgnoreCase("name"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
			}
			else if (locatortype.equalsIgnoreCase("id"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
			}
			else if(locatortype.equalsIgnoreCase("xpath"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
			}
			else
			{
				System.out.println("Unable to execute waitforelement method");
			}
		}

		//method for clickAcion
		public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
		{
			if(locatortype.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorvalue)).click();
			}
			else if(locatortype.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
			}
			else if(locatortype.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorvalue)).click();
			}
			else
			{
				System.out.println("Unable to execute clickaction method");
			}
		}
		//method for validate title
		public static void validateTitle(WebDriver driver,String expectedtitle)
		{
			String actualtitle = driver.getTitle();
			try
			{
				Assert.assertEquals(expectedtitle, actualtitle,"Title is Not matching");
			}catch(Throwable t)
			{
				System.out.println(t.getMessage());
			}
		}
		//method for close browser
		public static void closeBrowser(WebDriver driver)
		{
			driver.close();
		}
}
