package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.*;

import java.time.Instant;
import java.util.List;

public class TransakciaDao {
    private EntityManager em;

    public TransakciaDao(EntityManager em) {
        this.em = em;
    }

    public Transakcia insertTransakcia(int nehId, int kupId) {
        EntityTransaction et = em.getTransaction();
        Nehnutelnost nehnutelnost = em.find(Nehnutelnost.class, nehId);

        if (nehnutelnost != null && "na predaj".equals(nehnutelnost.getStav())) {
            Predavajuci predavajuci = em.find(Predavajuci.class, nehnutelnost.getPredavajuciId());

            Kupujuci kupujuci = em.find(Kupujuci.class, kupId);

            if (predavajuci == null || kupujuci == null) {
                throw new IllegalArgumentException("Predavajuci or Kupujuci not found");
            }

            // Create a new TransakciaId
            TransakciaId transakciaId = new TransakciaId();
            transakciaId.setPredavajuciId(predavajuci.getId());
            transakciaId.setCasVytvorenia(Instant.now());

            // Create a new Transakcia
            Transakcia transakcia = new Transakcia();
            transakcia.setId(transakciaId);
            transakcia.setPredavajuci(predavajuci);
            transakcia.setNehnutelnost(nehnutelnost);
            transakcia.setKupujuci(kupujuci);

            nehnutelnost.setStav("predaný");

            et.begin();
            em.persist(transakcia);
            et.commit();

            return transakcia;

        } else {
            throw new IllegalArgumentException("Nehnutelnosť so zadaným id neexistuje alebo nie je na predaj");
        }
    }

    public List<Transakcia> getAll() {
        return em.createQuery("SELECT t FROM Transakcia t", Transakcia.class).getResultList();
    }

    public Transakcia getTransakcia(TransakciaId id) {
        return em.find(Transakcia.class, id);
    }

    public void updateTransakcia(Transakcia transakcia) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(transakcia);
        et.commit();
    }

    public void deleteTransakcia(Transakcia transakcia) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(transakcia);
        et.commit();
    }
}
