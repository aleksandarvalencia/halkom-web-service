package wshalkom.model.requests;

/**
 * @author misa.stefanovic
 */

public class NalogRequest {

	  Integer idNA;
	  Integer tipdok;
	  String idDok;
	  String idPrejem;
	  String datumvalute;
	  String modb;
	  String racunb;
	  String sklicb;
	  String racund;
	  String iznos;
	  String imeb;
	  String ulicab;
	  String krajb;
	  String modd;
	  String imed;
	  String ulicad;
	  String krajd;
	  String sklicd;
	  String namen;
	  String sifra;
	  String datumprometa;

	
	

	public NalogRequest( Integer idNA,Integer tipdok,String idDok,String idPrejem,String datumvalute,String racunb,String modb,String sklicb,String iznos,
	  String imeb,String ulicab,String krajb,String modd,String racund,String sklicd,String imed,String ulicad,String krajd,String namen,String sifra,String datumprometa)
	{
		
		  System.out.println("Konstruktor");
		  this.idNA=idNA;
		  this.tipdok=tipdok;
		  this.idDok=idDok;
		  this.idPrejem=idPrejem;
		  this.datumvalute=datumvalute;
		  this.racunb=racunb;
		  this.sklicb=sklicb;
		  this.modb=modb;
		  this.modd=modd;
		  this.racund=racund;
		  this.iznos=iznos;
		  this.imeb=imeb;
		  this.ulicab=ulicab;
		  this.krajb=krajb;
		  this.imed=imed;
		  this.ulicad=ulicad;
		  this.krajd=krajd;
		  this.sklicd=sklicd;
		  this.namen=namen;
		  this.sifra=sifra;
		  this.datumprometa=datumprometa;
	}


	public Integer getIdNA() {
		return idNA;
	}


	public void setIdNA(Integer idNA) {
		this.idNA = idNA;
	}


	public Integer getTipdok() {
		return tipdok;
	}


	public void setTipdok(Integer tipdok) {
		this.tipdok = tipdok;
	}


	public String getIdDok() {
		return idDok;
	}


	public void setIdDok(String idDok) {
		this.idDok = idDok;
	}


	public String getIdPrejem() {
		return idPrejem;
	}


	public void setIdPrejem(String idPrejem) {
		this.idPrejem = idPrejem;
	}


	public String getDatumvalute() {
		return datumvalute;
	}


	public void setDatumvalute(String datumvalute) {
		this.datumvalute = datumvalute;
	}


	public String getRacunb() {
		return racunb;
	}


	public void setRacunb(String racunb) {
		this.racunb = racunb;
	}


	public String getSklicb() {
		return sklicb;
	}


	public void setSklicb(String sklicb) {
		this.sklicb = sklicb;
	}


	public String getRacund() {
		return racund;
	}


	public void setRacund(String racund) {
		this.racund = racund;
	}


	public String getIznos() {
		return iznos;
	}


	public void setIznos(String iznos) {
		this.iznos = iznos;
	}


	public String getImeb() {
		return imeb;
	}


	public void setImeb(String imeb) {
		this.imeb = imeb;
	}


	public String getUlicab() {
		return ulicab;
	}


	public void setUlicab(String ulicab) {
		this.ulicab = ulicab;
	}


	public String getKrajb() {
		return krajb;
	}


	public void setKrajb(String krajb) {
		this.krajb = krajb;
	}


	public String getImed() {
		return imed;
	}


	public void setImed(String imed) {
		this.imed = imed;
	}


	public String getUlicad() {
		return ulicad;
	}


	public void setUlicad(String ulicad) {
		this.ulicad = ulicad;
	}


	public String getKrajd() {
		return krajd;
	}


	public void setKrajd(String krajd) {
		this.krajd = krajd;
	}


	public String getSklicd() {
		return sklicd;
	}


	public void setSklicd(String sklicd) {
		this.sklicd = sklicd;
	}


	public String getNamen() {
		return namen;
	}


	public void setNamen(String namen) {
		this.namen = namen;
	}


	public String getSifra() {
		return sifra;
	}


	public void setSifra(String sifra) {
		this.sifra = sifra;
	}


	public String getDatumprometa() {
		return datumprometa;
	}


	public void setDatumprometa(String datumprometa) {
		this.datumprometa = datumprometa;
	}

	
	public String getModb() {
		return modb;
	}


	public void setModb(String modb) {
		this.modb = modb;
	}


	public String getModd() {
		return modd;
	}


	public void setModd(String modd) {
		this.modd = modd;
	}


	
}
    