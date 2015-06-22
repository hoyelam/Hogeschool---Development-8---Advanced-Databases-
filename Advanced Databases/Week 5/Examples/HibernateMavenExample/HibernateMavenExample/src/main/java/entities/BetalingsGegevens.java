package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import entities.Gebruiker;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "BetalingsGegevens")
public class BetalingsGegevens {
	private Integer idGebruiker;
	private Integer bankNummer;
	private String eigenaarNaam;
	private Gebruiker gebruiker;

	public BetalingsGegevens() {
	}

	public BetalingsGegevens(Integer bankNummer, String eigenaarNaam,
			Gebruiker gebruiker) {
		this();
		this.bankNummer = bankNummer;
		this.eigenaarNaam = eigenaarNaam;
		this.gebruiker = gebruiker;
	}

	@Id
	@GeneratedValue(generator = "gebruiker")
	@GenericGenerator(name = "gebruiker", strategy = "foreign", parameters = @Parameter(value = "gebruiker", name = "property"))
	public Integer getIdGebruiker() {
		return idGebruiker;
	}

	public void setIdGebruiker(Integer idGebruiker) {
		this.idGebruiker = idGebruiker;
	}

	@Column(name = "bankNummer")
	public Integer getBankNummer() {
		return bankNummer;
	}

	public void setBankNummer(Integer bankNummer) {
		this.bankNummer = bankNummer;
	}

	@Column(name = "eigenaarNaam")
	public String getEigenaarNaam() {
		return eigenaarNaam;
	}

	public void setEigenaarNaam(String eigenaarNaam) {
		this.eigenaarNaam = eigenaarNaam;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name="idGebruiker")
	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		this.gebruiker = gebruiker;
	}

}
