package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount page heading
	WebElement msgheading;

	@FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
	WebElement lnklogout;

	public boolean IsMyAccountPageExists() {
		try {
			return (msgheading.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	public void clickLgout() {
		lnklogout.click();
	}
}
