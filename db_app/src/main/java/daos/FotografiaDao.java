package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Fotografia;

import java.util.List;

public class FotografiaDao {
    private EntityManager em;

    public FotografiaDao(EntityManager em) {
        this.em = em;
    }

    public Fotografia insertFotografia(String nazov) {
        EntityTransaction et = em.getTransaction();
        Fotografia f = new Fotografia();
        f.setNazov(nazov);

        et.begin();
        em.persist(f);
        et.commit();

        return f;
    }

    public List<Fotografia> getAll() {
        return em.createQuery("SELECT f FROM Fotografia f", Fotografia.class).getResultList();
    }

    public Fotografia getFotografia(Integer id) {
        return em.find(Fotografia.class, id);
    }

    public void updateFotografia(Fotografia f) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(f);
        et.commit();
    }

    public void deleteFotografia(Fotografia f) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(f);
        et.commit();
    }
}

