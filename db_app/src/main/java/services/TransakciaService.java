package services;

import daos.TransakciaDao;
import jakarta.persistence.EntityManager;
import tables.Transakcia;
import tables.TransakciaId;

import java.util.List;

public class TransakciaService {
    private TransakciaDao transakciaDao;

    public TransakciaService(EntityManager em) {
        this.transakciaDao = new TransakciaDao(em);
    }

    public TransakciaId insertTransakcia(int nehnutelnostId, int kupujuciId) {
        return transakciaDao.insertTransakcia(nehnutelnostId, kupujuciId).getId();
    }

    public void deleteTransakcia(TransakciaId id) {
        Transakcia transakcia = transakciaDao.getTransakcia(id);
        transakciaDao.deleteTransakcia(transakcia);
    }


    public void showAllValues() {
        List<Transakcia> transakcie = transakciaDao.getAll();
        for (Transakcia t : transakcie) {
            System.out.println(t);
        }
    }
}
