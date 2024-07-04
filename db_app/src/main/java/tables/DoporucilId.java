package tables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoporucilId implements Serializable {
    private static final long serialVersionUID = 5100448963598212171L;
    @Column(name = "kupujuci_id", nullable = false)
    private Integer kupujuciId;

    @Column(name = "kupujuci_s_dop_id", nullable = false)
    private Integer kupujuciSDopId;


    public Integer getKupujuciId() {
        return kupujuciId;
    }

    public void setKupujuciId(Integer kupujuciId) {
        this.kupujuciId = kupujuciId;
    }

    public Integer getKupujuciSDopId() {
        return kupujuciSDopId;
    }

    public void setKupujuciSDopId(Integer kupujuciSDopId) {
        this.kupujuciSDopId = kupujuciSDopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoporucilId entity = (DoporucilId) o;
        return Objects.equals(this.kupujuciSDopId, entity.kupujuciSDopId) &&
                Objects.equals(this.kupujuciId, entity.kupujuciId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kupujuciSDopId, kupujuciId);
    }

}