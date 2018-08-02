-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 07, 2018 at 06:28 AM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `keyboarddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `product_info`
--

CREATE TABLE `product_info` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `category` varchar(10) NOT NULL,
  `price` double NOT NULL,
  `quote` varchar(250) NOT NULL,
  `color` varchar(100) NOT NULL,
  `connection` varchar(100) NOT NULL,
  `dimension` varchar(100) NOT NULL,
  `weight` double NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_info`
--

INSERT INTO `product_info` (`id`, `name`, `category`, `price`, `quote`, `color`, `connection`, `dimension`, `weight`, `description`) VALUES
(1, '[M]etallo', 'Villain', 99.99, 'It\'s all fake. A FRAUD! There\'s the reality! The metal behind the man! It\'s all I am now! It\'s who I am... Metallo.', 'Jet Black, Mucus Green', 'Wireless', '318mm x 118mm x 39mm', 11.5, 'The one that looks like the terminator when it looses it\'s skin'),
(2, '[J]oker', 'Villain', 199.99, 'Why so serious?', 'Deep Purple, Jet Black', 'Wired', '318mm x 118mm x 39mm', 11.7, 'DC Version of IT'),
(3, '[G]ambit', 'Hero', 99.99, 'Playin\' for keeps is still playin\', mon ami, so take a card...ANY CARD!', 'Purple Haze, Steel Grey, Faded Blue', 'Wireless', '316mm x 118mm x 39mm', 11.4, 'Magic Mike\'s Alter Ego'),
(4, '[H]untress', 'Hero', 99.99, 'Everything\'s always okay until someone hunts you down and kills you', 'Jet Black, Deep Purple', 'Wireless', '316mm x 118mm x 39mm', 11.4, 'Girl version of Hawkeye'),
(5, '[C]yborg', 'Hero', 199.99, 'Booyah', 'Steel Grey, Blood Red', 'Wireless', '316mm x 118mm x 39mm', 11.4, 'If Frankenstein decides to get an upgrade.'),
(6, '[S]hazam', 'Hero', 149.99, 'By speaking my name you can become the strongest and mightiest man in the world', 'Lightning Gold, Jet Black', 'Wireless', '395mm x 145mm x 56mm', 14, 'A boy that posseses the power of the wizard Shazam'),
(7, '[C]aptain [C]old', 'Villain', 199.99, 'Somebody call the ice cream man?', 'Arctic White, Icey Blue', 'Wireless', '278mm x 155mm x 43mm', 12.5, 'Master tactician and thief, with the bonus of having a really \"cool\" gun'),
(8, '[R]ogue', 'Hero', 99.99, 'You don\'t understand! My past is my future! I can\'t escape what I am... there\'s nothin\' you or anyone else can do. That day will be with me for the rest of my life.', 'Ghost White, Mucus Green', 'Wireless', '358mm x 138mm x 44mm', 13.5, 'Absorbs everything she touches'),
(9, '[B]ooster [G]old', 'Hero', 249.99, 'Security personnel? Man, I was in the JLA! \"Booster Gold, night watchman\", is a total status kill!', 'RGB, Fire Gold, Silk White', 'Wired', '388mm x 178mm x 59mm', 15.5, 'A hero from the future'),
(10, '[L]oki', 'Villain', 149.99, 'I never wanted the throne, I only ever wanted to be your equal!', 'Canary Gold, Pine Green', 'Wired', '368mm x 112mm x 39mm', 10, 'The god of mischief and evil');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product_info`
--
ALTER TABLE `product_info`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product_info`
--
ALTER TABLE `product_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
