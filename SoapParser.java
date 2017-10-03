package halkom.model.utils;

import java.util.ArrayList;
import java.util.List;

public class SoapParser {
	
	public static String parseSimpleTag(String soap, String tag) {
		int startPosition = soap.indexOf(tag);
		int endPosition = soap.indexOf(findMatcEndSimpleTag(tag));
		
		if(startPosition < 0 || endPosition < 0) return "";
		
		String value = soap.substring(startPosition+tag.length(), endPosition);
		return value;
	}
	
	public static List<String> parseSimpleTagList(String soap, String tag) {
		List<String> result = new ArrayList<String>();
		String workString = soap;
		int startPosition = 0;
		while (startPosition >= 0) {
			startPosition = workString.indexOf(tag);
			int endPosition = workString.indexOf(findMatcEndSimpleTag(tag));
			
			if (startPosition >= 0) {
				String value = workString.substring(startPosition+tag.length(), endPosition);
				result.add(value);
			}
			workString = workString.substring(endPosition+1); // Da ne bi zadnji zatvarajuci tag u�ao
			
			
		}
		
		return result;
	}
	
	public static String findMatcEndSimpleTag(String tag) {
		
		String endTag = tag.substring(1);
		
		endTag = "</"+endTag;
		
		return endTag;
		
	}

}
