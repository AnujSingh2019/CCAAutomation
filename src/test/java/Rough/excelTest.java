package Rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	
			       String name="Manish";
			       String ID= "ID333";
			       FileInputStream myxls = new FileInputStream("D:/Healthcare/CCA/Selenium/Test.xlsx");
			       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
			       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
			       int lastRow=worksheet.getLastRowNum();
			       System.out.println(lastRow);
			       XSSFRow row = worksheet.createRow(++lastRow);
			       row.createCell(0).setCellValue(ID);
			       row.createCell(1).setCellValue(name);
			       myxls.close();
			       FileOutputStream output_file =new FileOutputStream(new File("D:/Healthcare/CCA/Selenium/Test.xlsx"));  
			       //write changes
			       studentsSheet.write(output_file);
			       output_file.close();
			       System.out.println(" is successfully written");
			    }
			    

	}

