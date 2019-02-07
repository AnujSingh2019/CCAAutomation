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
public class PBLoginTest extends BaseTest1 {
	
	public void login(String username, String password) throws InterruptedException, IOException, SQLException
	{
	
	type("username_NAME",username);
	type("password_NAME",password);
	Thread.sleep(3000);
	click("login_XPATH");
	click("click_CSS");
	Thread.sleep(3000);
	
	//isElementPresent("menu_XPATH");
	
	if (isElementPresent("menu_XPATH"))
	{
	
	      
	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
	       int lastRow=worksheet.getLastRowNum();
	       System.out.println(lastRow);
	       XSSFRow row = worksheet.createRow(++lastRow);
	       row.createCell(0).setCellValue("PB login Test");
	       //row.createCell(1).setCellValue(subsID);
	       //row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("20");
	       //row.createCell(4).setCellValue(enrollmentStatus);
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
	       row.createCell(0).setCellValue("PB Login Test");
	       //row.createCell(1).setCellValue(subsID);
	       //row.createCell(2).setCellValue(arAccountID);
	       row.createCell(3).setCellValue("20");
	       //row.createCell(4).setCellValue(enrollmentStatus);
	       row.createCell(5).setCellValue("FAILED");
	       myxls.close();
	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
	       //write changes
	       studentsSheet.write(output_file);
	       output_file.close();
	       Assert.fail("Test is FAILED!!!");
	       //System.out.println(" is successfully written");
	}
	//Assert.assertEquals(enrollmentStatus, "30");
	//Assert.assertTrue((getText("enrollStatus_XPATH")=="30"), "Quote generated successfully.MemberEffectuationTest is PASS" );
	//System.out.println("Test 2- MemberEffectuationTest Ran successfully");
	//System.out.println("Quote generated successfully for Month--"+ postMonth +"   and for Subscriber ID--"+ subsID);
	
	 

}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("PBLogin");

	}
}
