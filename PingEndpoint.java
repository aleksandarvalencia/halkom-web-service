package wshalkom.sei;

/**
 * @author misa.stefanovic
 */

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import wshalkom.exception.ResponseErrorException;
import wshalkom.model.requests.PingRequest;

import org.springframework.oxm.ValidationFailureException;

@SuppressWarnings("deprecation")
@Endpoint
public class PingEndpoint {

    private XPath pingInputExpression;
    
    
    private static final String NAMESPACE_PREFIX = "ns";
    private static final String NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
        
    
    public PingEndpoint() throws JDOMException {                   
        
                
        Namespace namespace = Namespace.getNamespace("ns", "http://ws.wshalkom.javakurs.com/xsd");
        pingInputExpression = XPath.newInstance("//ns:pingInput");
        pingInputExpression.addNamespace(namespace);        
        
    }
    
    /**
     * Main entry method:
     * 
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pingRequest")  
    @ResponsePayload
   public Element handlePingRequest(@RequestPayload Element soapRequest) throws ResponseErrorException, Exception {           
    	
        //Ovde radimo LOG requesta
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(soapRequest);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        //1. read received data into object...
        PingRequest pingRequest = bindToObject(soapRequest);
        
                       
        //2. validate input data...
        validateRequest(pingRequest);
    	
        //3. invoke business logic
       
        String pingOutput = "O.K. - radi: "+pingRequest.getPingInput();
                   
        //4. create response Element
        Element responseElement = createElement(pingOutput);
    	return responseElement;
    }
    
    protected PingRequest bindToObject(Element soapRequest) throws Exception {
    	    	
    	String pingInput = pingInputExpression.valueOf(soapRequest);
                      
        PingRequest result = new PingRequest(pingInput);
        return result;
        
    }
    
    protected void validateRequest(PingRequest pingRequest) throws ValidationFailureException {
        String pingInput = pingRequest.getPingInput();
        
        if (pingInput != null && pingInput.equalsIgnoreCase("testError")){            
            throw new ValidationFailureException("Exception generisan radi testiranja");   
        }
        
        
    }
    
    
    protected Element createElement(String pingOutput) throws IOException {
        
        Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

        Element root = new Element("pingResponse", kbNs);
        
        Element pingOutputElement = new Element("pingOutput", kbNs);
        pingOutputElement.setText(pingOutput);               
                
        
        root.addContent(pingOutputElement);
                
        Document doc = new Document(root);        

        //Ovde radimo LOG response-a
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(doc);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        return doc.getRootElement();
    }
    
    
}