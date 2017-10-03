package halkom.model.ws;

import java.io.StringReader;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.ws.client.core.WebServiceTemplate;

import java.io.ByteArrayOutputStream;

public class WebServiceClient {

	private static final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    public void setDefaultUri(String defaultUri) {
        webServiceTemplate.setDefaultUri(defaultUri);
    }

    // send to the configured default URI
    public static String simpleSendAndReceive(String message) {
               
        StreamSource source = new StreamSource(new StringReader(message));                
        ByteArrayOutputStream ost = new ByteArrayOutputStream();       
        StreamResult result = new StreamResult(ost);        
        webServiceTemplate.sendSourceAndReceiveToResult(source, result); 
        String strigOutput = "";
        try {
            strigOutput = ost.toString("UTF-8");
        } catch(Exception e) {
            
            strigOutput = ost.toString();
        }
        return strigOutput;
        
        
    }
}
