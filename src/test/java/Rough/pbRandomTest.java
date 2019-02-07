package Rough;
import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.Select;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import utilities.TestExcel1;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


class pbRandomTest {
	
	public static void quote( String SubsID, String premiumAmount, String date)throws InterruptedException
	{
	
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\124859\\Downloads\\Selenium\\ChromeDriver\\chromedriver.exe");
		WebDriver D1 = new ChromeDriver();
		D1.get("https://fmssit1.cca-nonprod.nttdataservices.com/dell-exchange/pbFms.do");
		Thread.sleep(1000);
		D1.manage().window().maximize();
		
		//quote(D1,SubsID,premiumAmount,date);
		D1.findElement(By.name("userid")).sendKeys("doris");
		D1.findElement(By.name("password")).sendKeys("b0ma8c0nsit1$");
		//D1.findElement(By.classid("buttons")).click();
		D1.findElement(By.className("buttons")).click();
		Thread.sleep(3000);
		//D1.findElement(By.partialLinkText("/dell-exchange/pbFms.do")).click();
		D1.findElement(By.cssSelector("a[href*='/dell-exchange/pbFms.do']")).click();
		
		Actions actions = new Actions(D1);
		WebElement mainMenu = D1.findElement(By.id("MainMenu_Jobs"));
		actions.moveToElement(mainMenu);

		WebElement subMenu = D1.findElement(By.id("MainMenu_BillingConfiguration0"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		D1.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td/input[3]")).click();
	    D1.findElement(By.name("NewJob")).click();
	    D1.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr/td/table/tbody/tr[7]/td[1]/input")).click();
	    D1.findElement(By.name("subscriber")).sendKeys("761713864169-H");
	    D1.findElement(By.name("billthru")).sendKeys("10/31/2018");
	    D1.findElement(By.name("postmonth")).sendKeys("09/2018");
	    
	    WebElement dropdown= D1.findElement(By.name("runoption"));
        Select select=new Select(dropdown);
        select.selectByVisibleText("Immediate");
        D1.findElement(By.name("Submit")).click();
        D1.findElement(By.name("Close")).click();
        Thread.sleep(8000);
        D1.findElement(By.name("Refresh")).click();
        Thread.sleep(4000);
        D1.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[3]/td/table/tbody/tr[3]/td/div/table/tbody/tr[1]/td[8]/a[2]/img")).click();
        D1.findElement(By.name("post")).click();
        
        System.out.println("Invoice Generated Successfully");
	}
	public static void main(String[] args) throws InterruptedException
	{
		pbRandomTest var1=new pbRandomTest();
		var1.quote( "11111111-H","100", "08/2018");
	}
}
