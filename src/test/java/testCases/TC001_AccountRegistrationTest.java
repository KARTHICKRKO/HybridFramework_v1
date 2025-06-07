package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import pageObjects.AccountRegistrationpage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
   
	@Test(groups = {"Regression","Master"})
	public void Verify_account_registration() {
		
		logger.info("*******Started TC001_AccountRegistrationTest ********" );
		
		try {
		Homepage hp= new Homepage(driver);
		logger.info("*******Clicked on My Account Link ********" );
		hp.clickMyAccount();
		logger.info("*******Clicked on My Register Link ********" );
		hp.clickRegister();	
		
		AccountRegistrationpage regpage= new AccountRegistrationpage(driver);
		logger.info("*******Providing customer info details ********" );
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com"); //randomly generating the email
		regpage.setTelephone(randomNumber());
		
		String Password=randomAlphanumeric();
		regpage.setPassword(Password);
		regpage.setConfirmPassword(Password);
		
		regpage.setPrivacypolicy();
		regpage.clickcontinue();
		String confmsg=regpage.getConfirmationmsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed...");
			logger.debug("Debugging error");
			Assert.assertTrue(false);
		}
		logger.info("*******Validating expected message ********" );
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	
	}catch(Exception e){
		
		Assert.fail();;
	}
		logger.info("*******Finished TC001_AccountRegistrationTest ********" );
	}
}
	
	

