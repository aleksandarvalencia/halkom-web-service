package wshalkom.model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import wshalkom.exception.NotFoundException;
import wshalkom.model.dao.DaoUtils;
import wshalkom.model.dao.HalkomDao;
import wshalkom.model.vo.NalogCriteria;
import wshalkom.model.vo.pojo.Nalog;
import wshalkom.model.vo.pojo.PingDb;

public class JdbcHalkomDao extends JdbcDaoSupport implements HalkomDao {

	private RowMapper<PingDb> pingDbMapper = new RowMapper<PingDb>()
    {
        public PingDb mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            PingDb pingDb = new PingDb();
            pingDb.setNumber(rs.getInt("number"));
            return pingDb;
        }
    };

	public PingDb pingDb()
	{
	final String query = "select count(*) as broj from tbl_NA";
	Object[] params = new Object[] {};
	List<PingDb> result = getJdbcTemplate().query(query, params, pingDbMapper);
	PingDb pingDb = (PingDb) result.get(0);
	return pingDb;
	}

    public void insert(Nalog nalog) {
    	try
    	{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Date datval=sdf.parse(nalog.getDatumvalute());
	    	Date datprom=sdf.parse(nalog.getDatumprometa());

    	Object[] params = new Object[] {nalog.getTipdok(), nalog.getIdDok(), nalog.getIdPrejem(),datval, 
    			nalog.getRacunb(), nalog.getModb(), nalog.getSklicb(),nalog.getRacund(),nalog.getModd(),nalog.getSklicd(),
    			nalog.getIznos(),nalog.getImeb(),nalog.getUlicab(),nalog.getKrajb(),nalog.getImed(),nalog.getUlicad(),nalog.getKrajd(),nalog.getNamen(),
    			nalog.getSifra(),datprom,0};
    	
    	
    	getJdbcTemplate().update(
            "insert into tbl_NA (TIPDOK,IDDOK,IDPREJEM,DATUMVALUTE,RACUNB,MODB,SKLICB,RACUND,MODD,SKLICD,ZNESEK,IMEB,ULICAB,KRAJB,IMED,ULICAD,KRAJD,NAMEN,SIFRA,DATUMPROMETA,STATUS) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",params);
           
    	}
    	catch(ParseException ex)
    	{
    		
    		
    	}
        
    }
    
    public List<Nalog> search(NalogCriteria searchCriteria) {
    	
    	List<Nalog> contacts = null;
    	try
    	{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Date datval=sdf.parse(searchCriteria.getDatumvalute());
	    	searchCriteria = DaoUtils.prepareSearch(searchCriteria);

    		Object[] params = new Object[] {searchCriteria.getImeb(), searchCriteria.getImed(), searchCriteria.getIdPrejem(),searchCriteria.getRacunb(),searchCriteria.getRacund(),datval};
    		 
    		contacts = getJdbcTemplate().query(
                     "select idNA,tipdok,idDok,idPrejem,datumvalute,modb,racunb,sklicb,racund,znesek iznos,imeb,ulicab,krajb,modd,imed,ulicad,krajd,sklicd,namen,sifra,datumprometa from tbl_NA where imeb like ? and imed like ? and idPrejem like ? and Racunb like ? and Racund like ? and DatumValute = ? order by idNA desc", params,  new NalogMapper());  
                    
    	}
    	catch(ParseException ex)
    	{
    		
    		
    	}
         return contacts;
    }
    
    public void update(Nalog nalog) {
    	 
    	try
    	{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	Date datval=sdf.parse(nalog.getDatumvalute());
	    	Date datprom=sdf.parse(nalog.getDatumprometa());

    	
    	Object[] params = new Object[] {nalog.getTipdok(), nalog.getIdDok(), nalog.getIdPrejem(), datval, 
    			nalog.getRacunb(), nalog.getModb(), nalog.getSklicb(),nalog.getRacund(),nalog.getModd(),nalog.getSklicd(),
    			nalog.getIznos(),nalog.getImeb(),nalog.getUlicab(),nalog.getKrajb(),nalog.getImed(),nalog.getUlicad(),nalog.getKrajd(),nalog.getNamen(),
    			nalog.getSifra(),datprom,nalog.getIdNA()};
    	    	
    	getJdbcTemplate().update(
                "update tbl_NA set TIPDOK =?,IDDOK =?,IDPREJEM =?,DATUMVALUTE =?,RACUNB =?,MODB =?,SKLICB =?,RACUND =?,MODD =?,SKLICD =?,ZNESEK =?,IMEB =?,ULICAB =?,KRAJB =?,IMED =?,ULICAD =?,KRAJD =?,NAMEN = ?,SIFRA = ?,DATUMPROMETA = ? where idNA = ?", params);
               
    	}
    	catch(ParseException ex)
    	{
    		
    		
    	}
    }
    
