package utilities;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestExcel {

	public static  ArrayList<String> SubsId() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		FileInputStream f = new FileInputStream("C:/XML Generator/xml-gen/data/ENB.xlsx");
		Workbook ws =  WorkbookFactory.create(f);
		
			
			
		
		Sheet sh =  ws.getSheet("Sheet1");
		
		
		int rowcount = sh.getLastRowNum();
		ArrayList<String> l1 = new ArrayList<String>();
	
		
		for(int i=1;i<rowcount;i-- )
		{
		       Row row = sh.getRow(i);
		       		       
		       for(int j=0;j<row.getLastCellNum();j++)
		       {
		    	  	   System.out.println(row.getCell(i).getStringCellValue());
		    	   //l1.add(row.getCell(i));
		    	   
		    	   
		       }
		    	  
		    	  
		       
		       
		}
	System.out.println("Execution Done");	
		return l1;
		
		
		}
		
		
		
		
		
		
	
		
	

	
	//****************for premium amount******************
	
public static  ArrayList<String> premiumAmount() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		FileInputStream f = new FileInputStream("C:/XML Generator/xml-gen/data/ENB.xlsx");
		Workbook ws = WorkbookFactory.create(f);
		Sheet sh =  ws.getSheet("Sheet1");
		
		
		int rowcount = sh.getLastRowNum();
		
		ArrayList<String> l2 = new ArrayList<String>();
		for(int i=1;i<=rowcount;++i )
		{
                   
		       Row row = sh.getRow(i);
		       		       
		       for(int j=0;j<row.getLastCellNum();j++)
		       {
		    	  
		    	  
		    		  System.out.println(row.getCell(j).getStringCellValue());
		    		  l2.add(row.getCell(i).getStringCellValue());
		    	  
		       }  
		       
		       
		}
		       return l2;
				
		}
		
		
		
	

public static ArrayList<String> Gl_post_month() throws IOException, InvalidFormatException
{

	FileInputStream f = new FileInputStream("C:/XML Generator/xml-gen/data/ENB.xlsx");
	Workbook ws = WorkbookFactory.create(f);
	Sheet sh =  ws.getSheet("Sheet1");
	
	
	
	int rowcount = sh.getLastRowNum();
	
	ArrayList<String> l3 = new ArrayList<String>();
	
	
	
	for(int i=1;i<rowcount+1;i++ )
	{
	       Row row = sh.getRow(i);
	       		       
	       for(int j=0;j<row.getLastCellNum();j++)
	       {
	    	  
	    	  
	    		  System.out.println(row.getCell(j).getStringCellValue());
	    		  l3.add(row.getCell(i).getStringCellValue());
	    	  }
	    		  
	       
	       
	}
	return l3;
	}
	

	
	


	
	
	
	public static void main(String args[]) throws InvalidFormatException, IOException
	{
		ArrayList<String> list1= new ArrayList<String>();
		list1=SubsId();
		
		
		ArrayList<String> M = new ArrayList<String>();
		M=premiumAmount();
		ArrayList<String> p = new ArrayList<String>();
		p=Gl_post_month();
		
	}
	
	
}
