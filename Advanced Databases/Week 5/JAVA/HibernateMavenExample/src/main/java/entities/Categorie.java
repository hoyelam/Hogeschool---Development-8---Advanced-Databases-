package entities;

public class Categorie {
	
	private String categorieNaam;
	private Advertentie advertentie;
	private Categorie categorie;
	
	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public Categorie(String categorieNaam) {
		this();
		this.categorieNaam = categorieNaam;
	}


	public String getCategorieNaam() {
		return categorieNaam;
	}



	public void setCategorieNaam(String categorieNaam) {
		this.categorieNaam = categorieNaam;
	}



	public Advertentie getAdvertentie() {
		return advertentie;
	}



	public void setAdvertentie(Advertentie advertentie) {
		this.advertentie = advertentie;
	}



	public Categorie() {
	}
}
