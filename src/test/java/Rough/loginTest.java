package Rough;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest1;
import utilities.TestUtil;

public class loginTest extends BaseTest1{
	
	@Test(dataProvider = "getData")
	public void login(String username, String password) throws AddressException, IOException, MessagingException, InterruptedException
	{
		click("logBtn_XPATH");
		type("Usernametxtbox_XPATH", username);
		click("Contbtn_XPATH");
		type("Pswrdtxtbox_XPATH", password);
		click("loginBtn_XPATH");
		Thread.sleep(6000);
	}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("loginTest");

	}
}
