package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class BetalingsGegevens {

	private Integer bankNummer;
	private String eigenaarNaam;
	private Gebruiker gebruiker;
	private Integer	idGebruiker;


	public BetalingsGegevens() {
	}

	public BetalingsGegevens(Integer bankNummer, String eigenaarNaam,
			Integer idGebruiker) {
		this();
		this.bankNummer = bankNummer;
		this.eigenaarNaam = eigenaarNaam;
		this.idGebruiker = idGebruiker;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	public Integer getBankNummer() {
		return bankNummer;
	}

	public void setBankNummer(Integer bankNummer) {
		this.bankNummer = bankNummer;
	}

	public String getEigenaarNaam() {
		return eigenaarNaam;
	}

	public void setEigenaarNaam(String eigenaarNaam) {
		this.eigenaarNaam = eigenaarNaam;
	}

	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker){
		this.idGebruiker = idGebruiker;
	}

}
