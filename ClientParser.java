package halkom.model.utils;

import java.util.ArrayList;
import java.util.List;

import halkom.model.vo.pojo.Nalog;
import halkom.model.vo.pojo.ContactType;

public class ClientParser {
	
	public static Integer parseInputResponse(String soap){
		
		Integer resId = -999;
		
		try {
			String idString = SoapParser.parseSimpleTag(soap, "<ns:idNA>");
			resId = new Integer(idString);
			
		} catch(Exception e){
			System.out.println("Do�lo je do gre�ke u parsiranju");
			
		}
		
		return resId;
		
	}
	
	public static String parseUpdateResponse(String soap){
		
		String resString = "";
		
		try {
			resString = SoapParser.parseSimpleTag(soap, "<ns:isOk>");
			
			
		} catch(Exception e){
			System.out.println("Do�lo je do gre�ke u parsiranju");
			
		}
		
		return resString;
		
	}
	
	public static Nalog parseFindByIdResponse(String soap){
		
		Nalog contact = null;
		
		
		
		try {
			String idString = SoapParser.parseSimpleTag(soap, "<ns:idNA>");
			String tipdok = SoapParser.parseSimpleTag(soap, "<ns:tipdok>");
			String idDok = SoapParser.parseSimpleTag(soap, "<ns:idDok>");
			String idPrejem = SoapParser.parseSimpleTag(soap, "<ns:idPrejem>");
			String datumvalute = SoapParser.parseSimpleTag(soap, "<ns:datumvalute>");
			String racunb = SoapParser.parseSimpleTag(soap, "<ns:racunb>");
			String modb = SoapParser.parseSimpleTag(soap, "<ns:modb>");
			String modd = SoapParser.parseSimpleTag(soap, "<ns:modd>");
			String sklicb = SoapParser.parseSimpleTag(soap, "<ns:sklicb>");
			String racund = SoapParser.parseSimpleTag(soap, "<ns:racund>");
			String iznos = SoapParser.parseSimpleTag(soap, "<ns:iznos>");
			String imeb = SoapParser.parseSimpleTag(soap, "<ns:imeb>");
			String ulicab = SoapParser.parseSimpleTag(soap, "<ns:ulicab>");
			String krajb = SoapParser.parseSimpleTag(soap, "<ns:krajb>");
			String imed = SoapParser.parseSimpleTag(soap, "<ns:imed>");
			String ulicad = SoapParser.parseSimpleTag(soap, "<ns:ulicad>");
			String krajd = SoapParser.parseSimpleTag(soap, "<ns:krajd>");
			String sklicd = SoapParser.parseSimpleTag(soap, "<ns:sklicd>");
			String namen = SoapParser.parseSimpleTag(soap, "<ns:namen>");
			String sifra = SoapParser.parseSimpleTag(soap, "<ns:sifra>");
			String datumprometa = SoapParser.parseSimpleTag(soap, "<ns:datumprometa>");
			
			Integer id = new Integer(idString);
			Integer tipdokk = new Integer(tipdok);
			
			contact = new Nalog();
			contact.setIdNA(id);
			contact.setTipdok(tipdokk);
			contact.setIdDok(idDok);
			contact.setIdPrejem(idPrejem);
			contact.setDatumvalute(datumvalute);
			contact.setRacunb(racunb);
			contact.setRacund(racund);
			contact.setIznos(iznos);
			contact.setModb(modb);
			contact.setModd(modd);
			contact.setSklicb(sklicb);
			contact.setSklicd(sklicd);
			contact.setImeb(imeb);
			contact.setImed(imed);
			contact.setUlicab(ulicab);
			contact.setUlicad(ulicad);
			contact.setKrajd(krajd);
			contact.setKrajb(krajb);
			contact.setRacunb(racunb);
			contact.setRacund(racund);
			contact.setIznos(iznos);
			contact.setNamen(namen);
			contact.setSifra(sifra);
			contact.setDatumprometa(datumprometa);
			
			
			
		} catch(Exception e){
			System.out.println("Do�lo je do gre�ke u parsiranju");
			
		}
		
		return contact;
		
	}
	
