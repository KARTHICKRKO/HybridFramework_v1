package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemailaddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnlogin;
	
	public void setEmail(String email) {
		txtemailaddress.sendKeys(email);
	}
	
	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnlogin.click();
	}

}
