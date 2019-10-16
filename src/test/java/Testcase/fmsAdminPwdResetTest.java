
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

import baseclass.BaseTest3;
import utilities.TestConfig;
import utilities.TestUtil;

@Test(dataProvider = "getData")
public class fmsAdminPwdResetTest extends BaseTest3 {
	
	public void login(String userid,String newPassword, String ConfPassword) throws InterruptedException, IOException, SQLException
	{
	
	click("secMain_XPATH");
	Thread.sleep(1000);
	click("userSec_XPATH");
	Thread.sleep(1000);
	type("userID_XPATH",userid);
	Thread.sleep(1000);
	click("search_XPATH");
	Thread.sleep(1000);
	click("record_XPATH");
	Thread.sleep(1000);
	click("resetPWD_XPATH");
	Thread.sleep(1000);
	clear("newPwd_XPATH");
	type("newPwd_XPATH",newPassword);
	type("confirmPwd_XPATH",ConfPassword);
	Thread.sleep(2000);
	click("save_XPATH");
	Thread.sleep(2000);
	

}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("FMSADMINPWDRESET");

	}
}

