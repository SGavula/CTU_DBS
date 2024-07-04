DROP TABLE IF EXISTS Fotografia;
DROP TABLE IF EXISTS Prezera;
DROP TABLE IF EXISTS Transakcia;
DROP TABLE IF EXISTS Nehnutelnost;
DROP TABLE IF EXISTS Profil;
DROP TABLE IF EXISTS Oblubena_nehnutelnost;
DROP TABLE IF EXISTS Doporucil;
DROP TABLE IF EXISTS Predavajuci;
DROP TABLE IF EXISTS Kupujuci;
DROP TABLE IF EXISTS Uzivatel;
DROP TABLE IF EXISTS Kategoria;

CREATE TABLE Uzivatel (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL CONSTRAINT email_unique UNIQUE,
    heslo VARCHAR(100) NOT NULL,
    telefon CHAR(16) NOT NULL CHECK (telefon LIKE '+421 ___ ___ ___')
);

/*
INSERT INTO Uzivatel (email, heslo, telefon)
VALUES
    ('petra.novakova@email.com', '$2b$12$9gjUiUWtRVOEufIMr3qoyeyy7mqczA.xJ1gyvoEVGjFZ6FuaALsTm', '+421 123 456 789'),
    ('martin.horvath@email.com', '$2b$12$7gWek4qVz34aMVwB6ULCW.XHHoWiBPqq5EZfj2n.L2zD9Wb0t6bPO', '+421 234 567 890'),
    ('michaela.kovacova@email.com', '$2b$12$F9PnT38BuIqTImBZWip9HueyP8cbPA0/bZ7TPU3TJXgrz6S0g8IO.', '+421 345 678 901'),
    ('matej.holub@email.com', '$2b$12$ztvF3o5oqxxMA2s6r.WuKeJ1.mrt63Ez8aFh5Bn/m2y/MiPpMhTC.', '+421 456 789 012'),
    ('veronika.kovacikova@email.com', '$2b$12$8IQuXOK/JC6EoE30PMRvoe0BicvWfSMuTBVb5LST7IXLX5D1kqDCa', '+421 567 890 123'),
    ('jakub.molnar@email.com', '$2b$12$zBsdbt5ExJ5K2jBiGXYBBOz3jsOq6ZJEPHXX7s1eA1KlpkFQGxyIi', '+421 678 901 234'),
    ('natalia.balogova@email.com', '$2b$12$GtoU9oytAjQR/H9UsbIyTetXJi2xEH6iGkMP9dMx4h.6bMbmwQiLy', '+421 789 012 345'),
    ('adam.varga@email.com', '$2b$12$Z9sUSykgG/R8M1bjew9lpOlvO/G69WyQ6yHyuVoImBcwMZad3NhXq', '+421 890 123 456'),
    ('eva.bartosova@email.com', '$2b$12$zoJ.vUVN3StvO6vF2.eC6eUz2bc6OwOZwLU8t4JFLx0Wd/klPD0Mi', '+421 901 234 567'),
    ('tomas.hruska@email.com', '$2b$12$w4Z0yXyL/c9zDZ8HldJfze6n/BZ2wh.XCxCRBwylx1UoQabNDLpMy', '+421 012 345 678'),
    ('petra.kovacova@email.com', '$2b$12$Y.XJiukG37UBLOE9/mKg.uQV6ZSncwDU9XfZK7WnLBDAq7E7mQu7G', '+421 111 222 333'),
    ('martin.kovac@email.com', '$2b$12$v/NgltLMkRy1aVM5xgqu/uwfMLGp18YTe6Vh9D6nufCykP6bpNls2', '+421 444 555 666'),
    ('michaela.hruskova@email.com', '$2b$12$QP9/uT/DSMPvZgIkBWcI.Ov7ZbFLJmbRcIuJXmrCmPnPEoGjHlOm6', '+421 777 888 999'),
    ('matej.kriz@email.com', '$2b$12$04bHn1oYmiQcgN8FnsfATuFHFZWB8E.nQFdRh8iU/AlUY3Cqk5p.q', '+421 000 111 222'),
    ('veronika.jakubikova@email.com', '$2b$12$ANsK5vXVOKyoLXwvocSaNe0zrVwUCXz8yv/fXFEb4i4d77jUfhe6K', '+421 333 444 555');
*/

