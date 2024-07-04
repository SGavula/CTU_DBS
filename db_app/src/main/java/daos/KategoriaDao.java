package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Kategoria;

import java.util.List;

public class KategoriaDao {
    private EntityManager em;

    public KategoriaDao(EntityManager em) {
        this.em = em;
    }

    public Kategoria insertKategoria(String nazov) {
        EntityTransaction et = em.getTransaction();
        Kategoria k = new Kategoria();
        k.setNazov(nazov);

        et.begin();
        em.persist(k);
        et.commit();

        return k;
    }

    public List<Kategoria> getAll() {
        return em.createQuery("SELECT k FROM Kategoria k", Kategoria.class).getResultList();
    }

    public Kategoria getKategoria(Integer id) {
        return em.find(Kategoria.class, id);
    }

    public void updateKategoria(Kategoria k) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(k);
        et.commit();
    }

    public void deleteKategoria(Kategoria k) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(k);
        et.commit();
    }
}
