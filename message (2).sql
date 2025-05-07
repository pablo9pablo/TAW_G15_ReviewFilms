CREATE DATABASE  IF NOT EXISTS `reviewfilms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `reviewfilms`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: reviewfilms
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Leonardo DiCaprio','M'),(2,'Kate Winslet','F'),(3,'Brad Pitt','M'),(4,'Angelina Jolie','F'),(5,'Tom Hanks','M');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew`
--

DROP TABLE IF EXISTS `crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `GENDER` int DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `MOVIE_ID` int NOT NULL,
  `USER_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`USER_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `favorites_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Drama');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `keyword` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `BUDGET` decimal(15,2) DEFAULT NULL,
  `HOMEPAGE` varchar(255) DEFAULT NULL,
  `ORIGINAL_LANGUAGE` varchar(50) DEFAULT NULL,
  `ORIGINAL_TITLE` varchar(255) NOT NULL,
  `overview` tinytext,
  `POPULARITY` decimal(5,2) DEFAULT NULL,
  `RELEASE_DATE` date DEFAULT NULL,
  `REVENUE` decimal(15,2) DEFAULT NULL,
  `RUNTIME` int DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `TAGLINE` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `VOTE_AVERAGE` decimal(3,1) DEFAULT NULL,
  `VOTE_COUNT` int DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (16,0.00,NULL,'EN','New Movie Title','New movie overview',80.00,'2025-04-19',120000000.00,130,'Released','New Tagline','New Title',8.5,5000,'/images/Fotos-730453848-mmed.jpg'),(17,0.00,NULL,'EN','Dos generos','New movie overview',80.00,'2025-04-19',120000000.00,130,'Released','New Tagline','New Movie Title',8.5,5000,'/images/Fotos-730453848-mmed.jpg');
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_cast`
--

DROP TABLE IF EXISTS `movie_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_cast` (
  `MOVIE_ID` int NOT NULL,
  `ACTOR_ID` int NOT NULL,
  `CHARACTER_NAME` varchar(255) DEFAULT NULL,
  `CREDIT_ID` varchar(60) DEFAULT NULL,
  `CREDIT_ORDER` int DEFAULT NULL,
  `character` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MOVIE_ID`,`ACTOR_ID`),
  KEY `ACTOR_ID` (`ACTOR_ID`),
  CONSTRAINT `movie_cast_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `movie_cast_ibfk_2` FOREIGN KEY (`ACTOR_ID`) REFERENCES `actor` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_cast`
--

LOCK TABLES `movie_cast` WRITE;
/*!40000 ALTER TABLE `movie_cast` DISABLE KEYS */;
INSERT INTO `movie_cast` VALUES (16,1,'Jack Dawson','cred-001',1,NULL),(16,2,'Rose DeWitt Bukater','cred-002',2,NULL),(17,3,'Cliff Booth','cred-003',1,NULL),(17,4,'Evelyn Salt','cred-004',2,NULL),(17,5,'Forrest Gump','cred-005',3,NULL);
/*!40000 ALTER TABLE `movie_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `MOVIE_ID` int NOT NULL,
  `GENRE_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`GENRE_ID`),
  KEY `GENRE_ID` (`GENRE_ID`),
  CONSTRAINT `movie_genre_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `movie_genre_ibfk_2` FOREIGN KEY (`GENRE_ID`) REFERENCES `genre` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES (16,1),(17,1),(17,2);
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_keyword`
--

DROP TABLE IF EXISTS `movie_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_keyword` (
  `MOVIE_ID` int NOT NULL,
  `KEYWORD_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`KEYWORD_ID`),
  KEY `KEYWORD_ID` (`KEYWORD_ID`),
  CONSTRAINT `movie_keyword_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `movie_keyword_ibfk_2` FOREIGN KEY (`KEYWORD_ID`) REFERENCES `keyword` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_keyword`
--

LOCK TABLES `movie_keyword` WRITE;
/*!40000 ALTER TABLE `movie_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_production_company`
--

DROP TABLE IF EXISTS `movie_production_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_production_company` (
  `MOVIE_ID` int NOT NULL,
  `PRODUCTION_COMPANY_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`PRODUCTION_COMPANY_ID`),
  KEY `PRODUCTION_COMPANY_ID` (`PRODUCTION_COMPANY_ID`),
  CONSTRAINT `movie_production_company_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `movie_production_company_ibfk_2` FOREIGN KEY (`PRODUCTION_COMPANY_ID`) REFERENCES `production_company` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_production_company`
--

LOCK TABLES `movie_production_company` WRITE;
/*!40000 ALTER TABLE `movie_production_company` DISABLE KEYS */;
INSERT INTO `movie_production_company` VALUES (17,1),(16,2);
/*!40000 ALTER TABLE `movie_production_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_company`
--

DROP TABLE IF EXISTS `production_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_company` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_company`
--

LOCK TABLES `production_company` WRITE;
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` VALUES (1,'Universal Pictures'),(2,'Warner Bros.');
/*!40000 ALTER TABLE `production_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `MOVIE_ID` int NOT NULL,
  `USER_ID` int NOT NULL,
  `description` TEXT,
  `RATING` int NOT NULL,
  `DATE` date NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MOVIE_ID` (`MOVIE_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `review_chk_1` CHECK (((`RATING` >= 0) and (`RATING` <= 10)))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,16,2,'',10,'2025-04-20');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seen`
--

DROP TABLE IF EXISTS `seen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seen` (
  `MOVIE_ID` int NOT NULL,
  `USER_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`USER_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `seen_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `seen_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seen`
--

LOCK TABLES `seen` WRITE;
/*!40000 ALTER TABLE `seen` DISABLE KEYS */;
/*!40000 ALTER TABLE `seen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) NOT NULL,
  `PASSWORD_HASH` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'obou523@uma.es','$2a$12$wZKFxOiKjofrroi0ZR6oNeObzyuAC2Qn1Ai4UysXiMb0dX9ZInlia'),(2,'ouailbouazza6@gmail.com','$2a$12$9td2M8SHb0GISKbjuhQJ6e2IbXvptuhjv0Qa96RiV4QBkIAy.Nv7m'),(3,'zohramansouri9@gmail.com','$2a$12$XTLYUDq1QsgmWAtOVUDqdudyCszQva3ff5Ktz9E2jwYXb.g1KMup.'),(4,'madridmallorca5@gmail.com','$2a$12$EVu5a0krxE1TSF2PnEyBIubZk8uI.FxN2n47QRCOEhbH6ucK8/FkG');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `USER_ID` int NOT NULL,
  `ROLE_ID` int NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watchlist`
--

DROP TABLE IF EXISTS `watchlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watchlist` (
  `MOVIE_ID` int NOT NULL,
  `USER_ID` int NOT NULL,
  PRIMARY KEY (`MOVIE_ID`,`USER_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `watchlist_ibfk_1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `watchlist_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watchlist`
--

LOCK TABLES `watchlist` WRITE;
/*!40000 ALTER TABLE `watchlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `watchlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 16:26:21
