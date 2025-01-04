package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(css = ".cart")
	List<WebElement> cartItems;
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;
	
	public boolean verifyProductIsDisplaying(String productName)
	{		
		boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.findElement(By.cssSelector("h3")).getText().equals(productName));
		return match;		
	}
	 public CheckoutPage goToCheckout()
	 {
		 checkOutBtn.click();
		 CheckoutPage checkoutPage = new CheckoutPage(driver);
		 return checkoutPage;
	 }
	
	
	

}
