package Testcase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest1;
import utilities.TestConfig;
import utilities.TestUtil;

@Test(dataProvider = "getData")
public class paymentPortalGuestTest extends BaseTest1 {
	
	public void paymentserach(String subsID) throws InterruptedException, SQLException
	{
	
		Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
		PreparedStatement pstmt;
		int numUpd;
		ResultSet result;
		
		pstmt = con.prepareStatement(
				  " select SUBSCRIBER_ID,Plan_code,Group_ID,SEQ_SUBS_ID,EXCHANGE_ASSIGNED_POLICY_NO,EXCHANGE_MEMBER_ID,FAM_TOTAL_PREMIUM  from Member_elig_history where subscriber_id= ?"); 
				  
				pstmt.setString(1,subsID);  
		
				result = pstmt.executeQuery();
				result.next();
				
				String seqsubsID=result.getString("SEQ_SUBS_ID");
				String planCode=result.getString("Plan_Code");
				String groupID=result.getString("Group_ID");
				String refID=result.getString("EXCHANGE_ASSIGNED_POLICY_NO");
				String memberID=result.getString("EXCHANGE_MEMBER_ID");
				String premium=result.getString("FAM_TOTAL_PREMIUM");
				
		pstmt = con.prepareStatement(
				" select FIRST_NAME,LAST_NAME,DATE_OF_BIRTH,LANGUAGE_CODE,SOCIAL_SEC_NO from MEMBER_MASTER where SEQ_MEMB_ID= ?"); 
						  
						pstmt.setString(1,seqsubsID);  
				
						result = pstmt.executeQuery();
						result.next();
						
						String firstName=result.getString("FIRST_NAME");
						String lastName=result.getString("LAST_NAME");
						String doB=result.getString("DATE_OF_BIRTH");
						String langCode=result.getString("LANGUAGE_CODE");
						String ssn=result.getString("SOCIAL_SEC_NO");
						
		pstmt = con.prepareStatement(
				" select EMAIL,Effective_Date from MEMBER_ADDRESS where Address_Type='R1' and SEQ_MEMB_ID= ?"); 
						  
						pstmt.setString(1,seqsubsID);  
				
						result = pstmt.executeQuery();
						result.next();
						
						String email=result.getString("EMAIL");
						String effDate=result.getString("Effective_Date");
						
						System.out.println(groupID);
						System.out.println(planCode);
						System.out.println(seqsubsID);
						
						Thread.sleep(4000);
						//select("PId_XPATH","Billing Account Number");
						type("PArAccountNumber_XPATH","700080069");
						type("PFirstName_XPATH","Manoj");
						type("PLastName_XPATH","Singh");
						type("PDOB1_XPATH","05/24/1980");
						Thread.sleep(2000);
						click("PScreenClick_XPATH");
						click("pContinueBtn_XPATH");
						Thread.sleep(4000);
						click("pPlanSelectCheckbox_XPATH");
						Thread.sleep(8000);
						clear("pPmtAmntBox_XPATH");
						type("pPmtAmntBox_XPATH","10");
						click("PScreenClick2_XPATH");
						Thread.sleep(7000);
						click("pPayOnlineBtn_XPATH");
						Thread.sleep(8000);
						
}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("PaymentPortal");

	}
}