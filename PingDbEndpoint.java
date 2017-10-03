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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import wshalkom.exception.DataAccessException;
import wshalkom.exception.ResponseErrorException;
import wshalkom.model.managers.HalkomManager;
import wshalkom.model.requests.PingRequest;
import wshalkom.model.vo.pojo.PingDb;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.oxm.ValidationFailureException;

@SuppressWarnings("deprecation")
@Endpoint
public class PingDbEndpoint  {

    private XPath pingDbInputExpression;
    
    private final HalkomManager telimManager;
    
    private static final String NAMESPACE_PREFIX = "ns";
    private static final String  NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
    
    
   @Autowired
    public PingDbEndpoint(HalkomManager telimManager) throws JDOMException {                   
        
        this.telimManager = telimManager;
        
        Namespace namespace = Namespace.getNamespace("ns", "http://ws.wshalkom.javakurs.com/xsd");
        pingDbInputExpression = XPath.newInstance("//ns:pingDbInput");
        pingDbInputExpression.addNamespace(namespace);       
        
    }
    
    /**
     * Main entry method:
     * 
     */
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pingDbRequest")  
   @ResponsePayload
    public Element handlePingDbRequest(@RequestPayload Element soapRequest) throws ResponseErrorException, Exception {           
    	
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
        PingDb pingDb = new PingDb();
        pingDb.setNumber(0);
        
        try {
            pingDb = telimManager.pingDb(); 
            
            
        } catch (DataAccessResourceFailureException e1) {

            throw new DataAccessException("Greška u pristupu podacima!"); 
            
        } catch (BadSqlGrammarException e2) {

                throw new DataAccessException("Greška u SQL-u!");
                
        } catch (Exception e) {

            throw new ResponseErrorException("Greška u odzivu!"); 
        }
        
        String pingOutput = "O.K. - radi: "+pingRequest.getPingInput()+" - Podatak iz baze: "+pingDb.getNumber();
        
        if (pingDb.getNumber() == 0) pingOutput = "Nešto nije u redu: "+pingRequest.getPingInput()+" - Podatak iz baze nije adekvatan: "+pingDb.getNumber();
        
        
        
        //4. create response Element
        Element responseElement = createElement(pingOutput);
    	return responseElement;
    }
    
    protected PingRequest bindToObject(Element soapRequest) throws Exception {
    	    	
    	String pingDbInput = pingDbInputExpression.valueOf(soapRequest);
        
       
        PingRequest result = new PingRequest(pingDbInput);
        return result;
        
    }
    
    protected void validateRequest(PingRequest pingRequest) throws ValidationFailureException {
        String pingDbInput = pingRequest.getPingInput();
        
        if (pingDbInput != null && pingDbInput.equalsIgnoreCase("testError")){
           
            throw new ValidationFailureException("Exception generisan radi DB testiranja");   
        }
        
        
    }
    
    
    protected Element createElement(String pingDbOutput) throws IOException {
        
        Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

        Element root = new Element("pingDbResponse", kbNs);
        
        Element pingDbOutputElement = new Element("pingDbOutput", kbNs);
        pingDbOutputElement.setText(pingDbOutput);               
                
        
        root.addContent(pingDbOutputElement);
                
        Document doc = new Document(root);
        
      //Ovde radimo LOG response-a
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(doc);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        return doc.getRootElement();
    }
    
    
}