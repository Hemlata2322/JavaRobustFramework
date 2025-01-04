package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.JsonData.DataReader;
import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.LoginPage;
import rahulshettyacademy.PageObjects.OrdersPage;
import rahulshettyacademy.PageObjects.ProductCataloguePage;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.TestComponents.TestBase;

public class SubmitOrderTest extends TestBase{

	String productName = "IPHONE 13 PRO";
	@Test(dataProvider = "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException
	{		
		String countryName = "India";
		
		ProductCataloguePage productCataloguePage = loginPage.loginApplication(input.get("email"), input.get("password"));			
		
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(input.get("product"));
		CartPage cartPage = productCataloguePage.goToCartPage();
		
		boolean match = cartPage.verifyProductIsDisplaying(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		
		String confimationMsg = confirmationPage.getConfimationMessage();
		Assert.assertTrue(confimationMsg.equalsIgnoreCase("Thankyou for the order."));		
	}
	@Test(dependsOnMethods = {"submitOrder"}, retryAnalyzer=Retry.class)
	public void orderHistoryTest()
	{
		ProductCataloguePage productCataloguePage = loginPage.loginApplication("kiakivi@gmail.com", "Kiakivi@23");
		OrdersPage ordersPage = productCataloguePage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "kiakivi@gmail.com");
//		map.put("password", "Kiakivi@23");
//		map.put("product", "IPHONE 13 PRO");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "vjjkia@gmail.com");
//		map1.put("password", "Vjjkia@2525");
//		map1.put("product", "ADIDAS ORIGINAL");		
		
		 List<HashMap<String, String>> data = DataReader.getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\JsonData\\PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1)}};
	}
	
	/* Or
	public Object[][] getData()
	{
		return new Object[][] {{"kiakivi@gmail.com","Kiakivi@23","IPHONE 13 PRO"},{"vjjkia@gmail.com","Vjjkia@2525","ADIDAS ORIGINAL"}}; 
	}
	*/
}
