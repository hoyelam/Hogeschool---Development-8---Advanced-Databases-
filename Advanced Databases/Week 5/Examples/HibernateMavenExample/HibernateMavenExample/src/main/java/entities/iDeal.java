package entities;

public class iDeal {
	private String bankNaam;
	private Integer bankNummer;
	private BetalingsGegevens betalingsGegevens;
	
	public iDeal(String bankNaam, Integer bankNummer){
		this();
		this.bankNaam = bankNaam;
		this.bankNummer = bankNummer;
	}
	
	public iDeal(){		
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
