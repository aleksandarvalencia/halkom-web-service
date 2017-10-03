package wshalkom.sei;

/**
 * @author misa.stefanovic
 */

import java.io.IOException;
import java.util.List;

//import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.output.XMLOutputter;
//import org.jdom2.xpath.XPath;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
//XPathFactory XPath Expression XPath Builder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import wshalkom.exception.DataAccessException;
import wshalkom.exception.ResponseErrorException;
import wshalkom.model.managers.HalkomManager;
import wshalkom.model.requests.NalogRequest;
import wshalkom.model.vo.NalogCriteria;
import wshalkom.model.vo.pojo.Nalog;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.oxm.ValidationFailureException;


@Endpoint
public class SearchEndpoint  {

    Namespace namespace;
    XPathFactory xpfac;
    XPathExpression<Element> idPrejemExpression;
    XPathExpression<Element> datumvaluteExpression;
    XPathExpression<Element> racunbExpression;
    XPathExpression<Element> racundExpression;
    XPathExpression<Element> iznosExpression;
    XPathExpression<Element> imebExpression;
    XPathExpression<Element> imedExpression;

    private final HalkomManager telimManager;
    
    private static final String NAMESPACE_PREFIX = "ns";
    private static final String  NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
    
    
   @Autowired
    public SearchEndpoint(HalkomManager telimManager) throws JDOMException {                   
        
        this.telimManager = telimManager;

        /*XPathFactory xpfac = XPathFactory.instance();
		XPathExpression xp = xpfac.compile("//target/@name", Filters.attribute());
		for (Attribute att : xp.evaluate(antbuild)) {
		  System.out.println("We have target " + att.getValue());
		}*/
        //idPrejemExpression = XPathFactory.newInstance("//ns:idPrejem");
        //idPrejemExpression.addNamespace(namespace);     
        
        namespace = Namespace.getNamespace("ns", "http://ws.wshalkom.javakurs.com/xsd");
        xpfac = XPathFactory.instance();

		
		
		
//        racunbExpression = XPathFactory.newInstance("//ns:racunb");
//        racunbExpression.addNamespace(namespace);    

       // iznosExpression = XPathFactory.newInstance("//ns:iznos");
       // iznosExpression.addNamespace(namespace);      
        
//        imebExpression = XPathFactory.newInstance("//ns:imeb");
//        imebExpression.addNamespace(namespace);      
//        
//        racundExpression = XPathFactory.newInstance("//ns:racund");
//        racundExpression.addNamespace(namespace);  
//
//        imedExpression = XPathFactory.newInstance("//ns:imed");
//        imedExpression.addNamespace(namespace);  
//        
//        datumvaluteExpression = XPathFactory.newInstance("//ns:datumvalute");
//        datumvaluteExpression.addNamespace(namespace);  
        
        System.out.println("Konstruktor");
               
        
    }
    
    /**
     * Main entry method:
     * 
     */
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchRequest")  
   @ResponsePayload
    public Element handlePingDbRequest(@RequestPayload Element soapRequest) throws ResponseErrorException, Exception {     
	   
	   System.out.println("soapRequest");
    	
      //Ovde radimo LOG requesta
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(soapRequest);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        
        //1. read received data into object...
        NalogRequest contactRequest = bindToObject(soapRequest);

        //2. validate input data...
        //validateRequest(contactRequest);
    	
        //3. invoke business logic
        
        NalogCriteria searchCriteria = new NalogCriteria();
        
        searchCriteria.setImeb(contactRequest.getImeb());
        searchCriteria.setImed(contactRequest.getImed());
        searchCriteria.setRacund(contactRequest.getRacund());
        searchCriteria.setRacunb(contactRequest.getRacunb());
        searchCriteria.setIznos(contactRequest.getIznos());
        searchCriteria.setIdPrejem(contactRequest.getIdPrejem());
        searchCriteria.setDatumvalute(contactRequest.getDatumvalute());
//        if(contactRequest.getTypeId() != null && !contactRequest.getTypeId().equals("")) { 
//        	 searchCriteria.setTypeId(new Integer(contactRequest.getTypeId()));
//        }
//       
        
        List<Nalog> contacts = null;
        
        try {
        	 System.out.println("Ulazi u search");
           contacts = telimManager.search(searchCriteria); 
                       
            
        } catch (DataAccessResourceFailureException e1) {
        	// U praksi ovde bi bio LOG
    		e1.printStackTrace();
            throw new DataAccessException("Greška u pristupu podacima!"); 
            
        } catch (BadSqlGrammarException e2) {
        	// U praksi ovde bi bio LOG
        		e2.printStackTrace();
                throw new DataAccessException("Greška u SQL-u!");                
                
        } catch (Exception e) {
        	// U praksi ovde bi bio LOG
        	e.printStackTrace();
            throw new ResponseErrorException("Greška u odzivu!"); 
        }
        
                
        
        //4. create response Element
        Element responseElement = createElement(contacts);
    	return responseElement;
    }
   
