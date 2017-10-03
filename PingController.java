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

public class PingController implements Controller {

    private PingManager pingManager;
   

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new Date()).toString();
       

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        
        
        String responseWs = pingManager.ping("Pingue");
        
        System.out.println("---------- response WS -----------");
        System.out.println(responseWs);
        System.out.println("---------- response WS end -----------");
        
        String pingResult = SoapParser.parseSimpleTag(responseWs, "<ns:pingOutput>");
        
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