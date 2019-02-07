package Testcase;


	import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseclass.BaseTest1;
import utilities.DbManager;
import utilities.TestConfig;
import utilities.TestUtil;

	@Test(dataProvider="getData")
	public class DBUpdateTest extends BaseTest1 {

	   
        
	    public void Validation(String username, String password, String subsID)  throws ClassNotFoundException, SQLException, InterruptedException, IOException {
	    	
	        /*type("username_NAME",username);
	    	type("password_NAME",password);
	    	Thread.sleep(3000);
	    	click("login_XPATH");
	    	click("click_CSS");
	    	Thread.sleep(3000);*/
	    	action("mainMenuAR_XPATH");
	    	action("billHist_XPATH");
	    	type("subsID_XPATH",subsID);
	    	Thread.sleep(3000);
	    	click("searchBtn1_XPATH");
	    	Thread.sleep(3000);
	    	String ARAccountID=getText("arAccountCheck_XPATH");
	       
	    	Thread.sleep(4000);
	    	Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
	    	PreparedStatement pstmt;
	    	int numUpd;
	    	ResultSet result;

	    	pstmt = con.prepareStatement(
	  	    	  "update doris.INVOICE set invoice_date=TO_DATE('08/01/2018', 'MM/DD/YYYY') where invoice_id in (select INVOICE_ID from doris.INVOICE where AR_ACCOUNT_ID=? and INVOICE_FROM_DATE=TO_DATE('09/01/2018', 'MM/DD/YYYY'))"); 
	  	    pstmt.setString(1,ARAccountID); 
	  	    numUpd = pstmt.executeUpdate();
	  	    	
	  	    pstmt = con.prepareStatement(
	  	  	    	  "update doris.INVOICE set payment_due_date=TO_DATE('08/23/2018', 'MM/DD/YYYY') where invoice_id in (select INVOICE_ID from doris.INVOICE where AR_ACCOUNT_ID=? and INVOICE_FROM_DATE=TO_DATE('09/01/2018', 'MM/DD/YYYY'))"); 
	  	  	pstmt.setString(1,ARAccountID); 
	  	  	 numUpd = pstmt.executeUpdate();
	    	                                               
	                     

	           /* System.out.println("Update statement executed successfully");
	            System.out.println("Test case 8: DBUpdateTest is PASS");*/
	    	
	  	  	pstmt = con.prepareStatement(
	  			  "select enrollment_status,AR_Account_ID from AR_ACCOUNT WHERE SEQ_BILL_ENTITY_ID in ( select SEQ_SUBS_ID FROM MEMBER_ELIG_HISTORY WHERE SUBSCRIBER_ID= ?)"); 
	  			                                  // Create a PreparedStatement object        1 
	  			pstmt.setString(1,subsID);  
	  	
	  			result = pstmt.executeQuery();
	  			result.next();
	  			
	  			String enrollmentStatus=result.getString("ENROLLMENT_STATUS");
	  			String arAccountID=result.getString("AR_ACCOUNT_ID");
	   	if (enrollmentStatus.equalsIgnoreCase("70"))
	   	{
	   	
	   	      
	   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
	   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
	   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
	   	       int lastRow=worksheet.getLastRowNum();
	   	       System.out.println(lastRow);
	   	       XSSFRow row = worksheet.createRow(++lastRow);
	   	       row.createCell(0).setCellValue("Member Delinqueny Test");
	   	       row.createCell(1).setCellValue(subsID);
	   	       row.createCell(2).setCellValue(ARAccountID);
	   	       row.createCell(3).setCellValue("70");
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
	   	    row.createCell(0).setCellValue("Member Delinqueny Test");
	   	       row.createCell(1).setCellValue(subsID);
	   	       row.createCell(2).setCellValue(ARAccountID);
	   	       row.createCell(3).setCellValue("70");
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
	   	//System.out.println("Enrollment Status is---"+ S);
	   	
		pstmt.close(); 
	   	click("closeBtn4_XPATH");
	   	Thread.sleep(3000);
	   	click("closeBtn4_XPATH");
	   	
	        
	        }
	    
	    @DataProvider
		public Object[][] getData() {

			return TestUtil.getData("dbTest");

		}
	    
	    }
	
	
