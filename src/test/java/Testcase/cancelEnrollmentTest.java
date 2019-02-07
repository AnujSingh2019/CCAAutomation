package Testcase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.TestConfig;
import utilities.TestUtil;

@Test(dataProvider="getData")
public class cancelEnrollmentTest {
	
	public void Validation(String username, String password, String subsID)  throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		
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



    	pstmt = con.prepareStatement(
  	    	  "UPDATE QUOTE SET QUOTE_EXPIRATION_DATE=TO_DATE('01/01/2019', 'MM/DD/YYYY')where AR_ACCOUNT_ID=?"); 
  	    pstmt.setString(1,arAccountID); 
  	    numUpd = pstmt.executeUpdate();
  	    	
  	  
    	                                               
                     

           /* System.out.println("Update statement executed successfully");
            System.out.println("Test case 8: DBUpdateTest is PASS");*/
    	
  	  	
}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("dbTest");

}
}
