package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Bod {
	@Id
	@GeneratedValue
	private Integer idBod;
	private Integer prijs;
	private String datum;
	private Integer idAdvertentie;
	private Integer idGebruiker;
	private Advertentie advertentie;
	private Bod bod;

	public Bod(Integer idBod, Integer prijs, String datum, Integer idAdvertentie,
			Integer idGebruiker){
		this();
		this.idBod = idBod;
		this.prijs = prijs;
		this.datum = datum;
		this.idAdvertentie = idAdvertentie;
		this.idGebruiker = idGebruiker;
	}
	public Bod getBod() {
		return bod;
	}
	public void setBod(Bod bod) {
		this.bod = bod;
	}
	public Integer getIdBod() {
		return idBod;
	}


	public void setIdBod(Integer idBod) {
		this.idBod = idBod;
	}


	public Integer getPrijs() {
		return prijs;
	}


	public void setPrijs(Integer prijs) {
		this.prijs = prijs;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public Integer getIdAdvertentie() {
		return idAdvertentie;
	}


	public void setIdAdvertentie(Integer idAdvertentie) {
		this.idAdvertentie = idAdvertentie;
	}


	public Integer getIdGebruiker() {
		return idGebruiker;
	}


	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}


	public Advertentie getAdvertentie() {
		return advertentie;
	}


	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}


	public Bod() {
	}
}
