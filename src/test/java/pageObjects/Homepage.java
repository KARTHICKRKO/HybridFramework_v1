package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkmyaccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement lnkregister;
	
	@FindBy(linkText = "Login")
	WebElement login;

	public void clickMyAccount() {
		lnkmyaccount.click();
	}

	public void clickRegister() {
		lnkregister.click();
	}
	
	public void clickLogin() {
		login.click();
	}
}
