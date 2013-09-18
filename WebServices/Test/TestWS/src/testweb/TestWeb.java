/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testweb;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;

/**
 *
 * @author arpit.agarwal
 */
public class TestWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        org.webservices.MBPIDPServices31Service service = new org.webservices.MBPIDPServices31Service();

        QName portQName = new QName("urn:v3.idpservices.mbp.services.bgc", "MBP-IDPServices-3.1-SOAP-Port");
        String req = "<BridgeAccount  xmlns=\"urn:v3.idpservices.mbp.services.bgc\"><bridgeAccountRequestMessagePart>ENTER VALUE</bridgeAccountRequestMessagePart></BridgeAccount>";

        try { // Call Web Service Operation

            Dispatch<Source> sourceDispatch = null;
            sourceDispatch = service.createDispatch(portQName, Source.class, Service.Mode.PAYLOAD);
            Source result = sourceDispatch.invoke(new StreamSource(new StringReader(req)));
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    }
}
