package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Gebruiker")
public class Gebruiker {
	private Integer idGebruiker;
	private String voornaam;
	private String achternaam;
	private String email;
	private String wachtwoord;
	private BetalingsGegevens betalingsGegevens;

	public Gebruiker() {
	}

	public Gebruiker(String voornaam, String achternaam, String email,
			String wachtwoord) {
		this();
		this.voornaam = voornaam;
		this.achternaam = achternaam;
		this.email = email;
		this.wachtwoord = wachtwoord;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idGebruiker")
	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}

	@Column(name="voornaam", nullable = false, length = 100)
	public String getVoornaam() {
		return voornaam;
	}
	
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	@Column(name="achternaam", nullable = false , length = 100)
	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	@Column(name="email", nullable = false, length = 100, unique=true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="wachtwoord", nullable = false, length = 25)
	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	@OneToOne(cascade = CascadeType.ALL,mappedBy="gebruiker")
	public BetalingsGegevens getBetalingsGegevens() {
		return betalingsGegevens;
	}

	public void setBetalingsGegevens(BetalingsGegevens betalingsGegevens) {
		this.betalingsGegevens = betalingsGegevens;
	}	
}
