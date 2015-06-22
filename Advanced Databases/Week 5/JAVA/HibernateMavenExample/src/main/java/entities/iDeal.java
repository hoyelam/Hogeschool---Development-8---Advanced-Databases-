package entities;

public class iDeal {
	private String bankNaam;
	private Integer bankNummer;
	private Integer idGebruiker;
	private Gebruiker gebruiker;
	
	
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}
	private BetalingsGegevens betalingsGegevens;
	
	public iDeal(String bankNaam, Integer bankNummer){
		this();
		this.bankNaam = bankNaam;
		this.bankNummer = bankNummer;
	}
	
	public iDeal(){		
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
	public String getBankNaam() {
		return bankNaam;
	}
	public void setBankNaam(String bankNaam) {
		this.bankNaam = bankNaam;
	}
	public Integer getBankNummer() {
		return bankNummer;
	}
	public void setBankNummer(Integer bankNummer) {
		this.bankNummer = bankNummer;
	}
	
}
