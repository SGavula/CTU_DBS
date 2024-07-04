package tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "profil")
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "meno", nullable = false, length = 50)
    private String meno;

    @Column(name = "priezvisko", nullable = false, length = 50)
    private String priezvisko;

    @Column(name = "datum_narodenia", nullable = false)
    private LocalDate datumNarodenia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public LocalDate getDatumNarodenia() {
        return datumNarodenia;
    }

    public void setDatumNarodenia(LocalDate datumNarodenia) {
        this.datumNarodenia = datumNarodenia;
    }

    @Override
    public String toString() {
        return "Profil{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", datumNarodenia=" + datumNarodenia +
                '}';
    }
}