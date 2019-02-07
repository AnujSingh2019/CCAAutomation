package Rough;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utilities.TestConfig;


public class Test1 {
	
	public static void main(String[] args) throws SQLException {
	
	Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
	PreparedStatement pstmt;
	int numUpd;

	pstmt = con.prepareStatement(
	  "UPDATE AR_ACCOUNT SET ENROLLMENT_STATUS=70 WHERE AR_ACCOUNT_ID=?"); 
	                                  // Create a PreparedStatement object        1 
	pstmt.setString(1,"700069319");        // Assign first value to first parameter    2 
	//pstmt.setString(2,"000010");      // Assign first value to second parameter 
	numUpd = pstmt.executeUpdate();   // Perform first update                     3 
	//pstmt.setString(1,"4658");        // Assign second value to first parameter   
	//pstmt.setString(2,"000020");      // Assign second value to second parameter 
	//numUpd = pstmt.executeUpdate();   // Perform second update                 
	pstmt.close();                  

}
}

