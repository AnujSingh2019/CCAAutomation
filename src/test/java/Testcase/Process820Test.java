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

public class Process820Test {
	
	@Test(dataProvider = "getData")
	public void validation(String subsID,String CarrierID) throws AddressException, IOException, MessagingException, InterruptedException, SQLException
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
			
		pstmt = con.prepareStatement(
						"Delete from FMSADM.carrier_820_out_detail where seq_carr_820_out_mstr_id in (Select seq_carr_820_out_mstr_id from FMSADM.carrier_820_out_master where  carrier_id = ?)");
						
				pstmt.setString(1,CarrierID);
				numUpd = pstmt.executeUpdate();
				
		pstmt = con.prepareStatement(
								"Delete from FMSADM.Carrier_820_Out_Trans where seq_carr_820_out_mstr_id in \r\n" + 
								"(Select seq_carr_820_out_mstr_id from FMSADM.carrier_820_out_master \r\n" + 
								"where  carrier_id = ?)");
								
				 pstmt.setString(1,CarrierID);
				 numUpd = pstmt.executeUpdate();
			
		pstmt = con.prepareStatement("Delete from FMSADM.carrier_820_out_master where carrier_id =?");
							
					pstmt.setString(1,CarrierID);
					numUpd = pstmt.executeUpdate();
					
		pstmt = con.prepareStatement("commit");
		numUpd = pstmt.executeUpdate();
		
		
		pstmt = con.prepareStatement(
				"Update INVOICE  set INVOICE_DATE=TO_DATE('08/01/2018', 'MM/DD/YYYY') where AR_Account_id= ? ");
				
		pstmt.setString(1,arAccountID);
		numUpd = pstmt.executeUpdate();
		
		pstmt = con.prepareStatement("commit");
		numUpd = pstmt.executeUpdate();
		
		pstmt = con.prepareStatement("select INVOICE_DATE from INVOICE where AR_Account_id= ?"); 
				                                  // Create a PreparedStatement object        1 
				pstmt.setString(1,arAccountID);  
		
				result = pstmt.executeQuery();
				result.next();
				
				String invoiceDate=result.getString("INVOICE_DATE");
		
				
				if (invoiceDate.equalsIgnoreCase("2018-08-01 00:00:00.0"))
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
			   	       row.createCell(3).setCellValue("2018-08-01 00:00:00.0");
			   	       row.createCell(4).setCellValue(invoiceDate);
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
			   	       row.createCell(0).setCellValue("820 Process Test");
			   	       row.createCell(1).setCellValue(subsID);
			   	       row.createCell(2).setCellValue(arAccountID);
			   	       row.createCell(3).setCellValue("2018-08-01 00:00:00.0");
			   	       row.createCell(4).setCellValue(invoiceDate);
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

		return TestUtil.getData("process820Validation");

	}


}
