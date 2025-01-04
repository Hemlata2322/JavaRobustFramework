package rahulshettyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	@FindBy(xpath = "//table[contains(@class,'ng-star-inserted')]//tr/td[2]")
	List<WebElement> orderedProductList;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public boolean verifyOrderDisplay(String productName)
	{
		boolean match = orderedProductList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
