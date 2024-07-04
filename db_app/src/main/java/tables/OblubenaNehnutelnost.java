package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "oblubena_nehnutelnost")
public class OblubenaNehnutelnost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kupujuci_id", nullable = false)
    private Kupujuci kupujuci;

    @Column(name = "oblubena_nehnutelnost", nullable = false, length = 200)
    private String oblubenaNehnutelnost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Kupujuci getKupujuci() {
        return kupujuci;
    }

    public void setKupujuci(Kupujuci kupujuci) {
        this.kupujuci = kupujuci;
    }

    public String getOblubenaNehnutelnost() {
        return oblubenaNehnutelnost;
    }

    public void setOblubenaNehnutelnost(String oblubenaNehnutelnost) {
        this.oblubenaNehnutelnost = oblubenaNehnutelnost;
    }

    @Override
    public String toString() {
        return "OblubenaNehnutelnost{" +
                "id=" + id +
                ", kupujuci=" + kupujuci +
                ", oblubenaNehnutelnost='" + oblubenaNehnutelnost + '\'' +
                '}';
    }
}