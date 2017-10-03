package wshalkom.model.dao;

import wshalkom.model.vo.NalogCriteria;

public class DaoUtils {
	
	public static NalogCriteria prepareSearch(NalogCriteria criteria) {
		String newImeb = "%"+criteria.getImeb()+"%";
		String newImed = "%"+criteria.getImed()+"%";
		String newIdPrejem = criteria.getIdPrejem()+"%";
		String newRacunb = "%"+criteria.getRacunb()+"%";
		String newRacund = "%"+criteria.getRacund()+"%";
		String newIznos = "%"+ criteria.getIznos()+"%";
		String newDatumvalute = criteria.getDatumvalute();
		criteria.setImeb(newImeb);
		criteria.setImed(newImed);
		criteria.setIdPrejem(newIdPrejem);
		criteria.setRacunb(newRacunb);
		criteria.setRacund(newRacund);
		criteria.setIznos(newIznos);
		criteria.setDatumvalute(newDatumvalute);
		return criteria;
	}

}