CREATE TABLE Profil (
    id SERIAL PRIMARY KEY,
    meno VARCHAR(50) NOT NULL,
    priezvisko VARCHAR(50) NOT NULL,
    datum_narodenia DATE NOT NULL,
    CONSTRAINT profil_unique UNIQUE (meno, priezvisko, datum_narodenia), -- Table level second key
    uzivatel_id INT NOT NULL CONSTRAINT fk_uzivatel REFERENCES Uzivatel(id) ON DELETE CASCADE ON UPDATE CASCADE, -- Foreign key, delete if original user was deleted
    UNIQUE(uzivatel_id)
);

INSERT INTO Profil (meno, priezvisko, datum_narodenia, uzivatel_id)
VALUES
    ('Petra', 'Nováková', '1990-05-15', 1),
    ('Martin', 'Horváth', '1985-08-20', 2),
    ('Michaela', 'Kovačová', '1992-02-10', 3),
    ('Natalia', 'Balogová', '1988-11-25', 7),
    ('Adam', 'Varga', '1983-04-30', 8),
    ('Eva', 'Bartošová', '1995-07-02', 9),
    ('Tomáš', 'Hruška', '1980-12-18', 10),
    ('Petra', 'Kovačová', '1991-03-05', 11),
    ('Veronika', 'Jakubíková', '1987-09-12', 15);

CREATE TABLE Predavajuci (
    uzivatel_id INT PRIMARY KEY REFERENCES Uzivatel(id) ON DELETE CASCADE ON UPDATE CASCADE, -- Foreign key
    realitna_kancelaria VARCHAR(100) NOT NULL
);

INSERT INTO Predavajuci (uzivatel_id, realitna_kancelaria)
VALUES
    (1, 'Dream house s.r.o'),
    (2, 'RK Domy a byty s.r.o'),
    (3, 'Moj domov s.r.o'),
    (7, 'Best Homes Group'),
    (8, 'Sunset Realty s.r.o'),
    (9, 'Golden Key Estates'),
    (10, 'Exclusive properties');

