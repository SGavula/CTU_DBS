package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Predavajuci;

import java.util.List;

public class PredavajuciDao {
    private EntityManager em;

    public PredavajuciDao(EntityManager em) {
        this.em = em;
    }

    public Predavajuci insertPredavajuci(Integer id, String realitnaKancelaria) {
        EntityTransaction et = em.getTransaction();
        Predavajuci p = new Predavajuci();
        p.setId(id);
        p.setRealitnaKancelaria(realitnaKancelaria);

        et.begin();
        em.persist(p);
        et.commit();

        return p;
    }

    public List<Predavajuci> getAll() {
        return em.createQuery("SELECT p FROM Predavajuci p", Predavajuci.class).getResultList();
    }

    public Predavajuci getPredavajuci(Integer id) {
        return em.find(Predavajuci.class, id);
    }

    public void updatePredavajuci(Predavajuci predavajuci) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(predavajuci);
        et.commit();
    }

    public void deletePredavajuci(Predavajuci predavajuci) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(predavajuci);
        et.commit();
    }
}
