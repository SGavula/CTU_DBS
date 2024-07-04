package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "transakcia")
public class Transakcia {
    @EmbeddedId
    private TransakciaId id;

    @MapsId("predavajuciId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "predavajuci_id", nullable = false)
    private Predavajuci predavajuci;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nehnutelnost_id", nullable = false)
    private Nehnutelnost nehnutelnost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupujuci_id", nullable = false)
    private Kupujuci kupujuci;

    public TransakciaId getId() {
        return id;
    }

    public void setId(TransakciaId id) {
        this.id = id;
    }

    public Predavajuci getPredavajuci() {
        return predavajuci;
    }

    public void setPredavajuci(Predavajuci predavajuci) {
        this.predavajuci = predavajuci;
    }

    public Nehnutelnost getNehnutelnost() {
        return nehnutelnost;
    }

    public void setNehnutelnost(Nehnutelnost nehnutelnost) {
        this.nehnutelnost = nehnutelnost;
    }

    public Kupujuci getKupujuci() {
        return kupujuci;
    }

    public void setKupujuci(Kupujuci kupujuci) {
        this.kupujuci = kupujuci;
    }

    @Override
    public String toString() {
        return "Transakcia{" +
                "id=" + id +
                ", predavajuci=" + predavajuci +
                ", nehnutelnost=" + nehnutelnost +
                ", kupujuci=" + kupujuci +
                '}';
    }
}