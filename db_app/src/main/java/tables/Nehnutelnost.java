package tables;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "nehnutelnost", schema = "public", catalog = "gavulsim")
public class Nehnutelnost {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "typ")
    private String typ;
    @Basic
    @Column(name = "cena")
    private BigDecimal cena;
    @Basic
    @Column(name = "plocha")
    private BigDecimal plocha;
    @Basic
    @Column(name = "stav")
    private String stav;
    @Basic
    @Column(name = "popis")
    private String popis;
    @Basic
    @Column(name = "popisne_cislo")
    private String popisneCislo;
    @Basic
    @Column(name = "mesto")
    private String mesto;
    @Basic
    @Column(name = "psc")
    private String psc;
    @Basic
    @Column(name = "stat")
    private String stat;
    @Basic
    @Column(name = "rok_stavby")
    private String rokStavby;
    @Basic
    @Column(name = "gps_suradnice")
    private String gpsSuradnice;
    @Basic
    @Column(name = "kategoria_id")
    private int kategoriaId;
    @Basic
    @Column(name = "predavajuci_id")
    private int predavajuciId;

    @ManyToMany
    @JoinTable(name = "prezera",
            joinColumns = @JoinColumn(name = "nehnutelnost_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "kupujuci_id", nullable = false))
    private List<Kupujuci> kupujuci;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public BigDecimal getPlocha() {
        return plocha;
    }

    public void setPlocha(BigDecimal plocha) {
        this.plocha = plocha;
    }

    public String getStav() {
        return stav;
    }

    public void setStav(String stav) {
        this.stav = stav;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getPopisneCislo() {
        return popisneCislo;
    }

    public void setPopisneCislo(String popisneCislo) {
        this.popisneCislo = popisneCislo;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getRokStavby() {
        return rokStavby;
    }

    public void setRokStavby(String rokStavby) {
        this.rokStavby = rokStavby;
    }

    public String getGpsSuradnice() {
        return gpsSuradnice;
    }

    public void setGpsSuradnice(String gpsSuradnice) {
        this.gpsSuradnice = gpsSuradnice;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(int kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    public int getPredavajuciId() {
        return predavajuciId;
    }

    public void setPredavajuciId(int predavajuciId) {
        this.predavajuciId = predavajuciId;
    }

    public List<Kupujuci> getKupujuci() {
        return kupujuci;
    }

    public void setKupujuci(List<Kupujuci> kupujuci) {
        this.kupujuci = kupujuci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nehnutelnost that = (Nehnutelnost) o;

        if (id != that.id) return false;
        if (kategoriaId != that.kategoriaId) return false;
        if (predavajuciId != that.predavajuciId) return false;
        if (typ != null ? !typ.equals(that.typ) : that.typ != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;
        if (plocha != null ? !plocha.equals(that.plocha) : that.plocha != null) return false;
        if (stav != null ? !stav.equals(that.stav) : that.stav != null) return false;
        if (popis != null ? !popis.equals(that.popis) : that.popis != null) return false;
        if (popisneCislo != null ? !popisneCislo.equals(that.popisneCislo) : that.popisneCislo != null) return false;
        if (mesto != null ? !mesto.equals(that.mesto) : that.mesto != null) return false;
        if (psc != null ? !psc.equals(that.psc) : that.psc != null) return false;
        if (stat != null ? !stat.equals(that.stat) : that.stat != null) return false;
        if (rokStavby != null ? !rokStavby.equals(that.rokStavby) : that.rokStavby != null) return false;
        if (gpsSuradnice != null ? !gpsSuradnice.equals(that.gpsSuradnice) : that.gpsSuradnice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typ != null ? typ.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        result = 31 * result + (plocha != null ? plocha.hashCode() : 0);
        result = 31 * result + (stav != null ? stav.hashCode() : 0);
        result = 31 * result + (popis != null ? popis.hashCode() : 0);
        result = 31 * result + (popisneCislo != null ? popisneCislo.hashCode() : 0);
        result = 31 * result + (mesto != null ? mesto.hashCode() : 0);
        result = 31 * result + (psc != null ? psc.hashCode() : 0);
        result = 31 * result + (stat != null ? stat.hashCode() : 0);
        result = 31 * result + (rokStavby != null ? rokStavby.hashCode() : 0);
        result = 31 * result + (gpsSuradnice != null ? gpsSuradnice.hashCode() : 0);
        result = 31 * result + kategoriaId;
        result = 31 * result + predavajuciId;
        return result;
    }

    @Override
    public String toString() {
        return "Nehnutelnost{" +
                "id=" + id +
                ", typ='" + typ + '\'' +
                ", cena=" + cena +
                ", plocha=" + plocha +
                ", stav='" + stav + '\'' +
                ", popis='" + popis + '\'' +
                ", popisneCislo='" + popisneCislo + '\'' +
                ", mesto='" + mesto + '\'' +
                ", psc='" + psc + '\'' +
                ", stat='" + stat + '\'' +
                ", rokStavby='" + rokStavby + '\'' +
                ", gpsSuradnice='" + gpsSuradnice + '\'' +
                ", kategoriaId=" + kategoriaId +
                ", predavajuciId=" + predavajuciId +
                '}';
    }
}