	public static List<Nalog> parseSearchResponse(String soap){
		
		List<Nalog> contactList = null;
		
		try {
			List<String> tagList = SoapParser.parseSimpleTagList(soap, "<ns:nalog>");
			
			contactList = new ArrayList<Nalog>();
			
			for (int i = 0; i < tagList.size(); i++) {
				String tag = tagList.get(i);
				
				String idString = SoapParser.parseSimpleTag(tag, "<ns:idNA>");
				String tipdok = SoapParser.parseSimpleTag(tag, "<ns:tipdok>");
				String idDok = SoapParser.parseSimpleTag(tag, "<ns:idDok>");
				String idPrejem = SoapParser.parseSimpleTag(tag, "<ns:idPrejem>");
				String datumvalute = SoapParser.parseSimpleTag(tag, "<ns:datumvalute>");
				String racunb = SoapParser.parseSimpleTag(tag, "<ns:racunb>");
				String modb = SoapParser.parseSimpleTag(tag, "<ns:modb>");
				String modd = SoapParser.parseSimpleTag(tag, "<ns:modd>");
				String sklicb = SoapParser.parseSimpleTag(tag, "<ns:sklicb>");
				String racund = SoapParser.parseSimpleTag(tag, "<ns:racund>");
				String iznos = SoapParser.parseSimpleTag(tag, "<ns:iznos>");
				String imeb = SoapParser.parseSimpleTag(tag, "<ns:imeb>");
				String ulicab = SoapParser.parseSimpleTag(tag, "<ns:ulicab>");
				String krajb = SoapParser.parseSimpleTag(tag, "<ns:krajb>");
				String imed = SoapParser.parseSimpleTag(tag, "<ns:imed>");
				String ulicad = SoapParser.parseSimpleTag(tag, "<ns:ulicad>");
				String krajd = SoapParser.parseSimpleTag(tag, "<ns:krajd>");
				String sklicd = SoapParser.parseSimpleTag(tag, "<ns:sklicd>");
				String namen = SoapParser.parseSimpleTag(tag, "<ns:namen>");
				String sifra = SoapParser.parseSimpleTag(tag, "<ns:sifra>");
				String datumprometa = SoapParser.parseSimpleTag(tag, "<ns:datumprometa>");
				
				Integer id = new Integer(idString);
				Integer tipdokk = new Integer(tipdok);
				
				Nalog contact = new Nalog();
				contact = new Nalog();
				contact.setIdNA(id);
				contact.setTipdok(tipdokk);
				contact.setIdDok(idDok);
				contact.setIdPrejem(idPrejem);
				contact.setDatumvalute(datumvalute);
				contact.setRacunb(racunb);
				contact.setRacund(racund);
				contact.setIznos(iznos);
				contact.setModb(modb);
				contact.setModd(modd);
				contact.setSklicb(sklicb);
				contact.setSklicd(sklicd);
				contact.setImeb(imeb);
				contact.setImed(imed);
				contact.setUlicab(ulicab);
				contact.setUlicad(ulicad);
				contact.setKrajd(krajd);
				contact.setKrajb(krajb);
				contact.setRacunb(racunb);
				contact.setRacund(racund);
				contact.setIznos(iznos);
				contact.setNamen(namen);
				contact.setSifra(sifra);
				contact.setDatumprometa(datumprometa);
				
				contactList.add(contact);
				
			}
			
			
			
		} catch(Exception e){
			System.out.println("Do�lo je do gre�ke u parsiranju");
			
		}
		
		return contactList;
		
	}
	
	public static List<ContactType> parseGetAllTypesResponse(String soap){
		
		List<ContactType> contactTypeList = null;
		
		try {
			List<String> tagList = SoapParser.parseSimpleTagList(soap, "<ns:contactType>");
			
			contactTypeList = new ArrayList<ContactType>();
			
			for (int i = 0; i < tagList.size(); i++) {
				String tag = tagList.get(i);
				
				String idString = SoapParser.parseSimpleTag(tag, "<ns:idNA>");
				String ime = SoapParser.parseSimpleTag(tag, "<ns:name>");	
								
				Integer id = null;
				
				if (!idString.equals("")) {
					id = new Integer(idString);
				} 
				
				ContactType contactType = new ContactType();
				contactType.setId(id);
				contactType.setName(ime);
				
				contactTypeList.add(contactType);
				
			}
			
			
			
		} catch(Exception e){
			System.out.println("Do�lo je do gre�ke u parsiranju");
			e.printStackTrace();
			
		}
		
		return contactTypeList;
		
	}

}
