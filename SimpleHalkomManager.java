package wshalkom.model.managers.impl;

//import java.util.ArrayList;
import java.util.List;

import wshalkom.exception.NotFoundException;
import wshalkom.model.dao.HalkomDao;
import wshalkom.model.managers.HalkomManager;
import wshalkom.model.vo.NalogCriteria;
import wshalkom.model.vo.pojo.Nalog;
import wshalkom.model.vo.pojo.PingDb;


public class SimpleHalkomManager implements HalkomManager {
	
	private HalkomDao halkomDao;
	
	public PingDb pingDb()
    {
               
        return halkomDao.pingDb();        
         
    }

	public void insert(Nalog contact){
		halkomDao.insert(contact);
	}
	
	public List<Nalog> search(NalogCriteria searchCriteria) {
		return halkomDao.search(searchCriteria);
	}
	
	public void update(Nalog contact){
		halkomDao.update(contact);
	}
	
//	public List<ContactType> getAllTypes() {
//		return telimDao.getAllTypes();
//	}
	
//	public List<ContactType> getAllTypesForSelectList() {
//		List<ContactType> resList = new ArrayList<ContactType>();
//		ContactType blancElement = new ContactType();
//		resList.add(blancElement);
//		List<ContactType> addList = telimDao.getAllTypes();
//		for (int i = 0; i < addList.size(); i++){
//			resList.add(addList.get(i));
//		}
//		return resList;
//	}
	
	public Nalog findById (Integer id) throws NotFoundException {
		return halkomDao.findById (id);
	}
	
	public HalkomDao getHalkomDao() {
		return halkomDao;
	}
	
	public void setHalkomDao(HalkomDao halkomDao) {
		this.halkomDao = halkomDao;
	}
	
	

}
