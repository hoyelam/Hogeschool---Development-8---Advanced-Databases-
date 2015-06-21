package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class AdvertentieReactie {
	@Id
	@GeneratedValue
	private Integer idAdvertentieReactiie;
	private String tekst;
	private String datum;
	private Integer idAdvertentie ;
	private String categorieNaam;	
	private Integer idGebruiker;
	private Advertentie advertentie;
	private Gebruiker gebruiker; 
	private Categorie categorie;

	public AdvertentieReactie() {
	}

	public AdvertentieReactie(String tekst, String datum, Integer idAdvertentie) {
		this();
		this.tekst = tekst;
		this.datum = datum;
		this.idAdvertentie = idAdvertentie;
	}
	
	public Integer getIdAdvertentieReactiie() {
		return idAdvertentieReactiie;
	}

	public void setIdAdvertentieReactiie(Integer idAdvertentieReactiie) {
		this.idAdvertentieReactiie = idAdvertentieReactiie;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
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

	public String getCategorieNaam() {
		return categorieNaam;
	}

	public void setCategorieNaam(String categorieNaam) {
		this.categorieNaam = categorieNaam;
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

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	
}
