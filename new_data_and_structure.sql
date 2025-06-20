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
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Leonardo DiCaprio','M'),(2,'Kate Winslet','F'),(3,'Brad Pitt','M'),(4,'Angelina Jolie','F'),(5,'Tom Hanks','M'),(6,'Robert Pattinson','M'),(7,'Zoe Kravitz','F'),(8,'Timothée Chalamet','M'),(9,'Rebecca Ferguson','F'),(10,'Robert Downey Jr.','M'),(11,'Chris Evans','M'),(12,'Scarlett Johansson','F'),(13,'Song Kang-ho','M'),(14,'Choi Woo-shik','M'),(15,'Sally Hawkins','F'),(16,'Doug Jones','M'),(17,'Chris Pratt','M'),(18,'Bryce Dallas Howard','F'),(19,'Ryan Reynolds','M'),(20,'Morena Baccarin','F'),(21,'Chadwick Boseman','M'),(22,'Michael B. Jordan','M'),(23,'Leonardo DiCaprio','M'),(24,'Joseph Gordon-Levitt','M'),(25,'Matthew McConaughey','M'),(26,'Anne Hathaway','F'),(27,'Matt Damon','M'),(28,'Jessica Chastain','F'),(29,'Jesse Eisenberg','M'),(30,'Andrew Garfield','M'),(31,'Ralph Fiennes','M'),(32,'Tony Revolori','M'),(33,'Florence Pugh','F'),(34,'Jack Reynor','M'),(35,'Choi Min-sik','M'),(36,'Yoo Ji-tae','M'),(37,'Keanu Reeves','M'),(38,'Laurence Fishburne','M'),(39,'Jim Carrey','M'),(40,'Kate Winslet','F'),(41,'Chris Pratt','M'),(42,'Zoe Saldana','F'),(43,'Miles Teller','M'),(44,'J.K. Simmons','M'),(45,'Michael Keaton','M'),(46,'Edward Norton','M'),(47,'Trevante Rhodes','M'),(48,'Ashton Sanders','M'),(49,'Keira Knightley','F'),(50,'Matthew Macfadyen','M'),(51,'Tom Hardy','M'),(52,'Charlize Theron','F'),(53,'Ryan Gosling','M'),(54,'Emma Stone','F'),(55,'Olivia Colman','F'),(56,'Rachel Weisz','F'),(57,'Christian Bale','M'),(58,'Michael Caine','M'),(59,'Gary Oldman','M'),(60,'Liam Neeson','M'),(61,'Heath Ledger','M'),(62,'Aaron Eckhart','M'),(63,'Tom Hardy','M'),(64,'Anne Hathaway','F'),(65,'Morgan Freeman','M'),(69,'Marlon Brando','M'),(70,'Robert Duvall','M'),(71,'Liam Neeson','M'),(72,'Ben Kingsley','M'),(73,'Josh Hartnett','M'),(74,'Ewan McGregor','M'),(75,'Alec Guinness','M'),(76,'William Holden','M'),(77,'Charlie Sheen','M'),(78,'Matthew Modine','M'),(79,'Andrew Garfield','M'),(80,'Vince Vaughn','M'),(81,'Fionn Whitehead','M'),(82,'Harry Styles','M'),(83,'George MacKay','M'),(84,'Dean-Charles Chapman','M'),(85,'R. Lee Ermey','M'),(86,'Adam Driver','M'),(87,'Mel Gibson','M'),(106,'Will Smith','M'),(107,'Jaden Smith','M'),(108,'James Franco','M'),(109,'Benedict Cumberbatch','M'),(110,'Keira Knightley','F'),(111,'Taraji P. Henson','F'),(112,'Octavia Spencer','F'),(113,'Eddie Redmayne','M'),(114,'Laura Linney','F'),(115,'Sandra Bullock','F'),(116,'Quinton Aaron','M'),(117,'Jonah Hill','M'),(118,'Colin Firth','M'),(119,'Geoffrey Rush','M'),(120,'Thandie Newton','F'),(121,'Kevin Costner','M'),(122,'Tommy Lee Jones','M'),(123,'Mark Ruffalo','M'),(124,'Emma Watson','F'),(125,'Janelle Monáe','F');
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
  `gender` varchar(255) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKgv7nohyihgyg1cp3l6h1n445y` (`movie_id`),
  CONSTRAINT `FKgv7nohyihgyg1cp3l6h1n445y` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew`
--

