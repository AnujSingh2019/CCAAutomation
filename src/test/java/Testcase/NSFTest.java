package Testcase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest1;
import utilities.TestConfig;
import utilities.TestUtil;

public class NSFTest extends BaseTest1 {
	
	@Test(dataProvider = "getData")
	public void NSFAdjustment(String username, String password, String subsID,String amount,String glPostMonth) throws AddressException, IOException, MessagingException, InterruptedException, SQLException
	{
		
		
		/*type("username_NAME",username);
		type("password_NAME",password);
		Thread.sleep(3000);
		click("login_XPATH");
		click("click_CSS");
		Thread.sleep(3000);*/
		
		
		Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
		PreparedStatement pstmt;
		int numUpd;
		ResultSet result;
		
		pstmt = con.prepareStatement(
				  "select enrollment_status,AR_Account_ID from AR_ACCOUNT WHERE SEQ_BILL_ENTITY_ID in ( select SEQ_SUBS_ID FROM MEMBER_ELIG_HISTORY WHERE SUBSCRIBER_ID= ?)"); 
				                                  // Create a PreparedStatement object        1 
				pstmt.setString(1,subsID);  
		
				result = pstmt.executeQuery();
				result.next();
				
				String enrollmentStatus=result.getString("ENROLLMENT_STATUS");
				String arAccountID=result.getString("AR_ACCOUNT_ID");
		
				
				
		
		action("menuAR_XPATH");
		action("adj_XPATH");
		click("newadj_XPATH");
		Thread.sleep(3000);
		type("fundsAmt_XPATH",amount);
		type("arAcctID_XPATH",arAccountID);
		Thread.sleep(4000);
		select("reasonCode_XPATH","PB11");
		
		type("glPost_XPATH",glPostMonth);
		Thread.sleep(5000);
		select("cCode_XPATH","HIX [Health Connector]");
		select("glCode_XPATH","ADJ [Manual Adjustments]");
		click("adjSave_XPATH");
		click("adjPost_XPATH");
		click("adjClose_XPATH");
		click("adjCloseFinal_XPATH");
	}
		

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("NSF");

	}
}
