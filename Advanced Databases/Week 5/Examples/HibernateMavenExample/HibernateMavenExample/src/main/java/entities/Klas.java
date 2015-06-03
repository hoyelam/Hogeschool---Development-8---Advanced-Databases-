package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Klas {
    @Id
    @GeneratedValue
    private Long id;

    private String klasCode;

    public Klas() {}

    public Klas(String klasCode) {
        this();
        this.klasCode = klasCode;
    }

    public String getKlasCode() {
        return klasCode;
    }

    public void setKlasCode(String klasCode) {
        this.klasCode = klasCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Klas{" +
                "id=" + id +
                ", klasCode='" + klasCode + '\'' +
                '}';
    }
}
