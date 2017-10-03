package wshalkom.model.dao;

import java.util.List;

import wshalkom.exception.NotFoundException;
import wshalkom.model.vo.NalogCriteria;
import wshalkom.model.vo.pojo.Nalog;
import wshalkom.model.vo.pojo.PingDb;


public interface HalkomDao {

	void insert(Nalog contact);
	List<Nalog> search(NalogCriteria searchCriteria);
	void update(Nalog contact);
//	List<ContactType> getAllTypes();
	Nalog findById (Integer id) throws NotFoundException;
	PingDb pingDb();

}
