package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "prezera")
public class Prezera {
    @EmbeddedId
    private PrezeraId id;

    @MapsId("kupujuciId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupujuci_id", nullable = false)
    private Kupujuci kupujuci;

    @MapsId("nehnutelnostId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nehnutelnost_id", nullable = false)
    private Nehnutelnost nehnutelnost;

    public PrezeraId getId() {
        return id;
    }

    public void setId(PrezeraId id) {
        this.id = id;
    }

    public Kupujuci getKupujuci() {
        return kupujuci;
    }

    public void setKupujuci(Kupujuci kupujuci) {
        this.kupujuci = kupujuci;
    }

    public Nehnutelnost getNehnutelnost() {
        return nehnutelnost;
    }

    public void setNehnutelnost(Nehnutelnost nehnutelnost) {
        this.nehnutelnost = nehnutelnost;
    }

    @Override
    public String toString() {
        return "Prezera{" +
                "id=" + id +
                ", kupujuci=" + kupujuci +
                ", nehnutelnost=" + nehnutelnost +
                '}';
    }
}