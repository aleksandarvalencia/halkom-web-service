package wshalkom.sei;

/**
 * @author misa.stefanovic
 */

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
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
public class InsertEndpoint  {

   
	private XPathFactory xpfac;
	private Namespace namespace;
	XPathExpression<Element> idNAExpression;
	XPathExpression<Element> tipdokExpression;
	XPathExpression<Element> idDokExpression;
	XPathExpression<Element> idPrejemExpression;
	XPathExpression<Element> datumvaluteExpression;
	XPathExpression<Element> modbExpression;
	XPathExpression<Element> racunbExpression;
	XPathExpression<Element> sklicbExpression;
	XPathExpression<Element> racundExpression;
	XPathExpression<Element> iznosExpression;
	XPathExpression<Element> imebExpression;
	XPathExpression<Element> ulicabExpression;
	XPathExpression<Element> krajbExpression;
	XPathExpression<Element> moddExpression;
	XPathExpression<Element> imedExpression;
	XPathExpression<Element> ulicadExpression;
	XPathExpression<Element> krajdExpression;
	XPathExpression<Element> sklicdExpression;
	XPathExpression<Element> namenExpression;
	XPathExpression<Element> sifraExpression;
	XPathExpression<Element> datumprometaExpression;
    
    
    
    
    private final HalkomManager telimManager;
    
    private static final String NAMESPACE_PREFIX = "ns";
    private static final String  NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
    
    
   @Autowired
    public InsertEndpoint(HalkomManager telimManager) throws JDOMException {                   
        
        this.telimManager = telimManager;
        
        namespace = Namespace.getNamespace("ns", "http://ws.wshalkom.javakurs.com/xsd");
        xpfac = XPathFactory.instance();
        
      
        
        
    }
    
