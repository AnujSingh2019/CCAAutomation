
package Testcase;

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

import baseclass.BaseTest1;
import utilities.TestConfig;
import utilities.TestUtil;

@Test(dataProvider = "getData")
public class thresholdQuoteTest extends BaseTest1{
	
	public void quote(String subsID, String postMonth) throws InterruptedException, IOException, SQLException
	{
	
	/*type("username_NAME",username);
	type("password_NAME",password);
	Thread.sleep(3000);
	click("login_XPATH");
	click("click_CSS");*/
	Thread.sleep(3000);
	action("menu_XPATH");
	action("menuBill_XPATH");
	click("quoterRbtn_CSS");
	click("subscriber_XPATH");
	Thread.sleep(3000);
	
	clear("billingCycle_XPATH");
	Thread.sleep(2000);
	type ("billingCycle_XPATH",subsID);
	Thread.sleep(2000);
	
	click("btn_XPATH");
	Thread.sleep(2000);
	click("newJob_XPATH");
	Thread.sleep(2000);
	click("subID_CSS");
	type ("subIDBox_XPATH",subsID);
	type ("postMonth_XPATH",postMonth);
	Thread.sleep(1000);
	select("enrollCycle_XPATH","25 - Calculate Threshold");
	Thread.sleep(2000);
	select("runOption_XPATH","Immediate");
	Thread.sleep(2000);
	click("submitBtn1_XPATH");
	Thread.sleep(2000);
	click("closeBtn1_XPATH");
	Thread.sleep(2000);
	click("refreshBtn1_XPATH");
	Thread.sleep(2000);
	click("quoteImg_XPATH");
	click("postBtn1_XPATH");
	Thread.sleep(2000);
	click("closeBtn2_XPATH");
	Thread.sleep(2000);
	/*action("mainMenuAR_XPATH");
	action("billHist_XPATH");
	type("subsID_XPATH",subsID);
	Thread.sleep(3000);
	click("searchBtn1_XPATH");
	Thread.sleep(3000);
	String enrollmentStatus=getText("enrollStatus_XPATH");
	String ARAccountID=getText("arAccountCheck_XPATH");*/
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
	
	if (enrollmentStatus.equalsIgnoreCase("50"))
	{
	
	      
	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
	       int lastRow=worksheet.getLastRowNum();
	       //System.out.println(lastRow);
	       XSSFRow row = worksheet.createRow(++lastRow);
	       row.createCell(0).setCellValue("Threshold Quote generation Test");
	       row.createCell(1).setCellValue(subsID);
	       row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("50");
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
	       row.createCell(0).setCellValue("Threshold Quote generation Test");
	       row.createCell(1).setCellValue(subsID);
	       row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("50");
	       row.createCell(4).setCellValue(enrollmentStatus);
	       row.createCell(5).setCellValue("FAILED");
	       myxls.close();
	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
	       //write changes
	       studentsSheet.write(output_file);
	       output_file.close();
	       Assert.fail("Test is FAILED!!!");
	       //System.out.println(" is successfully written");
	}
	
	//System.out.println("Test 4- thresholdQuoteTest Ran successfully");
	//System.out.println("Threshold Quote generated successfully for Month--"+ postMonth +"   and for Subscriber ID--"+ subsID);
	
	/*
	click("closeBtn4_XPATH");
	Thread.sleep(3000);
	click("closeBtn4_XPATH");*/
	
	}

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("Quote");

	}
}
