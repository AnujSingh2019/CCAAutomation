package Testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import utilities.TestUtil;

public class Soap820ProcessTest {
	
	@SuppressWarnings("restriction")
	@Test(dataProvider = "getData")
	public static void ProcessPayLoadEnrollmentCalls(String carrier) throws SOAPException, ParserConfigurationException, SAXException, IOException, InterruptedException
    
    {
		           System.setProperty("java.net.useSystemProxies", "false");
		           
		   	   /* System.setProperty("http.proxyHost", "proxy.my.com");
			    System.setProperty("http.proxyPort", "80");
			    System.setProperty("https.proxyHost", "proxy.my.com");
			    System.setProperty("https.proxyPort", "80");*/
		           
                   String Username=System.getProperty("user.name");
                   String keystore =  "C://Program Files//Java//jdk1.8.0_112//jre//lib//security//keystore.jks";
                   String truststore =  "C://Program Files//Java//jdk1.8.0_112//jre//lib//security//cacerts";
                   String storepass= "changeit";
                   String storetype= "JKS";
                   String[][] props = {
                         { "javax.net.ssl.trustStore", truststore, },
                         { "javax.net.ssl.keyStore", keystore, },
                         { "javax.net.ssl.keyStorePassword", storepass, },
                         { "javax.net.ssl.keyStoreType", storetype, },
                       };
                       for (int i = 0; i < props.length; i++)
                         System.getProperties().setProperty(props[i][0], props[i][1]);
                   String filename= carrier+".xml";
                   //String f = carrier+".xml";
                   
                   File fXmlFile = new File("C:\\Users\\"+Username+"\\Desktop\\XMLs\\"+filename);
    String    soapAction="generate820OutboundTest";
    String SoapendpointURL ="https://fmssit1.cca-nonprod.nttdataservices.com/diamond-ws/services/FMSService";
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

		return TestUtil.getData("Soap820");

	}


}
