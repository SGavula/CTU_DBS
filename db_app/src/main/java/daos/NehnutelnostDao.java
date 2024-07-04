package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import tables.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class NehnutelnostDao {
    private EntityManager em;

    public NehnutelnostDao(EntityManager em) {
        this.em = em;
    }

    public Nehnutelnost insertNehnutelnost(String typ, BigDecimal cena, BigDecimal plocha, String stav, String popis, String popisneCislo, String mesto, String psc, String stat, String rokStavby, String gpsSuradnice, int kategoriaId, int predavajuciId,List<Kupujuci> kupujuci) {
        EntityTransaction et = em.getTransaction();
        Nehnutelnost nehnutelnost = new Nehnutelnost();
        nehnutelnost.setTyp(typ);
        nehnutelnost.setCena(cena);
        nehnutelnost.setPlocha(plocha);
        nehnutelnost.setStav(stav);
        nehnutelnost.setPopis(popis);
        nehnutelnost.setPopisneCislo(popisneCislo);
        nehnutelnost.setMesto(mesto);
        nehnutelnost.setPsc(psc);
        nehnutelnost.setStat(stat);
        nehnutelnost.setRokStavby(rokStavby);
        nehnutelnost.setGpsSuradnice(gpsSuradnice);
        nehnutelnost.setKategoriaId(kategoriaId);
        nehnutelnost.setKupujuci(kupujuci);
        nehnutelnost.setPredavajuciId(predavajuciId);

        et.begin();
        em.persist(nehnutelnost);
        et.commit();

        return nehnutelnost;
    }
    public List<Nehnutelnost> getAll() {
        return em.createQuery("SELECT n FROM Nehnutelnost n", Nehnutelnost.class).getResultList();
    }

    public Nehnutelnost getNehnutelnost(Integer id) {
        return em.find(Nehnutelnost.class, id);
    }

    public void updateNehnutelnost(Nehnutelnost nehnutelnost) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(nehnutelnost);
        et.commit();
    }

    public void deleteNehnutelnost(Nehnutelnost nehnutelnost) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(nehnutelnost);
        et.commit();
    }

    public List<Kupujuci> getAllViewers(int nehnutelnostId) {
        TypedQuery<Kupujuci> query = em.createQuery(
                "SELECT k FROM Nehnutelnost n JOIN n.kupujuci k WHERE n.id = :nehnutelnostId",
                Kupujuci.class);
        query.setParameter("nehnutelnostId", nehnutelnostId);
        return query.getResultList();
    }

}

