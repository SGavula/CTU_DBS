package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "predavajuci")
@PrimaryKeyJoinColumn(name = "uzivatel_id")
@DiscriminatorValue("Predavajuci")
public class Predavajuci extends Uzivatel{
    @Column(name = "realitna_kancelaria", nullable = false, length = 100)
    private String realitnaKancelaria;

    public String getRealitnaKancelaria() {
        return realitnaKancelaria;
    }

    public void setRealitnaKancelaria(String realitnaKancelaria) {
        this.realitnaKancelaria = realitnaKancelaria;
    }


    @Override
    public String toString() {
        return "Predavajuci{" +
                ", uzivatel_id=" + getId() + // Access inherited id field
                ", email='" + getEmail() + '\'' + // Access inherited fields
                ", heslo='" + getHeslo() + '\'' +
                ", telefon='" + getTelefon() + '\'' +
                ", realitna kancelaria='" + realitnaKancelaria + '\'' +
                '}';
    }
}