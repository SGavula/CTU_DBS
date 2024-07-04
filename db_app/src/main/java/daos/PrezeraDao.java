package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Kupujuci;
import tables.Nehnutelnost;
import tables.Prezera;
import tables.PrezeraId;

import java.util.List;

public class PrezeraDao {
    private EntityManager em;

    public PrezeraDao(EntityManager em) {
        this.em = em;
    }

    public Prezera insertPrezera(Kupujuci kupujuci, Nehnutelnost nehnutelnost) {
        EntityTransaction et = em.getTransaction();
        PrezeraId id = new PrezeraId();
        id.setKupujuciId(kupujuci.getId());
        id.setNehnutelnostId(nehnutelnost.getId());
        Prezera prezera = new Prezera();
        prezera.setId(id);
        prezera.setKupujuci(kupujuci);
        prezera.setNehnutelnost(nehnutelnost);

        et.begin();
        em.persist(prezera);
        et.commit();

        return prezera;
    }

    public List<Prezera> getAll() {
        return em.createQuery("SELECT p FROM Prezera p", Prezera.class).getResultList();
    }

    public Prezera getPrezera(PrezeraId id) {
        return em.find(Prezera.class, id);
    }

    public void updatePrezera(Prezera prezera) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(prezera);
        et.commit();
    }

    public void deletePrezera(Prezera prezera) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(prezera);
        et.commit();
    }
}
