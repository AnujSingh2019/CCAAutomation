package Testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import baseclass.BaseTest1;

//import com.google.gson.annotations.Until;

import javafx.scene.control.Alert;
import utilities.TestUtil;

public class soapTest {
	
	@Test(dataProvider = "getData")
	public static void ProcessPayLoadEnrollmentCalls(String carrier) throws SOAPException, ParserConfigurationException, SAXException, IOException, InterruptedException
    
    {
		           //System.setProperty("java.net.useSystemProxies", "true");
		           
                   String Username=System.getProperty("user.name");
                   /*String keystore =  "C://Program Files//Java//jdk1.8.0_112//jre//lib//security//keystore";
                   String storepass= "changeit";
                   String storetype= "JKS";
                   String[][] props = {
                         { "javax.net.ssl.trustStore", keystore, },
                         { "javax.net.ssl.keyStore", keystore, },
                         { "javax.net.ssl.keyStorePassword", storepass, },
                         { "javax.net.ssl.keyStoreType", storetype, },
                       };
                       for (int i = 0; i < props.length; i++)
                         System.getProperties().setProperty(props[i][0], props[i][1]);*/
                   String filename= carrier+".xml";
                   //String f = carrier+".xml";
                   
                   File fXmlFile = new File("C:\\Users\\"+Username+"\\Desktop\\XMLs\\"+filename);
    String    soapAction="updateProcessPayload2";
    String SoapendpointURL = "http://cca-nonprod.nttdataservices.com/intcca3sit2/ws/soap?wsdl";
  //String SoapendpointURL = "http://cca-nonprod.nttdataservices.com/intper2/ws/soap?wsdl";
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
                           
    }
	
	/*public static void main(String[] arg) throws SOAPException, ParserConfigurationException, SAXException, IOException, InterruptedException
	{
		ProcessPayLoadEnrollmentCalls("PB1");
	}*/
                   
	
	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("SoapUI");

	}
    }       