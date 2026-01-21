-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: monitorizare_veterinara
-- ------------------------------------------------------
-- Server version	8.0.44

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Animale`
--

DROP TABLE IF EXISTS `Animale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Animale` (
  `ID_Animal` int NOT NULL AUTO_INCREMENT,
  `ID_Stapan` int NOT NULL,
  `nume_animal` varchar(50) DEFAULT NULL,
  `specie` varchar(50) DEFAULT NULL,
  `rasa` varchar(50) DEFAULT NULL,
  `varsta` int DEFAULT NULL,
  `greutate` decimal(5,2) DEFAULT NULL,
  `gen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Animal`),
  KEY `proprietari_fk` (`ID_Stapan`),
  CONSTRAINT `proprietari_fk` FOREIGN KEY (`ID_Stapan`) REFERENCES `Utilizatori` (`ID_User`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Animale`
--

LOCK TABLES `Animale` WRITE;
/*!40000 ALTER TABLE `Animale` DISABLE KEYS */;
INSERT INTO `Animale` VALUES (10,4,'Patrocle','Caine','Teckel',2,2.50,'M'),(11,5,'Spot','Caine','Labrador',4,4.60,'M');
/*!40000 ALTER TABLE `Animale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hrana`
--

DROP TABLE IF EXISTS `Hrana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Hrana` (
  `ID_Hrana` int NOT NULL AUTO_INCREMENT,
  `ID_Animal` int NOT NULL,
  `nume_hrana` varchar(50) DEFAULT NULL,
  `tip_hrana` varchar(50) DEFAULT NULL,
  `cantitate` decimal(10,2) DEFAULT NULL,
  `portie_hrana_per_zi` int DEFAULT NULL,
  `ore_administrare` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Hrana`),
  UNIQUE KEY `hrana_animal_unique` (`ID_Animal`),
  CONSTRAINT `animale_hrana_fk` FOREIGN KEY (`ID_Animal`) REFERENCES `Animale` (`ID_Animal`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hrana`
--

LOCK TABLES `Hrana` WRITE;
/*!40000 ALTER TABLE `Hrana` DISABLE KEYS */;
INSERT INTO `Hrana` VALUES (15,10,'Royal Canin Puppy','Uscata',0.15,3,'08:00, 14:00, 20:00'),(16,11,'Purina Pro Plan','Uscata',0.25,2,'09:00, 19:00');
/*!40000 ALTER TABLE `Hrana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IstoricMedical`
--

DROP TABLE IF EXISTS `IstoricMedical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `IstoricMedical` (
  `ID_IstoricMedical` int NOT NULL AUTO_INCREMENT,
  `ID_Animal` int NOT NULL,
  `data` date DEFAULT NULL,
  `motiv` varchar(255) DEFAULT NULL,
  `diagnostic` varchar(255) DEFAULT NULL,
  `tratament` varchar(255) DEFAULT NULL,
  `nume_medic` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_IstoricMedical`),
  KEY `animale_istoric_fk` (`ID_Animal`),
  CONSTRAINT `animale_istoric_fk` FOREIGN KEY (`ID_Animal`) REFERENCES `Animale` (`ID_Animal`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IstoricMedical`
--

LOCK TABLES `IstoricMedical` WRITE;
/*!40000 ALTER TABLE `IstoricMedical` DISABLE KEYS */;
INSERT INTO `IstoricMedical` VALUES (4,10,'2025-12-01','Control anual','Sanatos','Niciunul','Dr. Gandalf'),(5,10,'2026-01-05','Schiopatare','Entorsa usoara','Repaus 3 zile','Dr. Gandalf'),(6,11,'2025-11-15','Alergie','Alergie alimentara','Schimbare dieta (Hypoallergenic)','Dr. Radagast');
/*!40000 ALTER TABLE `IstoricMedical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utilizatori`
--

DROP TABLE IF EXISTS `Utilizatori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Utilizatori` (
  `ID_User` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `parola` varchar(100) NOT NULL,
  `rol` enum('MEDIC','STAPAN') NOT NULL,
  `nume_complet` varchar(100) DEFAULT NULL,
  `nr_telefon` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_User`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utilizatori`
--

LOCK TABLES `Utilizatori` WRITE;
/*!40000 ALTER TABLE `Utilizatori` DISABLE KEYS */;
INSERT INTO `Utilizatori` VALUES (1,'medic1','med123','MEDIC','Dr. Saruman','0734852169','medic.official@gmail.com'),(4,'aragorn','1234','STAPAN','Aragorn','0768553410','aragorn.theMight@lotr.com'),(5,'bilbo','5678','STAPAN','Bilbo Baggins','0799562481','bilbo.baggins@lotr.com');
/*!40000 ALTER TABLE `Utilizatori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vaccinuri`
--

DROP TABLE IF EXISTS `Vaccinuri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Vaccinuri` (
  `ID_Vaccin` int NOT NULL AUTO_INCREMENT,
  `ID_Animal` int NOT NULL,
  `nume_vaccin` varchar(255) DEFAULT NULL,
  `data_administrare` date DEFAULT NULL,
  PRIMARY KEY (`ID_Vaccin`),
  KEY `animale_vaccin_fk` (`ID_Animal`),
  CONSTRAINT `animale_vaccin_fk` FOREIGN KEY (`ID_Animal`) REFERENCES `Animale` (`ID_Animal`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vaccinuri`
--

LOCK TABLES `Vaccinuri` WRITE;
/*!40000 ALTER TABLE `Vaccinuri` DISABLE KEYS */;
INSERT INTO `Vaccinuri` VALUES (8,10,'Antirabic','2025-10-15'),(9,10,'Polivalent','2025-11-20'),(10,11,'Parvoviroza','2025-12-10');
/*!40000 ALTER TABLE `Vaccinuri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'monitorizare_veterinara'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-21  3:33:27
