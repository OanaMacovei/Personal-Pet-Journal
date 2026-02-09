CREATE TABLE Utilizatori(
	ID_User INTEGER NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE,
	parola VARCHAR(100) NOT NULL,
	rol ENUM('MEDIC', 'STAPAN') NOT NULL,
	nume_complet VARCHAR(100),
	nr_telefon VARCHAR(20),
    email VARCHAR(50),
    CONSTRAINT utilizator_pk PRIMARY KEY (ID_User) 
);

INSERT INTO Utilizatori (username, parola, rol, nume_complet, nr_telefon, email)
VALUES ('medic1', 'med123', 'MEDIC', 'Dr. Saruman', '0734852169', 'medic.official@gmail.com');

INSERT INTO Utilizatori (username, parola, rol, nume_complet, nr_telefon, email)
VALUES ('aragorn', '1234', 'STAPAN', 'Aragorn', '0768553410', 'aragorn.theMight@lotr.com');

INSERT INTO Utilizatori (username, parola, rol, nume_complet, nr_telefon, email)
VALUES ('bilbo', '5678', 'STAPAN', 'Bilbo Baggins', '0799562481', 'bilbo.baggins@lotr.com');


SELECT * FROM Utilizatori;

CREATE TABLE Animale(
    ID_Animal INTEGER NOT NULL AUTO_INCREMENT,
    ID_Stapan INTEGER NOT NULL,
    nume_animal VARCHAR(50),
    specie VARCHAR(50),
    rasa VARCHAR(50),
    varsta INTEGER,
    greutate DECIMAL(5, 2),
    gen VARCHAR(255),
    CONSTRAINT animal_pk PRIMARY KEY (ID_Animal),
    CONSTRAINT proprietari_fk FOREIGN KEY (ID_Stapan) REFERENCES Utilizatori(ID_User) ON DELETE CASCADE
);

INSERT INTO Animale (ID_Stapan, nume_animal, specie, rasa, varsta, greutate, gen)
VALUES (4, 'Patrocle', 'Caine', 'Teckel', 2, 2.5, 'M');

INSERT INTO Animale (ID_Stapan, nume_animal, specie, rasa, varsta, greutate, gen)
VALUES (5, 'Spot', 'Caine', 'Labrador', 4, 4.6, 'M');


CREATE TABLE Vaccinuri(
    ID_Vaccin INTEGER NOT NULL AUTO_INCREMENT,
    ID_Animal INTEGER NOT NULL,
    nume_vaccin VARCHAR(255),
    data_administrare DATE,
    CONSTRAINT vaccin_pk PRIMARY KEY (ID_Vaccin),
    CONSTRAINT animale_vaccin_fk FOREIGN KEY (ID_Animal) REFERENCES Animale(ID_Animal) ON DELETE CASCADE
);

INSERT INTO Vaccinuri (ID_Animal, nume_vaccin, data_administrare) 
VALUES (10, 'Antirabic', '2025-10-15');

INSERT INTO Vaccinuri (ID_Animal, nume_vaccin, data_administrare) 
VALUES (10, 'Polivalent', '2025-11-20');

INSERT INTO Vaccinuri (ID_Animal, nume_vaccin, data_administrare) 
VALUES (11, 'Parvoviroza', '2025-12-10');


CREATE TABLE IstoricMedical(
    ID_IstoricMedical INTEGER NOT NULL AUTO_INCREMENT,
    ID_Animal INTEGER NOT NULL,
    data DATE,
    motiv VARCHAR(255),
    diagnostic VARCHAR(255),
    tratament VARCHAR(255),
    nume_medic VARCHAR(100),
    CONSTRAINT istoric_medical_pk PRIMARY KEY (ID_IstoricMedical),
    CONSTRAINT animale_istoric_fk FOREIGN KEY (ID_Animal) REFERENCES Animale(ID_Animal) ON DELETE CASCADE
);

INSERT INTO IstoricMedical (ID_Animal, data, motiv, diagnostic, tratament, nume_medic)
VALUES (10, '2025-12-01', 'Control anual', 'Sanatos', 'Niciunul', 'Dr. Gandalf');

INSERT INTO IstoricMedical (ID_Animal, data, motiv, diagnostic, tratament, nume_medic)
VALUES (10, '2026-01-05', 'Schiopatare', 'Entorsa usoara', 'Repaus 3 zile', 'Dr. Gandalf');

INSERT INTO IstoricMedical (ID_Animal, data, motiv, diagnostic, tratament, nume_medic)
VALUES (11, '2025-11-15', 'Alergie', 'Alergie alimentara', 'Schimbare dieta (Hypoallergenic)', 'Dr. Radagast');

CREATE TABLE Hrana(
    ID_Hrana INTEGER NOT NULL AUTO_INCREMENT,
    ID_Animal INTEGER NOT NULL,
    nume_hrana VARCHAR(50),
    tip_hrana VARCHAR(50),
    cantitate DECIMAL(10, 2),
    portie_hrana_per_zi INTEGER,
    ore_administrare VARCHAR(255),
    CONSTRAINT hrana_pk PRIMARY KEY (ID_Hrana),
    CONSTRAINT hrana_animal_unique UNIQUE (ID_Animal),
    CONSTRAINT animale_hrana_fk FOREIGN KEY (ID_Animal) REFERENCES Animale(ID_Animal) ON DELETE CASCADE
);

INSERT INTO Hrana (ID_Animal, nume_hrana, tip_hrana, cantitate, portie_hrana_per_zi, ore_administrare)
VALUES (10, 'Royal Canin Puppy', 'Uscata', 0.15, 3, '08:00, 14:00, 20:00');

INSERT INTO Hrana (ID_Animal, nume_hrana, tip_hrana, cantitate, portie_hrana_per_zi, ore_administrare)
VALUES (11, 'Purina Pro Plan', 'Uscata', 0.25, 2, '09:00, 19:00');

ALTER TABLE Animale
ADD CONSTRAINT unique_animal_per_stapan
UNIQUE (nume_animal, specie, rasa, id_stapan);