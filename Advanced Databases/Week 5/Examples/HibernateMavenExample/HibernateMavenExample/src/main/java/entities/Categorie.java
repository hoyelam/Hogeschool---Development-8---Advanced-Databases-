package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Categorie {
	private Integer idCategorie;
	private String categorieNaam;
	private Categorie subCategorie;
	private Set<Categorie> sub = new HashSet<Categorie>();
	
	public Categorie(String categorieNaam) {
		this();
		this.categorieNaam = categorieNaam;
	}

	public Categorie() {
	}

	@Id
	@GeneratedValue
	@Column(name="idCategorie")
	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	@Column(name="categorieNaam")
	public String getCategorieNaam() {
		return categorieNaam;
	}

	public void setCategorieNaam(String categorieNaam) {
		this.categorieNaam = categorieNaam;
	}

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="idSubCategorie")
	public Categorie getSubCategorie() {
		return subCategorie;
	}

	public void setSubCategorie(Categorie subCategorie) {
		this.subCategorie = subCategorie;
	}

	@OneToMany(mappedBy="subCategorie")
	public Set<Categorie> getSub() {
		return sub;
	}

	public void setSub(Set<Categorie> sub) {
		this.sub = sub;
	}
	
	
}
