package halkom.model.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import halkom.model.vo.SearchCriteria;
import halkom.model.vo.pojo.Nalog;

public class ClientSoapPreparer {

	private static final String NAMESPACE_PREFIX = "ns";
    private static final String NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";
    
    public static String prepareInsertSoap(Nalog contact) {
    	 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);
    	

    	    Element root = new Element("insertRequest", kbNs);
    	    
    	    Element tipdok = new Element("tipdok", kbNs);
    	    tipdok.setText(contact.getTipdok().toString()); 
    	    
    	    Element idDok = new Element("idDok", kbNs);
    	    idDok.setText(contact.getIdDok()); 
    	    
    	    Element idPrejem = new Element("idPrejem", kbNs);
    	    idPrejem.setText(contact.getIdPrejem()); 
    	    
    	    Element datumvalute = new Element("datumvalute", kbNs);
    	    datumvalute.setText(contact.getDatumvalute()); 
    	    
    	    Element racunb = new Element("racunb", kbNs);
    	    racunb.setText(contact.getRacunb()); 
    	    
    	    Element modb = new Element("modb", kbNs);
    	    modb.setText(contact.getModb()); 
    	    
    	    Element sklicb = new Element("sklicb", kbNs);
    	    sklicb.setText(contact.getSklicb()); 
    	    
    	    Element racund = new Element("racund", kbNs);
    	    racund.setText(contact.getRacund()); 
    	    
    	    Element modd = new Element("modd", kbNs);
    	    modd.setText(contact.getModd()); 
    	    
    	    Element sklicd = new Element("sklicd", kbNs);
    	    sklicd.setText(contact.getSklicd()); 
    	    
    	    Element iznos = new Element("iznos", kbNs);
    	    iznos.setText(contact.getIznos()); 
    	    
    	    Element imeb = new Element("imeb", kbNs);
    	    imeb.setText(contact.getImeb()); 
    	    
    	    Element ulicab = new Element("ulicab", kbNs);
    	    ulicab.setText(contact.getUlicab()); 
    	    
    	    Element krajb = new Element("krajb", kbNs);
    	    krajb.setText(contact.getKrajb()); 
    	    
    	    Element imed = new Element("imed", kbNs);
    	    imed.setText(contact.getImed()); 
    	    
    	    Element ulicad = new Element("ulicad", kbNs);
    	    ulicad.setText(contact.getUlicad()); 
    	    
    	    Element krajd = new Element("krajd", kbNs);
    	    krajd.setText(contact.getKrajd()); 
    	    
    	    Element namen = new Element("namen", kbNs);
    	    namen.setText(contact.getNamen()); 
    	    
    	    Element sifra = new Element("sifra", kbNs);
    	    sifra.setText(contact.getSifra()); 
    	    
    	    Element datumprometa = new Element("datumprometa", kbNs);
    	    datumprometa.setText(contact.getDatumprometa()); 
    	    
    	    root.addContent(tipdok);
    	    root.addContent(idDok);
    	    root.addContent(idPrejem);
    	    root.addContent(datumvalute);
    	    root.addContent(racunb);
    	    root.addContent(modb);
    	    root.addContent(sklicb);
    	    root.addContent(racund);
    	    root.addContent(modd);
    	    root.addContent(sklicd);
    	    root.addContent(iznos);
    	    root.addContent(imeb);
    	    root.addContent(ulicab);
    	    root.addContent(krajb);
    	    root.addContent(imed);
    	    root.addContent(ulicad);
    	    root.addContent(krajd);
    	    root.addContent(namen);
    	    root.addContent(sifra);
    	    root.addContent(datumprometa);
    	    
    	  
    	            
    	    Document doc = new Document(root);        


    	    XMLOutputter xmlOutputter = new XMLOutputter();
    	    String message = xmlOutputter.outputString(doc);
    	    
