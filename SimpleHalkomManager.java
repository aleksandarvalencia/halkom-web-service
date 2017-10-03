package halkom.model.managers.impl;

import java.util.List;

import halkom.model.managers.HalkomManager;
import halkom.model.utils.ClientParser;
import halkom.model.utils.ClientSoapPreparer;
import halkom.model.vo.SearchCriteria;
import halkom.model.vo.pojo.Nalog;
import halkom.model.vo.pojo.ContactType;
import halkom.model.ws.WebServiceClient;


public class SimpleHalkomManager implements HalkomManager {
	
	

	public void insert(Nalog contact){
		
		String message = ClientSoapPreparer.prepareInsertSoap(contact);
        
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        Integer id = ClientParser.parseInputResponse(result);
        System.out.println("Dobijen je ID: "+id);
		
	}
	
	public List<Nalog> search(SearchCriteria searchCriteria) {
		String message = ClientSoapPreparer.prepareSearchSoap(searchCriteria);
        
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        List<Nalog> resultList = ClientParser.parseSearchResponse(result);
        return resultList;
	}
	
	public void update(Nalog contact){
		
		System.out.println("Usao u update");
		String message = ClientSoapPreparer.prepareUpdateSoap(contact);
		System.out.println("Izasao update");
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        String resultMessage = ClientParser.parseUpdateResponse(result);
        System.out.println("Dobijen je rezultat: "+resultMessage);
	}
	
	public List<ContactType> getAllTypes() {
		String message = ClientSoapPreparer.prepareGetAllTypesSoap(new Integer(0));
        
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        List<ContactType> resultList = ClientParser.parseGetAllTypesResponse(result);
        return resultList;
	}
	
	public List<ContactType> getAllTypesForSelectList() {
		String message = ClientSoapPreparer.prepareGetAllTypesSoap(new Integer(1));
        
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        List<ContactType> resultList = ClientParser.parseGetAllTypesResponse(result);
        return resultList;
	}
	
	public Nalog findById (int id) {
		
		System.out.println("prepareFindByIdSoap");
		String message = ClientSoapPreparer.prepareFindByIdSoap(new Integer(id));
        
        System.out.println("---------- request WS -----------");
        System.out.println(message);
        System.out.println("---------- request WS end-----------");
	
        String result = WebServiceClient.simpleSendAndReceive(message);
        Nalog resultContact = ClientParser.parseFindByIdResponse(result);
        return resultContact;
	}
	
	

}
