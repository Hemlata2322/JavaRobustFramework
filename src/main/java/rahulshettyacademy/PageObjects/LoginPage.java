package rahulshettyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement passwordInput;
	@FindBy(id = "login")
	WebElement loginBtn;
	//ng-tns-c4-39 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCataloguePage loginApplication(String email, String password )
	{
		userEmail.sendKeys(email);
		passwordInput.sendKeys(password);
		loginBtn.click();
		ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
		return productCataloguePage;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		String errorMsg = errorMessage.getText();
		return errorMsg;
	}

}