    	    return message;
    }
    
    public static String prepareUpdateSoap(Nalog contact) {
   	 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

   	 	System.out.println("usao UpdateSoap");
   	    Element root = new Element("updateRequest", kbNs);
   	    
   	    Element idNA = new Element("idNA", kbNs);
   	    idNA.setText(contact.getIdNA().toString()); 
   	    
   	    Element tipdok = new Element("tipdok", kbNs);
	    tipdok.setText(contact.getTipdok().toString()); 
	    
	    Element idDok = new Element("idDok", kbNs);
	    idDok.setText(contact.getIdDok()); 
	    
	    Element idPrejem = new Element("idPrejem", kbNs);
	    idPrejem.setText(contact.getIdPrejem()); 
	    
	    Element datumvalute = new Element("datumvalute", kbNs);
	    datumvalute.setText(contact.getDatumvalute()); 
	    
	    Element racunb = new Element("racunb", kbNs);
	    racunb.setText(contact.getRacunb()); 
	    
	    Element modb = new Element("modb", kbNs);
	    modb.setText(contact.getModb()); 
	    
	    Element sklicb = new Element("sklicb", kbNs);
	    sklicb.setText(contact.getSklicb()); 
	    
	    Element racund = new Element("racund", kbNs);
	    racund.setText(contact.getRacund()); 
	    
	    Element modd = new Element("modd", kbNs);
	    modd.setText(contact.getModd()); 
	    
	    Element sklicd = new Element("sklicd", kbNs);
	    sklicd.setText(contact.getSklicd()); 
	    
	    Element iznos = new Element("iznos", kbNs);
	    iznos.setText(contact.getIznos()); 
	    
	    Element imeb = new Element("imeb", kbNs);
	    imeb.setText(contact.getImeb()); 
	    
	    Element ulicab = new Element("ulicab", kbNs);
	    ulicab.setText(contact.getUlicab()); 
	    
	    Element krajb = new Element("krajb", kbNs);
	    krajb.setText(contact.getKrajb()); 
	    
	    Element imed = new Element("imed", kbNs);
	    imed.setText(contact.getImed()); 
	    
	    Element ulicad = new Element("ulicad", kbNs);
	    ulicad.setText(contact.getUlicad()); 
	    
	    Element krajd = new Element("krajd", kbNs);
	    krajd.setText(contact.getKrajd()); 
	    
	    Element namen = new Element("namen", kbNs);
	    namen.setText(contact.getNamen()); 
	    
	    Element sifra = new Element("sifra", kbNs);
	    sifra.setText(contact.getSifra()); 
	    
	    Element datumprometa = new Element("datumprometa", kbNs);
	    datumprometa.setText(contact.getDatumprometa()); 
	    
	    System.out.println("izasao Element");
	    root.addContent(idNA);
	    root.addContent(tipdok);
	    root.addContent(idDok);
	    root.addContent(idPrejem);
	    root.addContent(datumvalute);
	    root.addContent(racunb);
	    root.addContent(modb);
	    root.addContent(sklicb);
	    root.addContent(racund);
	    root.addContent(modd);
	    root.addContent(sklicd);
	    root.addContent(iznos);
	    root.addContent(imeb);
	    root.addContent(ulicab);
	    root.addContent(krajb);
	    root.addContent(imed);
	    root.addContent(ulicad);
	    root.addContent(krajd);
	    root.addContent(namen);
	    root.addContent(sifra);
	    root.addContent(datumprometa);
	    
	    System.out.println("izasao addContent");    
	    
   	    Document doc = new Document(root);        

   	    XMLOutputter xmlOutputter = new XMLOutputter();
   	    String message = xmlOutputter.outputString(doc);
   	    
   	    return message;
   }
    
    public static String prepareFindByIdSoap(Integer id) {
      	 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);
      	 
      	    System.out.println("ClientSoapPreparer");    
      	   
      	    Element root = new Element("findByIdRequest", kbNs);
      	    
      	    Element idElement = new Element("idNA", kbNs);
   	    	idElement.setText(id.toString()); 
      	          	    
      	    root.addContent(idElement);
      	          	            
      	    Document doc = new Document(root);        

      	    XMLOutputter xmlOutputter = new XMLOutputter();
      	    String message = xmlOutputter.outputString(doc);
      	    
      	    return message;
      }
    
    public static String prepareGetAllTypesSoap(Integer purpose) {
     	 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

     	    Element root = new Element("getAllTypesRequest", kbNs);
     	    
     	    Element purposeElement = new Element("purpose", kbNs);
     	   purposeElement.setText(purpose.toString()); 
     	          	    
     	    root.addContent(purposeElement);
     	          	            
     	    Document doc = new Document(root);        

     	    // Pripremamo XML
     	    XMLOutputter xmlOutputter = new XMLOutputter();
     	    String message = xmlOutputter.outputString(doc);
     	    
     	    return message;
     }
    
    public static String prepareSearchSoap(SearchCriteria searchCriteria) {
   	 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

   	    Element root = new Element("searchRequest", kbNs);
   	    
   	    Element racunb = new Element("racunb", kbNs);
	    racunb.setText(searchCriteria.getRacunb()); 
   	    
	    Element racund = new Element("racund", kbNs);
	    racund.setText(searchCriteria.getRacund()); 
   	    
	    Element idPrejem = new Element("idPrejem", kbNs);
	    idPrejem.setText(searchCriteria.getIdPrejem()); 
	    
	    Element imed = new Element("imed", kbNs);
	    imed.setText(searchCriteria.getImed()); 
	    
	    Element imeb = new Element("imeb", kbNs);
	    imeb.setText(searchCriteria.getImeb()); 
	    
	    Element datumvalute = new Element("datumvalute", kbNs);
	    datumvalute.setText(searchCriteria.getDatumvalute()); 
	    
	    Element iznos = new Element("iznos", kbNs);
	    iznos.setText(searchCriteria.getIznos()); 
	    
	    
//   	    Element typeIdElement = new Element("typeId", kbNs);
//   	    if (searchCriteria.getTypeId() != null) {
//   	    	typeIdElement.setText(searchCriteria.getTypeId().toString()); 	
//   	    } else  {
//   	    	typeIdElement.setText(""); 	
//   	    }
   	    
   	       	    
   	    root.addContent(racunb);
   	    root.addContent(racund);
   	    root.addContent(idPrejem);   	   
   	    root.addContent(iznos);
   	    root.addContent(imed);
   	    root.addContent(imeb);
   	    root.addContent(datumvalute);
   	            
   	    Document doc = new Document(root);        

   	    // Pripremamo XML
   	    XMLOutputter xmlOutputter = new XMLOutputter();
   	    String message = xmlOutputter.outputString(doc);
   	    
   	    return message;
   }
    
   
}
