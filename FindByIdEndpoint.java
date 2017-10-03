package wshalkom.sei;

 

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.output.XMLOutputter;
//import org.jdom2.xpath.XPath;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import wshalkom.exception.DataAccessException;
import wshalkom.exception.NotFoundException;
import wshalkom.exception.ResponseErrorException;
import wshalkom.model.managers.HalkomManager;
import wshalkom.model.vo.pojo.Nalog;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
//import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.oxm.ValidationFailureException;

//@SuppressWarnings("deprecation")
@Endpoint
public class FindByIdEndpoint  {

	XPathExpression<Element> idExpression;
	private XPathFactory xpfac;
	private Namespace namespace;
	    
    private final HalkomManager halkomManager;
    
    private static final String NAMESPACE_PREFIX = "ns";
    private static final String  NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
    
    
   @Autowired
    public FindByIdEndpoint(HalkomManager halkomManager) throws JDOMException {                   
        
        this.halkomManager = halkomManager;
        
        namespace = Namespace.getNamespace("ns", "http://ws.wshalkom.javakurs.com/xsd");
        xpfac = XPathFactory.instance();
		
//        idExpression = XPath.newInstance("//ns:idNA");
//        idExpression.addNamespace(namespace);
//               
        
    }
    
    /**
     * Main entry method:
     * 
     */
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findByIdRequest")  
   @ResponsePayload
    public Element handlePingDbRequest(@RequestPayload Element soapRequest) throws ResponseErrorException, Exception {           
    	

        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(soapRequest);
        
        System.out.println(message);

        //  String idRequested = idExpression.valueOf(soapRequest); 
        XPathExpression<Element> idExpression = xpfac.compile("//ns:idNA", Filters.element(),null,namespace);
        Element idRequestedElem =idExpression.evaluateFirst(soapRequest);
        String idRequested=idRequestedElem.getText();
        
        System.out.println(idRequested);
        //2. validate input data...
        validateRequest(idRequested);
    	
        //3. invoke business logic
        
        Nalog contact = null;
        
        try {
        	 System.out.println(idRequested);
           contact = halkomManager.findById(new Integer(idRequested)); 
          
            
        } catch (DataAccessResourceFailureException e1) {
        	// U praksi ovde bi bio LOG
    		e1.printStackTrace();
            throw new DataAccessException("Greška u pristupu podacima!"); 
            
        } catch (BadSqlGrammarException e2) {
        	// U praksi ovde bi bio LOG
        		e2.printStackTrace();
                throw new DataAccessException("Greška u SQL-u!");
                
        } catch (NotFoundException e3) {
        	// U praksi ovde bi bio LOG
        		e3.printStackTrace();
                throw new NotFoundException("Ne postoje podaci!");
                
        } catch (Exception e) {
        	// U praksi ovde bi bio LOG
        	e.printStackTrace();
            throw new ResponseErrorException("Greška u odzivu!"); 
        }
        
                
        
        //4. create response Element
        Element responseElement = createElement(contact);
    	return responseElement;
    }
    
    
    
    protected void validateRequest(String idRequested) throws ValidationFailureException {
    	try {
    		@SuppressWarnings("unused")
			Integer id = new Integer(idRequested);
    	} catch (Exception e) {
    		throw new ValidationFailureException("Neodgovarajući podatak za ID - mora biti Integer");   
    	}
    	
        
    }
    
    
    protected Element createElement(Nalog nalog) throws IOException {
        
        Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

        Element root = new Element("findByIdResponse", kbNs);
        
        Element nalogElement = new Element("nalog", kbNs);
        
        Element idNA = new Element("idNA", kbNs);
        idNA.setText(nalog.getIdNA().toString()); 
        
        Element tipdok = new Element("tipdok", kbNs);
        tipdok.setText(nalog.getTipdok().toString());
        
        Element idDok = new Element("idDok", kbNs);
        idDok.setText(nalog.getIdDok());
        
        Element idPrejem = new Element("idPrejem", kbNs);
        idPrejem.setText(nalog.getIdPrejem());    
                
        Element datumvalute = new Element("datumvalute", kbNs);
        datumvalute.setText(nalog.getDatumvalute());
        
        Element racunb = new Element("racunb", kbNs);
        racunb.setText(nalog.getRacunb());
        
        Element sklicb = new Element("sklicb", kbNs);
        sklicb.setText(nalog.getSklicb());
        
        Element racund = new Element("racund", kbNs);
        racund.setText(nalog.getRacund()); 

        Element iznos = new Element("iznos", kbNs);
        iznos.setText(nalog.getIznos());
        
        Element imeb = new Element("imeb", kbNs);
        imeb.setText(nalog.getImeb());    
                
        Element ulicab = new Element("ulicab", kbNs);
        ulicab.setText(nalog.getUlicab());
        
        Element krajb = new Element("krajb", kbNs);
        krajb.setText(nalog.getKrajb());
        
        Element imed = new Element("imed", kbNs);
        imed.setText(nalog.getImed());
        
        Element ulicad = new Element("ulicad", kbNs);
        ulicad.setText(nalog.getUlicad()); 
        
        Element krajd = new Element("krajd", kbNs);
        krajd.setText(nalog.getKrajd());    
                
        Element sklicd = new Element("sklicd", kbNs);
        sklicd.setText(nalog.getSklicd());
        
        Element namen = new Element("namen", kbNs);
        namen.setText(nalog.getNamen());
        
        Element sifra = new Element("sifra", kbNs);
        sifra.setText(nalog.getSifra());
        
        Element datumprometa = new Element("datumprometa", kbNs);
        datumprometa.setText(nalog.getDatumprometa()); 
      
        nalogElement.addContent(idNA);
        nalogElement.addContent(idDok);
        nalogElement.addContent(tipdok);
        nalogElement.addContent(idPrejem);
        nalogElement.addContent(datumvalute);
        nalogElement.addContent(racunb);
        nalogElement.addContent(racund);
        nalogElement.addContent(krajb);
        nalogElement.addContent(krajd);
        nalogElement.addContent(namen);
        nalogElement.addContent(sklicb);
        nalogElement.addContent(sklicd);
        nalogElement.addContent(ulicab);
        nalogElement.addContent(ulicad);
        nalogElement.addContent(datumprometa);
        nalogElement.addContent(sifra);     
        nalogElement.addContent(imeb);
        nalogElement.addContent(imed);
        nalogElement.addContent(iznos); 
        
        root.addContent(nalogElement);
                
        Document doc = new Document(root);
        
      //Ovde radimo LOG response-a
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(doc);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        return doc.getRootElement();
    }
    
    
}