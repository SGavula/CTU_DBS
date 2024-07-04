SELECT * FROM uzivatel;

-- INNER (vnutorné spojenie) tabuliek
--  Vypis vsetkych užívateľov, ktorý majú profil
SELECT p.meno, p.priezvisko FROM Profil p
                                     INNER JOIN uzivatel u ON u.id = p.uzivatel_id;

-- Vypis kupujucich (meno priezvisko), ktorý majú profil
SELECT p.meno, p.priezvisko FROM Profil p
                                     INNER JOIN uzivatel u ON u.id = p.uzivatel_id
                                     INNER JOIN Kupujuci k ON k.uzivatel_id = u.id;

-- Vypis predavajucich, ktorý majú profil
SELECT p.meno, p.priezvisko FROM Profil p
                                     INNER JOIN Uzivatel u ON u.id = p.uzivatel_id
                                     INNER JOIN Predavajuci pr ON pr.uzivatel_id = u.id;

-- Vypisat kupujucich (meno priezvisko) a transakciu a čas vytvorenia, ktory vytvorili transakciu
SELECT p.meno, p.priezvisko, t.cas_vytvorenia, n.popis FROM Profil p
                                                                INNER JOIN Uzivatel u ON u.id = p.uzivatel_id
                                                                INNER JOIN Kupujuci k ON k.uzivatel_id = u.id
                                                                INNER JOIN Transakcia t ON t.kupujuci_id = k.uzivatel_id
                                                                INNER JOIN Nehnutelnost n ON n.id = t.nehnutelnost_id;

-- Vypis predavajuceho (meno, priezvisko) a ake nehnutelnosti pridal a vypis tieto atributy tej nehnutelnosti: kategoria, cena, popis, adresa
SELECT p.meno, p.priezvisko, k.nazov, n.typ, n.popis, n.cena, n.popisne_cislo, n.mesto, n.psc, n.stat FROM Profil p
                                                                                                               INNER JOIN Uzivatel u ON u.id = p.uzivatel_id
                                                                                                               INNER JOIN Predavajuci pr ON pr.uzivatel_id = u.id
                                                                                                               INNER JOIN Nehnutelnost n ON n.predavajuci_id = pr.uzivatel_id
                                                                                                               INNER JOIN Kategoria k ON k.id = n.kategoria_id;

-- LEFT INNER JOIN
-- Vráť všetkých kupujúcich s ich obľubenými nehnuteľnosťami
SELECT u.id, u.email, p.meno, p.priezvisko, ob.oblubena_nehnutelnost FROM Uzivatel u
                                                                              LEFT JOIN Profil p ON p.uzivatel_id = u.id
                                                                              INNER JOIN Kupujuci k ON k.uzivatel_id = u.id
                                                                              LEFT JOIN Oblubena_nehnutelnost ob ON ob.kupujuci_id = k.uzivatel_id;

-- RIGHT JOIN
SELECT u.id, u.email, p.meno, p.priezvisko FROM Profil p
                                                    RIGHT JOIN Uzivatel u ON u.id = p.uzivatel_id;

-- Podmineky na data
SELECT p.meno, p.priezvisko, p.datum_narodenia FROM Profil p
                                                        INNER JOIN Uzivatel u ON p.uzivatel_id = u.id
WHERE p.datum_narodenia BETWEEN '1980-01-01' AND '1989-12-31';

SELECT k.nazov, n.typ, n.cena, n.popis, n.GPS_suradnice FROM Nehnutelnost n
                                                                 INNER JOIN Kategoria k on n.kategoria_id = k.id
WHERE n.cena <= 100000;

-- Cenu môžem zadavať s apostrofami aj normálne bez apostrofov
SELECT k.nazov, n.typ, n.cena, n.popis, n.GPS_suradnice FROM Nehnutelnost n
                                                                 INNER JOIN Kategoria k on n.kategoria_id = k.id
WHERE n.cena <= '100000';

-- Vypis vsetky byty
SELECT n.id,  k.nazov, n.cena, n.mesto FROM Nehnutelnost n
                                                INNER JOIN Kategoria k ON k.id = n.kategoria_id
WHERE k.nazov = 'Byt';

-- Vyber všetky nehnuteľnosti, ktorých GPS súradnice sa začínajú na 48...
SELECT n.id, n.popis, n.popisne_cislo, n.mesto, n.psc, n.stat, n.GPS_suradnice FROM Nehnutelnost n
WHERE n.GPS_suradnice LIKE '48%';

-- Vypočítaj koľko nehnuteľností patrí do kategórie byty, koľko do kategórie domy a koľko do kategórie rekreačná nehnuteľnosť
SELECT k.nazov, count(*) FROM Nehnutelnost n
                                  INNER JOIN Kategoria k ON k.id = n.kategoria_id
GROUP BY k.nazov;

SELECT n.id, k.nazov FROM Nehnutelnost n
                              INNER JOIN Kategoria k ON k.id = n.kategoria_id;

-- Spočítaj všetky fotky pri každej nehnuteľnosti
SELECT n.id, n.typ, n.cena, n.mesto, COUNT(*) FROM Fotografia f
                                                       INNER JOIN Nehnutelnost n ON n.id = f.nehnutelnost_id
GROUP BY n.id, n.typ, n.cena, n.mesto;

-- Vypis nehnutelnosti, ktoré majú počet fotiek menšie ako 3000
SELECT n.typ, n.cena, n.mesto, COUNT(*) FROM Fotografia f
                                                 INNER JOIN Nehnutelnost n ON n.id = f.nehnutelnost_id
GROUP BY n.typ, n.cena, n.mesto
HAVING COUNT(*) < 4000;

-- Vypis najnižšiu, najvššiu a priemernú cenu nehnuteľností
SELECT k.nazov, n.typ, n.id, n.cena, n.mesto FROM Nehnutelnost n
                                                      INNER JOIN Kategoria k ON k.id = n.kategoria_id
WHERE n.cena = (SELECT MAX(cena) FROM Nehnutelnost);

SELECT k.nazov, n.typ, n.id, n.cena, n.mesto FROM Nehnutelnost n
                                                      INNER JOIN Kategoria k ON k.id = n.kategoria_id
WHERE n.cena = (SELECT MIN(cena) FROM Nehnutelnost);

SELECT AVG(n.cena) FROM Nehnutelnost n;

-- UNION
-- Vyber transakcie, ktoré boli uskutočnené pred dátumom:2024-04-12 a po dátume 2024-04-16
SELECT CONCAT(t.predavajuci_id, '_', t.cas_vytvorenia), t.cas_vytvorenia, n.popis, n.cena, n.mesto FROM Transakcia t
                                                                 INNER JOIN Nehnutelnost n ON n.id = t.nehnutelnost_id  WHERE t.cas_vytvorenia < '2024-04-12 00:00:00'
UNION
SELECT CONCAT(t.predavajuci_id, '_', t.cas_vytvorenia), t.cas_vytvorenia, n.popis, n.cena, n.mesto FROM Transakcia t
                                                                 INNER JOIN Nehnutelnost n ON n.id = t.nehnutelnost_id  WHERE t.cas_vytvorenia > '2024-04-16 24:00:00';

-- Zotrieď nehnuteľnosti podľa roku stavby - od najstarších po najmladšie a vypíš posledných 5
SELECT * FROM Nehnutelnost n
ORDER BY n.rok_stavby DESC
OFFSET 10
    LIMIT 5;

SELECT * FROM Nehnutelnost n
WHERE n.cena > (SELECT AVG(cena) FROM Nehnutelnost)

