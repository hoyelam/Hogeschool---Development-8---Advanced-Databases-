package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gebruiker {

	public Integer getIdAdvertentie() {
		return idAdvertentie;
	}

	public void setIdAdvertentie(Integer idAdvertentie) {
		this.idAdvertentie = idAdvertentie;
	}

	@Id
	private Integer idGebruiker;
	private String voornaam;
	private String achternaam;
	private String email;
	private String wachtwoord;
	private BetalingsGegevens betalingsGegevens;
	private Advertentie advertentie;
	private Integer idAdvertentie;

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
	
	public Advertentie getAdvertentie() {
		return advertentie;
	}

	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}

	
	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}

	public BetalingsGegevens getBetalingsGegevens() {
		return betalingsGegevens;
	}

	public void setBetalingsGegevens(BetalingsGegevens betalingsGegevens) {
		this.betalingsGegevens = betalingsGegevens;
	}

	public String getAchternaam(){
		return achternaam;
	}//Haal achternaam op
	
	public void setAchternaam(String achternaam){
		this.achternaam = achternaam;
	}//Set achternaam 
	
	public String getVoornaam() {
		return voornaam;
	}//Haal voornaam op

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}//Geef gebruiker een voornaam

	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public void setWachtwoord(String wachtwoord){
		this.wachtwoord = wachtwoord;
	}
	
	public String getWachtwoord(){
		return wachtwoord;
	}
	
	

	// @Override
	// public String toString() {
	// return "Klas{" +
	// "id=" + id +
	// ", klasCode='" + klasCode + '\'' +
	// '}';
	// }
}
