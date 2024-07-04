DROP VIEW IF EXISTS Lacna_nehnutelnost;
DROP VIEW IF EXISTS Lacna_nehnutelnost_check;
DROP INDEX IF EXISTS idx_uzivatel_email;
DROP FUNCTION IF EXISTS kontrola_transakcie;
DROP FUNCTION IF EXISTS zmazat_uziv;

-- View without check option
/*
CREATE VIEW Lacna_nehnutelnost AS
    SELECT k.nazov, n.typ, n.cena, n.popisne_cislo, n.mesto, n.psc, n.stat FROM Nehnutelnost n
    INNER JOIN Kategoria k ON k.id = n.kategoria_id
    WHERE n.cena <= 100000;
*/

-- toto nefunguje, lebo je tu INNER JOIN, čo robí neplechu
/*
CREATE VIEW Lacna_nehnutelnost_check AS
    SELECT k.nazov, n.typ, n.cena, n.popisne_cislo, n.mesto, n.psc, n.stat FROM Nehnutelnost n
    INNER JOIN Kategoria k ON k.id = n.kategoria_id
    WHERE n.cena <= 100000
WITH CHECK OPTION;
*/

-- Without check option
CREATE VIEW Lacna_nehnutelnost AS
    SELECT *
    FROM Nehnutelnost
    WHERE cena <= 100000;

-- With check option
CREATE VIEW Lacna_nehnutelnost_check AS
    SELECT *
    FROM Nehnutelnost
    WHERE cena <= 100000
WITH CHECK OPTION;

CREATE VIEW Lacna_nehnutelnost_local_check AS
    SELECT *
    FROM Nehnutelnost
    WHERE cena <= 100000
WITH LOCAL CHECK OPTION;

CREATE VIEW Lacna_nehnutelnost_cascaded_check AS
    SELECT *
    FROM Nehnutelnost
    WHERE cena <= 100000
WITH CASCADED CHECK OPTION;

SELECT * FROM Lacna_nehnutelnost;
SELECT * FROM Lacna_nehnutelnost_check;

INSERT INTO Lacna_nehnutelnost (typ, cena, plocha, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 110000.00, 45.00, NULL, '057', 'Bratislava', '811 01', 'Slovensko', '2015', '58.265478, 85.111413', 1, 2);

-- ERROR: new row violates check option for view "lacna_nehnutelnost_check"
INSERT INTO Lacna_nehnutelnost_check (typ, cena, plocha, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 110000.00, 45.00, NULL, '055', 'Bratislava', '811 01', 'Slovensko', '2015', '55.265478, 85.111413', 1, 2);

-- ERROR: new row violates check option for view "lacna_nehnutelnost_local_check" Detail: Failing row contains (27, 1-izbový, 110000.00, 45.00, null, 055, Bratislava, 811 01, Slovensko, 2015, 55.265478, 85.111413, 1, 2).
INSERT INTO Lacna_nehnutelnost_local_check (typ, cena, plocha, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 110000.00, 45.00, NULL, '055', 'Bratislava', '811 01', 'Slovensko', '2015', '55.265478, 85.111413', 1, 2);

-- ERROR: new row violates check option for view "lacna_nehnutelnost_cascaded_check" Detail: Failing row contains (28, 1-izbový, 110000.00, 45.00, null, 055, Bratislava, 811 01, Slovensko, 2015, 55.265478, 85.111413, 1, 2).
INSERT INTO Lacna_nehnutelnost_cascaded_check (typ, cena, plocha, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 110000.00, 45.00, NULL, '055', 'Bratislava', '811 01', 'Slovensko', '2015', '55.265478, 85.111413', 1, 2);


SELECT * FROM Nehnutelnost;

-- Indexes
CREATE INDEX idx_uzivatel_email ON uzivatel (email);

SELECT * FROM uzivatel u WHERE u.email = 'sofia.jurkovicova@outlook.com';

-- Trigger
CREATE FUNCTION kontrola_transakcie()
RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.predavajuci_id = NEW.kupujuci_id THEN
        RAISE EXCEPTION 'A seller cannot sell a preperty to themselves';
    END IF;
    RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER transakcia_trig BEFORE INSERT OR UPDATE ON Transakcia
    FOR EACH ROW EXECUTE PROCEDURE kontrola_transakcie();

