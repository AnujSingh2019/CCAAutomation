package Testcase;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest1;
import utilities.TestUtil;

public class generateInvoiceTest extends BaseTest1{
	
	@Test(dataProvider = "getData")
	public void login(String subsID, String billDate,String postMonth) throws AddressException, IOException, MessagingException, InterruptedException
	{
		
		/*type("username_NAME",username);
		type("password_NAME",password);
		Thread.sleep(3000);
		click("login_XPATH");
		click("click_CSS");*/
		Thread.sleep(3000);
		action("menu_XPATH");
		action("menuBill_XPATH");
		click("subscriber_XPATH");
		Thread.sleep(3000);
		click("newJob_XPATH");
		click("subID_XPATH");
		type("subs_NAME",subsID);
		type("billThru_NAME",billDate); 
		type("postMonth_NAME",postMonth);
		Thread.sleep(3000);
		select("run_NAME","Immediate");
		click("create_XPATH");
		click("close_XPATH");
		Thread.sleep(6000);
		click("refresh_XPATH");
		Thread.sleep(6000);
		click("invPost_XPATH");
		click("post_XPATH");
		System.out.println("Invoice generated successfully for Subscriber ID -"+subsID );
		System.out.println("Test case 8: DBUpdateTest is PASS");
		//click("logout_XPATH");
		//click("loginback_XPATH");
		Thread.sleep(2000);
		click("closeBtn2_XPATH");
		
	}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("Invoice");

	}
}
