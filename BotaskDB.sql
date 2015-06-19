-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 12, 2015 at 05:52 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `BotaskDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `ContinentesDB`
--

CREATE TABLE IF NOT EXISTS `ContinentesDB` (
  `ID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `NumberFounds` int(225) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ContinentesDB`
--

INSERT INTO `ContinentesDB` (`ID`, `Name`, `NumberFounds`) VALUES
(1, 'SouthAmericanCountries', 0),
(2, 'NorthAmericanCountries', 0),
(3, 'CentralAmericanCountries', 0),
(4, 'NorthernAmericanCountries', 0),
(7, 'EuropeanCountries', 0),
(8, 'EastAsianCountries', 1),
(9, 'SouthAsianCountries', 0),
(10, 'SoutheastAsianCountries', 0),
(11, 'WesternAsianCountries', 0),
(12, 'NorthAsianCountries', 0),
(13, 'CentralAsianCountries', 0),
(14, 'AfricanCountries', 0),
(15, 'NorthernAfricanCountries', 0),
(16, 'OceanianCountries', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ContinentesDB`
--
ALTER TABLE `ContinentesDB`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ContinentesDB`
--
ALTER TABLE `ContinentesDB`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
