import daos.*;
import jakarta.persistence.*;
import services.KupujuciService;
import services.NehnutelnostService;
import services.PredavajuciService;
import services.TransakciaService;
import tables.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ApplicationPU");
        EntityManager em = emf.createEntityManager();

        NehnutelnostService nehnutelnostService = new NehnutelnostService(em);
        KupujuciService kupujuciService = new KupujuciService(em);
        PredavajuciService predavajuciService = new PredavajuciService(em);
        TransakciaService transakciaService = new TransakciaService(em);


//        System.out.println("Table before inserting new value");
//        nehnutelnostService.showAllValues();
//
//        System.out.println("");
//
//        int nehnutelnostId = insertNehnutelnost(nehnutelnostService, kupujuciService);
//
//        System.out.println("Table after inserting new value");
//        nehnutelnostService.showAllValues();
//
//        System.out.println("");
//
//        // Update operation
//        nehnutelnostService.updateCena(nehnutelnostId, BigDecimal.valueOf(95000));
//        nehnutelnostService.updatePlocha(nehnutelnostId, BigDecimal.valueOf(50));
//        nehnutelnostService.updatePopis(nehnutelnostId, "Tento útulný 1-izbový byt sa nachádza v tichom a pokojnom prostredí mesta Martin. Je ideálny pre jednotlivca alebo pár, ktorý hľadá pohodlné a praktické bývanie. Byt ponúka dostatok priestoru s celkovou plochou 50 m², ktorá zahŕňa svetlú a priestrannú obývaciu izbu spojenú s kuchynským kútom, modernú kúpeľňu a úložný priestor.");
//
//        System.out.println("Table after updating new value");
//        nehnutelnostService.showAllValues();
//
//        System.out.println("");
//
//        // Delete operation
//        nehnutelnostService.deleteNehnutelnost(nehnutelnostId);
//
//        nehnutelnostService.showAllValues();
//
//        System.out.println("");

        // Output of descendants with patent fields
//        kupujuciService.showAllValues();
//        predavajuciService.showAllValues();
//
//        System.out.println("All viewers that viewed property with id 15");
//        nehnutelnostService.showAllViewers(15);
//
//        System.out.println("All properties that were viewed by buyer with id 12");
//        kupujuciService.showAllViewedProperties(12);
//
        transactionFromCP4(transakciaService, nehnutelnostService);

        em.close();
        emf.close();
    }

    private static int insertNehnutelnost(NehnutelnostService nehnutelnostService, KupujuciService kupujuciService) {
        // Values
        String typ = "1-izbový";
        BigDecimal cena = BigDecimal.valueOf(90000);
        BigDecimal plocha = BigDecimal.valueOf(44);
        String stav = "na predaj";
        String popis = null;
        String popisneCislo = "015";
        String mesto = "Martin";
        String psc = "120 32";
        String stat = "Slovensko";
        String rokStavby = "2000";
        String gpsSuradnice = "137.598145, 213.188448";
        int kategoriaId = 1;
        int predavajuciId = 1;

        List<Kupujuci> kupujuci = new ArrayList<>();
        kupujuci.add(kupujuciService.getKupujuci(4));
        kupujuci.add(kupujuciService.getKupujuci(5));
        kupujuci.add(kupujuciService.getKupujuci(6));

        // Insert
        return nehnutelnostService.insertNehnutelnost(
                typ,
                cena,
                plocha,
                stav,
                popis,
                popisneCislo,
                mesto,
                psc,
                stat,
                rokStavby,
                gpsSuradnice,
                kategoriaId,
                predavajuciId,
                kupujuci
        );
    }

    private static void transactionFromCP4(TransakciaService transakciaService, NehnutelnostService nehnutelnostService) {
        TransakciaId transakciaId = transakciaService.insertTransakcia(18, 11);
        nehnutelnostService.updateStav(18, "predaný");
        transakciaService.showAllValues();
        System.out.println("");
        nehnutelnostService.showAllValues();
        System.out.println("");
        transakciaService.deleteTransakcia(transakciaId);
        nehnutelnostService.updateStav(3, "na predaj");
        nehnutelnostService.showAllValues();
    }
}

//try {
//    em.getTransaction().begin();
//    TransakciaId transakciaId = transakciaService.insertTransakcia(3, 11);
//    transakciaService.showAllValues();
//    nehnutelnostService.showAllValues();
//    transakciaService.deleteTransakcia(transakciaId);
//    nehnutelnostService.updateStav(3, "na predaj");
//    nehnutelnostService.showAllValues();
//    em.getTransaction().commit();
//} catch (Exception e) {
//    em.getTransaction().rollback();
//    e.printStackTrace();
//} finally {
//    em.close();
//    emf.close();
//}