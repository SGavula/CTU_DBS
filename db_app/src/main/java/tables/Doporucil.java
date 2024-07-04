package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "doporucil")
public class Doporucil {
    @EmbeddedId
    private DoporucilId id;

    @MapsId("kupujuciId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupujuci_id", nullable = false)
    private Kupujuci kupujuci;

    @MapsId("kupujuciSDopId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupujuci_s_dop_id", nullable = false)
    private Kupujuci kupujuciSDop;

    @Lob
    @Column(name = "popis", nullable = false)
    private String popis;

    public DoporucilId getId() {
        return id;
    }

    public void setId(DoporucilId id) {
        this.id = id;
    }

    public Kupujuci getKupujuci() {
        return kupujuci;
    }

    public void setKupujuci(Kupujuci kupujuci) {
        this.kupujuci = kupujuci;
    }

    public Kupujuci getKupujuciSDop() {
        return kupujuciSDop;
    }

    public void setKupujuciSDop(Kupujuci kupujuciSDop) {
        this.kupujuciSDop = kupujuciSDop;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return "Doporucil{" +
                "id=" + id +
                ", kupujuci=" + kupujuci +
                ", kupujuciSDop=" + kupujuciSDop +
                ", popis='" + popis + '\'' +
                '}';
    }

}