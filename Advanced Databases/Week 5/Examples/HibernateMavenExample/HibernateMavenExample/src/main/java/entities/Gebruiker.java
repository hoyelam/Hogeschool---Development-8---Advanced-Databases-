package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class  Gebruiker{
	
	@Id
    @GeneratedValue
    private Integer idGebruiker;
    private String	voornaam;
    private String	achternaam;
    private	String	email;
    private	String	wachtwoord;

    public Gebruiker() {}

    public Gebruiker(String voornaam, String achternaam, String email, String wachtwoord) {
        this();
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public Integer get_idGebruiker() {
        return idGebruiker;
    }

    public void setId(Integer idGebruiker) {
        this.idGebruiker = idGebruiker;
    }

    //@Override
    //public String toString() {
    //    return "Klas{" +
    //            "id=" + id +
    //            ", klasCode='" + klasCode + '\'' +
    //          '}';
    //}
}