    /**
     * Main entry method:
     * 
     */
   @PayloadRoot(namespace = NAMESPACE_URI, localPart = "insertRequest")  
   @ResponsePayload
    public Element handlePingDbRequest(@RequestPayload Element soapRequest) throws ResponseErrorException, Exception {           
    	

        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(soapRequest);
        
        System.out.println(message);
        
        
        //1. read received data into object...
        NalogRequest contactRequest = bindToObject(soapRequest);

        //2. validate input data...
        validateRequest(contactRequest);
    	
        //3. invoke business logic
        
        Nalog contact = new Nalog(contactRequest);
        
        // Da vidimo šta je pročitano
    	System.out.println("imeb: "+contact.getImeb());
    	System.out.println("imed: "+contact.getImed());
    	System.out.println("iznos: "+contact.getIznos());
    	System.out.println("idPrejem: "+contact.getIdPrejem());
    	System.out.println("racunb: "+contact.getRacunb());
    	System.out.println("racund: "+contact.getRacund());
       
        
        try {
        	
        	System.out.println("Ulazim u Insert");
            telimManager.insert(contact); 
            
            // Treba odmah da nađemo i ID
            
            NalogCriteria nalogCriteria = new NalogCriteria();
            nalogCriteria.setImeb(contact.getImeb());
            nalogCriteria.setImed(contact.getImed());
            nalogCriteria.setIznos(contact.getIznos());
            nalogCriteria.setRacund(contact.getRacund());
            nalogCriteria.setRacunb(contact.getRacunb());
            nalogCriteria.setIdPrejem(contact.getIdPrejem());
            nalogCriteria.setDatumvalute(contact.getDatumvalute());
            //searchCriteria.setTypeId(contact.getTypeId());
            
            List<Nalog> list = telimManager.search(nalogCriteria);
            
            // Ovako smemo da radomo, jer smo stavilo order by ID desc
            contact = list.get(0);
            
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
        Element responseElement = createElement(contact);
    	return responseElement;
    }
    
    protected NalogRequest bindToObject(Element soapRequest) throws Exception {
    	    	

      	//XPathExpression<Element> idNAExpression = xpfac.compile("//ns:idNA", Filters.element(),null,namespace);
    	XPathExpression<Element> tipdokExpression = xpfac.compile("//ns:tipdok", Filters.element(),null,namespace);
    	XPathExpression<Element> idDokExpression = xpfac.compile("//ns:idDok", Filters.element(),null,namespace);
    	XPathExpression<Element> idPrejemExpression = xpfac.compile("//ns:idPrejem", Filters.element(),null,namespace);
    	XPathExpression<Element> datumvaluteExpression = xpfac.compile("//ns:datumvalute", Filters.element(),null,namespace);
    	XPathExpression<Element> modbExpression = xpfac.compile("//ns:modb", Filters.element(),null,namespace);
    	XPathExpression<Element> racunbExpression = xpfac.compile("//ns:racunb", Filters.element(),null,namespace);
      	XPathExpression<Element> sklicbExpression = xpfac.compile("//ns:sklicb", Filters.element(),null,namespace);
    	XPathExpression<Element> iznosExpression = xpfac.compile("//ns:iznos", Filters.element(),null,namespace);
    	XPathExpression<Element> imebExpression = xpfac.compile("//ns:imeb", Filters.element(),null,namespace);
    	XPathExpression<Element> ulicabExpression = xpfac.compile("//ns:ulicab", Filters.element(),null,namespace);
    	XPathExpression<Element> krajbExpression = xpfac.compile("//ns:krajb", Filters.element(),null,namespace);
    	XPathExpression<Element> moddExpression = xpfac.compile("//ns:modd", Filters.element(),null,namespace);
    	XPathExpression<Element> racundExpression = xpfac.compile("//ns:racund", Filters.element(),null,namespace);
    	
      	XPathExpression<Element> imedExpression = xpfac.compile("//ns:imed", Filters.element(),null,namespace);
    	XPathExpression<Element> ulicadExpression = xpfac.compile("//ns:ulicad", Filters.element(),null,namespace);
    	XPathExpression<Element> krajdExpression = xpfac.compile("//ns:krajd", Filters.element(),null,namespace);
    	XPathExpression<Element> sklicdExpression = xpfac.compile("//ns:sklicd", Filters.element(),null,namespace);
    	XPathExpression<Element> namenExpression = xpfac.compile("//ns:namen", Filters.element(),null,namespace);
    	XPathExpression<Element> sifraExpression = xpfac.compile("//ns:sifra", Filters.element(),null,namespace);
    	XPathExpression<Element> datumprometaExpression = xpfac.compile("//ns:datumprometa", Filters.element(),null,namespace);
    	
    	Integer idNa = 0;//idNAExpression.valueOf(soapRequest);
    	String tipdok = tipdokExpression.evaluateFirst(soapRequest).getText();
    	String idDok = idDokExpression.evaluateFirst(soapRequest).getText();
    	String idPrejem = idPrejemExpression.evaluateFirst(soapRequest).getText();
    	String datumvalute = datumvaluteExpression.evaluateFirst(soapRequest).getText();
    	String racunb = racunbExpression.evaluateFirst(soapRequest).getText();
    	String modb = modbExpression.evaluateFirst(soapRequest).getText();
    	String sklicb = sklicbExpression.evaluateFirst(soapRequest).getText();
    	String iznos = iznosExpression.evaluateFirst(soapRequest).getText();
    	String imeb = imebExpression.evaluateFirst(soapRequest).getText();
    	String ulicab = ulicabExpression.evaluateFirst(soapRequest).getText();
    	String krajb = krajbExpression.evaluateFirst(soapRequest).getText();
    	String modd = moddExpression.evaluateFirst(soapRequest).getText();
    	String racund =racundExpression.evaluateFirst(soapRequest).getText();
    	
    	String sklicd = sklicdExpression.evaluateFirst(soapRequest).getText();
    	String imed = imedExpression.evaluateFirst(soapRequest).getText();
    	String ulicad = ulicadExpression.evaluateFirst(soapRequest).getText();
    	String krajd = krajdExpression.evaluateFirst(soapRequest).getText();
    	String namen =namenExpression.evaluateFirst(soapRequest).getText();
    	String sifra = sifraExpression.evaluateFirst(soapRequest).getText();
    	String datumprometa =datumprometaExpression.evaluateFirst(soapRequest).getText();
       
    	System.out.println("Nalog Request");
    	NalogRequest result = new NalogRequest(idNa,Integer.valueOf(tipdok),idDok,idPrejem,datumvalute,racunb,modb,sklicb,iznos,
    			  imeb,ulicab,krajb,modd,racund,sklicd,imed,ulicad,krajd,namen,sifra,datumprometa);
        return result;
        
    }
    
    protected void validateRequest(NalogRequest nalogRequest) throws ValidationFailureException {
    	try {
    		@SuppressWarnings("unused")
			Integer typeId = new Integer(nalogRequest.getIdNA());
    	} catch (Exception e) {
    		throw new ValidationFailureException("Neodgovarajući podatak za type ID - mora biti Integer");   
    	}
    	
        
    }
    
    
    protected Element createElement(Nalog nalog) throws IOException {
        
        Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

        Element root = new Element("inputResponse", kbNs);
        
        Element idElement = new Element("idNA", kbNs);
        idElement.setText(nalog.getIdNA().toString());               
                
        
        root.addContent(idElement);
                
        Document doc = new Document(root);
        
      //Ovde radimo LOG response-a
        XMLOutputter xmlOutputter = new XMLOutputter();
        String message = xmlOutputter.outputString(doc);
        
        // U pravim aplikacijama trebalo bi neki LOG4J
        System.out.println(message);
        
        return doc.getRootElement();
    }
    
    
}