LOCK TABLES `crew` WRITE;
/*!40000 ALTER TABLE `crew` DISABLE KEYS */;
INSERT INTO `crew` VALUES (1,'Manu','M',20);
/*!40000 ALTER TABLE `crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crew_movie`
--

DROP TABLE IF EXISTS `crew_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `crew_movie` (
  `crew_id` int NOT NULL,
  `movie_id` int NOT NULL,
  PRIMARY KEY (`crew_id`,`movie_id`),
  KEY `FK4h48gy7hec7nmky0f3eunh58g` (`movie_id`),
  CONSTRAINT `FK4h48gy7hec7nmky0f3eunh58g` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`ID`),
  CONSTRAINT `FK568dg3guhjw0exwdslbs24b1r` FOREIGN KEY (`crew_id`) REFERENCES `crew` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crew_movie`
--

LOCK TABLES `crew_movie` WRITE;
/*!40000 ALTER TABLE `crew_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `crew_movie` ENABLE KEYS */;
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
INSERT INTO `favorites` VALUES (24,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (43,'acting'),(21,'afrofuturism'),(39,'aliens'),(36,'artificial intelligence'),(63,'based on true story'),(58,'battle'),(26,'botany'),(38,'breakup'),(42,'broadway'),(49,'car chase'),(15,'cold war'),(61,'combat'),(8,'comic book'),(45,'coming of age'),(31,'cult'),(11,'dark comedy'),(2,'dc comics'),(6,'desert'),(16,'dinosaurs'),(22,'dreams'),(41,'drumming'),(30,'europe'),(27,'facebook'),(12,'family'),(19,'fourth wall'),(23,'heist'),(50,'hollywood'),(29,'hotel'),(46,'jane austen'),(40,'jazz'),(34,'kidnapping'),(28,'lawsuit'),(44,'lgbt'),(14,'love story'),(25,'mars'),(7,'marvel'),(37,'memory'),(60,'military'),(13,'monster'),(18,'mutants'),(3,'noir'),(53,'politics'),(48,'post-apocalyptic'),(57,'prisoner of war'),(52,'queen anne'),(47,'regency era'),(62,'rescue mission'),(33,'revenge'),(4,'science fiction'),(35,'simulated reality'),(51,'singing'),(10,'social inequality'),(59,'soldier'),(56,'somalia conflict'),(5,'space opera'),(1,'superhero'),(32,'sweden'),(17,'theme park'),(24,'time dilation'),(9,'time travel'),(55,'vietnam war'),(20,'wakanda'),(54,'world war ii');
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
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (18,200000000.00,NULL,'EN','The Batman','When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city hidden corruption and question his family involvement.',NULL,'2022-03-04',770000000.00,176,NULL,NULL,'The Batman',NULL,NULL,'https://pics.filmaffinity.com/the_batman-449856406-large.jpg'),(19,100000000.00,NULL,'EN','Dune','Feature adaptation of Frank Herbert science fiction novel about the son of a noble family entrusted with the protection of the most valuable asset and most vital element in the galaxy.',NULL,'2021-10-22',400000000.00,155,NULL,NULL,'Dune',NULL,NULL,'https://pics.filmaffinity.com/dune-209834814-large.jpg'),(20,165000000.00,NULL,'EN','Avengers: Endgame','After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.',NULL,'2019-04-26',2798000000.00,181,NULL,NULL,'Avengers: Endgame',8.0,21000,'https://pics.filmaffinity.com/avengers_endgame-135478227-large.jpg'),(21,25000000.00,NULL,'KO','Gisaengchung','Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.',NULL,'2019-05-21',258000000.00,132,NULL,NULL,'Parasite',NULL,NULL,'https://pics.filmaffinity.com/gisaengchung-432616131-large.jpg'),(22,55000000.00,NULL,'EN','The Shape of Water','At a top secret research facility in the 1960s, a lonely janitor forms a unique relationship with an amphibious creature that is being held in captivity.',NULL,'2017-12-01',195000000.00,123,NULL,NULL,'The Shape of Water',NULL,NULL,'https://pics.filmaffinity.com/the_shape_of_water-856013521-large.jpg'),(23,185000000.00,NULL,'EN','Jurassic World','A new theme park, built on the original site of Jurassic Park, creates a genetically modified hybrid dinosaur that escapes containment and goes on a killing spree.',NULL,'2015-06-12',1670000000.00,124,NULL,NULL,'Jurassic World',NULL,NULL,'https://pics.filmaffinity.com/jurassic_world-664106758-large.jpg'),(24,70000000.00,NULL,'EN','Deadpool','A wisecracking mercenary gets experimented on and becomes immortal but ugly, and sets out to track down the man who ruined his looks.',NULL,'2016-02-12',783000000.00,108,NULL,NULL,'Deadpool',NULL,NULL,'https://pics.filmaffinity.com/deadpool-834516798-large.jpg'),(25,160000000.00,NULL,'EN','Black Panther','T\'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward to lead his people into a new future and must confront a challenger from his country\'s past.',NULL,'2018-02-16',1347000000.00,134,NULL,NULL,'Black Panther',5.0,1,'https://pics.filmaffinity.com/black_panther-992613805-large.jpg'),(26,200000000.00,NULL,'EN','Inception','A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.',NULL,'2010-07-16',836000000.00,148,NULL,NULL,'Inception',NULL,NULL,'https://pics.filmaffinity.com/inception-652954101-large.jpg'),(27,150000000.00,NULL,'EN','Interstellar','A team of explorers travel through a wormhole in space in an attempt to ensure humanity survival.',NULL,'2014-11-07',677000000.00,169,NULL,NULL,'Interstellar',NULL,NULL,'https://pics.filmaffinity.com/interstellar-366875261-large.jpg'),(28,20000000.00,NULL,'EN','The Martian','An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive.',NULL,'2015-10-02',630000000.00,144,NULL,NULL,'The Martian',NULL,NULL,'https://pics.filmaffinity.com/the_martian-972637166-large.jpg'),(29,190000000.00,NULL,'EN','The Social Network','As Harvard student Mark Zuckerberg creates the social networking site that would become known as Facebook, he is sued by the twins who claimed he stole their idea.',NULL,'2010-10-01',224000000.00,120,NULL,NULL,'The Social Network',NULL,NULL,'https://pics.filmaffinity.com/the_social_network-421032493-large.jpg'),(30,63000000.00,NULL,'EN','The Grand Budapest Hotel','The adventures of Gustave H, a legendary concierge at a famous hotel, and Zero Moustafa, the lobby boy who becomes his most trusted friend.',NULL,'2014-03-28',172000000.00,99,NULL,NULL,'The Grand Budapest Hotel',NULL,NULL,'https://pics.filmaffinity.com/the_grand_budapest_hotel-247331769-large.jpg'),(31,40000000.00,NULL,'EN','Midsommar','A couple travels to Sweden to visit a rural hometown fabled mid-summer festival. What begins as an idyllic retreat quickly devolves into an increasingly violent and bizarre competition.',NULL,'2019-07-03',48000000.00,148,NULL,NULL,'Midsommar',NULL,NULL,'https://pics.filmaffinity.com/midsommar-578791309-large.jpg'),(32,20000000.00,NULL,'KO','Oldboy','After being kidnapped and imprisoned for fifteen years, Oh Dae-Su is released, only to find that he must find his captor in five days.',NULL,'2003-11-21',15000000.00,120,NULL,NULL,'Oldboy',NULL,NULL,'https://pics.filmaffinity.com/oldeuboi-520509097-large.jpg'),(33,60000000.00,NULL,'EN','The Matrix','A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',NULL,'2099-03-31',463000000.00,136,NULL,NULL,'The Matrix',NULL,NULL,'https://pics.filmaffinity.com/the_matrix-155050517-large.jpg'),(34,55000000.00,NULL,'EN','Eternal Sunshine of the Spotless Mind','When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories.',NULL,'2004-03-19',74000000.00,108,NULL,NULL,'Eternal Sunshine of the Spotless Mind',9.0,1,'https://pics.filmaffinity.com/eternal_sunshine_of_the_spotless_mind-314689716-large.jpg'),(35,180000000.00,NULL,'EN','Guardians of the Galaxy','A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.',NULL,'2014-08-01',773000000.00,121,NULL,NULL,'Guardians of the Galaxy',NULL,NULL,'https://pics.filmaffinity.com/guardians_of_the_galaxy-595487268-large.jpg'),(36,25000000.00,NULL,'EN','Whiplash','A promising young drummer enrolls at a cut-throat music conservatory where his dreams of greatness are mentored by an instructor who will stop at nothing to realize a student potential.',NULL,'2014-10-10',49000000.00,106,NULL,NULL,'Whiplash',NULL,NULL,'https://pics.filmaffinity.com/whiplash-344887410-large.jpg'),(37,30000000.00,NULL,'EN','Birdman','A washed-up superhero actor attempts to revive his fading career by writing, directing, and starring in a Broadway production.',NULL,'2014-10-17',103000000.00,119,NULL,NULL,'Birdman',NULL,NULL,'https://pics.filmaffinity.com/birdman_or_the_unexpected_virtue_of_ignorance-402510071-large.jpg'),(38,9000000.00,NULL,'EN','Moonlight','A young African-American man grapples with his identity and sexuality while experiencing the everyday struggles of childhood, adolescence, and burgeoning adulthood.',NULL,'2016-10-21',65000000.00,111,NULL,NULL,'Moonlight',NULL,NULL,'https://pics.filmaffinity.com/moonlight-232276883-large.jpg'),(39,20000000.00,NULL,'EN','Pride & Prejudice','Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class.',NULL,'2005-11-11',121000000.00,129,NULL,NULL,'Pride & Prejudice',NULL,NULL,'https://pics.filmaffinity.com/pride_and_prejudice-766739677-large.jpg'),(40,150000000.00,NULL,'EN','Mad Max: Fury Road','In a post-apocalyptic wasteland, a woman rebels against a tyrannical ruler in search for her homeland with the aid of a group of female prisoners, a psychotic worshiper, and a drifter named Max.',NULL,'2015-05-15',375000000.00,120,NULL,NULL,'Mad Max: Fury Road',NULL,NULL,'https://pics.filmaffinity.com/mad_max_fury_road-429261909-large.jpg'),(41,30000000.00,NULL,'EN','La La Land','While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.',NULL,'2016-12-09',446000000.00,128,NULL,NULL,'La La Land',NULL,NULL,'https://pics.filmaffinity.com/la_la_land-262021831-large.jpg'),(42,40000000.00,NULL,'EN','The Favourite','In early 18th century England, a frail Queen Anne occupies the throne and her close friend, Lady Sarah, governs the country in her stead. When a new servant arrives, she charms her way into the queen favor.',NULL,'2018-11-23',95000000.00,119,NULL,NULL,'The Favourite',NULL,NULL,'https://pics.filmaffinity.com/the_favourite-984520950-large.jpg'),(43,150000000.00,NULL,'EN','Batman Begins','After training with his mentor, Batman begins his fight to free crime-ridden Gotham City from corruption.',NULL,'2005-06-15',373400000.00,140,NULL,NULL,'Batman Begins',NULL,NULL,'https://pics.filmaffinity.com/batman_begins-413277928-large.jpg'),(44,185000000.00,NULL,'EN','The Dark Knight','When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',NULL,'2008-07-18',1005000000.00,152,NULL,NULL,'The Dark Knight',NULL,NULL,'https://pics.filmaffinity.com/the_dark_knight-102763119-large.jpg'),(45,230000000.00,NULL,'EN','The Dark Knight Rises','Eight years after the Joker\'s reign of anarchy, Batman, with the help of the enigmatic Catwoman, is forced from his exile to save Gotham City from the brutal guerrilla terrorist Bane.',NULL,'2012-07-20',1081000000.00,164,NULL,NULL,'The Dark Knight Rises',NULL,NULL,'https://pics.filmaffinity.com/the_dark_knight_rises-149544881-large.jpg'),(46,70000000.00,NULL,'EN','Saving Private Ryan','Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.',NULL,'2098-07-24',481840909.00,169,NULL,NULL,'Saving Private Ryan',8.6,15000,'https://pics.filmaffinity.com/saving_private_ryan-585301228-large.jpg'),(47,31000000.00,NULL,'EN','Apocalypse Now','A U.S. Army officer serving in Vietnam is tasked with assassinating a renegade Special Forces Colonel who sees himself as a god.',NULL,'2079-08-15',150000000.00,153,NULL,NULL,'Apocalypse Now',8.5,9500,'https://pics.filmaffinity.com/apocalypse_now-915115475-large.jpg'),(48,22000000.00,NULL,'EN','Schindler\'s List','In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.',NULL,'2093-12-15',321306305.00,195,NULL,NULL,'Schindler\'s List',8.9,16000,'https://pics.filmaffinity.com/schindler_s_list-473662617-large.jpg'),(49,92000000.00,NULL,'EN','Black Hawk Down','160 elite U.S. soldiers drop into Somalia to capture two top lieutenants of a renegade warlord and find themselves in a desperate battle with a large force of heavily-armed Somalis.',NULL,'2001-12-28',172989651.00,144,NULL,NULL,'Black Hawk Down',7.7,7000,'https://pics.filmaffinity.com/black_hawk_down-847311943-large.jpg'),(50,3000000.00,NULL,'EN','The Bridge on the River Kwai','British POWs are forced to build a railway bridge across the river Kwai for their Japanese captors, not knowing that the allied forces are planning to destroy it.',NULL,'2057-12-14',27200000.00,161,NULL,NULL,'El puente sobre el río Kwai',8.2,5000,'https://pics.filmaffinity.com/the_bridge_on_the_river_kwai-547799755-large.jpg'),(51,6000000.00,NULL,'EN','Platoon','A young recruit in Vietnam faces a moral crisis when confronted with the horrors of war and the duality of man.',NULL,'2086-12-19',138530565.00,120,NULL,NULL,'Platoon',8.1,5500,'https://pics.filmaffinity.com/platoon-800279536-large.jpg'),(52,100000000.00,NULL,'EN','Dunkirk','Allied soldiers from Belgium, the British Empire and France are surrounded by the German army and evacuated during a fierce battle in World War II.',NULL,'2017-07-21',526940665.00,106,NULL,NULL,'Dunkirk',7.8,11000,'https://pics.filmaffinity.com/dunkirk-461720087-large.jpg'),(53,95000000.00,NULL,'EN','1917','April 6th, 1917. As a regiment assembles to wage war deep in enemy territory, two soldiers are assigned to race against time and deliver a message that will stop 1,600 men from walking straight into a deadly trap.',NULL,'2019-12-25',384577417.00,119,NULL,NULL,'1917',8.3,12000,'https://pics.filmaffinity.com/1917-960418215-large.jpg'),(54,30000000.00,NULL,'EN','Full Metal Jacket','A pragmatic U.S. Marine observes the dehumanizing effects the Vietnam War has on his fellow recruits from their brutal boot camp training to the bloody street fighting in Hue.',NULL,'2087-06-26',46357676.00,116,NULL,NULL,'La chaqueta metálica',8.3,8000,'https://pics.filmaffinity.com/full_metal_jacket-577943737-large.jpg'),(55,40000000.00,NULL,'EN','Hacksaw Ridge','World War II American Army Medic Desmond T. Doss, who served during the Battle of Okinawa, refuses to kill people, and becomes the first man in American history to receive the Medal of Honor without firing a shot.',NULL,'2016-11-04',175302354.00,139,NULL,NULL,'Hasta el último hombre',8.1,10000,'https://pics.filmaffinity.com/hacksaw_ridge-698653296-large.jpg'),(56,55000000.00,NULL,'EN','The Pursuit of Happyness','A struggling salesman takes custody of his son as he\'s poised to begin a life-changing professional endeavor.',NULL,'2006-12-15',307000000.00,117,NULL,NULL,'The Pursuit of Happyness',8.0,10500,'https://pics.filmaffinity.com/the_pursuit_of_happyness-660040804-large.jpg'),(58,18000000.00,NULL,'EN','127 Hours','A mountain climber becomes trapped under a boulder while canyoneering alone near Moab, Utah and resorts to desperate measures in order to survive.',NULL,'2010-11-05',60700000.00,94,NULL,NULL,'127 Hours',7.6,7000,'https://pics.filmaffinity.com/127_hours-846868462-large.jpg'),(59,14000000.00,NULL,'EN','The Imitation Game','During World War II, the English mathematical genius Alan Turing tries to crack the German Enigma code with help from fellow mathematicians.',NULL,'2014-11-28',233600000.00,114,NULL,NULL,'The Imitation Game',8.0,11500,'https://pics.filmaffinity.com/the_imitation_game-824166913-large.jpg'),(60,25000000.00,NULL,'EN','Hidden Figures','The story of a team of female African-American mathematicians who served a vital role in NASA during the early years of the U.S. space program.',NULL,'2016-12-25',236000000.00,127,NULL,NULL,'Hidden Figures',7.8,9500,'https://pics.filmaffinity.com/hidden_figures-810983135-large.jpg'),(61,15000000.00,NULL,'EN','The Theory of Everything','The story of renowned astrophysicist Stephen Hawking, who falls deeply in love with fellow Cambridge student Jane Wilde.',NULL,'2014-11-26',123700000.00,123,NULL,NULL,'The Theory of Everything',7.7,8000,'https://pics.filmaffinity.com/the_theory_of_everything-567835219-large.jpg'),(62,60000000.00,NULL,'EN','Sully','The story of Chesley Sullenberger, an American pilot who became a hero after landing his damaged plane on the Hudson River in order to save the flight\'s passengers and crew.',NULL,'2016-09-09',240800000.00,96,NULL,NULL,'Sully',7.4,6500,'https://pics.filmaffinity.com/sully-538349170-large.jpg'),(63,29000000.00,NULL,'EN','The Blind Side','The story of Michael Oher, a homeless and traumatized boy who became an All-American football player and first-round NFL draft pick with the help of a caring woman and her family.',NULL,'2009-11-20',309200000.00,129,NULL,NULL,'The Blind Side',7.6,7500,'https://pics.filmaffinity.com/the_blind_side-537970540-large.jpg'),(64,50000000.00,NULL,'EN','Moneyball','Oakland A\'s general manager Billy Beane\'s successful attempt to assemble a baseball team on a lean budget by employing computer-generated analysis to acquire new players.',NULL,'2011-09-23',110200000.00,133,NULL,NULL,'Moneyball',7.6,7000,'https://pics.filmaffinity.com/moneyball-572287299-large.jpg'),(65,15000000.00,NULL,'EN','The King\'s Speech','The story of King George VI of the United Kingdom of Great Britain and Northern Ireland, his impromptu ascension to the throne and the speech therapist who helped the unsure monarch become worthy of it.',NULL,'2010-12-25',424000000.00,118,NULL,NULL,'The King\'s Speech',8.0,9500,'https://pics.filmaffinity.com/the_king_s_speech-997653906-large.jpg');
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
INSERT INTO `movie_cast` VALUES (18,6,NULL,NULL,NULL),(18,7,NULL,NULL,NULL),(19,8,NULL,NULL,NULL),(19,9,NULL,NULL,NULL),(20,10,NULL,NULL,NULL),(20,11,NULL,NULL,NULL),(20,12,NULL,NULL,NULL),(21,13,NULL,NULL,NULL),(21,14,NULL,NULL,NULL),(22,15,NULL,NULL,NULL),(22,16,NULL,NULL,NULL),(23,17,NULL,NULL,NULL),(23,18,NULL,NULL,NULL),(24,19,NULL,NULL,NULL),(24,20,NULL,NULL,NULL),(25,21,NULL,NULL,NULL),(25,22,NULL,NULL,NULL),(26,23,NULL,NULL,NULL),(26,24,NULL,NULL,NULL),(27,25,NULL,NULL,NULL),(27,26,NULL,NULL,NULL),(28,27,NULL,NULL,NULL),(28,28,NULL,NULL,NULL),(29,29,NULL,NULL,NULL),(29,30,NULL,NULL,NULL),(30,31,NULL,NULL,NULL),(30,32,NULL,NULL,NULL),(31,33,NULL,NULL,NULL),(31,34,NULL,NULL,NULL),(32,35,NULL,NULL,NULL),(32,36,NULL,NULL,NULL),(33,37,NULL,NULL,NULL),(33,38,NULL,NULL,NULL),(34,39,NULL,NULL,NULL),(34,40,NULL,NULL,NULL),(35,41,NULL,NULL,NULL),(35,42,NULL,NULL,NULL),(36,43,NULL,NULL,NULL),(36,44,NULL,NULL,NULL),(37,45,NULL,NULL,NULL),(37,46,NULL,NULL,NULL),(38,47,NULL,NULL,NULL),(38,48,NULL,NULL,NULL),(39,49,NULL,NULL,NULL),(39,50,NULL,NULL,NULL),(40,51,NULL,NULL,NULL),(40,52,NULL,NULL,NULL),(41,53,NULL,NULL,NULL),(41,54,NULL,NULL,NULL),(42,55,NULL,NULL,NULL),(42,56,NULL,NULL,NULL),(43,57,'Bruce Wayne / Batman',NULL,NULL),(43,58,'Alfred',NULL,NULL),(43,59,'Jim Gordon',NULL,NULL),(43,60,'Ra\'s al Ghul',NULL,NULL),(43,65,'Lucius Fox',NULL,NULL),(44,57,'Bruce Wayne / Batman',NULL,NULL),(44,58,'Alfred',NULL,NULL),(44,59,'Jim Gordon',NULL,NULL),(44,61,'Joker',NULL,NULL),(44,62,'Harvey Dent',NULL,NULL),(44,65,'Lucius Fox',NULL,NULL),(45,57,'Bruce Wayne / Batman',NULL,NULL),(45,58,'Alfred',NULL,NULL),(45,59,'Jim Gordon',NULL,NULL),(45,63,'Bane',NULL,NULL),(45,64,'Selina Kyle / Catwoman',NULL,NULL),(45,65,'Lucius Fox',NULL,NULL),(47,37,'Captain Benjamin L. Willard',NULL,3),(47,69,'Colonel Walter E. Kurtz',NULL,1),(47,70,'Lieutenant Colonel Bill Kilgore',NULL,2),(48,46,'Amon Göth',NULL,3),(48,71,'Oskar Schindler',NULL,1),(48,72,'Itzhak Stern',NULL,2),(49,3,'Hoot',NULL,3),(49,73,'Staff Sergeant Matt Eversmann',NULL,1),(49,74,'Spec. John Grimes',NULL,2),(50,31,'Colonel Saito',NULL,3),(50,75,'Colonel Nicholson',NULL,1),(50,76,'Shears',NULL,2),(51,77,'Chris Taylor',NULL,1),(51,78,'Private Joker',NULL,2),(51,85,'Gunnery Sergeant Hartman',NULL,3),(52,38,'Commander Bolton',NULL,3),(52,81,'Tommy',NULL,1),(52,82,'Alex',NULL,2),(53,65,'General Erinmore',NULL,3),(53,83,'Lance Corporal Schofield',NULL,1),(53,84,'Lance Corporal Blake',NULL,2),(54,78,'Private Joker',NULL,1),(54,85,'Gunnery Sergeant Hartman',NULL,2),(54,86,'Private Cowboy',NULL,3),(55,79,'Desmond Doss',NULL,1),(55,80,'Sergeant Howell',NULL,2),(55,87,'Sergeant Howell',NULL,3),(56,106,'Chris Gardner',NULL,1),(56,107,'Christopher Gardner',NULL,2),(56,120,'Linda Gardner',NULL,3),(58,108,'Aron Ralston',NULL,1),(58,120,'Sonja Ralston',NULL,3),(58,124,'Megan',NULL,2),(59,109,'Alan Turing',NULL,1),(59,110,'Joan Clarke',NULL,2),(59,118,'Commander Denniston',NULL,3),(60,111,'Katherine Johnson',NULL,1),(60,112,'Dorothy Vaughan',NULL,2),(61,110,'Jane Wilde Hawking',NULL,2),(61,113,'Stephen Hawking',NULL,1),(61,119,'Professor Dennis Sciama',NULL,3),(62,5,'Chesley \"Sully\" Sullenberger',NULL,1),(62,114,'Lorraine Sullenberger',NULL,2),(62,121,'Jeff Skiles',NULL,3),(63,115,'Leigh Anne Tuohy',NULL,1),(63,116,'Michael Oher',NULL,2),(63,122,'Coach Cotton',NULL,3),(64,3,'Billy Beane',NULL,1),(64,117,'Peter Brand',NULL,2),(64,121,'Ron Hopkins',NULL,3),(65,110,'Queen Elizabeth',NULL,3),(65,118,'King George VI',NULL,1),(65,119,'Lionel Logue',NULL,2);
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
INSERT INTO `movie_genre` VALUES (18,1),(20,1),(23,1),(24,1),(25,1),(26,1),(33,1),(40,1),(43,1),(44,1),(45,1),(49,1),(21,2),(22,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(34,2),(36,2),(37,2),(38,2),(39,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2),(50,2),(51,2),(52,2),(53,2),(54,2),(55,2),(56,2),(58,2),(59,2),(60,2),(61,2),(62,2),(63,2),(64,2),(65,2),(19,3),(20,3),(23,3),(25,3),(26,3),(27,3),(28,3),(33,3),(34,3),(35,3),(40,3),(18,4),(21,4),(32,4),(43,4),(44,4),(45,4),(47,4),(52,4),(54,4),(19,5),(20,5),(23,5),(25,5),(27,5),(28,5),(35,5),(40,5),(50,5),(58,5),(22,6),(31,7),(21,8),(24,8),(30,8),(37,8),(42,8),(22,9),(34,9),(39,9),(41,9),(61,9),(30,11),(32,11),(44,11),(45,11),(26,12),(31,12),(29,13),(48,13),(49,13),(55,13),(56,13),(58,13),(59,13),(60,13),(61,13),(62,13),(63,13),(64,13),(65,13),(39,14),(42,14),(46,14),(48,14),(49,14),(50,14),(52,14),(53,14),(55,14),(59,14),(60,14),(65,14),(36,15),(41,16),(46,17),(47,17),(48,17),(49,17),(50,17),(51,17),(52,17),(53,17),(54,17),(55,17),(63,19),(64,19),(18,21),(20,21),(24,21),(25,21),(35,21),(43,21),(44,21),(45,21);
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
INSERT INTO `movie_keyword` VALUES (18,1),(25,1),(40,1),(43,1),(44,1),(45,1),(18,2),(38,2),(43,2),(44,2),(45,2),(18,3),(33,3),(43,3),(44,3),(45,3),(19,4),(27,4),(32,4),(19,5),(23,5),(27,5),(28,5),(35,5),(19,6),(20,7),(31,7),(20,8),(24,8),(35,8),(37,8),(20,9),(34,9),(39,9),(21,10),(21,11),(30,11),(44,11),(21,12),(26,12),(22,13),(29,13),(22,14),(42,14),(22,15),(36,15),(23,16),(41,16),(23,17),(24,18),(24,19),(25,20),(25,21),(26,22),(26,23),(44,23),(27,24),(28,25),(28,26),(29,27),(29,28),(30,29),(30,30),(31,31),(31,32),(32,33),(32,34),(43,34),(33,35),(33,36),(34,37),(34,38),(35,39),(36,40),(36,41),(37,42),(37,43),(38,44),(38,45),(39,46),(39,47),(40,48),(45,48),(40,49),(41,50),(41,51),(42,52),(42,53),(45,53),(46,54),(48,54),(50,54),(52,54),(53,54),(55,54),(47,55),(51,55),(54,55),(49,56),(48,57),(50,57),(46,58),(49,58),(50,58),(52,58),(53,58),(46,59),(49,59),(51,59),(54,59),(55,59),(47,60),(51,60),(54,60),(47,61),(53,62),(29,63),(46,63),(48,63),(49,63),(52,63),(53,63),(55,63),(56,63),(58,63),(59,63),(60,63),(61,63),(62,63),(63,63),(64,63),(65,63);
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
INSERT INTO `movie_production_company` VALUES (23,1),(28,1),(18,2),(19,2),(26,2),(27,2),(33,2),(40,2),(43,2),(44,2),(45,2),(46,2),(49,2),(52,2),(53,2),(54,2),(62,2),(18,3),(19,4),(43,4),(44,4),(45,4),(53,4),(20,5),(25,5),(35,5),(21,6),(22,7),(20,8),(25,8),(35,8),(38,10),(56,11),(64,11),(23,12),(46,12),(48,12),(23,13),(24,14),(30,14),(51,14),(58,14),(59,14),(60,14),(63,14),(25,15),(52,15),(26,16),(27,17),(27,18),(28,18),(49,18),(50,18),(29,19),(34,19),(36,19),(37,19),(41,19),(30,20),(42,20),(32,21),(26,22),(33,22),(29,23),(31,24),(51,24),(54,24),(56,24),(60,24),(62,24),(63,24),(64,24),(31,25),(38,25),(47,25),(34,26),(36,26),(37,26),(58,26),(59,26),(61,26),(65,26),(39,27),(40,28),(41,29),(65,29),(24,30),(28,30),(29,30),(30,30),(42,30),(61,30),(55,31),(55,32);
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_company`
--

LOCK TABLES `production_company` WRITE;
/*!40000 ALTER TABLE `production_company` DISABLE KEYS */;
INSERT INTO `production_company` VALUES (14,'20th Century Fox'),(10,'A24'),(12,'Amblin Entertainment'),(22,'Anonymous Content'),(6,'Barunson E&A'),(23,'Blumhouse Productions'),(20,'Bona Film Group'),(18,'Columbia Pictures'),(31,'Cross Creek Pictures'),(3,'DC Films'),(8,'Disney'),(7,'Double Dare You'),(29,'Element Pictures'),(19,'Indian Paintbrush'),(27,'Kennedy Miller Mitchell'),(13,'Legendary Entertainment'),(4,'Legendary Pictures'),(16,'Lynda Obst Productions'),(28,'Marc Platt Productions'),(5,'Marvel Studios'),(32,'Pascal Pictures'),(9,'Pixar'),(24,'Plan B Entertainment'),(25,'Regency Enterprises'),(17,'Scott Free Productions'),(11,'Sony Pictures'),(15,'Syncopy'),(30,'TSG Entertainment'),(1,'Universal Pictures'),(21,'Village Roadshow Pictures'),(2,'Warner Bros.'),(26,'Working Title Films');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (2,20,2,'Good film',8,'2025-06-04'),(3,34,2,'I like it.',9,'2025-06-04'),(4,25,2,'Good',5,'2025-06-20');
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
INSERT INTO `seen` VALUES (18,2),(20,2),(21,2);
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
INSERT INTO `watchlist` VALUES (19,2);
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

-- Dump completed on 2025-06-20 22:29:15
