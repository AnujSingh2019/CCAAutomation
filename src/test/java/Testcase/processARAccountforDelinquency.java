package Testcase;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import baseclass.BaseTest1;

//import com.google.gson.annotations.Until;

import javafx.scene.control.Alert;
import utilities.TestConfig;
import utilities.TestUtil;

public class processARAccountforDelinquency {
	
	@Test(dataProvider = "getData")
	public static void ProcessPayLoadEnrollmentCalls(String carrier) throws SOAPException, ParserConfigurationException, SAXException, IOException, InterruptedException, SQLException
    
    {
		           System.setProperty("java.net.useSystemProxies", "true");
		           
                   String Username=System.getProperty("user.name");
                   String keystore =  "C://Program Files//Java//jdk1.8.0_112//jre//lib//security//keystore.jks";
                   String storepass= "changeit";
                   String storetype= "JKS";
                   String[][] props = {
                         { "javax.net.ssl.trustStore", keystore, },
                         { "javax.net.ssl.keyStore", keystore, },
                         { "javax.net.ssl.keyStorePassword", storepass, },
                         { "javax.net.ssl.keyStoreType", storetype, },
                       };
                       for (int i = 0; i < props.length; i++)
                         System.getProperties().setProperty(props[i][0], props[i][1]);
                   String filename= carrier+".xml";
                   //String f = carrier+".xml";
                   
                   File fXmlFile = new File("C:\\Users\\"+Username+"\\Desktop\\XMLs\\"+filename);
                   
                   /*String    soapAction="updateProcessPayload";
                   String SoapendpointURL = "http://cca-nonprod.nttdataservices.com/intcca3sit1/ws/soap?wsdl";*/
                   String    soapAction="processDelinquencyForARAccount";
                   String SoapendpointURL ="https://fmssit1.cca-nonprod.nttdataservices.com/diamond-ws/services/AccountsReceivableService?wsdl";
                   SOAPMessage message = MessageFactory.newInstance().createMessage();
SOAPPart soapPart = message.getSOAPPart();
soapPart.setContent(new StreamSource(new FileInputStream(fXmlFile)));
// DocumentBuilderFactory.setNamespaceAware(true);
MimeHeaders headers = message.getMimeHeaders();
headers.addHeader("SOAPAction", soapAction);
DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
dbFactory.setNamespaceAware(true);
                   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                   Document doc = dBuilder.parse(fXmlFile);
                   
                   //optional, but recommended
                   //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                   
                   SOAPEnvelope envelope = soapPart.getEnvelope();
                   SOAPBody soapBody = envelope.getBody();
                                                soapBody.addDocument(doc);
                                                message.saveChanges();
                                                SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
                               SOAPConnection soapConnection = soapConnectionFactory.createConnection();

                               // Send SOAP Message to SOAP Server
                               SOAPMessage soapResponse = soapConnection.call(message, SoapendpointURL);
                            //  System.out.println(soapResponse.getSOAPPart());
                               soapResponse.writeTo(System.out);
                               
                               Thread.sleep(3000);
           
                               
                               /*Connection con = DriverManager.getConnection(TestConfig.dbConnectionUrl, TestConfig.dbUserName, TestConfig.dbPassword);
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
                      					
                      					if (enrollmentStatus.equalsIgnoreCase("71"))
                      				   	{
                      				   	
                      				   	      
                      				   	       FileInputStream myxls = new FileInputStream("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx");
                      				   	       XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
                      				   	       XSSFSheet worksheet = studentsSheet.getSheetAt(0);
                      				   	       int lastRow=worksheet.getLastRowNum();
                      				   	       System.out.println(lastRow);
                      				   	       XSSFRow row = worksheet.createRow(++lastRow);
                      				   	       row.createCell(0).setCellValue("Delinquency job run Test");
                      				   	       row.createCell(1).setCellValue(subsID);
                      				   	       row.createCell(2).setCellValue(arAccountID);
                      				   	       row.createCell(3).setCellValue("71");
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
                      				   	       row.createCell(0).setCellValue("Delinquency job run Test");
                      				   	       row.createCell(1).setCellValue(subsID);
                      				   	       row.createCell(2).setCellValue(arAccountID);
                      				   	       row.createCell(3).setCellValue("71");
                      				   	       row.createCell(4).setCellValue(enrollmentStatus);
                      				   	       row.createCell(5).setCellValue("FAILED");
                      				   	       myxls.close();
                      				   	       FileOutputStream output_file =new FileOutputStream(new File("D:/Selenium/Eclispse/SeleniumPractice/DataDrivenFrameworkMVN/src/test/resources/excel/Testoutput.xlsx"));  
                      				   	       //write changes
                      				   	       studentsSheet.write(output_file);
                      				   	       output_file.close();
                      				   	       //System.out.println(" is successfully written");
                      				   	    Assert.fail("Test is FAILED!!!");
                      				   	}
                      					
                      			
                      					pstmt.close();  
                              
                               */
                              
    }
	
	/*public static void main(String[] arg) throws SOAPException, ParserConfigurationException, SAXException, IOException, InterruptedException
	{
		ProcessPayLoadEnrollmentCalls("PB1");
	}*/
                   
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("Delinquency");

	}
    }       
