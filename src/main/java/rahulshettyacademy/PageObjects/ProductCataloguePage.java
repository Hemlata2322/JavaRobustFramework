package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCataloguePage extends AbstractComponent {
	
	WebDriver driver;
	public ProductCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}		
		
	@FindBy(css= ".card-body")
	List<WebElement> productList;
	
	
	
	By productsBy = By.cssSelector(".card-body");
	By addToCart = By.cssSelector("button:last-of-type");
	By toastMessage = By.cssSelector(".toast-message");
	By spinner = By.tagName("ngx-spinner");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return productList;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement productByName = getProductList().stream().filter(product -> product.findElement(By.cssSelector("h5 b")).
				getText().equals(productName)).findFirst().orElse(null);
		return productByName;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement productByName = getProductByName(productName);
				productByName.findElement(addToCart).click();
				waitForElementToAppear(toastMessage);
				waitForElementToDisappear(spinner);		
	}	
	
}
