-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 24, 2016 at 09:11 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oi2016`
--

-- --------------------------------------------------------

--
-- Table structure for table `disciplina`
--

CREATE TABLE `disciplina` (
  `idDisciplina` int(11) NOT NULL,
  `idSport` int(11) NOT NULL,
  `naziv` char(50) DEFAULT NULL,
  `vrsta` char(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `disciplina`
--

INSERT INTO `disciplina` (`idDisciplina`, `idSport`, `naziv`, `vrsta`) VALUES
(1, 8, '100m trcanje', 'individualni'),
(2, 7, '100m trcanje', 'individualni'),
(3, 7, '200m trcanje', 'individualni'),
(4, 8, '200m trcanje', 'individualni'),
(5, 8, '400m trcanje', 'individualni'),
(6, 7, '400m trcanje', 'individualni'),
(7, 7, '800m trcanje', 'individualni'),
(8, 8, '800m trcanje', 'individualni'),
(9, 8, '5000m trcanje', 'individualni'),
(10, 7, '5000m trcanje', 'individualni'),
(11, 7, '10000m trcanje', 'individualni'),
(12, 8, '10000m trcanje', 'individualni'),
(13, 8, 'skok u vis', 'individualni'),
(14, 7, 'skok u vis', 'individualni'),
(15, 7, 'skok u dalj', 'individualni'),
(16, 8, 'skok u dalj', 'individualni'),
(17, 8, 'troskok', 'individualni'),
(18, 7, 'troskok', 'individualni'),
(19, 7, 'skok s motkom', 'individualni'),
(20, 8, 'skok s motkom', 'individualni'),
(21, 8, 'bacanje kugle', 'individualni'),
(22, 7, 'bacanje kugle', 'individualni'),
(23, 7, 'bacanje diska', 'individualni'),
(24, 8, 'bacanje diska', 'individualni'),
(25, 8, 'bacanje kladiva', 'individualni'),
(26, 7, 'bacanje kladiva', 'individualni'),
(27, 7, 'bacanje koplja', 'individualni'),
(28, 8, 'bacanje koplja', 'individualni'),
(29, 8, 'maraton', 'individualni'),
(30, 7, 'maraton', 'individualni'),
(31, 7, '20km brzo hodanje', 'individualni'),
(32, 8, '20km brzo hodanje', 'individualni'),
(33, 8, '50km brzo hodanje', 'individualni'),
(34, 7, '50km brzo hodanje', 'individualni'),
(35, 9, 'drumska trka 225km', 'individualni'),
(36, 10, 'drumska trka 225km', 'individualni'),
(37, 12, '100m leptir', 'individualni'),
(38, 11, '100m leptir', 'individualni'),
(39, 11, '200m slobodno', 'individualni'),
(40, 12, '200m slobodno', 'individualni'),
(41, 13, 'singl', 'individualni'),
(42, 14, 'singl', 'individualni'),
(43, 14, 'dubl', 'ekipni'),
(44, 13, 'dubl', 'ekipni'),
(45, 16, '50m trostav', 'individualni'),
(46, 15, '50m trostav', 'individualni'),
(47, 15, '10m vazdusna puska', 'individualni'),
(48, 16, '10m vazdusna puska', 'individualni'),
(49, 16, '25m malokalibarki pistolj', 'individualni'),
(50, 15, '25m malokalibarki pistolj', 'individualni'),
(51, 15, '10m vazdusni pistolj', 'individualni'),
(52, 16, '10m vazdusni pistolj', 'individualni'),
(53, 17, 'singl', 'individualni'),
(54, 18, 'singl', 'individualni'),
(55, 18, 'dubl', 'ekipni'),
(56, 17, 'dubl', 'ekipni');

-- --------------------------------------------------------

--
-- Table structure for table `ekipa`
--

CREATE TABLE `ekipa` (
  `idEkipa` int(11) NOT NULL,
  `idSport` int(11) NOT NULL,
  `nacionalnost` char(25) NOT NULL,
  `brojClanova` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ekipa`
--

INSERT INTO `ekipa` (`idEkipa`, `idSport`, `nacionalnost`, `brojClanova`) VALUES
(1, 2, 'Srbija', 6),
(2, 18, 'Srbija', 2),
(3, 2, 'SAD', 6),
(4, 18, 'SAD', 2),
(5, 2, 'Japan', 6),
(6, 18, 'Japan', 2),
(7, 2, 'Francuska', 6),
(8, 18, 'Francuska', 2),
(9, 2, 'Brazil', 6),
(10, 2, 'Nigerija', 6),
(11, 18, 'Nigerija', 2),
(12, 2, 'Kina', 6),
(13, 18, 'Kina', 2),
(14, 2, 'Australija', 6),
(15, 18, 'Australija', 2),
(16, 2, 'Argentina', 6),
(17, 18, 'Argentina', 2),
(18, 2, 'Kenija', 6),
(19, 18, 'Kenija', 2),
(20, 2, 'Nemacka', 6),
(21, 2, 'Slovacka', 6),
(22, 4, 'Srbija', 7),
(23, 4, 'SAD', 7),
(24, 4, 'Japan', 7),
(25, 4, 'Francuska', 7),
(26, 4, 'Brazil', 7),
(27, 4, 'Nigerija', 7),
(28, 4, 'Kina', 7),
(29, 4, 'Australija', 7),
(30, 4, 'Argentina', 7),
(31, 4, 'Kenija', 7),
(32, 4, 'Nemacka', 7),
(33, 4, 'Slovacka', 7);

-- --------------------------------------------------------

--
-- Table structure for table `ekipabodovi`
--

CREATE TABLE `ekipabodovi` (
  `idTakmicenje` int(11) NOT NULL,
  `idEkipa` int(11) NOT NULL,
  `grupa` char(25) NOT NULL,
  `brojBodova` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ekipabodovi`
--

INSERT INTO `ekipabodovi` (`idTakmicenje`, `idEkipa`, `grupa`, `brojBodova`) VALUES
(4, 1, 'grupaA', 10),
(4, 3, 'grupaB', 9),
(4, 5, 'grupaA', 7),
(4, 7, 'grupaB', 10),
(4, 9, 'grupaB', 8),
(4, 10, 'grupaA', 6),
(4, 12, 'grupaB', 5),
(4, 14, 'grupaA', 8),
(4, 16, 'grupaB', 7),
(4, 18, 'grupaA', 5),
(4, 20, 'grupaB', 6),
(4, 21, 'grupaA', 9),
(13, 22, 'grupaA', 0),
(13, 23, 'grupaA', 0),
(13, 24, 'grupaA', 0),
(13, 25, 'grupaB', 0),
(13, 26, 'grupaB', 0),
(13, 27, 'grupaB', 0),
(13, 28, 'grupaB', 0),
(13, 29, 'grupaA', 0),
(13, 30, 'grupaA', 0),
(13, 31, 'grupaB', 0),
(13, 32, 'grupaA', 0),
(13, 33, 'grupaB', 0);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `idKorisnik` int(11) NOT NULL,
  `username` char(18) DEFAULT NULL,
  `password` char(18) DEFAULT NULL,
  `ime` char(18) DEFAULT NULL,
  `prezime` char(18) DEFAULT NULL,
  `nacionalnost` char(18) DEFAULT NULL,
  `email` char(18) DEFAULT NULL,
  `tip` smallint(6) DEFAULT NULL,
  `aktivan` smallint(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKorisnik`, `username`, `password`, `ime`, `prezime`, `nacionalnost`, `email`, `tip`, `aktivan`) VALUES
