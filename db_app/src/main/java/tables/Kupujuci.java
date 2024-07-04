package tables;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kupujuci")
@PrimaryKeyJoinColumn(name = "uzivatel_id")
@DiscriminatorValue("Kupujuci")
public class Kupujuci extends Uzivatel {

    @ManyToMany(mappedBy = "kupujuci")
    private List<Nehnutelnost> nehnutelnosti;
    public List<Nehnutelnost> getNehnutelnosti() {
        return nehnutelnosti;
    }

    public void setNehnutelnosti(List<Nehnutelnost> nehnutelnosti) {
        this.nehnutelnosti = nehnutelnosti;
    }

    @Override
    public String toString() {
        return "Kupujuci{" +
                ", uzivatel_id=" + getId() + // Access inherited id field
                ", email='" + getEmail() + '\'' + // Access inherited fields
                ", heslo='" + getHeslo() + '\'' +
                ", telefon='" + getTelefon() + '\'' +
                '}';
    }
}
