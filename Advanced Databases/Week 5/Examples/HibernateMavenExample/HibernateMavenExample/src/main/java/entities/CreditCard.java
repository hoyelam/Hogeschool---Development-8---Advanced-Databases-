package entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class CreditCard {
	@Id
	@GeneratedValue
	private Integer vervalMaand;
	private Integer vervalJaar;
	private Integer idGebruiker;
	private Integer bankNummer;
	private BetalingsGegevens betalingsGegevens;
	private Gebruiker gebruiker;

	public CreditCard() {
	}

	public CreditCard(Integer vervalMaand, Integer vervalJaar,
			Integer bankNummer, Integer idGebruiker) {
		this();
		this.vervalMaand = vervalMaand;
		this.vervalJaar = vervalJaar;
		this.idGebruiker = idGebruiker;
		this.bankNummer = bankNummer;
	}

	public BetalingsGegevens getBetalingsGegevens() {
		return betalingsGegevens;
	}

	public void setBetalingsGegevens(BetalingsGegevens betalingsGegevens) {
		this.betalingsGegevens = betalingsGegevens;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

	public Integer getVervalMaand() {
		return vervalMaand;
	}

	public void setVervalMaand(Integer vervalMaand) {
		this.vervalMaand = vervalMaand;
	}

	public Integer getVervalJaar() {
		return vervalJaar;
	}

	public void setVervalJaar(Integer vervalJaar) {
		this.vervalJaar = vervalJaar;
	}

	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}

	public Integer getBankNummer() {
		return bankNummer;
	}

	public void setBankNummer(Integer bankNummer) {
		this.bankNummer = bankNummer;
	}
}
