package rahulshettyacademy.PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(css = "input[placeholder='Select Country']")
	private WebElement countryInputBox;
	@FindBy(css = ".ta-results.list-group.ng-star-inserted button:last-of-type")
	private WebElement searchedCountryOption;
	@FindBy(css = ".actions a")
	private WebElement placeOrderBtn;
	
	
	private By countryList = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	
	public void selectCountry(String country)
	{
		Actions act = new Actions(driver);
		act.sendKeys(countryInputBox, country).build().perform();
		waitForElementToAppear(countryList);
	
		searchedCountryOption.click();
	}
	public ConfirmationPage placeOrder()
	{
		placeOrderBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

	
}
