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
@Table(name="Bod")
public class Bod {
	
	private Integer idBod;
	private Integer prijs;
	private Date datum;
	private Advertentie advertentie;
	private Gebruiker gebruiker;

	public Bod() {
	}
	
	public Bod(Integer prijs, Advertentie advertentie,
				Gebruiker gebruiker){
		this();
		this.prijs = prijs;
		this.datum = new Date();
		this.advertentie = advertentie;
		this.gebruiker = gebruiker;
	}

	@Id
	@GeneratedValue
	@Column(name="idBod")
	public Integer getIdBod() {
		return idBod;
	}

	public void setIdBod(Integer idBod) {
		this.idBod = idBod;
	}

	@Column(name="prijs")
	public Integer getPrijs() {
		return prijs;
	}

	public void setPrijs(Integer prijs) {
		this.prijs = prijs;
	}

	@Column(name="datum")
	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}

	@ManyToOne (cascade = CascadeType.ALL)
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
}
