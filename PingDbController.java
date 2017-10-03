package halkom.web;

import org.springframework.web.servlet.mvc.Controller;

import halkom.model.managers.PingManager;
import halkom.model.utils.SoapParser;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

public class PingDbController implements Controller {

    private PingManager pingManager;
   

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new Date()).toString();
       

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        
        
        String responseWs = pingManager.pingDb("Pingue");
        
        System.out.println("---------- response WS DB-----------");
        System.out.println(responseWs);
        System.out.println("---------- response WS DB end -----------");
        
        String pingResult = SoapParser.parseSimpleTag(responseWs, "<ns:pingDbOutput>");
        
        System.out.println("  pingResult: " + pingResult);
        
        myModel.put("pingResult", pingResult);

        return new ModelAndView("ping", "model", myModel);
    }


	public PingManager getPingManager() {
		return pingManager;
	}


	public void setPingManager(PingManager pingManager) {
		this.pingManager = pingManager;
	}
    
    



}