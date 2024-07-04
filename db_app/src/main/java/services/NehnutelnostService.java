package services;

import daos.KategoriaDao;
import daos.KupujuciDao;
import daos.NehnutelnostDao;
import jakarta.persistence.EntityManager;
import tables.Kupujuci;
import tables.Nehnutelnost;

import java.math.BigDecimal;
import java.util.List;

public class NehnutelnostService {
    private NehnutelnostDao nehnutelnostDao;
    private KupujuciDao kupujuciDao;
    private KategoriaDao kategoriaDao;

    public NehnutelnostService(EntityManager em) {
        this.nehnutelnostDao = new NehnutelnostDao(em);
        this.kupujuciDao = new KupujuciDao(em);
        this.kategoriaDao = new KategoriaDao(em);
    }

    public int insertNehnutelnost(String typ, BigDecimal cena, BigDecimal plocha, String stav, String popis, String popisneCislo, String mesto, String psc, String stat, String rokStavby, String gpsSuradnice, int kategoriaId, int predavajuciId, List<Kupujuci> kupujuci) {
        // Insert
        return nehnutelnostDao.insertNehnutelnost(
                typ, cena, plocha, stav, popis, popisneCislo,
                mesto, psc, stat, rokStavby, gpsSuradnice,
                kategoriaId, predavajuciId, kupujuci
        ).getId();
    }

    public void deleteNehnutelnost(Integer id) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnostDao.deleteNehnutelnost(nehnutelnost);
    }

    public void showAllViewers(Integer id) {
        List<Kupujuci> viewers = nehnutelnostDao.getAllViewers(id);
        for (Kupujuci v : viewers) {
            System.out.println(v);
        }
    }

    public void showAllValues() {
        List<Nehnutelnost> nehnutelnosti = nehnutelnostDao.getAll();
        for (Nehnutelnost n : nehnutelnosti) {
            System.out.println(n);
            for (Kupujuci k : n.getKupujuci()) {
                System.out.println(k);
            }
        }
    }

    public void updateTyp(Integer id, String newTyp) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setTyp(newTyp);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateCena(Integer id, BigDecimal newCena) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setCena(newCena);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updatePlocha(Integer id, BigDecimal newPlocha) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setPlocha(newPlocha);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateStav(Integer id, String newStav) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setStav(newStav);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updatePopis(Integer id, String newPopis) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setPopis(newPopis);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updatePopisneCislo(Integer id, String newPopisneCislo) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setPopisneCislo(newPopisneCislo);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateMesto(Integer id, String newMesto) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setMesto(newMesto);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updatePsc(Integer id, String newPsc) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setPsc(newPsc);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateStat(Integer id, String newStat) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setStat(newStat);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateRokStavby(Integer id, String newRokStavby) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setRokStavby(newRokStavby);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateGpsSuradnice(Integer id, String newGpsSuradnice) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setGpsSuradnice(newGpsSuradnice);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateKategoria(Integer id, int newKategoriaId) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setKategoriaId(newKategoriaId);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updatePredavajuci(Integer id, int newPredavajuciId) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setPredavajuciId(newPredavajuciId);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

    public void updateKupujuci(Integer id, List<Kupujuci> newKupujuci) {
        Nehnutelnost nehnutelnost = nehnutelnostDao.getNehnutelnost(id);
        nehnutelnost.setKupujuci(newKupujuci);
        nehnutelnostDao.updateNehnutelnost(nehnutelnost);
    }

}
