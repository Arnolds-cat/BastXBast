-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.1.23-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dump della struttura del database bastxbast
CREATE DATABASE IF NOT EXISTS `bastxbast` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bastxbast`;



-- Dump della struttura di tabella bastxbast.user
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userfirstname` varchar(250) NOT NULL,
  `userfamilyname` varchar(250) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(16) NOT NULL,
  `userphone` varchar(40) DEFAULT NULL,
  `useremail` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella bastxbast.user: ~0 rows (circa)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userfirstname`, `userfamilyname`, `username`, `password`, `userphone`, `useremail`) VALUES
	('Dario', 'Martello', 'dm', 'test1', NULL, NULL),
	('Riccardo', 'Martello', 'rm', 'test2', NULL, NULL),
	('Lorenzo', 'Martello', 'lm', 'test3', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
