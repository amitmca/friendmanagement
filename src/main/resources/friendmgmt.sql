-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 09, 2018 at 01:00 PM
-- Server version: 5.1.33
-- PHP Version: 5.2.9-2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `friendmgmt`
--

-- --------------------------------------------------------

--
-- Table structure for table `operations`
--

CREATE TABLE IF NOT EXISTS `operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `operations`
--

INSERT INTO `operations` (`id`, `operation_name`) VALUES
(1, 'Friend'),
(2, 'Unfriend'),
(3, 'Block'),
(4, 'Subscribe');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contactno` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `contactno`, `email`) VALUES
(2, 'Amit', '8796154725', 'amit@gmail.com'),
(3, 'Amod', '9970446416', 'amod@gmail.com'),
(4, 'Amey', '8796154725', 'amey@gmail.com'),
(10, 'Ajit', '8796154725', 'ajit@gmail.com'),
(11, 'Kasturi', '9970446416', 'kasturi@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user_operations`
--

CREATE TABLE IF NOT EXISTS `user_operations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  `operation_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid1` (`userid1`),
  KEY `userid2` (`userid2`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user_operations`
--

INSERT INTO `user_operations` (`id`, `userid1`, `userid2`, `operation_id`) VALUES
(3, 3, 11, 1),
(4, 3, 4, 1),
(5, 2, 11, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_operations`
--
ALTER TABLE `user_operations`
  ADD CONSTRAINT `user_operations_ibfk_1` FOREIGN KEY (`userid1`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_operations_ibfk_2` FOREIGN KEY (`userid2`) REFERENCES `users` (`id`);
