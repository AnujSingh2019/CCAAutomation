
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
public class fmsAdminLoginTest extends BaseTest3 {
	
	public void login(String username, String password) throws InterruptedException, IOException, SQLException
	{
	
	type("username_XPATH",username);
	type("password_XPATH",password);
	Thread.sleep(2000);
	click("FMSlogin_XPATH");
	//click("click_CSS");
	Thread.sleep(2000);
	
	
}
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("FMSADMLogin");

	}
}

