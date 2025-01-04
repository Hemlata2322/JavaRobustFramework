package rahulshettyacademy.Tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.PageObjects.LoginPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LoginPage loginPage = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("kiakivi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kiakivi@23");
		driver.findElement(By.id("login")).click();
		
		String productName = "ADIDAS ORIGINAL";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".card-body"));
		/*for(WebElement product : products)
		{
			System.out.println(product.getText());
		}*/
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("h5 b")).
				getText().equals(productName)).findFirst().orElse(null);
				
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		System.out.println(driver.findElement(By.tagName("ngx-spinner")).getAttribute("class"));
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName("ngx-spinner")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
		String actualMsg = driver.findElement(By.cssSelector(".toast-message")).getText();
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, "Product Added To Cart");
		driver.findElement(By.cssSelector("button[routerlink = \"/dashboard/cart\"]")).click();
		
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart"));
		boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.findElement(By.cssSelector("h3")).getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		driver.findElement(By.cssSelector(".ta-results.list-group.ng-star-inserted button:last-of-type")).click();
		//or
		/*
		  driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("IND");
		String action = Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("IND");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(action);
		*/
		
		driver.findElement(By.cssSelector(".actions a")).click();
		
		String confimationMsg = driver.findElement(By.className("hero-primary")).getText();
		System.out.println(confimationMsg);
		Assert.assertTrue(confimationMsg.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

}
