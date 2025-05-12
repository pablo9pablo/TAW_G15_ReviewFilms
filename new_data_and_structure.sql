-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Leonardo DiCaprio','M'),(2,'Kate Winslet','F'),(3,'Brad Pitt','M'),(4,'Angelina Jolie','F'),(5,'Tom Hanks','M'),(6,'Robert Pattinson','M'),(7,'Zoe Kravitz','F'),(8,'Timothée Chalamet','M'),(9,'Rebecca Ferguson','F'),(10,'Robert Downey Jr.','M'),(11,'Chris Evans','M'),(12,'Scarlett Johansson','F'),(13,'Song Kang-ho','M'),(14,'Choi Woo-shik','M'),(15,'Sally Hawkins','F'),(16,'Doug Jones','M'),(17,'Chris Pratt','M'),(18,'Bryce Dallas Howard','F'),(19,'Ryan Reynolds','M'),(20,'Morena Baccarin','F'),(21,'Chadwick Boseman','M'),(22,'Michael B. Jordan','M'),(23,'Leonardo DiCaprio','M'),(24,'Joseph Gordon-Levitt','M'),(25,'Matthew McConaughey','M'),(26,'Anne Hathaway','F'),(27,'Matt Damon','M'),(28,'Jessica Chastain','F'),(29,'Jesse Eisenberg','M'),(30,'Andrew Garfield','M'),(31,'Ralph Fiennes','M'),(32,'Tony Revolori','M'),(33,'Florence Pugh','F'),(34,'Jack Reynor','M'),(35,'Choi Min-sik','M'),(36,'Yoo Ji-tae','M'),(37,'Keanu Reeves','M'),(38,'Laurence Fishburne','M'),(39,'Jim Carrey','M'),(40,'Kate Winslet','F'),(41,'Chris Pratt','M'),(42,'Zoe Saldana','F'),(43,'Miles Teller','M'),(44,'J.K. Simmons','M'),(45,'Michael Keaton','M'),(46,'Edward Norton','M'),(47,'Trevante Rhodes','M'),(48,'Ashton Sanders','M'),(49,'Keira Knightley','F'),(50,'Matthew Macfadyen','M'),(51,'Tom Hardy','M'),(52,'Charlize Theron','F'),(53,'Ryan Gosling','M'),(54,'Emma Stone','F'),(55,'Olivia Colman','F'),(56,'Rachel Weisz','F'),(57,'Christian Bale','M'),(58,'Michael Caine','M'),(59,'Gary Oldman','M'),(60,'Liam Neeson','M'),(61,'Heath Ledger','M'),(62,'Aaron Eckhart','M'),(63,'Tom Hardy','M'),(64,'Anne Hathaway','F'),(65,'Morgan Freeman','M');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(5,'Adventure'),(10,'Animation'),(13,'Biography'),(8,'Comedy'),(11,'Crime'),(20,'Documentary'),(2,'Drama'),(6,'Fantasy'),(14,'History'),(7,'Horror'),(15,'Music'),(16,'Musical'),(12,'Mystery'),(9,'Romance'),(3,'Sci-Fi'),(19,'Sport'),(21,'Superhero'),(4,'Thriller'),(17,'War'),(18,'Western');
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
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (43,'acting'),(21,'afrofuturism'),(39,'aliens'),(36,'artificial intelligence'),(26,'botany'),(38,'breakup'),(42,'broadway'),(49,'car chase'),(15,'cold war'),(8,'comic book'),(45,'coming of age'),(31,'cult'),(11,'dark comedy'),(2,'dc comics'),(6,'desert'),(16,'dinosaurs'),(22,'dreams'),(41,'drumming'),(30,'europe'),(27,'facebook'),(12,'family'),(19,'fourth wall'),(23,'heist'),(50,'hollywood'),(29,'hotel'),(46,'jane austen'),(40,'jazz'),(34,'kidnapping'),(28,'lawsuit'),(44,'lgbt'),(14,'love story'),(25,'mars'),(7,'marvel'),(37,'memory'),(13,'monster'),(18,'mutants'),(3,'noir'),(53,'politics'),(48,'post-apocalyptic'),(52,'queen anne'),(47,'regency era'),(33,'revenge'),(4,'science fiction'),(35,'simulated reality'),(51,'singing'),(10,'social inequality'),(5,'space opera'),(1,'superhero'),(32,'sweden'),(17,'theme park'),(24,'time dilation'),(9,'time travel'),(20,'wakanda');
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (18,200000000.00,'https://www.thebatman.com','EN','The Batman','When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city hidden corruption and question his family involvement.',85.00,'2022-03-04',770000000.00,176,'Released','Unmask the truth.','The Batman',7.9,8500,'/images/the-batman.jpg'),(19,100000000.00,'https://www.dune-movie.com','EN','Dune','Feature adaptation of Frank Herbert science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset and most vital element in the galaxy.',90.00,'2021-10-22',400000000.00,155,'Released','It begins.','Dune',8.0,12000,'/images/dune.jpg'),(20,165000000.00,'https://www.marvel.com/movies/avengers-endgame','EN','Avengers: Endgame','After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.',95.00,'2019-04-26',2798000000.00,181,'Released','Avenge the fallen.','Avengers: Endgame',8.4,25000,'/images/avengers-endgame.jpg'),(21,25000000.00,'https://www.paramount.com/movies/parasite','KO','Gisaengchung','Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.',80.00,'2019-05-21',258000000.00,132,'Released','Act like you own the place.','Parasite',8.6,18000,'/images/gisaengchung.jpg'),(22,55000000.00,'https://www.focusfeatures.com/the-shape-of-water','EN','The Shape of Water','At a top secret research facility in the 1960s, a lonely janitor forms a unique relationship with an amphibious creature that is being held in captivity.',75.00,'2017-12-01',195000000.00,123,'Released','A Fairy Tale for Troubled Times','The Shape of Water',7.3,9500,'/images/the-shape-of-water.jpg'),(23,185000000.00,'https://www.jurassicworld.com','EN','Jurassic World','A new theme park, built on the original site of Jurassic Park, creates a genetically modified hybrid dinosaur that escapes containment and goes on a killing spree.',85.00,'2015-06-12',1670000000.00,124,'Released','The park is open.','Jurassic World',6.9,15000,'/images/jurassic-world.jpg'),(24,70000000.00,'https://www.foxmovies.com/movies/deadpool','EN','Deadpool','A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.',85.00,'2016-02-12',783000000.00,108,'Released','Witness the beginning of a happy ending.','Deadpool',7.6,18000,'/images/deadpool.jpg'),(25,160000000.00,'https://www.marvel.com/movies/black-panther','EN','Black Panther','T\'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country\'s past.',90.00,'2018-02-16',1347000000.00,134,'Released','Long live the king.','Black Panther',7.3,17000,'/images/black-panther.jpg'),(26,200000000.00,'https://www.warnerbros.com/movies/inception','EN','Inception','A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.',90.00,'2010-07-16',836000000.00,148,'Released','Your mind is the scene of the crime.','Inception',8.8,30000,'/images/inception.jpg'),(27,150000000.00,'https://www.interstellarmovie.com','EN','Interstellar','A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.',85.00,'2014-11-07',677000000.00,169,'Released','Mankind was born on Earth. It was never meant to die here.','Interstellar',8.6,25000,'/images/interstellar.jpg'),(28,20000000.00,'https://www.focusfeatures.com/the-martian','EN','The Martian','An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive.',80.00,'2015-10-02',630000000.00,144,'Released','Bring Him Home.','The Martian',8.0,22000,'/images/the-martian.jpg'),(29,190000000.00,'https://www.sonypictures.com/movies/thesocialnetwork','EN','The Social Network','As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea.',75.00,'2010-10-01',224000000.00,120,'Released','You don get to 500 million friends without making a few enemies.','The Social Network',7.7,18000,'/images/the-social-network.jpg'),(30,63000000.00,'https://www.foxsearchlight.com/thegrandbudapesthotel','EN','The Grand Budapest Hotel','The adventures of Gustave H, a legendary concierge at a famous hotel, and Zero Moustafa, the lobby boy who becomes his most trusted friend.',80.00,'2014-03-28',172000000.00,99,'Released','A colorful past. A bright future. A present in need of a butler.','The Grand Budapest Hotel',8.1,19000,'/images/the-grand-budapest-hotel.jpg'),(31,40000000.00,'https://www.a24films.com/films/midsommar','EN','Midsommar','A couple travels to Sweden to visit a rural hometown fabled mid-summer festival. What begins as an idyllic retreat quickly devolves into an increasingly violent and bizarre competition.',70.00,'2019-07-03',48000000.00,148,'Released','Let the festivities begin.','Midsommar',7.1,8500,'/images/midsommar.jpg'),(32,20000000.00,'https://www.neonrated.com/parasite','KO','Oldboy','After being kidnapped and imprisoned for fifteen years, Oh Dae-Su is released, only to find that he must find his captor in five days.',80.00,'2003-11-21',15000000.00,120,'Released','15 years of imprisonment. 5 days of vengeance.','Oldboy',8.4,16000,'/images/oldboy.jpg'),(33,60000000.00,'https://www.warnerbros.com/movies/matrix','EN','The Matrix','A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',85.00,'1999-03-31',463000000.00,136,'Released','Welcome to the Real World.','The Matrix',8.7,28000,'/images/the-matrix.jpg'),(34,55000000.00,'https://www.focusfeatures.com/eternal-sunshine','EN','Eternal Sunshine of the Spotless Mind','When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories.',75.00,'2004-03-19',74000000.00,108,'Released','You can erase someone from your mind. Getting them out of your heart is another story.','Eternal Sunshine of the Spotless Mind',8.3,17000,'/images/eternal-sunshine-of-the-spotless-mind.jpg'),(35,180000000.00,'https://www.marvel.com/movies/guardians-of-the-galaxy','EN','Guardians of the Galaxy','A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.',90.00,'2014-08-01',773000000.00,121,'Released','All heroes start somewhere.','Guardians of the Galaxy',8.0,22000,'/images/guardians-of-the-galaxy.jpg'),(36,25000000.00,'https://www.sonypictures.com/movies/whiplash','EN','Whiplash','A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student potential.',80.00,'2014-10-10',49000000.00,106,'Released','The road to greatness can take you to the edge.','Whiplash',8.5,16000,'/images/whiplash.jpg'),(37,30000000.00,'https://www.foxsearchlight.com/birdman','EN','Birdman','A washed-up superhero actor attempts to revive his fading career by writing, directing, and starring in a Broadway production.',75.00,'2014-10-17',103000000.00,119,'Released','The unexpected virtue of ignorance.','Birdman',7.7,15000,'/images/birdman.jpg'),(38,9000000.00,'https://www.a24films.com/films/moonlight','EN','Moonlight','A young African-American man grapples with his identity and sexuality while experiencing the everyday struggles of childhood, adolescence, and burgeoning adulthood.',80.00,'2016-10-21',65000000.00,111,'Released','This is the story of a lifetime.','Moonlight',7.4,12000,'/images/moonlight.jpg'),(39,20000000.00,'https://www.focusfeatures.com/pride-and-prejudice','EN','Pride & Prejudice','Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class.',75.00,'2005-11-11',121000000.00,129,'Released','A romance ahead of its time.','Pride & Prejudice',7.8,14000,'/images/pride-and-prejudice.jpg'),(40,150000000.00,'https://www.warnerbros.com/movies/mad-max-fury-road','EN','Mad Max: Fury Road','In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.',85.00,'2015-05-15',375000000.00,120,'Released','What a lovely day.','Mad Max: Fury Road',8.1,21000,'/images/mad-max-fury-road.jpg'),(41,30000000.00,'https://www.sonypictures.com/movies/lalaland','EN','La La Land','While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.',80.00,'2016-12-09',446000000.00,128,'Released','Here to the fools who dream.','La La Land',8.0,18000,'/images/la-la-land.jpg'),(42,40000000.00,'https://www.foxsearchlight.com/the-favourite','EN','The Favourite','In early 18th century England, a frail Queen Anne occupies the throne and her close friend, Lady Sarah, governs the country in her stead. When a new servant arrives, she charms her way into the queen favor.',75.00,'2018-11-23',95000000.00,119,'Released','Some things are worth fighting for.','The Favourite',7.5,13000,'/images/the-favourite.jpg'),(43,150000000.00,'https://www.warnerbros.com/movies/batman-begins','EN','Batman Begins','After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.',85.00,'2005-06-15',373400000.00,140,'Released','Evil fears the knight.','Batman Begins',7.7,18000,'/images/batman-begins.jpg'),(44,185000000.00,'https://www.warnerbros.com/movies/dark-knight','EN','The Dark Knight','When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',95.00,'2008-07-18',1005000000.00,152,'Released','Why So Serious?','The Dark Knight',9.0,28000,'/images/the-dark-knight.jpg'),(45,230000000.00,'https://www.warnerbros.com/movies/dark-knight-rises','EN','The Dark Knight Rises','Eight years after the Joker\'s reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City from the brutal guerrilla terrorist Bane.',90.00,'2012-07-20',1081000000.00,164,'Released','The Legend Ends','The Dark Knight Rises',8.2,22000,'/images/the-dark-knight-rises.jpg');
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
INSERT INTO `movie_cast` VALUES (18,6,NULL,NULL,NULL,NULL),(18,7,NULL,NULL,NULL,NULL),(19,8,NULL,NULL,NULL,NULL),(19,9,NULL,NULL,NULL,NULL),(20,10,NULL,NULL,NULL,NULL),(20,11,NULL,NULL,NULL,NULL),(20,12,NULL,NULL,NULL,NULL),(21,13,NULL,NULL,NULL,NULL),(21,14,NULL,NULL,NULL,NULL),(22,15,NULL,NULL,NULL,NULL),(22,16,NULL,NULL,NULL,NULL),(23,17,NULL,NULL,NULL,NULL),(23,18,NULL,NULL,NULL,NULL),(24,19,NULL,NULL,NULL,NULL),(24,20,NULL,NULL,NULL,NULL),(25,21,NULL,NULL,NULL,NULL),(25,22,NULL,NULL,NULL,NULL),(26,23,NULL,NULL,NULL,NULL),(26,24,NULL,NULL,NULL,NULL),(27,25,NULL,NULL,NULL,NULL),(27,26,NULL,NULL,NULL,NULL),(28,27,NULL,NULL,NULL,NULL),(28,28,NULL,NULL,NULL,NULL),(29,29,NULL,NULL,NULL,NULL),(29,30,NULL,NULL,NULL,NULL),(30,31,NULL,NULL,NULL,NULL),(30,32,NULL,NULL,NULL,NULL),(31,33,NULL,NULL,NULL,NULL),(31,34,NULL,NULL,NULL,NULL),(32,35,NULL,NULL,NULL,NULL),(32,36,NULL,NULL,NULL,NULL),(33,37,NULL,NULL,NULL,NULL),(33,38,NULL,NULL,NULL,NULL),(34,39,NULL,NULL,NULL,NULL),(34,40,NULL,NULL,NULL,NULL),(35,41,NULL,NULL,NULL,NULL),(35,42,NULL,NULL,NULL,NULL),(36,43,NULL,NULL,NULL,NULL),(36,44,NULL,NULL,NULL,NULL),(37,45,NULL,NULL,NULL,NULL),(37,46,NULL,NULL,NULL,NULL),(38,47,NULL,NULL,NULL,NULL),(38,48,NULL,NULL,NULL,NULL),(39,49,NULL,NULL,NULL,NULL),(39,50,NULL,NULL,NULL,NULL),(40,51,NULL,NULL,NULL,NULL),(40,52,NULL,NULL,NULL,NULL),(41,53,NULL,NULL,NULL,NULL),(41,54,NULL,NULL,NULL,NULL),(42,55,NULL,NULL,NULL,NULL),(42,56,NULL,NULL,NULL,NULL),(43,57,'Bruce Wayne / Batman',NULL,NULL,NULL),(43,58,'Alfred',NULL,NULL,NULL),(43,59,'Jim Gordon',NULL,NULL,NULL),(43,60,'Ra\'s al Ghul',NULL,NULL,NULL),(43,65,'Lucius Fox',NULL,NULL,NULL),(44,57,'Bruce Wayne / Batman',NULL,NULL,NULL),(44,58,'Alfred',NULL,NULL,NULL),(44,59,'Jim Gordon',NULL,NULL,NULL),(44,61,'Joker',NULL,NULL,NULL),(44,62,'Harvey Dent',NULL,NULL,NULL),(44,65,'Lucius Fox',NULL,NULL,NULL),(45,57,'Bruce Wayne / Batman',NULL,NULL,NULL),(45,58,'Alfred',NULL,NULL,NULL),(45,59,'Jim Gordon',NULL,NULL,NULL),(45,63,'Bane',NULL,NULL,NULL),(45,64,'Selina Kyle / Catwoman',NULL,NULL,NULL),(45,65,'Lucius Fox',NULL,NULL,NULL);
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
INSERT INTO `movie_genre` VALUES (18,1),(20,1),(23,1),(24,1),(25,1),(26,1),(33,1),(35,1),(40,1),(43,1),(44,1),(45,1),(21,2),(22,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(34,2),(36,2),(37,2),(38,2),(39,2),(41,2),(42,2),(43,2),(44,2),(45,2),(19,3),(20,3),(23,3),(25,3),(26,3),(27,3),(28,3),(33,3),(34,3),(35,3),(40,3),(18,4),(21,4),(32,4),(43,4),(44,4),(45,4),(19,5),(20,5),(23,5),(25,5),(27,5),(28,5),(35,5),(40,5),(22,6),(31,7),(21,8),(24,8),(30,8),(37,8),(42,8),(22,9),(34,9),(39,9),(41,9),(30,11),(32,11),(44,11),(45,11),(26,12),(31,12),(29,13),(39,14),(42,14),(36,15),(41,16),(18,21),(20,21),(24,21),(25,21),(35,21),(43,21),(44,21),(45,21);
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
INSERT INTO `movie_keyword` VALUES (18,1),(25,1),(40,1),(43,1),(44,1),(45,1),(18,2),(38,2),(43,2),(44,2),(45,2),(18,3),(33,3),(43,3),(44,3),(45,3),(19,4),(27,4),(32,4),(19,5),(23,5),(27,5),(28,5),(35,5),(19,6),(20,7),(31,7),(20,8),(24,8),(35,8),(37,8),(20,9),(34,9),(39,9),(21,10),(21,11),(30,11),(44,11),(21,12),(26,12),(22,13),(29,13),(22,14),(42,14),(22,15),(36,15),(23,16),(41,16),(23,17),(24,18),(24,19),(25,20),(25,21),(26,22),(26,23),(44,23),(27,24),(28,25),(28,26),(29,27),(29,28),(30,29),(30,30),(31,31),(31,32),(32,33),(32,34),(43,34),(33,35),(33,36),(34,37),(34,38),(35,39),(36,40),(36,41),(37,42),(37,43),(38,44),(38,45),(39,46),(39,47),(40,48),(45,48),(40,49),(41,50),(41,51),(42,52),(42,53),(45,53);
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
INSERT INTO `movie_production_company` VALUES (23,1),(28,1),(18,2),(19,2),(26,2),(27,2),(33,2),(40,2),(43,2),(44,2),(45,2),(18,3),(19,4),(43,4),(44,4),(45,4),(20,5),(25,5),(35,5),(21,6),(22,7),(20,8),(25,8),(35,8),(38,10),(23,12),(23,13),(24,14),(30,14),(25,15),(26,16),(27,17),(27,18),(28,18),(29,19),(34,19),(36,19),(37,19),(41,19),(30,20),(42,20),(32,21),(26,22),(33,22),(29,23),(31,24),(31,25),(38,25),(34,26),(36,26),(37,26),(39,27),(40,28),(41,29),(24,30),(28,30),(29,30),(30,30),(42,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_company`
--

LOCK TABLES `production_company` WRITE;
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` VALUES (14,'20th Century Fox'),(10,'A24'),(12,'Amblin Entertainment'),(22,'Anonymous Content'),(6,'Barunson E&A'),(23,'Blumhouse Productions'),(20,'Bona Film Group'),(18,'Columbia Pictures'),(3,'DC Films'),(8,'Disney'),(7,'Double Dare You'),(29,'Element Pictures'),(19,'Indian Paintbrush'),(27,'Kennedy Miller Mitchell'),(13,'Legendary Entertainment'),(4,'Legendary Pictures'),(16,'Lynda Obst Productions'),(28,'Marc Platt Productions'),(5,'Marvel Studios'),(9,'Pixar'),(24,'Plan B Entertainment'),(25,'Regency Enterprises'),(17,'Scott Free Productions'),(11,'Sony Pictures'),(15,'Syncopy'),(30,'TSG Entertainment'),(1,'Universal Pictures'),(21,'Village Roadshow Pictures'),(2,'Warner Bros.'),(26,'Working Title Films');
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
  `description` tinytext,
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'obou523@uma.es','$2a$12$wZKFxOiKjofrroi0ZR6oNeObzyuAC2Qn1Ai4UysXiMb0dX9ZInlia'),(2,'ouailbouazza6@gmail.com','$2a$12$9td2M8SHb0GISKbjuhQJ6e2IbXvptuhjv0Qa96RiV4QBkIAy.Nv7m'),(3,'zohramansouri9@gmail.com','$2a$12$XTLYUDq1QsgmWAtOVUDqdudyCszQva3ff5Ktz9E2jwYXb.g1KMup.'),(4,'madridmallorca5@gmail.com','$2a$12$EVu5a0krxE1TSF2PnEyBIubZk8uI.FxN2n47QRCOEhbH6ucK8/FkG'),(5,'ouailbouazza123@gmail.com','$2a$12$1oqGQn5XKD1UOhm7e58FyO0HU.k6aue8e5dJGB8uwoXYtC0EeVuga'),(6,'manug@gmail.com','$2a$12$0UZ8aRXYnICtO0wYJlfHzOPD9ANgd4WK9Y4qu.xBUbdT/nMnJMyzq');
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

-- Dump completed on 2025-05-12 10:49:19
