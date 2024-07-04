package tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrezeraId implements Serializable {
    private static final long serialVersionUID = 141516743075836177L;
    @Column(name = "kupujuci_id", nullable = false)
    private Integer kupujuciId;

    @Column(name = "nehnutelnost_id", nullable = false)
    private Integer nehnutelnostId;

    public Integer getKupujuciId() {
        return kupujuciId;
    }

    public void setKupujuciId(Integer kupujuciId) {
        this.kupujuciId = kupujuciId;
    }

    public Integer getNehnutelnostId() {
        return nehnutelnostId;
    }

    public void setNehnutelnostId(Integer nehnutelnostId) {
        this.nehnutelnostId = nehnutelnostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrezeraId entity = (PrezeraId) o;
        return Objects.equals(this.nehnutelnostId, entity.nehnutelnostId) &&
                Objects.equals(this.kupujuciId, entity.kupujuciId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nehnutelnostId, kupujuciId);
    }

}