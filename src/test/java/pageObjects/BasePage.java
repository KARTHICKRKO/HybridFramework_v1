package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


//It's the parent of all the PageObject Classes
//Re-usable component of all the PageObject Classes
public class BasePage {
	
	WebDriver driver;
	
	BasePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

}
