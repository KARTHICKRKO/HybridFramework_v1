package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups="DataDriven") // getting dataproviders from different
																				// class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException {

		// Homepage
		logger.info("******** Started TC_003_LoginDDT *************");
		try {
			logger.info("*******Launching the Homepage*******");
			Homepage hp = new Homepage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// LoginPage
			logger.info("*******Clicking on Loginpage*******");
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			MyAccountPage ap = new MyAccountPage(driver);
			boolean targetpage = ap.IsMyAccountPageExists();

			// Data is valid -- login success--test pass--logout
			// Data is valid -- login failed-- test failed

			// Data is invalid-- login success--test fail-- logout
			// Data is invalid-- login failed -- test pass
			if (exp.equalsIgnoreCase("Valid")) {
				if (targetpage == true) {
					ap.clickLgout();
					Assert.assertTrue(true);

				} else {
					Assert.assertTrue(false);
				}
				if (exp.equalsIgnoreCase("Invalid")) {
					if (targetpage == true) {
						ap.clickLgout();
						Assert.assertTrue(false);
					} else {
						Assert.assertTrue(true);
					}
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("******** Finished TC_003_LoginDDT *************");
	}
}
