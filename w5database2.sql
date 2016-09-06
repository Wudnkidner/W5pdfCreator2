-- MySQL dump 10.13  Distrib 5.7.13, for Linux (x86_64)
--
-- Host: localhost    Database: w5database2
-- ------------------------------------------------------
-- Server version	5.7.13-0ubuntu0.16.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Fighters`
--

DROP TABLE IF EXISTS `Fighters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fighters` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `weight` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fighters`
--

LOCK TABLES `Fighters` WRITE;
/*!40000 ALTER TABLE `Fighters` DISABLE KEYS */;
INSERT INTO `Fighters` VALUES (1,'Boris','Kuspal','Slovakia','81'),(2,'Marko','Gurdeljevi?','Serbia','81'),(3,'Greaga','Smole','Slovenia','77'),(4,'Mateusz','?ukowski','Brazil','77');
/*!40000 ALTER TABLE `Fighters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fights`
--

DROP TABLE IF EXISTS `Fights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fights` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `eventname` varchar(30) NOT NULL,
  `place` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  `fightnumber` varchar(30) NOT NULL,
  `cornerred` varchar(30) NOT NULL,
  `countryred` varchar(30) NOT NULL,
  `cornerblue` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `firstjudge` varchar(30) NOT NULL,
  `secondjudge` varchar(30) NOT NULL,
  `thridjudge` varchar(30) NOT NULL,
  `referee` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fights`
--

LOCK TABLES `Fights` WRITE;
/*!40000 ALTER TABLE `Fights` DISABLE KEYS */;
INSERT INTO `Fights` VALUES (1,'FORTUNE FAVORS THE BRAVE','REBUY STARS','18.06.2016','1','Boris Kuspal','Slovakia','Marko Gurdeljevi?','Slovakia','Gyorgy Vojvoda','Mladen Kranjcec','Denis Sidenko','Jiri Bel'),(2,'FORTUNE FAVORS THE BRAVE','REBUY STARS','18.06.2016','2','Greaga Smole','Slovakia','Mateusz ?ukowski','Slovakia','Jiri Bel','Gyorgy Vojvoda','Igor Shimdt','Mladen Kranjcec'),(3,'FORTUNE FAVORS THE BRAVE','REBUY STARS','18.06.2016','3','Boris Kuspal','Slovakia','Mateusz ?ukowski','Slovakia','Igor Shimdt','Jiri Bel','Denis Sidenko','Gyorgy Vojvoda'),(4,'FORTUNE FAVORS THE BRAVE','REBUY STARS','18.06.2016','4','Mateusz ?ukowski','Slovakia','Boris Kuspal','Slovakia','Mladen Kranjcec','Denis Sidenko','Gyorgy Vojvoda','Jiri Bel');
/*!40000 ALTER TABLE `Fights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Judges`
--

DROP TABLE IF EXISTS `Judges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Judges` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Judges`
--

LOCK TABLES `Judges` WRITE;
/*!40000 ALTER TABLE `Judges` DISABLE KEYS */;
INSERT INTO `Judges` VALUES (1,'Gyorgy','Vojvoda','Hungary'),(2,'Jiri','Bel','Czech'),(3,'Mladen','Kranjcec','Croatia'),(4,'Denis','Sidenko','Russia'),(5,'Igor','Shimdt','Slovakia');
/*!40000 ALTER TABLE `Judges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tournaments`
--

DROP TABLE IF EXISTS `Tournaments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tournaments` (
  `id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `place` varchar(30) DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tournaments`
--

LOCK TABLES `Tournaments` WRITE;
/*!40000 ALTER TABLE `Tournaments` DISABLE KEYS */;
INSERT INTO `Tournaments` VALUES (1,'W5 EUROPEAN LEAGUE','Prievidza','REBUY STARS','18.06.2016'),(8,'FORTUNE FAVORS THE BRAVE','ZVOLEN','REBUY STARS','31.08.2016');
/*!40000 ALTER TABLE `Tournaments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-06  5:31:23
