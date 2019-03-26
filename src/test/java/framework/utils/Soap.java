package framework.utils;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Soap {

    /**
     * @param endpointUrl
     * @param request
     * @return
     */
    public static Xml sendSoapRequest(String endpointUrl, SOAPMessage request) {
        try {
            // Send HTTP SOAP request and get response
            SOAPConnection soapConnection = SOAPConnectionFactory.newInstance().createConnection();
            SOAPMessage response = soapConnection.call(request, endpointUrl);
            // Close connection
            soapConnection.close();
            return new Xml(response);
        } catch (SOAPException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
