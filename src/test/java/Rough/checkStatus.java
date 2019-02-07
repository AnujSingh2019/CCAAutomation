package Rough;



	import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	import baseclass.BaseTest1;
	import utilities.TestUtil;

	@Test(dataProvider = "getData")
	public class checkStatus extends BaseTest1{
		
		public void checkStatus(String username, String password,String subsID) throws InterruptedException, IOException
		{
			type("username_NAME",username);
	    	type("password_NAME",password);
	    	Thread.sleep(3000);
	    	click("login_XPATH");
	    	click("click_CSS");
	    	//Thread.sleep(3000);
		Thread.sleep(3000);
		action("mainMenuAR_XPATH");
		action("billHist_XPATH");
		type("subsID_XPATH",subsID);
		Thread.sleep(3000);
		click("searchBtn1_XPATH");
		Thread.sleep(3000);
		String enrollmentStatus=getText("enrollStatus_XPATH");
		String ARAccountID=getText("arAccountCheck_XPATH");
		
		//Assert.assertEquals(enrollmentStatus, "71");
		//System.out.println("Test 6- CheckMemberEffectuation Test Ran successfully");
		//System.out.println("Enrollment Status is----"+enrollmentStatus);
		if (enrollmentStatus.equalsIgnoreCase("70"))
		{
		
		      
		       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
		       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
		       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
		       int lastRow=worksheet.getLastRowNum();
		       System.out.println(lastRow);
		       XSSFRow row = worksheet.createRow(++lastRow);
		       row.createCell(0).setCellValue("Member Delinquency Test");
		       row.createCell(1).setCellValue(subsID);
		       row.createCell(2).setCellValue(ARAccountID);
		       row.createCell(3).setCellValue(enrollmentStatus);
		       row.createCell(4).setCellValue("PASS");
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
		       row.createCell(0).setCellValue("Member Delinquency Test");
		       row.createCell(1).setCellValue(subsID);
		       row.createCell(2).setCellValue(ARAccountID);
		       row.createCell(3).setCellValue(enrollmentStatus);
		       row.createCell(4).setCellValue("FAILED");
		       myxls.close();
		       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
		       //write changes
		       studentsSheet.write(output_file);
		       output_file.close();
		       //System.out.println(" is successfully written");
		}
		click("closeBtn4_XPATH");
		Thread.sleep(2000);
		click("closeBtn4_XPATH");
	}
		
		@DataProvider
		public Object[][] getData() {

			return TestUtil.getData("StatusCheck");

		}
		
		
		
	}


