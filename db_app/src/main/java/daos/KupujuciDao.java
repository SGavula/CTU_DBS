package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import tables.Kupujuci;
import tables.Nehnutelnost;
import tables.Predavajuci;

import java.util.List;

public class KupujuciDao {
    private EntityManager em;
    public KupujuciDao(EntityManager em) {
        this.em = em;
    }
    public Kupujuci insertKupujuci(Integer id) {
        EntityTransaction et = em.getTransaction();
        Kupujuci k = new Kupujuci();
        k.setId(id);

        et.begin();
        em.persist(k);
        et.commit();

        return k;
    }
    public List<Kupujuci> getAll() {
        return em.createQuery("SELECT k FROM Kupujuci k", Kupujuci.class).getResultList();
    }
    public Kupujuci getKupujuci(Integer id) {
        return em.find(Kupujuci.class, id);
    }
    public void updateKupujuci(Kupujuci k) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(k);
        et.commit();
    }
    public void deleteKupujuci(Kupujuci k) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(k);
        et.commit();
    }

    public List<Nehnutelnost> getAllViewedProperties(int kupujuciId) {
        TypedQuery<Nehnutelnost> query = em.createQuery(
                "SELECT n FROM Kupujuci k JOIN k.nehnutelnosti n WHERE k.id = :kupujuciId",
                Nehnutelnost.class);
        query.setParameter("kupujuciId", kupujuciId);
        return query.getResultList();
    }
}
