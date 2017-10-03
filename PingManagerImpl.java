package halkom.model.managers.impl;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;

import halkom.model.managers.PingManager;
import halkom.model.ws.WebServiceClient;

public class PingManagerImpl implements PingManager {
	
	private static final String NAMESPACE_PREFIX = "ns";
    private static final String NAMESPACE_URI = "http://ws.wshalkom.javakurs.com/xsd";


	
	
	public String ping(String text) {
		
		 	Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

	        Element root = new Element("pingRequest", kbNs);
	        
	        Element pingInputElement = new Element("pingInput", kbNs);
	        pingInputElement.setText(text);               
	                
	        
	        root.addContent(pingInputElement);
	                
	        Document doc = new Document(root);        

	        // Pripremamo XML
	        XMLOutputter xmlOutputter = new XMLOutputter();
	        String message = xmlOutputter.outputString(doc);
	        
	        System.out.println("---------- request WS -----------");
	        System.out.println(message);
	        System.out.println("---------- request WS end-----------");
		
		String result = WebServiceClient.simpleSendAndReceive(message);
		
		return result;
	}
	
	public String pingDb(String text) {
		
		 Namespace kbNs = Namespace.getNamespace(NAMESPACE_PREFIX, NAMESPACE_URI);

	        Element root = new Element("pingDbRequest", kbNs);
	        
	        Element pingInputElement = new Element("pingDbInput", kbNs);
	        pingInputElement.setText(text);               
	                
	        
	        root.addContent(pingInputElement);
	                
	        Document doc = new Document(root);        

	        // Pripremamo XML
	        XMLOutputter xmlOutputter = new XMLOutputter();
	        String message = xmlOutputter.outputString(doc);
	        
	        System.out.println("---------- request WS DB -----------");
	        System.out.println(message);
	        System.out.println("---------- request WS DB end-----------");
		
		String result = WebServiceClient.simpleSendAndReceive(message);
		
		return result;
	}

}
