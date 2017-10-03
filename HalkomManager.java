package wshalkom.model.managers;

import java.util.List;

import wshalkom.exception.NotFoundException;
import wshalkom.model.vo.NalogCriteria;
import wshalkom.model.vo.pojo.Nalog;
import wshalkom.model.vo.pojo.PingDb;


public interface HalkomManager {

	void insert(Nalog contact);
	List<Nalog> search(NalogCriteria searchCriteria);
	void update(Nalog contact);
//	List<ContactType> getAllTypes();
//	List<ContactType> getAllTypesForSelectList();
	Nalog findById (Integer id) throws NotFoundException;
	PingDb pingDb(); 

}
