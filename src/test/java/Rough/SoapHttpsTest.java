package Rough;

import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPConnectionFactory;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPMessage;
import org.testng.annotations.Test;

public class SoapHttpsTest
{
	@Test
	public static void soapXMLtoEndpoint(String endpointURL, SOAPMessage soapXMLFileLocation) throws SOAPException {
        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();
        //SOAPMessage response = connection.call(xmlStringToSOAPMessage(soapXMLFileLocation), endpointURL);
        SOAPMessage response = connection.call(soapXMLFileLocation, endpointURL);
        connection.close();
        SOAPBody responseBody = response.getSOAPBody();
        SOAPBodyElement responseElement = (SOAPBodyElement) responseBody.getChildElements().next();
        SOAPElement returnElement = (SOAPElement) responseElement.getChildElements().next();
        if (responseBody.getFault() != null) {
            System.out.println("fault != null");
            System.out.println(returnElement.getValue() + " " + responseBody.getFault().getFaultString());
        } else {
            String serverResponse = returnElement.getValue();
            System.out.println(serverResponse);
            System.out.println("\nfault == null, got the response properly.\n");
        }
    }
	
	
}


