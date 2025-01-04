package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.PageObjects.CartPage;
import rahulshettyacademy.PageObjects.CheckoutPage;
import rahulshettyacademy.PageObjects.ConfirmationPage;
import rahulshettyacademy.PageObjects.ProductCataloguePage;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.TestComponents.TestBase;

public class ErrorValidationsTest extends TestBase{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException
	{		
		loginPage.loginApplication("kiakivi@gmail.com", "Kiakiv@23");	
		String errorMessage = loginPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");	
		
		System.out.println("Thanks for your helping hand");
		System.out.println("Making changes on HS branch");
		System.out.println("Good that you added me back");
		System.out.println("I want to see the magic of CICD using webhooks");
		
		
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
		String productName = "ADIDAS ORIGINAL";
				
		ProductCataloguePage productCataloguePage = loginPage.loginApplication("kiakivi@gmail.com", "Kiakivi@23");			
		
		List<WebElement> products = productCataloguePage.getProductList();
		productCataloguePage.addProductToCart(productName);
		CartPage cartPage = productCataloguePage.goToCartPage();
		
		boolean match = cartPage.verifyProductIsDisplaying("AADIDAS ORIGINAL");
		Assert.assertFalse(match);
			
	}
}
