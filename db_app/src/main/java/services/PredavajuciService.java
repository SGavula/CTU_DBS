package services;

import daos.PredavajuciDao;
import jakarta.persistence.EntityManager;
import tables.Predavajuci;

import java.util.List;

public class PredavajuciService {
    private PredavajuciDao predavajuciDao;

    public PredavajuciService(EntityManager em) {
        this.predavajuciDao = new PredavajuciDao(em);
    }

    public void showAllValues() {
        List<Predavajuci> predavajuci = predavajuciDao.getAll();
        for (Predavajuci p : predavajuci) {
            System.out.println(p);
        }
    }
}
