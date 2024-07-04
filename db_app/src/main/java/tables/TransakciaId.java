package tables;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class TransakciaId implements Serializable {
    private static final long serialVersionUID = 2470418369996595877L;
    @Column(name = "predavajuci_id", nullable = false)
    private Integer predavajuciId;

    @Column(name = "cas_vytvorenia", nullable = false)
    @Convert(converter = tables.InstantAttributeConverter.class)
    private Instant casVytvorenia;

    public Integer getPredavajuciId() {
        return predavajuciId;
    }

    public void setPredavajuciId(Integer predavajuciId) {
        this.predavajuciId = predavajuciId;
    }

    public Instant getCasVytvorenia() {
        return casVytvorenia;
    }

    public void setCasVytvorenia(Instant casVytvorenia) {
        this.casVytvorenia = casVytvorenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransakciaId entity = (TransakciaId) o;
        return Objects.equals(this.predavajuciId, entity.predavajuciId) &&
                Objects.equals(this.casVytvorenia, entity.casVytvorenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(predavajuciId, casVytvorenia);
    }

    @Override
    public String toString() {
        return "TransakciaId{" +
                "predavajuciId=" + predavajuciId +
                ", casVytvorenia=" + casVytvorenia +
                '}';
    }
}