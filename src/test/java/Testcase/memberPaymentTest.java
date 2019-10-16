
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
public class memberPaymentTest extends BaseTest1{
	
	public void quote(String username, String password,String premium,String postMonth,String subsID) throws InterruptedException, IOException, SQLException
	{
	
	/*type("username_NAME",username);
	type("password_NAME",password);
	Thread.sleep(3000);
	click("login_XPATH");
	click("click_CSS");*/
	Thread.sleep(2000);
	action("mainMenuAR_XPATH");
	action("billHist_XPATH");
	type("subsID_XPATH",subsID);
	Thread.sleep(1000);
	click("searchBtn1_XPATH");
	Thread.sleep(4000);
	String ARAccountID=getText("arAccountCheck_XPATH");
	click("closeBtn3_XPATH");
	Thread.sleep(2000);
	click("closeBtn3_XPATH");
	Thread.sleep(2000);
	action("mainMenuAR_XPATH");
	action("payment_XPATH");
	click("createRBtn_CSS");
	click("continueBtn_XPATH");
	Thread.sleep(2000);
	type("itemCount_XPATH","1");
	type("batchTotal_XPATH",premium);
	Thread.sleep(1000);
	type("paymentPostDate_XPATH",postMonth);
	Thread.sleep(1000);
	click("saveBtn_XPATH");
	Thread.sleep(2000);
	click("addTrans_XPATH");
	Thread.sleep(2000);
	type("arAcct_XPATH",ARAccountID);
	click("label_XPATH");
	Thread.sleep(2000);
	type("fundsAmt_XPATH",premium);
	select("compCode_XPATH","HIX [Health Connector]");
	Thread.sleep(2000);
	select("glRefCode_XPATH","PRM [Premium]");
	Thread.sleep(2000);
	click("saveBtn2_XPATH");
	Thread.sleep(2000);
	click("closeBtn3_XPATH");
	Thread.sleep(2000);
	click("postBatchBtn_XPATH");
	Thread.sleep(2000);
	click("closeBtn4_XPATH");
	Thread.sleep(2000);
	click("closeBtn4_XPATH");
	Thread.sleep(2000);
	/*action("mainMenuAR_XPATH");
	action("billHist_XPATH");
	type("subsID_XPATH",subsID);
	Thread.sleep(3000);
	click("searchBtn1_XPATH");
	Thread.sleep(3000);
	String enrollmentStatus=getText("enrollStatus_XPATH");
	 ARAccountID=getText("arAccountCheck_XPATH");*/
	
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
			
			
			   FileInputStream myxls1 = new FileInputStream("C:/Users/124859/Desktop/excel/Testoutput.xlsx");
		       XSSFWorkbook studentsSheet1 = new XSSFWorkbook(myxls1);
		       XSSFSheet worksheet1 = studentsSheet1.getSheetAt(0);
		       int firstRow1=worksheet1.getFirstRowNum();
		       //System.out.println(lastRow1);
		       XSSFRow row1 = worksheet1.createRow(++firstRow1);
		       row1.createCell(0).setCellValue("Doris");
		       row1.createCell(1).setCellValue("b0ma8c0nsit1$");
		       row1.createCell(2).setCellValue(arAccountID);
		       row1.createCell(3).setCellValue("2018-08-29");
		       
		       myxls1.close();
		       FileOutputStream output_file1 =new FileOutputStream(new File("C:/Users/124859/Desktop/excel/Testoutput.xlsx"));  
		       //write changes
		       studentsSheet1.write(output_file1);
		       output_file1.close();
		      
	
	if (enrollmentStatus.equalsIgnoreCase("25"))
	{
	
	      
	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
	       int lastRow=worksheet.getLastRowNum();
	       //System.out.println(lastRow);
	       XSSFRow row = worksheet.createRow(++lastRow);
	       row.createCell(0).setCellValue("Member payment Test");
	       row.createCell(1).setCellValue(subsID);
	       row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("25");
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
	       row.createCell(0).setCellValue("Member payment Test");
	       row.createCell(1).setCellValue(subsID);
	       row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("25");
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
	//System.out.println("Enrollment Status is---"+ S);
	
	//System.out.println("Test 3- MemberPaymentTest Ran successfully");
	//System.out.println("Payment is successfull for AR Account--"+ ARAccountID +"   and for Premium Amount--"+ premium);
	
/*	click("closeBtn4_XPATH");
	Thread.sleep(3000);
	click("closeBtn4_XPATH");
	*/
	
	}

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("Payment");

	}
}
