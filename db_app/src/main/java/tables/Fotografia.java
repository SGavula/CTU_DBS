package tables;

import jakarta.persistence.*;

@Entity
@Table(name = "fotografia")
public class Fotografia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nazov", nullable = false, length = 50)
    private String nazov;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @Override
    public String toString() {
        return "Fotografia{" +
                "id=" + id +
                ", nazov='" + nazov + '\'' +
                '}';
    }
}