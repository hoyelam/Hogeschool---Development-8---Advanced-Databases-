package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AdvertentieReactie")
public class AdvertentieReactie {
	private Integer idAdvertentieReactie;
	private String tekst;
	private Date datum;
	private Advertentie advertentie;
	private Gebruiker gebruiker;

	
	public AdvertentieReactie() {
	}

	public AdvertentieReactie(String tekst, Advertentie advertentie, Gebruiker gebruiker) {
		this();
		this.tekst = tekst;
		this.datum = new Date();
		this.advertentie = advertentie;
		this.gebruiker = gebruiker;
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
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
}
