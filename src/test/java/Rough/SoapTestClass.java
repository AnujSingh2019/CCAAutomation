/*package Rough;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.xmlbeans.impl.soap.SOAPConnection;
import org.apache.xmlbeans.impl.soap.SOAPConnectionFactory;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPMessage;

public class SoapTestClass {
	
	*//**
	 * Dummy class implementing X509TrustManager to trust all certificates
	 *//*
	private static class TrustAllCertificates implements X509TrustManager {
	    public void checkClientTrusted(X509Certificate[] certs, String authType) {
	    }
	 
	    public void checkServerTrusted(X509Certificate[] certs, String authType) {
	    }
	 
	    public X509Certificate[] getAcceptedIssuers() {
	        return null;
	    }
	}
	private static final SOAPMessage message = null;

	

	
	
	public SOAPMessage sendSoapRequest(String endpointUrl, SOAPMessage request) {
	    try {
	        final boolean isHttps = endpointUrl.toLowerCase().startsWith("https");
	        HttpsURLConnection httpsConnection = null;
	        // Open HTTPS connection
	        if (isHttps) {
	            // Create SSL context and trust all certificates
	            SSLContext sslContext = SSLContext.getInstance("SSL");
	            TrustManager[] trustAll
	                    = new TrustManager[] {new TrustAllCertificates()};
	            sslContext.init(null, trustAll, new java.security.SecureRandom());
	            // Set trust all certificates context to HttpsURLConnection
	            HttpsURLConnection
	                    .setDefaultSSLSocketFactory(sslContext.getSocketFactory());
	            // Open HTTPS connection
	            URL url = new URL(endpointUrl);
	            httpsConnection = (HttpsURLConnection) url.openConnection();
	            // Trust all hosts
	            httpsConnection.setHostnameVerifier((HostnameVerifier) new TrustAllStrategy());
	            // Connect
	            httpsConnection.connect();
	        }
	        // Send HTTP SOAP request and get response
	        SOAPConnection soapConnection
	                = SOAPConnectionFactory.newInstance().createConnection();
	        SOAPMessage response = soapConnection.call(request, endpointUrl);
	        // Close connection
	        soapConnection.close();
	        // Close HTTPS connection
	        if (isHttps) {
	            httpsConnection.disconnect();
	        }
	        return response;
	    } catch (SOAPException | IOException
	            | NoSuchAlgorithmException | KeyManagementException ex) {
	        // Do Something
	    }
	    return null;
	    
	    
	}
	public static void main(String[] args)
	

    {
		SoapTestClass Var1=new SoapTestClass();
		Var1.sendSoapRequest("https://cca-nonprod.nttdataservices.com/intcca3sit1/ws/soap?wsdl", message);
    }
}
*/