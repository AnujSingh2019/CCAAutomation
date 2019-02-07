package Testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.TestConfig;
import utilities.TestUtil;

public class quoteStatusValidationTest {
	
	@Test(dataProvider = "getData")
	public void validation(String subsID) throws AddressException, IOException, MessagingException, InterruptedException, SQLException
	{
	
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
				
				if (enrollmentStatus.equalsIgnoreCase("20"))
			   	{
			   	
			   	      
			   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
			   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
			   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
			   	       int lastRow=worksheet.getLastRowNum();
			   	       System.out.println(lastRow);
			   	       XSSFRow row = worksheet.createRow(++lastRow);
			   	       row.createCell(0).setCellValue("Validate and Post Test");
			   	       row.createCell(1).setCellValue(subsID);
			   	       row.createCell(2).setCellValue(arAccountID);
			   	       row.createCell(3).setCellValue("20");
			   	       row.createCell(4).setCellValue(enrollmentStatus);
			   	       row.createCell(5).setCellValue("PASS");
			   	       myxls.close();
			   	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
			   	       //write changes
			   	       studentsSheet.write(output_file);
			   	       output_file.close();
			   	       //System.out.println(" is successfully written");
			   	      
			   	    }
			   	    

			   	else
			   	{
			   		 
			   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
			   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
			   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
			   	       int lastRow=worksheet.getLastRowNum();
			   	       //System.out.println(lastRow);
			   	       XSSFRow row = worksheet.createRow(++lastRow);
			   	       row.createCell(0).setCellValue("Validate and Post Test");
			   	       row.createCell(1).setCellValue(subsID);
			   	       row.createCell(2).setCellValue(arAccountID);
			   	       row.createCell(3).setCellValue("20");
			   	       row.createCell(4).setCellValue(enrollmentStatus);
			   	       row.createCell(5).setCellValue("FAILED");
			   	       myxls.close();
			   	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
			   	       //write changes
			   	       studentsSheet.write(output_file);
			   	       output_file.close();
			   	       //System.out.println(" is successfully written");
			   	    Assert.fail("Test is FAILED!!!");
			   	}
				
		
				pstmt.close();
	}

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("StatusValidation");

	}


}
