package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Advertentie {
	@Id
	@GeneratedValue
	private Integer idAdvertentie;
	private String naam;
	private String beschrijving;
	private Integer startPrijs;
	private boolean actief;
	private String startDatum;
	private Integer idGebruiker;
	private String categorieNaam;
	private Gebruiker gebruiker;


	public Advertentie() {
	}

	public Advertentie(String naam, String beschrijving, Integer startPrijs,
			boolean actief, String startDatum) {
		this();
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.startPrijs = startPrijs;
		this.actief = actief;
		this.startDatum = startDatum;
	}
	
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	
	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}

	public String getCategorieNaam() {
		return categorieNaam;
	}

	public void setCategorieNaam(String categorieNaam) {
		this.categorieNaam = categorieNaam;
	}

	public Integer getIdAdvertentie() {
		return idAdvertentie;
	}

	public void setIdAdvertentie(Integer idAdvertentie) {
		this.idAdvertentie = idAdvertentie;
	}

	public Integer getId() {
		return idAdvertentie;
	}

	public void setId(Integer idAdvertentie) {
		this.idAdvertentie = idAdvertentie;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Integer getStartPrijs() {
		return startPrijs;
	}

	public void setStartPrijs(Integer startPrijs) {
		this.startPrijs = startPrijs;
	}

	public void setStartDatum(String startDatum) {
		this.startDatum = startDatum;
	}

	public String getStartDatum() {
		return startDatum;
	}
	
	public void setActief(boolean actief){
		this.actief = actief;
	}
	
	public boolean getActief(){
		return actief;
	}

}