-- ERROR: A seller cannot sell a preperty to themselves Where: PL/pgSQL function kontrola_transakcie() line 4 at RAISE
INSERT INTO Transakcia (nehnutelnost_id, predavajuci_id, kupujuci_id, cas_vytvorenia)
    VALUES (13, 4, 4, '2024-05-03 13:00:00');

-- Transaction
-- SELECT * FROM Transakcia;
SELECT * FROM Nehnutelnost;
SELECT * FROM Transakcia;

DELETE FROM Nehnutelnost WHERE id = 17;
DELETE FROM Nehnutelnost WHERE id = 25;

SELECT * FROM Transakcia WHERE nehnutelnost_id = 3;

CREATE PROCEDURE zmazat_uziv(uziv_id INT)
AS $$
BEGIN
    IF EXISTS ( SELECT * FROM Uzivatel u WHERE u.id = uziv_id ) THEN
        IF EXISTS (
            SELECT * FROM Profil p WHERE p.uzivatel_id = uziv_id
        ) THEN
            DELETE FROM Profil p WHERE p.uzivatel_id = uziv_id;
            DELETE FROM uzivatel u WHERE u.id = uziv_id;
        ELSE
            DELETE FROM uzivatel u WHERE u.id = uziv_id;
        END IF;
    ELSE
        RAISE EXCEPTION 'The user does not exist';
    END IF;
END;
$$
LANGUAGE plpgsql;

/*
CREATE PROCEDURE vymazat_kup(kup_id INT)
AS $$
BEGIN
    IF EXISTS ( SELECT * FROM Kupujuci k WHERE k.uzivatel_id = kup_id ) THEN
        IF EXISTS (
            SELECT * FROM Transakcia t WHERE t.kupujuci_id = kup_id
        ) THEN
            DELETE FROM Transakcia t WHERE t.kupujuci_id = kup_id;
        END IF;

        IF EXISTS (
            SELECT * FROM Prezera p WHERE p.kupujuci_id = kup_id
        ) THEN
            DELETE FROM Prezera p WHERE p.kupujuci_id = kup_id;
        END IF;

        IF EXISTS (
            SELECT * FROM Doporucil d WHERE d.kupujuci_id = kup_id
        ) THEN
            DELETE FROM Doporucil d WHERE d.kupujuci_id = kup_id;
        END IF;

        IF EXISTS (
            SELECT * FROM Doporucil d WHERE d.kupujuci_s_dop_id = kup_id
        ) THEN
            DELETE FROM Doporucil d WHERE d.kupujuci_s_dop_id = kup_id;
        END IF;

        IF EXISTS (
            SELECT * FROM Profil p WHERE p.uzivatel_id = kup_id
        ) THEN
            DELETE FROM Profil p WHERE p.uzivatel_id = kup_id;
            DELETE FROM uzivatel u WHERE u.id = kup_id;
        ELSE
            DELETE FROM uzivatel u WHERE u.id = kup_id;
        END IF;
    ELSE
        RAISE EXCEPTION 'Užívateľ nie je kupujúci alebo neexistuje';
    END IF;
END;
$$
    LANGUAGE plpgsql;

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
CALL vymazat_kup(152465581);
COMMIT TRANSACTION;

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
CALL vymazat_kup(4);
COMMIT TRANSACTION;
ROLLBACK;

SELECT * FROM Kupujuci k WHERE k.uzivatel_id = 4;
*/

/*
CREATE FUNCTION kontrola_transakcie()
    RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.predavajuci_id = NEW.kupujuci_id THEN
        RAISE EXCEPTION 'A seller cannot sell a preperty to themselves';
    END IF;
    RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER transakcia_trig BEFORE INSERT OR UPDATE ON Transakcia
    FOR EACH ROW EXECUTE PROCEDURE kontrola_transakcie();

-- ERROR: A seller cannot sell a preperty to themselves Where: PL/pgSQL function kontrola_transakcie() line 4 at RAISE
INSERT INTO Transakcia (nehnutelnost_id, predavajuci_id, kupujuci_id, cas_vytvorenia)
VALUES (13, 4, 4, CURRENT_TIMESTAMP);
*/