DROP VIEW IF EXISTS Lacna_nehnutelnost;
DROP VIEW IF EXISTS lacna_nehnutelnost_check;
DROP VIEW IF EXISTS lacna_nehnutelnost_local_check;
DROP VIEW IF EXISTS lacna_nehnutelnost_cascaded_check;
DROP INDEX IF EXISTS idx_uzivatel_email;
DROP FUNCTION IF EXISTS kontrola_transakcie;
DROP TRIGGER IF EXISTS kontrola_ceny_trigger ON Nehnutelnost;
DROP FUNCTION IF EXISTS kontrola_cena;
DROP PROCEDURE IF EXISTS pridat_transakciu;

-- Trigger
CREATE OR REPLACE FUNCTION kontrola_cena()
RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.cena <= 0 THEN
        RAISE EXCEPTION 'Zadaná cena nie je validná';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER kontrola_ceny_trigger BEFORE INSERT OR UPDATE ON Nehnutelnost
    FOR EACH ROW EXECUTE FUNCTION kontrola_cena();

INSERT INTO Nehnutelnost (typ, cena, plocha, stav, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
('4-izbový', 130000.00, 120.00, 'na predaj', NULL, '018', 'Banská Bystrica', '974 02', 'Slovensko', '2001', '48.738633, 125.142775', 2, 1);

SELECT * FROM nehnutelnost;

-- Transactions
CREATE PROCEDURE pridat_transakciu(neh_id INT, kup_id INT)
AS $$
DECLARE
    pred_id INT;
    stav VARCHAR(10);
BEGIN
    SELECT n.stav INTO stav FROM Nehnutelnost n WHERE n.id = neh_id;
    IF FOUND AND stav = 'na predaj' THEN
        pred_id := (SELECT n.predavajuci_id FROM Nehnutelnost n WHERE n.id = neh_id);
        INSERT INTO Transakcia (nehnutelnost_id, predavajuci_id, kupujuci_id, cas_vytvorenia)
        VALUES
            (neh_id, pred_id, kup_id, CURRENT_TIMESTAMP);

        UPDATE Nehnutelnost n
        SET stav = 'predaný' WHERE n.id = neh_id;
    ELSE
        RAISE EXCEPTION 'Nehnutelnosť so zadaným id neexistuje alebo nie je na predaj';
    END IF;
END;
$$ LANGUAGE plpgsql;

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
CALL pridat_transakciu(1, 11);
COMMIT TRANSACTION;
ROLLBACK;

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
CALL pridat_transakciu(3, 11);
COMMIT TRANSACTION;

SELECT * FROM transakcia WHERE nehnutelnost_id = 3;

SELECT * FROM transakcia;

-- View
CREATE VIEW Lacna_nehnutelnost AS
SELECT *
FROM Nehnutelnost
WHERE cena <= 100000
WITH CHECK OPTION;

SELECT * FROM Lacna_nehnutelnost;

INSERT INTO Lacna_nehnutelnost (typ, cena, plocha, stav, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 110000.00, 45.00, 'na predaj', NULL, '058', 'Bratislava', '812 01', 'Slovensko', '2015', '59.265478, 85.111413', 1, 2);

INSERT INTO Lacna_nehnutelnost (typ, cena, plocha, stav, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 90000.00, 45.00, 'na predaj', NULL, '055', 'Bratislava', '811 01', 'Slovensko', '2015', '55.265478, 85.111413', 1, 2);

SELECT * FROM Lacna_nehnutelnost;

SELECT * FROM Nehnutelnost;

-- Indexes
CREATE INDEX idx_uzivatel_email ON uzivatel (email);
SELECT * FROM uzivatel u WHERE u.email = 'sofia.jurkovicova@outlook.com';