(1, 'org', 'org123', 'Mirko', 'Milosevic', 'Srbija', 'org@rio.com', 1, 1),
(2, 'del', 'del123', 'Edison', 'Nascimento', 'Brazil', 'del@rio.com', 2, 1),
(3, 'vod', 'vod123', 'Vlade', 'Divac', 'Srbija', 'vod@rio.com', 3, 1),
(4, 'del2', 'Aasd33!!', 'Zan', 'Pietrus', 'Francuska', 'del2@rio.com', 2, 1),
(5, 'del3', 'Aasd33!!', 'Suzuki', 'Jato', 'Japan', 'del3@rio.com', 2, 1),
(6, 'vod2', 'Aasd33!!', 'Dzon', 'MekGregor', 'SAD', 'vod2@rio.com', 3, 1),
(7, 'vod3', 'Aasd33!!', 'Jamato', 'He', 'Japan', 'vod3@rio.com', 3, 1),
(8, 'vod4', 'Aasd33!!', 'Fransoa', 'Zili', 'Francuska', 'vod4@rio.com', 3, 1),
(9, 'vod5', 'Aasd33!!', 'Ronaldo', 'Ziva', 'Brazil', 'vod5@rio.com', 3, 1),
(10, 'vod6', 'Aasd33!!', 'Alberto', 'Nglamunda', 'Nigerija', 'vod6@rio.com', 3, 1),
(11, 'vod7', 'Aasd33!!', 'Djin', 'Jang', 'Kina', 'vod7@rio.com', 3, 1),
(12, 'vod8', 'Aasd33!!', 'Stiv', 'Mekenlao', 'Australija', 'vod8@rio.com', 3, 1),
(13, 'vod9', 'Aasd33!!', 'Hoze', 'Ramirez', 'Argentina', 'vod9@rio.com', 3, 1),
(14, 'vod10', 'Aasd33!!', 'Adam', 'Jatombo', 'Kenija', 'vod10@rio.com', 3, 1),
(15, 'vod11', 'Aasd33!!', 'Johan', 'Smidt', 'Nemacka', 'vod11@rio.com', 3, 1),
(16, 'vod12', 'Aasd33!!', 'Jaroslav', 'Palicka', 'Slovacka', 'vod12@rio.com', 3, 1),
(17, 'del4', 'Aasd33!!', 'Gregor', 'Miler', 'Nemacka', 'del4@rio.com', 2, 1),
(18, 'del5', 'Aasd33!!', 'Aleks', 'Britan', 'Engleska', 'del5@rio.com', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mec`
--

CREATE TABLE `mec` (
  `idMec` int(11) NOT NULL,
  `idTakmicenje` int(11) NOT NULL,
  `faza` char(25) NOT NULL,
  `ucesnik1` int(11) DEFAULT NULL,
  `ucesnik2` int(11) DEFAULT NULL,
  `datum` char(25) DEFAULT NULL,
  `vreme` char(25) DEFAULT NULL,
  `lokacija` char(25) DEFAULT NULL,
  `rezultat` char(25) DEFAULT NULL,
  `pobednik` int(11) DEFAULT NULL,
  `zavrseno` int(11) DEFAULT NULL,
  `format` int(11) NOT NULL,
  `zakazano` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mec`
--

INSERT INTO `mec` (`idMec`, `idTakmicenje`, `faza`, `ucesnik1`, `ucesnik2`, `datum`, `vreme`, `lokacija`, `rezultat`, `pobednik`, `zavrseno`, `format`, `zakazano`) VALUES
(1, 4, 'grupaA', 10, 21, '1.9.2016', '19:00', 'RioArena', '50:90', 2, 1, 2, 1),
(2, 4, 'grupaA', 18, 1, '2.9.2016', '17:00', 'RioArena', '70:93', 2, 1, 2, 1),
(3, 4, 'grupaA', 14, 5, '4.9.2016', '19:00', 'RioArena1', '93:50', 1, 1, 2, 1),
(4, 4, 'grupaA', 10, 18, '', '', '', '80:60', 1, 1, 2, 1),
(5, 4, 'grupaA', 1, 14, '5.9.2016', '12:00', 'ArenaRio1', '90:5', 1, 1, 2, 1),
(6, 4, 'grupaA', 21, 5, 'ads', 'asd', 'asg', '90:76', 1, 1, 2, 1),
(7, 4, 'grupaA', 10, 1, 'adhadf', 'h', 'hadha', '30:58', 2, 1, 2, 1),
(8, 4, 'grupaA', 21, 14, 'DG', 'dD', 'dg', '80:77', 1, 1, 2, 1),
(9, 4, 'grupaA', 18, 5, 'hksf', 'hsfm', 'sf', '70:78', 2, 1, 2, 1),
(10, 4, 'grupaA', 10, 14, 'aas', 'asg', 'ha', '80:90', 2, 1, 2, 1),
(11, 4, 'grupaA', 21, 18, 'dhd', 'dfh', 'dhf', '90:78', 1, 1, 2, 1),
(12, 4, 'grupaA', 1, 5, 'sms', 'sms', 'dsfdxn', '103:80', 1, 1, 2, 1),
(13, 4, 'grupaA', 10, 5, 'msdg', 'xdsgmdg', 'df,hgxf,', '70:76', 2, 1, 2, 1),
(14, 4, 'grupaA', 21, 1, 'xsf', 'xfh,f', ',xfh,', '80:90', 2, 1, 2, 1),
(15, 4, 'grupaA', 18, 14, 'dg', 'dsgmsd', 'dsgm', '100:107', 2, 1, 2, 1),
(16, 4, 'grupaB', 16, 3, 'sdgjsd', 'sdgjs', 'dgjsdg', '90:100', 2, 1, 2, 1),
(17, 4, 'grupaB', 7, 12, 'fh', 'xfhk,df', 'df,h', '100:8', 1, 1, 2, 1),
(18, 4, 'grupaB', 20, 9, 'sjsfs', 'sgjsjg', 'sjg', '90:97', 2, 1, 2, 1),
(19, 4, 'grupaB', 16, 7, 'hfkxf', 'xfhxfhm', 'xfhmxf', '60:90', 2, 1, 2, 1),
(20, 4, 'grupaB', 12, 20, 'gzjzdg', 'jzdgzdg', 'zdm', '90:100', 2, 1, 2, 1),
(21, 4, 'grupaB', 3, 9, 'ahasf', 'hsfhsfdh', 'dsfh', '100:90', 1, 1, 2, 1),
(22, 4, 'grupaB', 16, 12, 'gmx', 'gmxfgmxf', 'mxdfgm', '100:89', 1, 1, 2, 1),
(23, 4, 'grupaB', 3, 20, 'dands', 'nsdndsn', 'dsgndsgn', '100:70', 1, 1, 2, 1),
(24, 4, 'grupaB', 7, 9, 'xfh,xf', 'h,xfh,xfh,', 'fh,fh,', '100:90', 1, 1, 2, 1),
(25, 4, 'grupaB', 16, 20, 'mdfgmxdf', 'gmdfgm', 'dfgmdf', '100:80', 1, 1, 2, 1),
(26, 4, 'grupaB', 3, 7, 'xdgm', 'xdmgxdmxf', 'xgfmf', '100:101', 2, 1, 2, 1),
(27, 4, 'grupaB', 12, 9, 'zdgzdg', 'zdgmxdzgm', 'dxgm', '80:90', 2, 1, 2, 1),
(28, 4, 'grupaB', 16, 9, 'SHSF', 'SFHSFh', 'sfhzsf', '100:103', 2, 1, 2, 1),
(29, 4, 'grupaB', 3, 12, 'xfmx', 'mxfcmxcmb', 'xcbmx', '100:90', 1, 1, 2, 1),
(30, 4, 'grupaB', 7, 20, 'dgd', 'djd', 'dgjd', '100:70', 1, 1, 2, 1),
(31, 4, '4', 1, 16, 'Ssf', 'bSFbS', 'SFBSCb', '100:90', 1, 1, 2, 1),
(32, 4, '4', 3, 14, 'XCBXB', 'SFBS', 'SbsZdb', '100:98', 1, 1, 2, 1),
(33, 4, '4', 7, 5, 'XBZSb', 'SbZSbzx', 'zxcbzs', '100:70', 1, 1, 2, 1),
(34, 4, '4', 21, 9, 'SbSDFb', 'szfbxcb', 'zxcbzsf', '90:80', 1, 1, 2, 1),
(35, 4, '2', 1, 3, 'sgasg', 'asdgasdg', 'asgasg', '103:100', 1, 1, 2, 1),
(36, 4, '2', 7, 21, 'asgas', 'aasdgas', 'aga', '90:70', 1, 1, 2, 1),
(37, 4, '3', 3, 21, 'g', 'ddddd', 'sss', '90:91', 2, 1, 2, 1),
(38, 4, '1', 1, 7, 'utfj,dfg', 'fgj,.fgj,f', 'gj,dg,', '100:98', 1, 1, 2, 1),
(39, 1, 'grupa1', 21, NULL, '2.9.2016.', '19:00', 'atletska staza', '10,0', 1000, 1, 3, 1),
(40, 1, 'grupa1', 35, NULL, '2.9.2016.', '19:00', 'atletska staza', '10,2', 1002, 1, 3, 1),
(41, 1, 'grupa1', 37, NULL, '2.9.2016.', '19:00', 'atletska staza', '10,5', 1005, 1, 3, 1),
(42, 1, 'grupa1', 36, NULL, '2.9.2016.', '19:00', 'atletska staza', '9,95', 995, 1, 3, 1),
(43, 1, 'grupa1', 63, NULL, '2.9.2016.', '19:00', 'atletska staza', '9,88', 988, 1, 3, 1),
(44, 1, 'grupa1', 19, NULL, '2.9.2016.', '19:00', 'atletska staza', '11,0', 1100, 1, 3, 1),
(45, 1, 'grupa1', 23, NULL, '2.9.2016.', '19:00', 'atletska staza', '9,0', 900, 1, 3, 1),
(46, 1, 'grupa1', 64, NULL, '2.9.2016.', '19:00', 'atletska staza', '10,0', 1000, 1, 3, 1),
(47, 1, 'grupa2', 22, NULL, '2.9.2016.', '20', 'atletska staza', '11,0', 1100, 1, 3, 1),
(48, 1, 'grupa2', 65, NULL, '2.9.2016.', '20', 'atletska staza', '9,56', 956, 1, 3, 1),
(49, 1, 'grupa2', 20, NULL, '2.9.2016.', '20', 'atletska staza', '13,0', 1300, 1, 3, 1),
(50, 1, '1', 23, NULL, '3.9.2016.', '12', 'staza', '9,93', 993, 1, 3, 1),
(51, 1, '1', 65, NULL, '3.9.2016.', '12', 'staza', '10,0', 1000, 1, 3, 1),
(52, 1, '1', 63, NULL, '3.9.2016.', '12', 'staza', '9,92', 992, 1, 3, 1),
(53, 1, '1', 36, NULL, '3.9.2016.', '12', 'staza', '12,0', 1200, 1, 3, 1),
(54, 1, '1', 21, NULL, '3.9.2016.', '12', 'staza', '12,0', 1200, 1, 3, 1),
(55, 1, '1', 64, NULL, '3.9.2016.', '12', 'staza', '12,0', 1200, 1, 3, 1),
(56, 1, '1', 35, NULL, '3.9.2016.', '12', 'staza', '12,0', 1200, 1, 3, 1),
(57, 1, '1', 37, NULL, '3.9.2016.', '12', 'staza', '9,91', 991, 1, 3, 1),
(58, 9, '4', 17, 13, '3.9.2016.', '15:00', 'tereni', '2:1', 1, 1, 1, 1),
(59, 9, '4', 19, 6, '3.9.2016', '16:00', 'tereni', '2:0', 1, 1, 1, 1),
(60, 9, '4', 8, 4, '3.9.2016', '17:00', 'tereni', '2:0', 1, 1, 1, 1),
(61, 9, '4', 15, 2, '4.9.2016', '18:00', 'tereni', '1:2', 2, 1, 1, 1),
(62, 9, '2', 17, 19, '4.9.2016', '12:00', 'tereni', '2:1', 1, 1, 1, 1),
(63, 9, '2', 8, 2, '4.9.2016', '15:00', 'tereni', '3:0', 1, 1, 1, 1),
(64, 9, '3', 19, 2, '5.9.2016', '12:00', 'tereni', '0:2', 2, 1, 1, 1),
(65, 9, '1', 17, 8, '5.9.2016', '13:00', 'tereni', '2:1', 1, 1, 1, 1),
(66, 10, '1', 73, NULL, '6.9.2016', '19:00', 'Ipanema', '1,52,22', 15222, 1, 4, 1),
(67, 10, '1', 72, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(68, 10, '1', 71, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(69, 10, '1', 26, NULL, '6.9.2016', '19:00', 'Ipanema', '1,59,0', 15900, 1, 4, 1),
(70, 10, '1', 24, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(71, 10, '1', 53, NULL, '6.9.2016', '19:00', 'Ipanema', '1,59,33', 15933, 1, 4, 1),
(72, 10, '1', 52, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(73, 10, '1', 25, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(74, 10, '1', 54, NULL, '6.9.2016', '19:00', 'Ipanema', '2,0,0', 20000, 1, 4, 1),
(75, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(76, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(77, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(78, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(79, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(80, 11, 'grupa1', 9, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(81, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(82, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(83, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(84, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(85, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(86, 11, 'grupa1', 27, NULL, '6.9.2016.', '15:00', 'streljana', '99', NULL, 1, 5, 1),
(87, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(88, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(89, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(90, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(91, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(92, 11, 'grupa1', 46, NULL, '6.9.2016.', '15:00', 'streljana', '98', NULL, 1, 5, 1),
(93, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(94, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(95, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(96, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(97, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(98, 11, 'grupa1', 47, NULL, '6.9.2016.', '15:00', 'streljana', '99', NULL, 1, 5, 1),
(99, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(100, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(101, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(102, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(103, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(104, 11, 'grupa1', 67, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(105, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(106, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(107, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(108, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(109, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(110, 11, 'grupa1', 66, NULL, '6.9.2016.', '15:00', 'streljana', '90', NULL, 1, 5, 1),
(111, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(112, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(113, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(114, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(115, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(116, 11, 'grupa1', 81, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(117, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '99', NULL, 1, 5, 1),
(118, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(119, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '99', NULL, 1, 5, 1),
(120, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '99', NULL, 1, 5, 1),
(121, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(122, 11, 'grupa1', 80, NULL, '6.9.2016.', '15:00', 'streljana', '100', NULL, 1, 5, 1),
(123, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '100', NULL, 1, 5, 1),
(124, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '1000', NULL, 1, 5, 1),
(125, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '100', NULL, 1, 5, 1),
(126, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '100', NULL, 1, 5, 1),
(127, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '100', NULL, 1, 5, 1),
(128, 11, 'grupa2', 48, NULL, '5.9.2016.', '17:00', 'streljana', '100', NULL, 1, 5, 1),
(129, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(130, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(131, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(132, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(133, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(134, 11, '1', 48, NULL, '7.9.2016.', '19:00', 'streljana', '99', NULL, 1, 5, 1),
(135, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(136, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(137, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(138, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(139, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(140, 11, '1', 9, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(141, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(142, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(143, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(144, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(145, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '100', NULL, 1, 5, 1),
(146, 11, '1', 67, NULL, '7.9.2016.', '19:00', 'streljana', '97', NULL, 1, 5, 1),
(147, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(148, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(149, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(150, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(151, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(152, 11, '1', 81, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(153, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(154, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(155, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(156, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(157, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '9', NULL, 1, 5, 1),
(158, 11, '1', 27, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(159, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(160, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(161, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(162, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(163, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(164, 11, '1', 46, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(165, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(166, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(167, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(168, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(169, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(170, 11, '1', 80, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(171, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(172, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(173, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(174, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(175, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(176, 11, '1', 66, NULL, '7.9.2016.', '19:00', 'streljana', '90', NULL, 1, 5, 1),
(177, 13, 'grupaA', 32, 22, '5.8.2016.', '10:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(178, 13, 'grupaA', 24, 30, '5.8.2016.', '11:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(179, 13, 'grupaA', 29, 23, '5.8.2016.', '12:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(180, 13, 'grupaA', 32, 24, '5.8.2016.', '13:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(181, 13, 'grupaA', 30, 29, '5.8.2016.', '14:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(182, 13, 'grupaA', 22, 23, '5.8.2016.', '15:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(183, 13, 'grupaA', 32, 30, '5.8.2016.', '16:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(184, 13, 'grupaA', 22, 29, '5.8.2016.', '17:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(185, 13, 'grupaA', 24, 23, '5.8.2016.', '18:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(186, 13, 'grupaA', 32, 29, '5.8.2016.', '19:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(187, 13, 'grupaA', 22, 24, '5.8.2016.', '20:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(188, 13, 'grupaA', 30, 23, '5.8.2016.', '21:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(189, 13, 'grupaA', 32, 23, '5.8.2016.', '22:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(190, 13, 'grupaA', 22, 30, '6.8.2016', '10:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(191, 13, 'grupaA', 24, 29, '6.8.2016', '11:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(192, 13, 'grupaB', 31, 28, '6.8.2016', '12:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(193, 13, 'grupaB', 25, 33, '6.8.2016', '13:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(194, 13, 'grupaB', 26, 27, '6.8.2016', '14:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(195, 13, 'grupaB', 31, 25, '6.8.2016', '15:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(196, 13, 'grupaB', 33, 26, '6.8.2016', '16:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(197, 13, 'grupaB', 28, 27, '7.8.2016.', '10:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(198, 13, 'grupaB', 31, 33, '7.8.2016.', '11:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(199, 13, 'grupaB', 28, 26, '7.8.2016.', '12:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(200, 13, 'grupaB', 25, 27, '7.8.2016.', '13:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(201, 13, 'grupaB', 31, 26, '7.8.2016.', '14:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(202, 13, 'grupaB', 28, 25, '7.8.2016.', '15:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(203, 13, 'grupaB', 33, 27, '7.8.2016.', '16:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(204, 13, 'grupaB', 31, 27, '7.8.2016.', '17:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(205, 13, 'grupaB', 28, 33, '7.8.2016.', '18:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(206, 13, 'grupaB', 25, 26, '7.8.2016.', '19:00', 'Hala Rio', NULL, NULL, 0, 2, 1),
(207, 13, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(208, 13, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(209, 13, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(210, 13, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(211, 13, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(212, 13, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(213, 13, '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0),
(214, 13, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `rekord`
--

CREATE TABLE `rekord` (
  `idRekord` int(11) NOT NULL,
  `godina` int(11) NOT NULL,
  `mesto` char(25) NOT NULL,
  `disciplina` char(50) NOT NULL,
  `takmicar` char(30) NOT NULL,
  `nacionalnost` char(25) NOT NULL,
  `vrednost` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rekord`
--

INSERT INTO `rekord` (`idRekord`, `godina`, `mesto`, `disciplina`, `takmicar`, `nacionalnost`, `vrednost`) VALUES
(1, 2012, 'London', '100m M', 'Jusejn Bolt', 'Jamajka', '9,63'),
(2, 2008, 'Peking', '200m M', 'Jusejn Bolt', 'Jamajka', '19,30'),
(3, 2016, 'Rio', '400m M', 'Vejd van Nikerk', 'Juznoafricka republika', '43,03'),
(4, 2012, 'London', '800m M', 'Dejvid Rudisa', 'Kenija', '1,40,91'),
(5, 1996, 'Atlanta', '1500m M', 'Noa Ngeni', 'Kenija', '3,32,07'),
(6, 2008, 'Peking', '5000m M', 'Kenenisa Bekele', 'Etiopija', '12,57,82'),
(7, 2008, 'Peking', '10000m M', 'Kenenisa Bekele', 'Etiopija', '27,01,17'),
(8, 2008, 'London', 'maraton M', 'Samjuel Kamau Vanjiru', 'Kenija', '2,06,32'),
(9, 1996, 'Atlanta', 'skok u vis M', 'Carls Ostin', 'SAD', '2,39'),
(10, 2016, 'Rio', 'skok s motkom M', 'Tijago Braz da Silva', 'Brazil', '6,03'),
(11, 1968, 'Meksiko Siti', 'skok udalj M', 'Bob Bimon', 'SAD', '8,90'),
(12, 1996, 'Atlanta', 'troskok M', 'Keni Harison', 'SAD', '18,09'),
(13, 1988, 'Seul', 'bacanje kugle M', 'Ulf Timerman', 'Istocna Nemacka', '22,47'),
(14, 2004, 'Atina', 'bacanje diska M', 'Virgilijus Alekna', 'Litvanija', '69,89'),
(15, 1988, 'Seul', 'bacanje kladiva M', 'Sergej Litvinov', 'Sovjetski Savez', '84,80'),
(16, 2008, 'Peking', 'bacanje koplja M', 'Andreas Torkildsen', 'Norveska', '90,57'),
(17, 2012, 'London', '20km hodanje M', 'Cen Ding', 'Kina', '1,18,46'),
(18, 2012, 'London', '50km hodanje M', 'Sergej Kirdjapkin', 'Rusija', '3,35,59'),
(19, 1988, 'Seul', '100m Z', 'Florens Grifit Dzojner', 'SAD', '10,62'),
(20, 1988, 'Seul', '200m Z', 'Florens Grifit Dzojner', 'SAD', '21,34'),
(21, 1996, 'Atlanta', '400m Z', 'Mari-Zoze Perek', 'Francuska', '48,25'),
(22, 1980, 'Moskva', '800m Z', 'Nadezda Olizarenko', 'Sovjetski Savez', '1,53,43'),
(23, 1988, 'Seul', '1500m Z', 'Paula Ivan', 'Rumunija', '3,53,96'),
(24, 2016, 'Rio', '5000m Z', 'Vivijan Cerijot', 'Kenija', '14,26,17'),
(25, 2016, 'Rio', '10000m Z', 'Almaz Ajana', 'Etiopija', '29,17,45'),
(26, 2012, 'London', 'maraton Z', 'Tiki Gelana', 'Etiopija', '2,23,07'),
(27, 2004, 'Atina', 'skok uvis Z', 'Jelena Slesarenko', 'Rusija', '2,06'),
(28, 2008, 'Peking', 'skok motkom Z', 'Jelena Isinbajeva', 'Rusija', '4,91'),
(29, 1988, 'Seul', 'skok udalj Z', 'Dzeki Dzojner Kersi', 'SAD', '7,40'),
(30, 2008, 'Peking', 'troskok Z', 'Fransoaz Mobango Etone', 'Kambodza', '15,39'),
(31, 1980, 'Moskva', 'bacanje diska Z', 'Iliona Slupijanek', 'Istocna Nemacka', '22,41'),
(32, 1988, 'Seul', 'bacanje diska Z', 'Martina Helman', 'Istocna Nemacka', '72,30'),
(33, 2016, 'Rio', 'bacanje kladiva Z', 'Anita Vlodarcik', 'Rusija', '82,29'),
(34, 2004, 'Atina', 'bacanje koplja Z', 'Osleidis Manendes', 'Kuba', '71,53'),
(35, 2012, 'London', '20km hodanje Z', 'Jelena Lasmanova', 'Rusija', '1,25,02');

-- --------------------------------------------------------

--
-- Table structure for table `sport`
--

CREATE TABLE `sport` (
  `idSport` int(11) NOT NULL,
  `naziv` char(25) DEFAULT NULL,
  `kategorija` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sport`
--

INSERT INTO `sport` (`idSport`, `naziv`, `kategorija`) VALUES
(1, 'Kosarka', 'Z'),
(2, 'Kosarka', 'M'),
(3, 'Odbojka', 'M'),
(4, 'Odbojka', 'Z'),
(5, 'Vaterpolo', 'Z'),
(6, 'Vaterpolo', 'M'),
(7, 'Atletika', 'M'),
(8, 'Atletika', 'Z'),
(9, 'Biciklizam', 'Z'),
(10, 'Biciklizam', 'M'),
(11, 'Plivanje', 'M'),
(12, 'Plivanje', 'Z'),
(13, 'Stoni tenis', 'Z'),
(14, 'Stoni tenis', 'M'),
(15, 'Streljastvo', 'M'),
(16, 'Streljastvo', 'Z'),
(17, 'Tenis', 'Z'),
(18, 'Tenis', 'M');

-- --------------------------------------------------------

--
-- Table structure for table `sportista`
--

CREATE TABLE `sportista` (
  `idSportista` int(11) NOT NULL,
  `idSport` int(11) NOT NULL,
  `ime` char(25) DEFAULT NULL,
  `prezime` char(25) DEFAULT NULL,
  `nacionalnost` char(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportista`
--

INSERT INTO `sportista` (`idSportista`, `idSport`, `ime`, `prezime`, `nacionalnost`) VALUES
(1, 2, 'Kosarkas', 'M1', 'Srbija'),
(2, 2, 'Kosarkas', 'M2', 'Srbija'),
(3, 2, 'Kosarkas', 'M3', 'Srbija'),
(4, 2, 'Kosarkas', 'M4', 'Srbija'),
(5, 2, 'Kosarkas', 'M5', 'Srbija'),
(6, 2, 'Kosarkas', 'M6', 'Srbija'),
(7, 18, 'TeniserSrb', 'M1', 'Srbija'),
(8, 18, 'TeniserSrb', 'M2', 'Srbija'),
(9, 16, 'StreljasicaSrb', 'Z1', 'Srbija'),
(10, 15, 'StreljasSrb', 'M1', 'Srbija'),
(11, 2, 'Kosarkas', 'M1', 'SAD'),
(12, 2, 'Kosarkas', 'M2', 'SAD'),
(13, 2, 'Kosarkas', 'M3', 'SAD'),
(14, 2, 'Kosarkas', 'M4', 'SAD'),
(15, 2, 'Kosarkas', 'M5', 'SAD'),
(16, 2, 'Kosarkas', 'M6', 'SAD'),
(17, 18, 'TeniserSAD', 'M1', 'SAD'),
(18, 18, 'TeniserSAD', 'M2', 'SAD'),
(19, 8, 'AtleticarkaSAD', 'Z1', 'SAD'),
(20, 8, 'AtleticarkaSAD', 'Z2', 'SAD'),
(21, 8, 'AtleticarkaSAD', 'Z3', 'SAD'),
(22, 8, 'AtleticarkaSAD', 'Z4', 'SAD'),
(23, 8, 'AtleticarkaSAD', 'Z5', 'SAD'),
(24, 10, 'BiciklistaSAD', 'M1', 'SAD'),
(25, 10, 'BiciklistaSAD', 'M2', 'SAD'),
(26, 10, 'BiciklistaSAD', 'M3', 'SAD'),
(27, 16, 'StreljasicaJAP', 'Z1', 'Japan'),
(28, 15, 'StreljasJap', 'M1', 'Japan'),
(29, 2, 'Kosarkas', 'M1', 'Japan'),
(30, 2, 'Kosarkas', 'M2', 'Japan'),
(31, 2, 'Kosarkas', 'M3', 'Japan'),
(32, 2, 'Kosarkas', 'M4', 'Japan'),
(33, 2, 'Kosarkas', 'M5', 'Japan'),
(34, 2, 'Kosarkas', 'M6', 'Japan'),
(35, 8, 'AtleticarkaJap', 'Z1', 'Japan'),
(36, 8, 'AtleticarkaJap', 'Z2', 'Japan'),
(37, 8, 'AtleticarkaJap', 'Z3', 'Japan'),
(38, 18, 'TeniserJap', 'M1', 'Japan'),
(39, 18, 'TeniserJap', 'M2', 'Japan'),
(40, 2, 'Kosarkas', 'M1', 'Francuska'),
(41, 2, 'Kosarkas', 'M2', 'Francuska'),
(42, 2, 'Kosarkas', 'M3', 'Francuska'),
(43, 2, 'Kosarkas', 'M4', 'Francuska'),
(44, 2, 'Kosarkas', 'M5', 'Francuska'),
(45, 2, 'Kosarkas', 'M6', 'Francuska'),
(46, 16, 'StreljasicaFra', 'Z1', 'Francuska'),
(47, 16, 'StreljasicaFra', 'Z2', 'Francuska'),
(48, 16, 'StreljasicaFra', 'Z3', 'Francuska'),
(49, 15, 'StreljasicaFra', 'M1', 'Francuska'),
(50, 15, 'StreljasFra', 'M2', 'Francuska'),
(51, 15, 'StreljasFra', 'M3', 'Francuska'),
(52, 10, 'BiciklistaFra', 'M1', 'Francuska'),
(53, 10, 'BiciklistaFra', 'M2', 'Francuska'),
(54, 10, 'BiciklistaFra', 'M3', 'Francuska'),
(55, 18, 'TeniserFra', 'M1', 'Francuska'),
(56, 18, 'TeniserFra', 'M2', 'Francuska'),
(57, 2, 'Kosarkas', 'M1', 'Brazil'),
(58, 2, 'Kosarkas', 'M2', 'Brazil'),
(59, 2, 'Kosarkas', 'M3', 'Brazil'),
(60, 2, 'Kosarkas', 'M4', 'Brazil'),
(61, 2, 'Kosarkas', 'M5', 'Brazil'),
(62, 2, 'Kosarkas', 'M6', 'Brazil'),
(63, 8, 'AtleticarkaBra', 'Z1', 'Brazil'),
(64, 8, 'AtleticarkaBra', 'Z2', 'Brazil'),
(65, 8, 'AtleticarkaBra', 'Z3', 'Brazil'),
(66, 16, 'StreljasicaBra', 'Z1', 'Brazil'),
(67, 16, 'StreljasicaBra', 'Z2', 'Brazil'),
(68, 15, 'StreljasBra', 'M1', 'Brazil'),
(69, 15, 'StreljasBra', 'M2', 'Brazil'),
(70, 15, 'StreljasBra', 'M3', 'Brazil'),
(71, 10, 'BiciklistaBra', 'M1', 'Brazil'),
(72, 10, 'BiciklistaBra', 'M2', 'Brazil'),
(73, 10, 'BiciklistaBra', 'M3', 'Brazil'),
(74, 2, 'Kosarkas', 'M1', 'Nigerija'),
(75, 2, 'Kosarkas', 'M2', 'Nigerija'),
(76, 2, 'Kosarkas', 'M3', 'Nigerija'),
(77, 2, 'Kosarkas', 'M4', 'Nigerija'),
(78, 2, 'Kosarkas', 'M5', 'Nigerija'),
(79, 2, 'Kosarkas', 'M6', 'Nigerija'),
(80, 16, 'StreljasicaNig', 'Z1', 'Nigerija'),
(81, 16, 'StreljasicaNig', 'Z2', 'Nigerija'),
(82, 15, 'StreljasNig', 'M1', 'Nigerija'),
(83, 15, 'StreljasNig', 'M2', 'Nigerija'),
(84, 15, 'StreljasNig', 'M3', 'Nigerija'),
(85, 18, 'TeniserNig', 'M1', 'Nigerija'),
(86, 18, 'TeniserNig', 'M2', 'Nigerija'),
(87, 2, 'Kosarkas', 'M1', 'Kina'),
(88, 2, 'Kosarkas', 'M2', 'Kina'),
(89, 2, 'Kosarkas', 'M3', 'Kina'),
(90, 2, 'Kosarkas', 'M4', 'Kina'),
(91, 2, 'Kosarkas', 'M5', 'Kina'),
(92, 2, 'Kosarkas', 'M6', 'Kina'),
(93, 18, 'TeniserKin', 'M1', 'Kina'),
(94, 18, 'TeniserKin', 'M2', 'Kina'),
(95, 15, 'StreljasKin', 'M1', 'Kina'),
(96, 15, 'StreljasKin', 'M2', 'Kina'),
(97, 15, 'StreljasKin', 'M3', 'Kina'),
(98, 7, 'MaratonacKin', 'M1', 'Kina'),
(99, 7, 'MaratonacKin', 'M2', 'Kina'),
(100, 7, 'MaratonacKin', 'M3', 'Kina'),
(101, 7, 'MaratonacKin', 'M4', 'Kina'),
(102, 2, 'Kosarkas', 'M1', 'Australija'),
(103, 2, 'Kosarkas', 'M2', 'Australija'),
(104, 2, 'Kosarkas', 'M3', 'Australija'),
(105, 2, 'Kosarkas', 'M4', 'Australija'),
(106, 2, 'Kosarkas', 'M5', 'Australija'),
(107, 2, 'Kosarkas', 'M6', 'Australija'),
(108, 18, 'TeniserAus', 'M1', 'Australija'),
(109, 18, 'TeniserAus', 'M2', 'Australija'),
(110, 7, 'MaratonacAus', 'M1', 'Australija'),
(111, 7, 'MaratonacAus', 'M2', 'Australija'),
(112, 8, 'AtleticarkaAus', 'Z1', 'Australija'),
(113, 8, 'AtleticarkaAus', 'Z2', 'Australija'),
(114, 8, 'AtleticarkaAus', 'Z3', 'Australija'),
(115, 8, 'AtleticarkaAus', 'Z4', 'Australija'),
(116, 8, 'AtleticarkaAus', 'Z5', 'Australija'),
(117, 2, 'Kosarkas', 'M1', 'Argentina'),
(118, 2, 'Kosarkas', 'M2', 'Argentina'),
(119, 2, 'Kosarkas', 'M3', 'Argentina'),
(120, 2, 'Kosarkas', 'M4', 'Argentina'),
(121, 2, 'Kosarkas', 'M5', 'Argentina'),
(122, 2, 'Kosarkas', 'M6', 'Argentina'),
(123, 18, 'TeniserArg', 'M1', 'Argentina'),
(124, 18, 'TeniserArg', 'M2', 'Argentina'),
(125, 7, 'MaratonacArg', 'M1', 'Argentina'),
(126, 7, 'MaratonacArg', 'M2', 'Argentina'),
(127, 7, 'MaratonacArg', 'M3', 'Argentina'),
(128, 7, 'MaratonacArg', 'M4', 'Argentina'),
(129, 7, 'MaratonacArg', 'M5', 'Argentina'),
(130, 8, 'AtleticarkaArg', 'Z1', 'Argentina'),
(131, 8, 'AtleticarkaArg', 'Z2', 'Argentina'),
(132, 8, 'AtleticarkaArg', 'Z3', 'Argentina'),
(133, 8, 'AtleticarkaArg', 'Z4', 'Argentina'),
(134, 2, 'Kosarkas', 'M1', 'Kenija'),
(135, 2, 'Kosarkas', 'M2', 'Kenija'),
(136, 2, 'Kosarkas', 'M3', 'Kenija'),
(137, 2, 'Kosarkas', 'M4', 'Kenija'),
(138, 2, 'Kosarkas', 'M5', 'Kenija'),
(139, 2, 'Kosarkas', 'M6', 'Kenija'),
(140, 18, 'TeniserKen', 'M1', 'Kenija'),
(141, 18, 'TeniserKen', 'M2', 'Kenija'),
(142, 7, 'MaratonacKen', 'M1', 'Kenija'),
(143, 7, 'MaratonacKen', 'M2', 'Kenija'),
(144, 8, 'AtleticarkaKen', 'Z1', 'Kenija'),
(145, 8, 'AtleticarkaKen', 'Z2', 'Kenija'),
(146, 8, 'AtleticarkaKen', 'Z3', 'Kenija'),
(147, 2, 'Kosarkas', 'M1', 'Nemacka'),
(148, 2, 'Kosarkas', 'M2', 'Nemacka'),
(149, 2, 'Kosarkas', 'M3', 'Nemacka'),
(150, 2, 'Kosarkas', 'M4', 'Nemacka'),
(151, 2, 'Kosarkas', 'M5', 'Nemacka'),
(152, 2, 'Kosarkas', 'M6', 'Nemacka'),
(153, 2, 'Kosarkas', 'M1', 'Slovacka'),
(154, 2, 'Kosarkas', 'M2', 'Slovacka'),
(155, 2, 'Kosarkas', 'M3', 'Slovacka'),
(156, 2, 'Kosarkas', 'M4', 'Slovacka'),
(157, 2, 'Kosarkas', 'M5', 'Slovacka'),
(158, 2, 'Kosarkas', 'M6', 'Slovacka'),
(159, 4, 'OdbojkasicaSrb', 'Z1', 'Srbija'),
(160, 4, 'OdbojkasicaSrb', 'Z2', 'Srbija'),
(161, 4, 'OdbojkasicaSrb', 'Z3', 'Srbija'),
(162, 4, 'OdbojkasicaSrb', 'Z4', 'Srbija'),
(163, 4, 'OdbojkasicaSrb', 'Z5', 'Srbija'),
(164, 4, 'OdbojkasicaSrb', 'Z6', 'Srbija'),
(165, 4, 'OdbojkasicaSrb', 'Z7', 'Srbija'),
(166, 14, 'StonoteniserSrb', 'M1', 'Srbija'),
(167, 8, 'SkokUVisSrb', 'Z1', 'Srbija'),
(168, 8, 'SkokUVisSrb', 'Z2', 'Srbija'),
(169, 15, 'PuskaSrb', 'M1', 'Srbija'),
(170, 9, 'BiciklSrb', 'Z1', 'Srbija'),
(171, 4, 'OdbojkasicaSAD', 'Z1', 'SAD'),
(172, 4, 'OdbojkasicaSAD', 'Z2', 'SAD'),
(173, 4, 'OdbojkasicaSAD', 'Z3', 'SAD'),
(174, 4, 'OdbojkasicaSAD', 'Z4', 'SAD'),
(175, 4, 'OdbojkasicaSAD', 'Z5', 'SAD'),
(176, 4, 'OdbojkasicaSAD', 'Z6', 'SAD'),
(177, 4, 'OdbojkasicaSAD', 'Z7', 'SAD'),
(178, 14, 'StonoteniserSAD', 'M1', 'SAD'),
(179, 8, 'SkokUVisSAD', 'Z1', 'SAD'),
(180, 8, 'SkokUVisSAD', 'Z2', 'SAD'),
(181, 15, 'PuskaSAD', 'M1', 'SAD'),
(182, 9, 'Bicikl', 'Z1', 'SAD'),
(183, 4, 'OdbojkasicaJap', 'Z1', 'Japan'),
(184, 4, 'OdbojkasicaJap', 'Z2', 'Japan'),
(185, 4, 'OdbojkasicaJap', 'Z3', 'Japan'),
(186, 4, 'OdbojkasicaJap', 'Z4', 'Japan'),
(187, 4, 'OdbojkasicaJap', 'Z5', 'Japan'),
(188, 4, 'OdbojkasicaJap', 'Z6', 'Japan'),
(189, 4, 'OdbojkasicaJap', 'Z7', 'Japan'),
(190, 14, 'StonoteniserJap', 'M1', 'Japan'),
(191, 8, 'SkokUVisJap', 'Z1', 'Japan'),
(192, 8, 'SkokUVisJap', 'Z2', 'Japan'),
(193, 15, 'PuskaJap', 'M1', 'Japan'),
(194, 9, 'Bicikl', 'Z1', 'Japan'),
(195, 4, 'OdbojkasicaFra', 'Z1', 'Francuska'),
(196, 4, 'OdbojkasicaFra', 'Z2', 'Francuska'),
(197, 4, 'OdbojkasicaFra', 'Z3', 'Francuska'),
(198, 4, 'OdbojkasicaFra', 'Z4', 'Francuska'),
(199, 4, 'OdbojkasicaFra', 'Z5', 'Francuska'),
(200, 4, 'OdbojkasicaFra', 'Z6', 'Francuska'),
(201, 4, 'OdbojkasicaFra', 'Z7', 'Francuska'),
(202, 14, 'StonoteniserFra', 'M1', 'Francuska'),
(203, 8, 'SkokUVisFra', 'Z1', 'Francuska'),
(204, 8, 'SkokUVisFra', 'Z2', 'Francuska'),
(205, 15, 'PuskaFra', 'M1', 'Francuska'),
(206, 9, 'BiciklFra', 'Z1', 'Francuska'),
(207, 4, 'OdbojkasicaBra', 'Z1', 'Brazil'),
(208, 4, 'OdbojkasicaBra', 'Z2', 'Brazil'),
(209, 4, 'OdbojkasicaBra', 'Z3', 'Brazil'),
(210, 4, 'OdbojkasicaBra', 'Z4', 'Brazil'),
(211, 4, 'OdbojkasicaBra', 'Z5', 'Brazil'),
(212, 4, 'OdbojkasicaBra', 'Z6', 'Brazil'),
(213, 4, 'OdbojkasicaBra', 'Z7', 'Brazil'),
(214, 14, 'StonoteniserBra', 'M1', 'Brazil'),
(215, 8, 'SkokUVisBra', 'Z1', 'Brazil'),
(216, 8, 'SkokUVisBra', 'Z2', 'Brazil'),
(217, 15, 'PuskaBra', 'M1', 'Brazil'),
(218, 9, 'BiciklBra', 'Z1', 'Brazil'),
(219, 4, 'OdbojkasicaNig', 'Z1', 'Nigerija'),
(220, 4, 'OdbojkasicaNig', 'Z2', 'Nigerija'),
(221, 4, 'OdbojkasicaNig', 'Z3', 'Nigerija'),
(222, 4, 'OdbojkasicaNig', 'Z4', 'Nigerija'),
(223, 4, 'OdbojkasicaNig', 'Z5', 'Nigerija'),
(224, 4, 'OdbojkasicaNig', 'Z6', 'Nigerija'),
(225, 4, 'OdbojkasicaNig', 'Z7', 'Nigerija'),
(226, 14, 'StonoteniserNig', 'M1', 'Nigerija'),
(227, 8, 'SkokUVisNig', 'Z1', 'Nigerija'),
(228, 8, 'SkokUVisNig', 'Z2', 'Nigerija'),
(229, 15, 'PuskaNig', 'M1', 'Nigerija'),
(230, 9, 'BiciklNig', 'Z1', 'Nigerija'),
(231, 4, 'OdbojkasicaKin', 'Z1', 'Kina'),
(232, 4, 'OdbojkasicaKin', 'Z2', 'Kina'),
(233, 4, 'OdbojkasicaKin', 'Z3', 'Kina'),
(234, 4, 'OdbojkasicaKin', 'Z4', 'Kina'),
(235, 4, 'OdbojkasicaKin', 'Z5', 'Kina'),
(236, 4, 'OdbojkasicaKin', 'Z6', 'Kina'),
(237, 4, 'OdbojkasicaKin', 'Z7', 'Kina'),
(238, 14, 'StonoteniserKin', 'M1', 'Kina'),
(239, 8, 'SkokUVisKin', 'Z1', 'Kina'),
(240, 8, 'SkokUVisKin', 'Z2', 'Kina'),
(241, 15, 'PuskaKin', 'M1', 'Kina'),
(242, 4, 'OdbojkasicaAus', 'Z1', 'Australija'),
(243, 4, 'OdbojkasicaAus', 'Z2', 'Australija'),
(244, 4, 'OdbojkasicaAus', 'Z3', 'Australija'),
(245, 4, 'OdbojkasicaAus', 'Z4', 'Australija'),
(246, 4, 'OdbojkasicaAus', 'Z5', 'Australija'),
(247, 4, 'OdbojkasicaAus', 'Z6', 'Australija'),
(248, 4, 'OdbojkasicaAus', 'Z7', 'Australija'),
(249, 14, 'StonoteniserAus', 'M1', 'Australija'),
(250, 15, 'PuskaAus', 'M1', 'Australija'),
(251, 9, 'BiciklAus', 'Z1', 'Australija'),
(252, 4, 'OdbojkasicaArg', 'Z1', 'Argentina'),
(253, 4, 'OdbojkasicaArg', 'Z2', 'Argentina'),
(254, 4, 'OdbojkasicaArg', 'Z3', 'Argentina'),
(255, 4, 'OdbojkasicaArg', 'Z4', 'Argentina'),
(256, 4, 'OdbojkasicaArg', 'Z5', 'Argentina'),
(257, 4, 'OdbojkasicaArg', 'Z6', 'Argentina'),
(258, 4, 'OdbojkasicaArg', 'Z7', 'Argentina'),
(259, 15, 'PuskaArg', 'M1', 'Argentina'),
(260, 4, 'OdbojkasicaKen', 'Z1', 'Kenija'),
(261, 4, 'OdbojkasicaKen', 'Z2', 'Kenija'),
(262, 4, 'OdbojkasicaKen', 'Z3', 'Kenija'),
(263, 4, 'OdbojkasicaKen', 'Z4', 'Kenija'),
(264, 4, 'OdbojkasicaKen', 'Z5', 'Kenija'),
(265, 4, 'OdbojkasicaKen', 'Z6', 'Kenija'),
(266, 4, 'OdbojkasicaKen', 'Z7', 'Kenija'),
(267, 15, 'PuskaKen', 'M1', 'Kenija'),
(268, 4, 'OdbojkasicaNem', 'Z1', 'Nemacka'),
(269, 4, 'OdbojkasicaNem', 'Z2', 'Nemacka'),
(270, 4, 'OdbojkasicaNem', 'Z3', 'Nemacka'),
(271, 4, 'OdbojkasicaNem', 'Z4', 'Nemacka'),
(272, 4, 'OdbojkasicaNem', 'Z5', 'Nemacka'),
(273, 4, 'OdbojkasicaNem', 'Z6', 'Nemacka'),
(274, 4, 'OdbojkasicaNem', 'Z7', 'Nemacka'),
(275, 4, 'OdbojkasicaSlo', 'Z1', 'Slovacka'),
(276, 4, 'OdbojkasicaSlo', 'Z2', 'Slovacka'),
(277, 4, 'OdbojkasicaSlo', 'Z3', 'Slovacka'),
(278, 4, 'OdbojkasicaSlo', 'Z4', 'Slovacka'),
(279, 4, 'OdbojkasicaSlo', 'Z5', 'Slovacka'),
(280, 4, 'OdbojkasicaSlo', 'Z6', 'Slovacka'),
(281, 4, 'OdbojkasicaSlo', 'Z7', 'Slovacka');

-- --------------------------------------------------------

--
-- Table structure for table `sportistadisciplina`
--

CREATE TABLE `sportistadisciplina` (
  `idSportista` int(11) NOT NULL,
  `idDisciplina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportistadisciplina`
--

INSERT INTO `sportistadisciplina` (`idSportista`, `idDisciplina`) VALUES
(7, 54),
(7, 55),
(8, 55),
(9, 52),
(10, 46),
(17, 54),
(17, 55),
(18, 55),
(19, 1),
(19, 4),
(20, 1),
(20, 4),
(21, 1),
(21, 4),
(22, 1),
(22, 4),
(23, 1),
(23, 4),
(24, 36),
(25, 36),
(26, 36),
(27, 52),
(28, 46),
(35, 1),
(36, 1),
(37, 1),
(38, 54),
(38, 55),
(39, 55),
(46, 52),
(47, 52),
(48, 52),
(49, 46),
(50, 46),
(51, 46),
(52, 36),
(53, 36),
(54, 36),
(55, 54),
(55, 55),
(56, 55),
(63, 1),
(64, 1),
(65, 1),
(66, 52),
(67, 52),
(68, 46),
(69, 46),
(70, 46),
(71, 36),
(72, 36),
(73, 36),
(80, 52),
(81, 52),
(82, 46),
(83, 46),
(84, 46),
(85, 54),
(85, 55),
(86, 55),
(93, 54),
(93, 55),
(94, 55),
(95, 46),
(96, 46),
(97, 46),
(98, 30),
(99, 30),
(100, 30),
(101, 30),
(108, 54),
(108, 55),
(109, 55),
(110, 30),
(111, 30),
(112, 4),
(113, 4),
(114, 4),
(115, 4),
(116, 4),
(123, 54),
(123, 55),
(124, 55),
(125, 30),
(126, 30),
(127, 30),
(128, 30),
(129, 30),
(130, 4),
(131, 4),
(132, 4),
(133, 4),
(140, 54),
(140, 55),
(141, 55),
(142, 30),
(143, 30),
(144, 4),
(145, 4),
(146, 4),
(166, 42),
(167, 13),
(168, 13),
(169, 47),
(170, 35),
(178, 42),
(179, 13),
(180, 13),
(181, 47),
(182, 35),
(190, 42),
(191, 13),
(192, 13),
(193, 47),
(194, 35),
(202, 42),
(203, 13),
(204, 13),
(205, 47),
(206, 35),
(214, 42),
(215, 13),
(216, 13),
(217, 47),
(218, 35),
(226, 42),
(227, 13),
(228, 13),
(229, 47),
(230, 35),
(238, 42),
(239, 13),
(240, 13),
(241, 47),
(249, 42),
(250, 47),
(251, 35),
(259, 47),
(267, 47);

-- --------------------------------------------------------

--
-- Table structure for table `sportistaekipa`
--

CREATE TABLE `sportistaekipa` (
  `idSportista` int(11) NOT NULL,
  `idEkipa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportistaekipa`
--

INSERT INTO `sportistaekipa` (`idSportista`, `idEkipa`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 2),
(8, 2),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 4),
(18, 4),
(29, 5),
(30, 5),
(31, 5),
(32, 5),
(33, 5),
(34, 5),
(38, 6),
(39, 6),
(40, 7),
(41, 7),
(42, 7),
(43, 7),
(44, 7),
(45, 7),
(55, 8),
(56, 8),
(57, 9),
(58, 9),
(59, 9),
(60, 9),
(61, 9),
(62, 9),
(74, 10),
(75, 10),
(76, 10),
(77, 10),
(78, 10),
(79, 10),
(85, 11),
(86, 11),
(87, 12),
(88, 12),
(89, 12),
(90, 12),
(91, 12),
(92, 12),
(93, 13),
(94, 13),
(102, 14),
(103, 14),
(104, 14),
(105, 14),
(106, 14),
(107, 14),
(108, 15),
(109, 15),
(117, 16),
(118, 16),
(119, 16),
(120, 16),
(121, 16),
(122, 16),
(123, 17),
(124, 17),
(134, 18),
(135, 18),
(136, 18),
(137, 18),
(138, 18),
(139, 18),
(140, 19),
(141, 19),
(147, 20),
(148, 20),
(149, 20),
(150, 20),
(151, 20),
(152, 20),
(153, 21),
(154, 21),
(155, 21),
(156, 21),
(157, 21),
(158, 21),
(159, 22),
(160, 22),
(161, 22),
(162, 22),
(163, 22),
(164, 22),
(165, 22),
(171, 23),
(172, 23),
(173, 23),
(174, 23),
(175, 23),
(176, 23),
(177, 23),
(183, 24),
(184, 24),
(185, 24),
(186, 24),
(187, 24),
(188, 24),
(189, 24),
(195, 25),
(196, 25),
(197, 25),
(198, 25),
(199, 25),
(200, 25),
(201, 25),
(207, 26),
(208, 26),
(209, 26),
(210, 26),
(211, 26),
(212, 26),
(213, 26),
(219, 27),
(220, 27),
(221, 27),
(222, 27),
(223, 27),
(224, 27),
(225, 27),
(231, 28),
(232, 28),
(233, 28),
(234, 28),
(235, 28),
(236, 28),
(237, 28),
(242, 29),
(243, 29),
(244, 29),
(245, 29),
(246, 29),
(247, 29),
(248, 29),
(252, 30),
(253, 30),
(254, 30),
(255, 30),
(256, 30),
(257, 30),
(258, 30),
(260, 31),
(261, 31),
(262, 31),
(263, 31),
(264, 31),
(265, 31),
(266, 31),
(268, 32),
(269, 32),
(270, 32),
(271, 32),
(272, 32),
(273, 32),
(274, 32),
(275, 33),
(276, 33),
(277, 33),
(278, 33),
(279, 33),
(280, 33),
(281, 33);

-- --------------------------------------------------------

--
-- Table structure for table `sportistamedalja`
--

CREATE TABLE `sportistamedalja` (
  `idSportista` int(11) NOT NULL,
  `idTakmicenje` int(11) NOT NULL,
  `medalja` char(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sportistamedalja`
--

INSERT INTO `sportistamedalja` (`idSportista`, `idTakmicenje`, `medalja`) VALUES
(1, 4, 'zlato'),
(2, 4, 'zlato'),
(3, 4, 'zlato'),
(4, 4, 'zlato'),
(5, 4, 'zlato'),
(6, 4, 'zlato'),
(7, 9, 'bronza'),
(8, 9, 'bronza'),
(9, 11, 'zlato'),
(23, 1, 'bronza'),
(26, 10, 'srebro'),
(37, 1, 'zlato'),
(40, 4, 'srebro'),
(41, 4, 'srebro'),
(42, 4, 'srebro'),
(43, 4, 'srebro'),
(44, 4, 'srebro'),
(45, 4, 'srebro'),
(48, 11, 'srebro'),
(53, 10, 'bronza'),
(55, 9, 'srebro'),
(56, 9, 'srebro'),
(63, 1, 'srebro'),
(67, 11, 'bronza'),
(73, 10, 'zlato'),
(123, 9, 'zlato'),
(124, 9, 'zlato'),
(153, 4, 'bronza'),
(154, 4, 'bronza'),
(155, 4, 'bronza'),
(156, 4, 'bronza'),
(157, 4, 'bronza'),
(158, 4, 'bronza');

-- --------------------------------------------------------

--
-- Table structure for table `takmicenje`
--

CREATE TABLE `takmicenje` (
  `idTakmicenje` int(11) NOT NULL,
  `idSport` int(11) NOT NULL,
  `idDisciplina` int(11) DEFAULT NULL,
  `datumOd` char(25) DEFAULT NULL,
  `datumDo` char(25) DEFAULT NULL,
  `lokacija` char(25) DEFAULT NULL,
  `idDelegat` int(11) NOT NULL,
  `brojUcesnika` int(11) DEFAULT NULL,
  `tip` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `takmicenje`
--

INSERT INTO `takmicenje` (`idTakmicenje`, `idSport`, `idDisciplina`, `datumOd`, `datumDo`, `lokacija`, `idDelegat`, `brojUcesnika`, `tip`) VALUES
(1, 8, 1, '1.9.2016.', '15.9.2016.', 'Atletska staza', 2, NULL, 1),
(2, 8, 4, '1.9.2016.', '15.9.2016.', 'Atletska staza', 2, NULL, 1),
(3, 7, 30, '1.9.2016.', '15.9.2016.', 'Ipanema', 2, NULL, 1),
(4, 2, NULL, '1.9.2016.', '15.9.2016.', 'RioArena', 5, NULL, 2),
(6, 18, 54, '1.9.2016.', '15.9.2016.', 'Teniski tereni', 5, NULL, 1),
(9, 18, 56, '1.9.2016.', '18.9.2016.', 'Teniski tereni', 4, NULL, 2),
(10, 10, 36, '1.9.2016.', '3.9.2016.', 'ulice', 5, NULL, 1),
(11, 16, 52, '1.9.2016.', '3.9.2016.', 'StreljanaRio', 4, NULL, 1),
(12, 15, 46, '1.9.2016.', '3.9.2016.', 'StreljanaRio', 4, NULL, 1),
(13, 4, NULL, '5.8.2016.', '15.8.2016.', 'HalaRio', 17, NULL, 2),
(14, 14, 42, '5.8.2016.', '15.8.2016.', 'HalaRio', 17, NULL, 1),
(15, 8, 13, '5.8.2016.', '15.8.2016.', 'Atletski stadion Rio', 17, NULL, 1),
(16, 15, 47, '5.8.2016.', '15.8.2016.', 'Streljana Rio', 18, NULL, 1),
(17, 9, 35, '5.8.2016.', '15.8.2016.', 'ulice Rija', 18, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ucesnik`
--

CREATE TABLE `ucesnik` (
  `idTakmicenje` int(11) NOT NULL,
  `idUcesnik` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ucesnik`
--

INSERT INTO `ucesnik` (`idTakmicenje`, `idUcesnik`) VALUES
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 35),
(1, 36),
(1, 37),
(1, 63),
(1, 64),
(1, 65),
(4, 1),
(4, 3),
(4, 5),
(4, 7),
(4, 9),
(4, 10),
(4, 12),
(4, 14),
(4, 16),
(4, 18),
(4, 20),
(4, 21),
(9, 2),
(9, 4),
(9, 6),
(9, 8),
(9, 13),
(9, 15),
(9, 17),
(9, 19),
(10, 24),
(10, 25),
(10, 26),
(10, 52),
(10, 53),
(10, 54),
(10, 71),
(10, 72),
(10, 73),
(11, 9),
(11, 27),
(11, 46),
(11, 47),
(11, 48),
(11, 66),
(11, 67),
(11, 80),
(11, 81),
(13, 22),
(13, 23),
(13, 24),
(13, 25),
(13, 26),
(13, 27),
(13, 28),
(13, 29),
(13, 30),
(13, 31),
(13, 32),
(13, 33);

-- --------------------------------------------------------

--
-- Table structure for table `zemljamedalje`
--

CREATE TABLE `zemljamedalje` (
  `nacionalnost` char(25) NOT NULL,
  `zlato` int(11) NOT NULL DEFAULT '0',
  `srebro` int(11) NOT NULL DEFAULT '0',
  `bronza` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zemljamedalje`
--

INSERT INTO `zemljamedalje` (`nacionalnost`, `zlato`, `srebro`, `bronza`) VALUES
('Argentina', 1, 0, 0),
('Brazil', 1, 1, 1),
('Francuska', 0, 3, 1),
('Japan', 1, 0, 0),
('SAD', 0, 1, 1),
('Slovacka', 0, 0, 1),
('Srbija', 2, 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `disciplina`
--
ALTER TABLE `disciplina`
  ADD PRIMARY KEY (`idDisciplina`);

--
-- Indexes for table `ekipa`
--
ALTER TABLE `ekipa`
  ADD PRIMARY KEY (`idEkipa`);

--
-- Indexes for table `ekipabodovi`
--
ALTER TABLE `ekipabodovi`
  ADD PRIMARY KEY (`idTakmicenje`,`idEkipa`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`idKorisnik`);

--
-- Indexes for table `mec`
--
ALTER TABLE `mec`
  ADD PRIMARY KEY (`idMec`);

--
-- Indexes for table `rekord`
--
ALTER TABLE `rekord`
  ADD PRIMARY KEY (`idRekord`);

--
-- Indexes for table `sport`
--
ALTER TABLE `sport`
  ADD PRIMARY KEY (`idSport`);

--
-- Indexes for table `sportista`
--
ALTER TABLE `sportista`
  ADD PRIMARY KEY (`idSportista`),
  ADD KEY `R_6` (`idSport`);

--
-- Indexes for table `sportistadisciplina`
--
ALTER TABLE `sportistadisciplina`
  ADD PRIMARY KEY (`idSportista`,`idDisciplina`),
  ADD KEY `R_12` (`idDisciplina`);

--
-- Indexes for table `sportistaekipa`
--
ALTER TABLE `sportistaekipa`
  ADD PRIMARY KEY (`idSportista`);

--
-- Indexes for table `sportistamedalja`
--
ALTER TABLE `sportistamedalja`
  ADD PRIMARY KEY (`idSportista`,`idTakmicenje`);

--
-- Indexes for table `takmicenje`
--
ALTER TABLE `takmicenje`
  ADD PRIMARY KEY (`idTakmicenje`),
  ADD KEY `R_1` (`idSport`),
  ADD KEY `R_2` (`idDisciplina`),
  ADD KEY `R_3` (`idDelegat`);

--
-- Indexes for table `ucesnik`
--
ALTER TABLE `ucesnik`
  ADD PRIMARY KEY (`idTakmicenje`,`idUcesnik`);

--
-- Indexes for table `zemljamedalje`
--
ALTER TABLE `zemljamedalje`
  ADD PRIMARY KEY (`nacionalnost`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mec`
--
ALTER TABLE `mec`
  MODIFY `idMec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=215;
--
-- AUTO_INCREMENT for table `rekord`
--
ALTER TABLE `rekord`
  MODIFY `idRekord` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `sportista`
--
ALTER TABLE `sportista`
  ADD CONSTRAINT `R_6` FOREIGN KEY (`idSport`) REFERENCES `sport` (`idSport`);

--
-- Constraints for table `sportistadisciplina`
--
ALTER TABLE `sportistadisciplina`
  ADD CONSTRAINT `R_11` FOREIGN KEY (`idSportista`) REFERENCES `sportista` (`idSportista`),
  ADD CONSTRAINT `R_12` FOREIGN KEY (`idDisciplina`) REFERENCES `disciplina` (`idDisciplina`);

--
-- Constraints for table `takmicenje`
--
ALTER TABLE `takmicenje`
  ADD CONSTRAINT `R_1` FOREIGN KEY (`idSport`) REFERENCES `sport` (`idSport`),
  ADD CONSTRAINT `R_2` FOREIGN KEY (`idDisciplina`) REFERENCES `disciplina` (`idDisciplina`),
  ADD CONSTRAINT `R_3` FOREIGN KEY (`idDelegat`) REFERENCES `korisnik` (`idKorisnik`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
