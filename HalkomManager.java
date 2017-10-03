package halkom.model.managers;

import java.util.List;

import halkom.model.vo.SearchCriteria;
import halkom.model.vo.pojo.Nalog;
import halkom.model.vo.pojo.ContactType;


public interface HalkomManager {

	void insert(Nalog contact);
	List<Nalog> search(SearchCriteria searchCriteria);
	void update(Nalog contact);
	List<ContactType> getAllTypes();
	List<ContactType> getAllTypesForSelectList();
	Nalog findById (int id);

}
