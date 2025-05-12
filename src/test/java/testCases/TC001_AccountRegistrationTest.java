package testCases;

import TestBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;


public class TC001_AccountRegistrationTest extends BaseClass {

@Test(groups = {"Regression","Master"})
public void verify_account_registration() {

        logger.info("Starting TC001_AccountRegistrationTest");
        // Test steps can be added here
        try {
                HomePage hp = new HomePage(driver);
                hp.clickMyAccount();
                logger.info("Clicked on MyAccount link");
                hp.clickRegister();
                logger.info("Clicked on Register Link");
                AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
                logger.info("Providing customer details");
                regpage.setFirstName(randomString().toUpperCase());
                regpage.setLastName(randomString().toUpperCase());
                regpage.setEmail(randomString() + "@gmail.com"); // random email
                regpage.setTelephone(randomNumber());

                String password = randomAlphaNumeric();

                regpage.setPassword(password);
                regpage.setConfirmPassword(password);

                regpage.setPrivacyPolicy();
                regpage.clickContinue();
                logger.info("Validating expected message");
                String confirmMessage = regpage.getConfirmationMsg();
                Assert.assertEquals(confirmMessage, "Your Account Has Been Created!");
        }
        catch(Exception e)
        {
                logger.error("Test Failed");
                logger.debug("Debug Logs");
                Assert.fail();
        }
        logger.info("Finished testcase");

   }
}
