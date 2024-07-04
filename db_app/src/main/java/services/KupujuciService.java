package services;

import daos.KupujuciDao;
import jakarta.persistence.EntityManager;
import tables.Kupujuci;
import tables.Nehnutelnost;

import java.util.List;

public class KupujuciService {
    private KupujuciDao kupujuciDao;

    public KupujuciService(EntityManager em) {
        this.kupujuciDao = new KupujuciDao(em);
    }

    public Kupujuci getKupujuci(Integer id) {
        return kupujuciDao.getKupujuci(id);
    }

    public void showAllValues() {
        List<Kupujuci> kupujuci = kupujuciDao.getAll();
        for (Kupujuci k : kupujuci) {
            System.out.println(k);
        }
    }

    public void showAllViewedProperties(int kupujuciId) {
        List<Nehnutelnost> viewed = kupujuciDao.getAllViewedProperties(kupujuciId);
        for (Nehnutelnost v : viewed) {
            System.out.println(v);
        }
    }
}
