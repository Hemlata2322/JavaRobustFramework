package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LoginPage;

public class TestBase {
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException
	{		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
		//WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
				if(browserName.contains("headless"))
				{
				options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				//if you see it's breaking due to dimensions in headless mode add this full screen command
				driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();	
			driver = new FirefoxDriver();
		}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
				return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToUrl();
		return loginPage;
	}
//	public String getScreenshot(String testCaseName) 
//	{
//		try
//		{
//		TakesScreenshot screenshot = (TakesScreenshot)driver;
//		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
//		File destFile = new File(System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png");
//		FileUtils.copyFile(srcFile,destFile);
//		return System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png";
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//	}
		
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}

}
