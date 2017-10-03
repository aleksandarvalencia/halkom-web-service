package halkom.web;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

public class IntroController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new Date()).toString();
       

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);               

        return new ModelAndView("intro", "model", myModel);
    }


}