//    public List<ContactType> getAllTypes() {
//    	List<ContactType> contactTypes = null;
//    	
//		 contactTypes = getJdbcTemplate().query(
//                "select id, name from test_user_types order by id", 
//                new ContactTypeMapper()); 	
//	
//		 return contactTypes;
//	}
//    
    public Nalog findById (Integer id) throws NotFoundException {
    	List<Nalog> nalog = null;
    	Object[] params = new Object[] {id};
    	
    	nalog = getJdbcTemplate().query(
                "select idNA,tipdok,idDok,idPrejem,datumvalute,modb,racunb,sklicb,racund,znesek iznos,imeb,ulicab,krajb,modd,imed,ulicad,krajd,sklicd,namen,sifra,datumprometa from tbl_NA where idNA = ?", params,  new NalogMapper()); 
    	
    	if (nalog == null ) {
    		throw new NotFoundException("Ne postoji nalog sa tim ID-jem");
    	} else  if (nalog.size() == 0 ) {
    		throw new NotFoundException("Ne postoji nalog sa tim ID-jem");
    	}
    	
    	return nalog.get(0);
                
                
    }
    
    private static class NalogMapper implements ParameterizedRowMapper<Nalog> {

        public Nalog mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Nalog nalog = new Nalog();
        	nalog.setIdNA(rs.getInt("idNA"));
        	nalog.setTipdok(rs.getInt("tipdok"));
        	nalog.setIdDok(rs.getString("idDok"));
        	nalog.setIdPrejem(rs.getString("idPrejem"));
        	nalog.setDatumvalute(rs.getString("datumvalute"));
        	nalog.setModb(rs.getString("modb"));
        	nalog.setRacunb(rs.getString("racunb"));
        	nalog.setSklicb(rs.getString("sklicb"));
        	nalog.setRacund(rs.getString("racund"));
        	nalog.setIznos(rs.getString("iznos"));
        	nalog.setImeb(rs.getString("imeb"));
        	nalog.setUlicab(rs.getString("ulicab"));
        	nalog.setKrajb(rs.getString("krajb"));
        	nalog.setModd(rs.getString("modd"));
        	nalog.setImed(rs.getString("imed"));
        	nalog.setUlicad(rs.getString("ulicad"));
        	nalog.setKrajd(rs.getString("krajd"));
        	nalog.setSklicd(rs.getString("sklicd"));
        	nalog.setNamen(rs.getString("namen"));
        	nalog.setSifra(rs.getString("sifra"));
        	nalog.setDatumprometa(rs.getString("datumprometa"));
            return nalog;
        }

    }
    
 
    
    
//    private static class ContactTypeMapper implements ParameterizedRowMapper<ContactType> {
//
//        public ContactType mapRow(ResultSet rs, int rowNum) throws SQLException {
//        	ContactType contactType = new ContactType();
//        	contactType.setId(rs.getInt("id"));
//        	contactType.setName(rs.getString("name"));
//            return contactType;
//        }
//
//    }

}