   protected NalogRequest bindToObject(Element soapRequest) throws Exception {
   	
	
	XPathExpression<Element> idPrejemExpression = xpfac.compile("//ns:idPrejem", Filters.element(),null,namespace);
	XPathExpression<Element> racunbExpression = xpfac.compile("//ns:racunb", Filters.element(),null,namespace);
	XPathExpression<Element> iznosExpression = xpfac.compile("//ns:iznos", Filters.element(),null,namespace);
	XPathExpression<Element> imebExpression = xpfac.compile("//ns:imeb", Filters.element(),null,namespace);
	XPathExpression<Element> racundExpression = xpfac.compile("//ns:racund", Filters.element(),null,namespace);
	XPathExpression<Element> imedExpression = xpfac.compile("//ns:imed", Filters.element(),null,namespace);
	XPathExpression<Element> datumvaluteExpression = xpfac.compile("//ns:datumvalute", Filters.element(),null,namespace);
	   
	Integer idNa = null;
   	Integer tipdok = null;
   	String idDok = null;//idDokExpression.valueOf(soapRequest);
   	String idPrejem = idPrejemExpression.evaluateFirst(soapRequest).getText();
   	String datumvalute = datumvaluteExpression.evaluateFirst(soapRequest).getText();
   	String racunb = racunbExpression.evaluateFirst(soapRequest).getText();
   	String modb = null;//modbExpression.valueOf(soapRequest);
   	String sklicb = null;//sklicbExpression.valueOf(soapRequest);
   	String iznos = iznosExpression.evaluateFirst(soapRequest).getText();
   	String imeb = imebExpression.evaluateFirst(soapRequest).getText();
   	String ulicab = null;//ulicabExpression.valueOf(soapRequest);
   	String krajb = null;//krajbExpression.valueOf(soapRequest);
   	String modd =null;// moddExpression.valueOf(soapRequest);
   	String racund =racundExpression.evaluateFirst(soapRequest).getText();
   	String sklicd = null;//sklicdExpression.valueOf(soapRequest);
   	String imed = imedExpression.evaluateFirst(soapRequest).getText();
   	String ulicad = null;//ulicadExpression.valueOf(soapRequest);
   	String krajd = null;//krajdExpression.valueOf(soapRequest);
   	String namen =null;//namenExpression.valueOf(soapRequest);
   	String sifra = null;//sifraExpression.valueOf(soapRequest);
   	String datumprometa =null;//datumprometaExpression.valueOf(soapRequest);
       
       
      
	   	NalogRequest result = new NalogRequest(idNa,tipdok,idDok,idPrejem,datumvalute,racunb,modb,sklicb,iznos,
	  			  imeb,ulicab,krajb,modd,racund,sklicd,imed,ulicad,krajd,namen,sifra,datumprometa);
       return result;
       
   }
    
    
    
   protected void validateRequest(NalogRequest contactRequest) throws ValidationFailureException {
      	
   	if(contactRequest.getIznos() == null || contactRequest.getIznos().equals("")) {
   		return;
   	}
	   
	   try {
   		@SuppressWarnings("unused")
			Integer tipdok = new Integer(contactRequest.getTipdok());
   	} catch (Exception e) {
   		throw new ValidationFailureException("Neodgovarajući podatak za type ID - mora biti Integer");   
   	}
   	
       
   }
    
    
    protected Element createElement(List<Nalog> nalozi) throws IOException {
        
        Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

        Element root = new Element("searchResponse", kbNs);
        
        Element contactListElement = new Element("nalogList", kbNs);
        
        for (int i = 0; i < nalozi.size(); i++) {
        	Nalog nalog = nalozi.get(i);
        	

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
            
            contactListElement.addContent(nalogElement);
        }
        
        root.addContent(contactListElement);        
        Document doc = new Document(root);
        
      //Ovde radimo LOG response-a
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(doc);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        return doc.getRootElement();
    }
    
    
}