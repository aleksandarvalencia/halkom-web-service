package halkom.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.SimpleFormController;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;

import halkom.model.managers.HalkomManager;
import halkom.model.vo.SearchCriteria;
import halkom.model.vo.pojo.Nalog;
import halkom.web.forms.UserForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NewUserController {    

	@Autowired
    private HalkomManager halkomManager;
    
    @RequestMapping(value = "/newUser.htm", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("userForm") Nalog nalog, Model model)throws ServletException {

    	 String imeb = nalog.getImeb();
         String imed = nalog.getImed();
         String iznos = nalog.getIznos();
         Integer tipdok = nalog.getTipdok();
         String racunb = nalog.getRacunb();
         String idPrejem = nalog.getIdPrejem();
         String datumvalute = nalog.getDatumvalute();      
         String datumprometa =nalog.getDatumprometa();
         String modb = nalog.getModb();
         String modd = nalog.getModd();
         Integer idNA = nalog.getIdNA();
         String idDok = nalog.getIdDok();
         String racund = nalog.getRacund();
         String krajb = nalog.getKrajb();
         String krajd = nalog.getKrajd();
         String namen = nalog.getNamen();
         String sifra = nalog.getSifra();
         String sklicb = nalog.getSklicb();
         String sklicd = nalog.getSklicd();
         String ulicab = nalog.getUlicab();
         String ulicad = nalog.getUlicad();
        
        Nalog contact = new Nalog();
        contact.setImeb(imeb);
        contact.setImed(imed);
        contact.setIznos(iznos);
        contact.setRacund(racund);
        contact.setRacunb(racunb);
        contact.setIdPrejem(idPrejem);
        contact.setDatumvalute(datumvalute);
        contact.setDatumprometa(datumprometa);
        contact.setModb(modb);
        contact.setModd(modd);
        contact.setIdDok(idDok);
        contact.setIdNA(idNA);
        contact.setKrajb(krajb);
        contact.setKrajd(krajd);
        contact.setNamen(namen);
        contact.setSifra(sifra);
        contact.setSklicb(sklicb);
        contact.setSklicd(sklicd);
        contact.setTipdok(tipdok);
        contact.setUlicab(ulicab);
        contact.setUlicad(ulicad);
        
       halkomManager.insert(contact);   
       
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
       
       //RedirectView rView = new RedirectView();
       
       //rView.setUrl(rView.getUrl()+"?insertSucces=1");
       
//       model.addAttribute("nalogList", nalozi);
//         
//       return "searchresult";
        //return new ModelAndView(new RedirectView(getSuccessView()));
       
    }

    @RequestMapping(value = "/newUser.htm", method = RequestMethod.GET)
    public String formBackingObject(HttpServletRequest request, Model model) throws ServletException {
    	UserForm userForm = new UserForm();
    	userForm.setImeb("");
    	userForm.setImed("");
    	userForm.setIznos("");
    	userForm.setRacund("");
    	userForm.setRacunb("");
        userForm.setIdPrejem("");
        userForm.setDatumvalute("");
        userForm.setDatumprometa("");
        userForm.setModb("");
        userForm.setModd("");
        userForm.setIdDok("");
        userForm.setIdNA(0);
        userForm.setKrajb("");
        userForm.setKrajd("");
        userForm.setNamen("");
        userForm.setSifra("");
        userForm.setSklicb("");
        userForm.setSklicd("");
        userForm.setTipdok(0);
        userForm.setUlicab("");
        userForm.setUlicad("");

        
        model.addAttribute("userForm",userForm);
        return "userform";
    }

	public HalkomManager getHalkomManager() {
		return halkomManager;
	}

	public void setHalkomManager(HalkomManager halkomManager) {
		this.halkomManager = halkomManager;
	}

	

}
