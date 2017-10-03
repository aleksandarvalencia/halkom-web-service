package halkom.web;

import java.text.ParseException;
import java.util.HashMap;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.Controller;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import halkom.model.managers.HalkomManager;
import halkom.model.vo.SearchCriteria;
//import halkom.model.utils.DateTimeConversion;
import halkom.model.vo.pojo.Nalog;
import halkom.web.forms.UserForm;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;

@Controller
public class EditUserController {    

	@Autowired
    private HalkomManager halkomManager;
    
    @RequestMapping(value = "/editUser.htm/{pId}", method = RequestMethod.POST)
    public ModelAndView NalogUpdate( @PathVariable("pId") Integer pId, @ModelAttribute("userForm") Nalog nalog,Model model) throws RuntimeException, ParseException {
    	

    	System.out.println("Edit Nalog Post");
        String imeb = nalog.getImeb();
        String imed = nalog.getImed();
        String iznos = nalog.getIznos();
        String racunb = nalog.getRacunb();
        String idPrejem = nalog.getIdPrejem();
        String datumvalute = nalog.getDatumvalute();      
        String racund = nalog.getRacund();
       
        
//        Nalog contact = new Nalog();
//        contact.setIdNA(pId);
//        contact.setImeb(imeb);
//        contact.setImed(imed);
//        contact.setIznos(iznos);
//        contact.setRacund(racund);
//        contact.setRacunb(racunb);
//        contact.setIdPrejem(idPrejem);
//        contact.setDatumvalute(datumvalute);
//        contact.setDatumprometa(datumprometa);
//        contact.setModb(modb);
//        contact.setModd(modd);
//        contact.setIdDok(idDok);
//        contact.setIdNA(idNA);
//        contact.setKrajb(krajb);
//        contact.setKrajd(krajd);
//        contact.setNamen(namen);
//        contact.setSifra(sifra);
//        contact.setSklicb(sklicb);
//        contact.setSklicd(sklicd);
//        contact.setTipdok(tipdok);
//        contact.setUlicab(ulicab);
//        contact.setUlicad(ulicad);
    	

    	
        halkomManager.update(nalog);  
        
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setImeb(imeb);
        searchCriteria.setImed(imed);
        searchCriteria.setRacunb(racunb);
        searchCriteria.setRacund(racund);
        searchCriteria.setDatumvalute(datumvalute);
        searchCriteria.setIdPrejem(idPrejem);
        searchCriteria.setIznos(iznos);
        
        List<Nalog> nalozi= halkomManager.search(searchCriteria);
        
        Map<String, Object> myModel = new HashMap<String, Object>();
        
        myModel.put("nalogList", nalozi);
       
         return new ModelAndView("searchresult", "model", myModel);
//        System.out.println(nalozi.size());
//        model.addAttribute("nalogList",nalozi);
//        
//        return "searchresult";


       
    }
    @RequestMapping(value = "/editUser.htm/{pd}", method = RequestMethod.GET)
    public String NalogEdit(@PathVariable("pd") Integer pd, Model model) throws RuntimeException {
    	
    	System.out.println("Edit Nalog Get");
    	Nalog contact = halkomManager.findById(pd);
    	UserForm userForm = new UserForm();
    	userForm.setImeb(contact.getImeb());
    	userForm.setImed(contact.getImed());
    	userForm.setIznos(contact.getIznos());
    	userForm.setRacund(contact.getRacund());
    	userForm.setRacunb(contact.getRacunb());
        userForm.setIdPrejem(contact.getIdPrejem());
        userForm.setDatumvalute(contact.getDatumvalute());
        userForm.setDatumprometa(contact.getDatumprometa());
        userForm.setModb(contact.getModb());
        userForm.setModd(contact.getModd());
        userForm.setIdDok(contact.getIdDok());
        userForm.setIdNA(contact.getIdNA());
        userForm.setKrajb(contact.getKrajb());
        userForm.setKrajd(contact.getKrajd());
        userForm.setNamen(contact.getNamen());
        userForm.setSifra(contact.getSifra());
        userForm.setSklicb(contact.getSklicb());
        userForm.setSklicd(contact.getSklicd());
        userForm.setTipdok(contact.getTipdok());
        userForm.setUlicab(contact.getUlicab());
        userForm.setUlicad(contact.getUlicad());


        model.addAttribute("userForm", userForm);
        
        return "userform";
    }

	public HalkomManager getHalkomManager() {
		return halkomManager;
	}

	public void setHalkomManager(HalkomManager halkomManager) {
		this.halkomManager = halkomManager;
	}

	

}
