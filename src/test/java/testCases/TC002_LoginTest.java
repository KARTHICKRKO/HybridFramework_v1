package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"sanity","Master"})
	public void verifyLogin() {
		
		logger.info("*******Started TC002_LoginTest*******");
		try {
		//Homepage
		logger.info("*******Launching the Homepage*******");
		Homepage hp= new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		logger.info("*******Clicking on Loginpage*******");
		LoginPage lp= new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountpage
		logger.info("*******Verifying Account page*******");
		MyAccountPage ap= new MyAccountPage(driver);
		boolean targetpage=ap.IsMyAccountPageExists();
		
		Assert.assertEquals(targetpage, true,"Login is Failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		//LogoutPage
		MyAccountPage ap= new MyAccountPage(driver);
		ap.clickLgout();
		logger.info("*******Successfully Logged out********");
		logger.info("*******Finished TC002_LoginTest*******");
	}
}
