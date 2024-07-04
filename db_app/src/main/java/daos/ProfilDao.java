package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import tables.Profil;

import java.time.LocalDate;
import java.util.List;

public class ProfilDao {
    private EntityManager em;

    public ProfilDao(EntityManager em) {
        this.em = em;
    }

    public Profil insertProfil(String meno, String priezvisko, LocalDate datumNarodenia) {
        EntityTransaction et = em.getTransaction();
        Profil profil = new Profil();
        profil.setMeno(meno);
        profil.setPriezvisko(priezvisko);
        profil.setDatumNarodenia(datumNarodenia);

        et.begin();
        em.persist(profil);
        et.commit();

        return profil;
    }

    public List<Profil> getAll() {
        return em.createQuery("SELECT p FROM Profil p", Profil.class).getResultList();
    }

    public Profil getProfil(Integer id) {
        return em.find(Profil.class, id);
    }

    public void updateProfil(Profil profil) {

        EntityTransaction et = em.getTransaction();

        et.begin();
        em.merge(profil);
        et.commit();
    }

    public void deleteProfil(Profil profil) {
        EntityTransaction et = em.getTransaction();

        et.begin();
        em.remove(profil);
        et.commit();
    }
}
