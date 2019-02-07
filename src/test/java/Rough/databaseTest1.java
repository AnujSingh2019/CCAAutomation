package Rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.TestConfig;
import utilities.TestUtil;

@Test(dataProvider = "getData")
public class databaseTest1 {
	
	public  void database(String subsID) throws SQLException, IOException {
	
	Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
	PreparedStatement pstmt;
	int numUpd;
	ResultSet result;
	

	//pstmt = con.prepareStatement(
	  //"UPDATE AR_ACCOUNT SET ENROLLMENT_STATUS=70 WHERE AR_ACCOUNT_ID=?"); 
	                                  // Create a PreparedStatement object        1 
	//pstmt.setString(1,"700069319");        // Assign first value to first parameter    2 
	//pstmt.setString(2,"000010");      // Assign first value to second parameter 
	//numUpd = pstmt.executeUpdate();   // Perform first update                     3 
	//pstmt.setString(1,"4658");        // Assign second value to first parameter   
	//pstmt.setString(2,"000020");      // Assign second value to second parameter 
	//numUpd = pstmt.executeUpdate();   // Perform second update                 
	//pstmt.close();  
	
	
	pstmt = con.prepareStatement(
			  "select enrollment_status,AR_Account_ID from AR_ACCOUNT WHERE SEQ_BILL_ENTITY_ID in ( select SEQ_SUBS_ID FROM MEMBER_ELIG_HISTORY WHERE SUBSCRIBER_ID= ?)"); 
			                                  // Create a PreparedStatement object        1 
			pstmt.setString(1,subsID);  
	
			result = pstmt.executeQuery();
			result.last();
			
			String enrollmentStatus=result.getString("ENROLLMENT_STATUS");
			String arAccountID=result.getString("AR_ACCOUNT_ID");
			
			if (enrollmentStatus.equalsIgnoreCase("71"))
		   	{
		   	
		   	      
		   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DatabaseTest/src/test/resources/excel/Testoutput.xlsx");
		   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
		   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
		   	       int lastRow=worksheet.getLastRowNum();
		   	       System.out.println(lastRow);
		   	       XSSFRow row = worksheet.createRow(++lastRow);
		   	       row.createCell(0).setCellValue("Member Delinqueny Test");
		   	      /* row.createCell(1).setCellValue(subsID);
		   	       row.createCell(2).setCellValue(ARAccountID);*/
		   	       row.createCell(3).setCellValue("71");
		   	       row.createCell(4).setCellValue(enrollmentStatus);
		   	       row.createCell(5).setCellValue("PASS");
		   	       myxls.close();
		   	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DatabaseTest/src/test/resources/excel/Testoutput.xlsx"));  
		   	       //write changes
		   	       studentsSheet.write(output_file);
		   	       output_file.close();
		   	       //System.out.println(" is successfully written");
		   	      
		   	    }
		   	    

		   	else
		   	{
		   		 
		   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DatabaseTest/src/test/resources/excel/Testoutput.xlsx");
		   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
		   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
		   	       int lastRow=worksheet.getLastRowNum();
		   	       //System.out.println(lastRow);
		   	       XSSFRow row = worksheet.createRow(++lastRow);
		   	    row.createCell(0).setCellValue("Member Delinqueny Test");
		   	      /* row.createCell(1).setCellValue(subsID);
		   	       row.createCell(2).setCellValue(ARAccountID);*/
		   	       row.createCell(3).setCellValue("71");
		   	       row.createCell(4).setCellValue(enrollmentStatus);
		   	       row.createCell(5).setCellValue("FAILED");
		   	       myxls.close();
		   	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DatabaseTest/src/test/resources/excel/Testoutput.xlsx"));  
		   	       //write changes
		   	       studentsSheet.write(output_file);
		   	       output_file.close();
		   	       //System.out.println(" is successfully written");
		   	    Assert.fail("Test is FAILED!!!");
		   	}
			
		/*	if(result.getString(1)=="30")
			{
				System.out.println("Test is Passed");
			}
			else
			{
				System.out.println("Test is Fail");
			}
*/
			
			pstmt.close();  
}
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("Quote");

	
}
}

