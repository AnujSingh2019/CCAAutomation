
	
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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

	//import baseclass.BaseTest1;
    import baseclass.BaseTest2;
import utilities.TestConfig;
import utilities.TestUtil;

	public class GenerateDailyMembershipEligibilityTest extends BaseTest2 {
		
		@Test(dataProvider = "getData")
		public void NSFBoomi(String username, String password, String CarrierName) throws AddressException, IOException, MessagingException, InterruptedException, SQLException
		{
			
			//Thread.sleep(28000);
			type("BoomiUserName_XPATH",username);
			type("BoomiPassword_XPATH",password);
			
			click("BoomiLogin_XPATH");
			
			try {
			Alert alert=driver.switchTo().alert();
			alert.accept();
			}
			catch (Exception e)
			{
				System.out.println("Alert handled");
			};
			Thread.sleep(12000);
			//select("manage_XPATH","Process Reporting");
			
			//Thread.sleep(4000);
			click("executeProcess_XPATH");
			//action("menu_XPATH");
			//action("menuBill_XPATH");
			Thread.sleep(3000);
			select("atom_XPATH","CCA3_SIT1_A");
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='gwt-TextBox gwt-TextBox-readonly validatable']")));
			click("processSearch_XPATH");
		
			Thread.sleep(3000);
			clear("keySearch_XPATH");
			Thread.sleep(3000);
			type("keySearch_XPATH","GenerateDailyMembershipEligibility"+CarrierName);
			Thread.sleep(4000);
			click("GenerateDailyJob_XPATH");
			Thread.sleep(4000);
			click("executeFinal_CSS");
			Thread.sleep(12000);
			click("refreshBoomi_XPATH");
			Thread.sleep(20000);
			click("refreshBoomi_XPATH");
			
			
		
		
		
		}

		@DataProvider
		public Object[][] getData() {

			return TestUtil.getData("EDI834");

		}
	}



