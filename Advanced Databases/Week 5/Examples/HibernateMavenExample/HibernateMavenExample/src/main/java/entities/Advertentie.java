package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Advertentie")
public class Advertentie {

	private Integer idAdvertentie;
	private String naam;
	private String beschrijving;
	private Integer startPrijs;
	private boolean actief;
	private String startDatum;
	private Gebruiker gebruiker;
    private Categorie categorie;
	
	public Advertentie() {
	}

	public Advertentie(String naam, String beschrijving, Integer startPrijs,
			boolean actief, String startDatum, Gebruiker gebruiker, Categorie categorie) {
		this();
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.startPrijs = startPrijs;
		this.actief = actief;
		this.startDatum = startDatum;
		this.gebruiker = gebruiker;
		this.categorie = categorie;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idAdvertentie")
	public Integer getIdAdvertentie() {
		return idAdvertentie;
	}

	public void setIdAdvertentie(Integer idAdvertentie) {
		this.idAdvertentie = idAdvertentie;
	}

	@Column(name="naam")
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Column(name="beschrijving")
	public String getBeschrijving() {
		return beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	@Column(name="startPrijs")
	public Integer getStartPrijs() {
		return startPrijs;
	}

	public void setStartPrijs(Integer startPrijs) {
		this.startPrijs = startPrijs;
	}

	@Column(name="actief")
	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	@Column(name="startDatum")
	public String getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(String startDatum) {
		this.startDatum = startDatum;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Gebruiker getGebruiker() {
		return gebruiker;
	}
	
	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
}