package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "uzivatel")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Uzivatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "heslo", nullable = false, length = 100)
    private String heslo;

    @Column(name = "telefon", nullable = false, length = 16)
    private String telefon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Uzivatel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", heslo='" + heslo + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}