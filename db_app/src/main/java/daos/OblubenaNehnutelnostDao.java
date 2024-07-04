package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Kupujuci;
import tables.OblubenaNehnutelnost;

import java.util.List;

public class OblubenaNehnutelnostDao {
    private EntityManager em;

    public OblubenaNehnutelnostDao(EntityManager em) {
        this.em = em;
    }

    public OblubenaNehnutelnost insertOblubenaNehnutelnost(Kupujuci kupujuci, String oblubenaNehnutelnost) {
        EntityTransaction et = em.getTransaction();
        OblubenaNehnutelnost o = new OblubenaNehnutelnost();
        o.setKupujuci(kupujuci);
        o.setOblubenaNehnutelnost(oblubenaNehnutelnost);

        et.begin();
        em.persist(o);
        et.commit();

        return o;
    }

    public List<OblubenaNehnutelnost> getAll() {
        return em.createQuery("SELECT o FROM OblubenaNehnutelnost o", OblubenaNehnutelnost.class).getResultList();
    }

    public OblubenaNehnutelnost getOblubenaNehnutelnost(Integer id) {
        return em.find(OblubenaNehnutelnost.class, id);
    }

    public void updateOblubenaNehnutelnost(OblubenaNehnutelnost oblubenaNehnutelnost) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(oblubenaNehnutelnost);
        et.commit();
    }

    public void deleteOblubenaNehnutelnost(OblubenaNehnutelnost oblubenaNehnutelnost) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.remove(oblubenaNehnutelnost);
        et.commit();
    }
}
