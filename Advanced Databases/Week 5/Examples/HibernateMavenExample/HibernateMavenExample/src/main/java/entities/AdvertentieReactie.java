package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AdvertentieReactie")
public class AdvertentieReactie {
	private Integer idAdvertentieReactie;
	private String tekst;
	private String datum;
	private Advertentie advertentie;
	private Gebruiker gebruiker;

	
	public AdvertentieReactie() {
	}

	public AdvertentieReactie(String tekst, String datum, Integer idAdvertentie) {
		this();
		this.tekst = tekst;
		this.datum = datum;
	}
	
	@Id
	@GeneratedValue
	@Column(name="idAdvertentie")
	public Integer getIdAdvertentieReactie() {
		return idAdvertentieReactie;
	}

	public void setIdAdvertentieReactie(Integer idAdvertentieReactie) {
		this.idAdvertentieReactie = idAdvertentieReactie;
	}

	@Column(name="tekst")
	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	@Column(name="datum")
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
}
