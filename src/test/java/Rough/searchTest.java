package Rough;

import org.testng.annotations.Test;

import baseclass.BaseTest1;

@Test
public class searchTest extends BaseTest1 {
	
	public void search() throws InterruptedException
	{
		type("searchTextBox_XPATH", "Redmi Note 5 Pro");
		click("SearchGobtn_XPATH");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click("selectItem_XPATH");
		Thread.sleep(6000);
		
	}

}
