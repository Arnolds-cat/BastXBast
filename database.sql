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
CREATE DATABASE IF NOT EXISTS `dbinstance_6073_1` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbinstance_6073_1`;

-- Dump della struttura di tabella bastxbast.bet
CREATE TABLE IF NOT EXISTS `bet` (
  `IDBet` int(11) NOT NULL,
  `skema` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `positionInSkema` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `initialAmount` float DEFAULT NULL,
  `realQuote` float DEFAULT NULL,
  `playedAmount` float DEFAULT NULL,
  `result` int(11) DEFAULT NULL,
  `skemaCurrentCashAfterResult` float DEFAULT NULL,
  PRIMARY KEY (`IDBet`),
  KEY `skema` (`skema`),
  CONSTRAINT `skema` FOREIGN KEY (`skema`) REFERENCES `skema` (`IDSkema`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella bastxbast.bet: ~2 rows (circa)
/*!40000 ALTER TABLE `bet` DISABLE KEYS */;
/*!40000 ALTER TABLE `bet` ENABLE KEYS */;

-- Dump della struttura di tabella bastxbast.matchresult
CREATE TABLE IF NOT EXISTS `matchresult` (
  `MatchResultID` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  KEY `MatchResultID` (`MatchResultID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella bastxbast.matchresult: ~62 rows (circa)
/*!40000 ALTER TABLE `matchresult` DISABLE KEYS */;
INSERT INTO `matchresult` (`MatchResultID`, `description`) VALUES
	(0, '1'),
	(1, '2'),
	(2, 'X'),
	(3, '1X (d. shance)'),
	(4, 'X2 (d. shance)'),
	(5, '12 (d. shance)'),
	(6, 'Gol'),
	(7, 'JoGol'),
	(8, 'Gol 0-1'),
	(9, 'Gol 0-2'),
	(10, 'Gol 2-3'),
	(11, '3+'),
	(12, '4+'),
	(13, 'Gol 4-6'),
	(14, '7+'),
	(15, '1 dhe 3+'),
	(16, '2 dhe 3+'),
	(17, '1 (p1)'),
	(18, '2 (p1)'),
	(19, 'X (p1)'),
	(20, '1+ (p1)'),
	(21, '2+ (p1)'),
	(22, '0 Gol (p1)'),
	(23, '1 Gol (p1)'),
	(24, '2 Gol (p1)'),
	(25, '3 Gol (p1)'),
	(26, '4 Gol (p1)'),
	(27, '1/1 (45-90)'),
	(28, '1/X (45-90)'),
	(29, '1/2 (45-90)'),
	(30, 'X/1 (45-90)'),
	(31, 'X/X (45-90)'),
	(32, 'X/2 (45-90)'),
	(33, '2/1 (45-90)'),
	(34, '2/X (45-90)'),
	(35, '2/2 (45-90)'),
	(36, 'Korne 0-5'),
	(37, 'Korne 0-6'),
	(38, 'Korne 0-7'),
	(39, 'Korne 0-8'),
	(40, 'Korne 0-9'),
	(41, 'Korne 0-10'),
	(42, 'Korne 0-11'),
	(43, 'Korne 0-12'),
	(44, 'Korne 0-13'),
	(45, 'Korne 0-14'),
	(46, 'Korne 0-15'),
	(47, 'Korne 0-16'),
	(48, 'Korne 0-17'),
	(49, 'Korne 6+'),
	(50, 'Korne 7+'),
	(51, 'Korne 8+'),
	(52, 'Korne 9+'),
	(53, 'Korne 10+'),
	(54, 'Korne 11+'),
	(55, 'Korne 12+'),
	(56, 'Korne 13+'),
	(57, 'Korne 14+'),
	(58, 'Korne 15+'),
	(59, 'Korne 16+'),
	(60, 'Korne 17+'),
	(61, 'Korne 18+');
/*!40000 ALTER TABLE `matchresult` ENABLE KEYS */;

-- Dump della struttura di tabella bastxbast.skema
CREATE TABLE IF NOT EXISTS `skema` (
  `IDSkema` int(11) NOT NULL,
  `NEvents` int(11) DEFAULT NULL,
  `NExpected` int(11) DEFAULT NULL,
  `won` int(11) DEFAULT NULL,
  `played` int(11) DEFAULT NULL,
  `currentCash` float DEFAULT NULL,
  `initialCash` float DEFAULT NULL,
  `finalCash` float DEFAULT NULL,
  `roundedFinalCash` int(11) DEFAULT NULL,
  `share` float DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IDSkema`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella bastxbast.skema: ~2 rows (circa)
/*!40000 ALTER TABLE `skema` DISABLE KEYS */;
/*!40000 ALTER TABLE `skema` ENABLE KEYS */;

-- Dump della struttura di tabella bastxbast.subbet
CREATE TABLE IF NOT EXISTS `subbet` (
  `subbetID` int(11) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `userGuess` int(11) DEFAULT NULL,
  `share` float DEFAULT NULL,
  `bet` int(11) DEFAULT NULL,
  PRIMARY KEY (`subbetID`),
  KEY `FK_subbet_matchresult` (`userGuess`),
  KEY `FK_subbet_bet` (`bet`),
  CONSTRAINT `FK_subbet_bet` FOREIGN KEY (`bet`) REFERENCES `bet` (`IDBet`),
  CONSTRAINT `FK_subbet_matchresult` FOREIGN KEY (`userGuess`) REFERENCES `matchresult` (`MatchResultID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dump dei dati della tabella bastxbast.subbet: ~4 rows (circa)
/*!40000 ALTER TABLE `subbet` DISABLE KEYS */;
/*!40000 ALTER TABLE `subbet` ENABLE KEYS */;

-- Dump della struttura di tabella bastxbast.user
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `score` float DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `userFamilyName` varchar(255) DEFAULT NULL,
  `userFirstName` varchar(255) DEFAULT NULL,
  `userPhone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `manager` int(11) DEFAULT NULL,
  `income` float DEFAULT NULL,
  `managerPercent` float DEFAULT NULL,
  PRIMARY KEY (`userID`),
  KEY `FK_user_user` (`manager`),
  CONSTRAINT `FK_user_user` FOREIGN KEY (`manager`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
