package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Doporucil;
import tables.DoporucilId;
import tables.Kupujuci;

import java.util.List;

public class DoporucilDao {
    private EntityManager em;
    public DoporucilDao(EntityManager em) {
        this.em = em;
    }
    public Doporucil insertDoporucil(Kupujuci kupujuci, Kupujuci kupujuciSDop, String popis) {
        EntityTransaction et = em.getTransaction();
        DoporucilId id = new DoporucilId();
        id.setKupujuciId(kupujuci.getId());
        id.setKupujuciSDopId(kupujuciSDop.getId());
        Doporucil doporucil = new Doporucil();
        doporucil.setId(id);
        doporucil.setKupujuci(kupujuci);
        doporucil.setKupujuciSDop(kupujuciSDop);
        doporucil.setPopis(popis);

        et.begin();
        em.persist(doporucil);
        et.commit();

        return doporucil;
    }
    public List<Doporucil> getAll() {
        return em.createQuery("SELECT d FROM Doporucil d", Doporucil.class).getResultList();
    }
    public Doporucil getDoporucil(DoporucilId id) {
        return em.find(Doporucil.class, id);
    }
    public void updateDoporucil(Doporucil d) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(d);
        et.commit();
    }
    public void deleteDoporucil(Doporucil d) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(d);
        et.commit();
    }
}