CREATE TABLE Kupujuci (
    uzivatel_id INT PRIMARY KEY REFERENCES Uzivatel(id) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
INSERT INTO Kupujuci (uzivatel_id)
VALUES
    (4),
    (5),
    (6),
    (11),
    (12),
    (13),
    (14),
    (15);
*/

CREATE TABLE Oblubena_nehnutelnost (
    id SERIAL PRIMARY KEY,
    kupujuci_id INT NOT NULL CONSTRAINT fk_kupujuci REFERENCES Kupujuci(uzivatel_id) ON DELETE CASCADE, -- Foreign key
    oblubena_nehnutelnost VARCHAR(200) NOT NULL,
    CONSTRAINT obl_neh_unique UNIQUE (kupujuci_id, oblubena_nehnutelnost) -- Table level second key
);

INSERT INTO Oblubena_nehnutelnost (kupujuci_id, oblubena_nehnutelnost)
VALUES
    (4, 'Moderný byt v centre mesta'),
    (5, 'Útulná chata v lesoch'),
    (6, 'Apartmán pri pláži'),
    (11, 'Rodinný dom s veľkou záhradou'),
    (11, 'Historický dom v historickom centre'),
    (12, 'Novostavba v tichom prostredí'),
    (14, 'Veľký pozemok s farmou na vidieku'),
    (14, 'Prenajatý byt s výhľadom na hory'),
    (14, 'Starobylý zámok so záhradou'),
    (15, 'Elegantný penthouse v luxusnom komplexe'),
    (15, 'Luxusný apartmán v historickom centre');

CREATE TABLE Doporucil (
    -- id SERIAL PRIMARY KEY,
    kupujuci_id INT NOT NULL CONSTRAINT fk_kupujuci REFERENCES Kupujuci(uzivatel_id), -- Foreign key
    kupujuci_s_dop_id INT NOT NULL CONSTRAINT fk_kupujuci_s_dop REFERENCES Kupujuci(uzivatel_id), -- Foreign key
    popis TEXT NOT NULL,
    PRIMARY KEY (kupujuci_id, kupujuci_s_dop_id)
);

INSERT INTO Doporucil (kupujuci_id, kupujuci_s_dop_id, popis)
VALUES
    (4, 5, 'Odporúčam Adama Vargu, pretože je spoľahlivý a mám skvelé skúsenosti s ním.'),
    (5, 6, 'Vysoko si cením rady a doporučenia od Evy Bartošovej, ktoré mi pomohli pri výbere vhodnej nehnuteľnosti.'),
    (6, 4, 'Natalia Balogová mi odporučila niekoľko skvelých možností nehnuteľností, ktoré zodpovedali mojim potrebám.'),
    (11, 12, 'Som veľmi spokojný s radami a odporúčaniami od kupujucého 12, ktorý mu pomohol nájsť ideálny dom pre jeho rodinu.'),
    (12, 13, 'Odporúčam kupujúceho 13, pretože mi pomohol pri hľadaní nehnuteľností.'),
    (13, 14, 'Veľmi oceňujem pomoc a podporu od kupujúceho 14, ktorý mu ukázal niekoľko skvelých nehnuteľností.'),
    (14, 15, 'Som vďačný kupujúcemu 15 za odporúčenia luxusných nehnuteľností, ktoré spĺňajú moje vysoké očakávania.'),
    (15, 11, 'Vypočul som si odporúčania od kupujúceho 11 a bol som veľmi spokojný s výsledkom.');


CREATE TABLE Kategoria (
    id SERIAL PRIMARY KEY,
    nazov VARCHAR(50) NOT NULL CONSTRAINT nazov_unique UNIQUE
);

INSERT INTO Kategoria ( nazov ) VALUES ('Byt');
INSERT INTO Kategoria ( nazov ) VALUES ('Dom');
INSERT INTO Kategoria ( nazov ) VALUES ('Reakreačná nehnuteľnosť');

CREATE TABLE Nehnutelnost (
    id SERIAL PRIMARY KEY,
    typ VARCHAR(50) NOT NULL,
    cena DECIMAL(8, 2) NOT NULL,
    plocha DECIMAL(10, 2) NOT NULL,
    stav VARCHAR(10) NOT NULL,
    popis TEXT,
    popisne_cislo VARCHAR(5) NOT NULL,
    mesto VARCHAR(100) NOT NULL,
    psc VARCHAR(10) NOT NULL,
    stat VARCHAR(50) NOT NULL,
    rok_stavby CHAR(4),
    GPS_suradnice VARCHAR(50) NOT NULL CONSTRAINT unique_gps UNIQUE,
    kategoria_id INT NOT NULL CONSTRAINT fk_kategoria REFERENCES Kategoria(id), -- Foreign key
    predavajuci_id INT NOT NULL CONSTRAINT fk_predavajuci REFERENCES Predavajuci(uzivatel_id) ON UPDATE CASCADE, -- Foreign key
    CONSTRAINT unique_neh UNIQUE (popisne_cislo, mesto, psc, stat)
);

INSERT INTO Nehnutelnost (typ, cena, plocha, stav, popis, popisne_cislo, mesto, psc, stat, rok_stavby, GPS_suradnice, kategoria_id, predavajuci_id)
VALUES
    ('1-izbový', 75000.00, 40.00, 'predaný', NULL, '001', 'Bratislava', '811 01', 'Slovensko', '2008', '147.148598, 17.107748', 1, 1),
    ('2-izbový', 120000.00, 65.00, 'predaný', 'Priestranný byt s dvoma izbami a balkónom', '002', 'Košice', '040 01', 'Slovensko', '2012', '48.720269, 21.257692', 1, 2),
    ('3-izbový', 180000.00, 90.00, 'na predaj','Rodinný dom s tromi izbami a záhradou', '003', 'Nitra', '949 01', 'Slovensko', '2005', '48.308124, 188.084032', 2, 3),
    ('4-izbový', 250000.00, 120.00, 'predaný', 'Priestranný dom s štyrmi izbami a terasou', '004', 'Trnava', '917 01', 'Slovensko', '2010', '48.373901, 137.587651', 2, 7),
    ('5-izbový', 350000.00, 150.00, 'na predaj', 'Veľký dom s päťizbovým priestorom a záhradou', '005', 'Žilina', '010 01', 'Slovensko', '2008', '49.224850, 418.739971', 2, 8),
    ('3-izbový', 90000.00, 80.00, 'predaný', 'Chata pri jazere v tichom prostredí', '006', 'Liptovský Mikuláš', '031 01', 'Slovensko', '1995', '49.083424, 9.612086', 3, 9),
    ('2-izbový', 120000.00, 100.00, 'na predaj', 'Chata na samote v horskom prostredí', '007', 'Banská Bystrica', '975 01', 'Slovensko', '1980', '48.738633, 199.142775', 3, 10),
    ('1-izbový', 80000.00, 70.00, 'predaný', NULL, '008', 'Prešov', '080 01', 'Slovensko', '1998', '49.003167, 21.239033', 3, 2),
    ('1-izbový', 85000.00, 45.00, 'predaný', 'Moderný byt s jednou izbou a vybavenou kuchyňou', '009', 'Bratislava', '811 02', 'Slovensko', '2015', '8.148598, 17.107748', 1, 3),
    ('2-izbový', 130000.00, 70.00, 'predaný', 'Priestranný byt s dvoma izbami a terasou', '010', 'Košice', '040 02', 'Slovensko', '2010', '48.720269, 221.257692', 1, 10),
    ('3-izbový', 200000.00, 110.00, 'na predaj', 'Moderný dom s tromi izbami a garážou', '011', 'Nitra', '949 02', 'Slovensko', '2018', '48.308124, 18.084032', 2, 10),
    ('4-izbový', 280000.00, 140.00, 'predaný', NULL, '012', 'Trnava', '917 02', 'Slovensko', '2005', '48.373901, 47.587651', 2, 2),
    ('5-izbový', 380000.00, 180.00, 'na predaj', 'Moderný dom s päťizbovým priestorom a bazénom', '013', 'Žilina', '010 02', 'Slovensko', '2012', '289.224850, 118.739971', 2, 1),
    ('4-izbový', 95000.00, 85.00, 'predaný', 'Chata na brehu rieky s možnosťou rybolovu', '014', 'Liptovský Mikuláš', '031 02', 'Slovensko', '2000', '549.083424, 19.612086', 3, 2),
    ('4-izbový', 130000.00, 110.00, 'predaný', NULL, '015', 'Banská Bystrica', '975 02', 'Slovensko', '1995', '48.738633, 19.142775', 3, 3);

CREATE TABLE Fotografia (
    id SERIAL PRIMARY KEY,
    nazov VARCHAR(50) NOT NULL,
    nehnutelnost_id INT NOT NULL CONSTRAINT fk_nehnutelnost REFERENCES Nehnutelnost(id) ON DELETE CASCADE, -- Foreign key
    CONSTRAINT fotografia_unique UNIQUE (nehnutelnost_id, nazov) -- Table level second key
);

CREATE TABLE Prezera (
    kupujuci_id INT NOT NULL CONSTRAINT fk_kupujuci REFERENCES Kupujuci(uzivatel_id), -- Foreign key
    nehnutelnost_id INT NOT NULL CONSTRAINT fk_nehnutelnost REFERENCES Nehnutelnost(id), -- Foreign key
    PRIMARY KEY (nehnutelnost_id, kupujuci_id)
);

INSERT INTO Prezera(kupujuci_id, nehnutelnost_id) VALUES
                                                      (5, 4),
                                                      (6, 1),
                                                      (4, 15),
                                                      (6, 10),
                                                      (6, 7),
                                                      (11, 2),
                                                      (12, 11),
                                                      (12, 10),
                                                      (13, 4),
                                                      (14, 4),
                                                      (15, 3),
                                                      (15, 2),
                                                      (15, 15),
                                                      (6, 15);

CREATE TABLE Transakcia (
    -- id SERIAL PRIMARY KEY,
    nehnutelnost_id INT NOT NULL CONSTRAINT fk_nehnutelnost REFERENCES Nehnutelnost(id) ON UPDATE CASCADE, -- Foreign key
    predavajuci_id INT NOT NULL CONSTRAINT fk_predavajuci REFERENCES Predavajuci(uzivatel_id) ON UPDATE CASCADE, -- Foreign key
    kupujuci_id INT NOT NULL CONSTRAINT fk_kupujuci REFERENCES Kupujuci(uzivatel_id) ON UPDATE CASCADE, -- Foreign key
    cas_vytvorenia TIMESTAMP NOT NULL CONSTRAINT cas_vytvorenia_unique UNIQUE,
    PRIMARY KEY (predavajuci_id, cas_vytvorenia),
    CONSTRAINT neh_unique UNIQUE (nehnutelnost_id), -- Table level second key
    CONSTRAINT cas_kupujuci_unique UNIQUE (kupujuci_id, cas_vytvorenia) -- Table level fourth key
);

INSERT INTO Transakcia (nehnutelnost_id, predavajuci_id, kupujuci_id, cas_vytvorenia)
VALUES
    (12, 1, 4, '2024-04-10 10:00:00'),
    (4, 2, 5, '2024-04-12 11:50:00'),
    (10, 3, 6, '2024-04-16 13:30:00'),
    (9, 7, 11, '2024-04-16 14:40:00'),
    (8, 8, 12, '2024-04-16 14:50:00'),
    (2, 9, 13, '2024-04-17 10:00:00'),
    (14, 10, 14, '2024-04-17 11:00:00'),
    (1, 1, 15, '2024-04-18 09:00:00'),
    (15, 2, 4, '2024-04-18 10:30:00'),
    (6, 3, 5, '2024-04-18 12:15:00');
