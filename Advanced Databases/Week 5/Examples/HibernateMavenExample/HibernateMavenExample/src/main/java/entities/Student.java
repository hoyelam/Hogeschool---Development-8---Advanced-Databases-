package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Jesse on 4-6-2014.
 */
@Entity
public class Student {
    @Id @GeneratedValue
    private Long id;

    private String voornaam;
    private String achternaam;

    @ManyToOne
    private Klas klas;

    public Student() {}

    public Student(String voornaam, String achternaam) {
        this();
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Klas getKlas() {
        return klas;
    }

    public void setKlas(Klas klas) {
        this.klas = klas;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", klas=" + klas +
                '}';
    }
}
