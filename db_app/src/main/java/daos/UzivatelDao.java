package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Uzivatel;

import java.util.List;

public class UzivatelDao {
    private EntityManager em;

    public UzivatelDao(EntityManager em) {
        this.em = em;
    }

    public Uzivatel insertUzivatel(String email, String heslo, String telefon) {
        EntityTransaction et = em.getTransaction();

        Uzivatel uzivatel = new Uzivatel();
        uzivatel.setEmail(email);
        uzivatel.setHeslo(heslo);
        uzivatel.setTelefon(telefon);

        et.begin();
        em.persist(uzivatel);
        et.commit();

        return uzivatel;
    }

    public List<Uzivatel> getAll() {
        return em.createQuery("SELECT u FROM Uzivatel u", Uzivatel.class).getResultList();
    }

    public Uzivatel getUzivatel(Integer id) {
        return em.find(Uzivatel.class, id);
    }

    public void updateUzivatel(Uzivatel uzivatel) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(uzivatel);
        et.commit();
    }

    public void deleteUzivatel(Uzivatel uzivatel) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(uzivatel);
        et.commit();
    }
}
