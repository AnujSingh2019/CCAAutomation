package Testcase;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest2;
import utilities.TestUtil;

public class cancelEnrollmentBoomiTest extends BaseTest2 {
	
	@Test(dataProvider = "getData")
	public void NSFBoomi (String username, String password) throws AddressException, IOException, MessagingException, InterruptedException, SQLException
	{
		
		//Thread.sleep(28000);
		/*type("BoomiUserName_XPATH",username);
		type("BoomiPassword_XPATH",password);
		
		click("BoomiLogin_XPATH");*/
		Thread.sleep(3000);
		//select("manage_XPATH","Process Reporting");
		
		//Thread.sleep(4000);
		click("executeProcess_XPATH");
		//action("menu_XPATH");
		//action("menuBill_XPATH");
		Thread.sleep(3000);
		select("atom_XPATH","CCA3_SIT1_A");
		Thread.sleep(3000);
		click("processSearch_XPATH");
		Thread.sleep(3000);
		clear("keySearch_XPATH");
		Thread.sleep(3000);
		type("keySearch_XPATH","cancel Enrollment");
		//click("keySelect_XPATH");
	    click("keySelectCancelEnroll_XPATH");
		Thread.sleep(4000);
		click("executeFinal_CSS");
		Thread.sleep(8000);
		
		
	}

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("NSFBoomi");

	}

}
