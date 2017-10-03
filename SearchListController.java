package halkom.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import halkom.model.managers.HalkomManager;
import halkom.model.vo.SearchCriteria;
import halkom.model.vo.pojo.Nalog;
import halkom.web.forms.SearchForm;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
public class SearchListController {

	@Autowired
	private HalkomManager halkomManager;

//	public SearchListController()
//	{
//		setHalkomManager(halkomManager);
//	}
   
    
    @RequestMapping(value = "/searchlist.htm", method = RequestMethod.POST)
    public ModelAndView onSubmit(@ModelAttribute("searchForm") Nalog nalog) throws ServletException {

        String imeb = nalog.getImeb();
        String imed = nalog.getImed();
        String racunb = nalog.getRacunb();
        String racund = nalog.getRacund();
        String datumvalute = nalog.getDatumvalute();
        String idPrejem = nalog.getIdPrejem();
        String iznos = nalog.getIznos();
        
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setImeb(imeb);
        searchCriteria.setImed(imed);
        searchCriteria.setRacunb(racunb);
        searchCriteria.setRacund(racund);
        searchCriteria.setDatumvalute(datumvalute);
        searchCriteria.setIdPrejem(idPrejem);
        searchCriteria.setIznos(iznos);

        List<Nalog> nalogList = halkomManager.search(searchCriteria); 

        Map<String, Object> myModel = new HashMap<String, Object>();
        
        myModel.put("nalogList", nalogList);
       
        return new ModelAndView("searchresult", "model", myModel);
       
    }

    @RequestMapping(value = "/searchlist.htm", method = RequestMethod.GET)
    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
    	
    	String par = (String) request.getParameter("insertSucces");
    	
    	
    	SearchForm searchForm = new SearchForm();
    	searchForm.setImeb("");
    	searchForm.setImed("");
    	searchForm.setIznos("");
    	searchForm.setRacund("");
    	searchForm.setRacunb("");
    	searchForm.setIdPrejem("");
    	searchForm.setDatumvalute("");
    	searchForm.setDatumprometa("");
    	searchForm.setModb("");
    	searchForm.setModd("");
    	searchForm.setIdDok("");
    	searchForm.setIdNA(0);
    	searchForm.setKrajb("");
    	searchForm.setKrajd("");
        searchForm.setNamen("");
        searchForm.setSifra("");
        searchForm.setSklicb("");
        searchForm.setSklicd("");
        searchForm.setTipdok(0);
        searchForm.setUlicab("");
        searchForm.setUlicad("");
    	//searchForm.setTypes(telimManager.getAllTypesForSelectList()); 
    	//System.out.println("SIZE OF TYPES: "+searchForm.getTypes().size());
    	if(par != null ) searchForm.setInsertSucces("1");
        return searchForm;
    }

	public HalkomManager getHalkomManager() {
		return halkomManager;
	}

	public void setHalkomManager(HalkomManager halkomManager) {
		this.halkomManager = halkomManager;
	}

	

}
