-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 12:46 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `renatal`
--

-- --------------------------------------------------------

--
-- Table structure for table `houses`
--

CREATE TABLE `houses` (
  `h_id` int(20) NOT NULL,
  `h_number` varchar(50) NOT NULL,
  `h_status` varchar(50) NOT NULL,
  `h_price` varchar(50) NOT NULL,
  `h_electricity` varchar(50) NOT NULL,
  `h_water` varchar(50) NOT NULL,
  `h_maintenance` varchar(50) NOT NULL,
  `h_security` varchar(50) NOT NULL,
  `h_total` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `houses`
--

INSERT INTO `houses` (`h_id`, `h_number`, `h_status`, `h_price`, `h_electricity`, `h_water`, `h_maintenance`, `h_security`, `h_total`) VALUES
(9, '2', 'AVAILABLE', '94', '100.0', '100.0', '100.0', '100.0', '500.0'),
(12, '12', '12', '12', '12', '12', '12', '12', '12'),
(13, '14478', 'AVAILABLE', '1450.0', '100.0', '100.0', '100.0', '100.0', '1850.0');

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE `issue` (
  `is_ID` int(20) NOT NULL,
  `o_id` varchar(20) NOT NULL,
  `h_id` varchar(20) NOT NULL,
  `started_date` date NOT NULL,
  `end_date` date NOT NULL,
  `is_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`is_ID`, `o_id`, `h_id`, `started_date`, `end_date`, `is_status`) VALUES
(1, '1', '9', '2024-06-17', '2024-06-11', ''),
(2, '1', '9', '2024-06-20', '2024-06-10', 'Rented'),
(3, '1', '9', '2024-06-19', '2024-06-28', 'Rented'),
(4, '1', '9', '2024-06-18', '2024-06-24', 'Pending'),
(5, '1', '9', '2024-06-18', '2024-06-26', 'Pending'),
(6, '1', '9', '2024-06-18', '2024-06-24', 'Pending'),
(7, '1', '9', '2024-06-18', '2024-06-19', 'Pending'),
(8, '1', '9', '2024-06-19', '2024-06-28', 'Pending'),
(9, '1', '9', '2024-06-12', '2024-06-28', 'Pending'),
(10, '1', '9', '2024-06-11', '2024-06-27', 'Pending'),
(11, '1', '9', '2024-06-11', '2024-06-18', 'Pending'),
(12, '1', '9', '2024-06-14', '2024-06-21', 'Pending'),
(13, '2', '3', '2024-06-12', '2024-06-28', 'Pending'),
(14, '3', '9', '2024-06-12', '2024-06-28', 'Pending'),
(15, '4', '9', '2024-06-12', '2024-06-28', 'Pending'),
(16, '1', '9', '2024-06-20', '2024-06-29', 'Available'),
(17, '1', '9', '2024-06-21', '2024-06-28', 'Available'),
(18, '1', '9', '2024-06-21', '2024-06-21', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `occupant`
--

CREATE TABLE `occupant` (
  `o_id` int(100) NOT NULL,
  `o_firstname` varchar(59) NOT NULL,
  `o_lastname` varchar(59) NOT NULL,
  `o_contact` varchar(59) NOT NULL,
  `o_members` varchar(59) NOT NULL,
  `o_date` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `occupant`
--

INSERT INTO `occupant` (`o_id`, `o_firstname`, `o_lastname`, `o_contact`, `o_members`, `o_date`) VALUES
(1, 'rhon', 'llanto', '09166764802', '5', '0000-00-00'),
(2, 'kaths', 'reps', '0935547361', '3', '05-16-2024'),
(3, 'king', 'kong', '09569553633', '6', '06-06-2024'),
(5, 'sip', 'Canoy', '096071558361', '7', '06-20-2024');

-- --------------------------------------------------------

--
-- Table structure for table `rent`
--

CREATE TABLE `rent` (
  `t_id` int(20) NOT NULL,
  `t_fn` varchar(50) NOT NULL,
  `t_ln` varchar(50) NOT NULL,
  `t_email` varchar(50) NOT NULL,
  `t_username` varchar(50) NOT NULL,
  `t_pass` varchar(150) NOT NULL,
  `t_contact` varchar(50) NOT NULL,
  `t_type` varchar(50) NOT NULL,
  `t_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rent`
--

INSERT INTO `rent` (`t_id`, `t_fn`, `t_ln`, `t_email`, `t_username`, `t_pass`, `t_contact`, `t_type`, `t_status`) VALUES
(37, 'taehyung', 'kim', 'kimtae', 'tae', 'apPCbAHbnQTs6k+AgFofhTeWZjvUfkJAQR9MWeAJMnE=', '095331235678', 'Admin', 'Active'),
(61, 'kath', 'kath', 'kath@gmail', 'kathy', 'u0Ifo124hc5Qew71w/I8sJxi6zePrjZBwWW99MAnKUk=', '09506774836', 'Admin', 'Active'),
(62, 'mikey', 'mikey', 'mikey@gmail.com', 'mikey', 'u0Ifo124hc5Qew71w/I8sJxi6zePrjZBwWW99MAnKUk=', '09506774836', 'User', 'Active'),
(63, 'impas', 'pass@gmail.com', 'pass', 'pass', 'bTUKIVWs8MDNfcuqsMlYdSClnl2kZ5SNClaPSmHA96A=', '09506774836', 'Admin', 'Pending '),
(64, 'ryan', 'ryan', 'vic', 'ryry', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', '09785645677', 'User', 'Active'),
(65, 'ryan', 'ryan', 'bini', 'bini', 'FeKw08M4keuw8e9gnsQZQgwg4yDOlMZfvIwzEkSOsiU=', '09785645677', 'User', 'Active'),
(66, 'g leen', 'gleen@gmail.com', 'gleenn', 'glenny', '8SC7VpjVIMVpG21gOgC/1mLRO/F3oEVx+dEMB0XfoqU=', '09213204500', 'User', 'Pending '),
(67, 'Bloosom', 'gleenyy@gmail.com', 'balatero', 'glenn', '47DEQpj8HBSa+/TImW+5JCeuQeRkm5NMpJWZG3hSuFU=', '09213204500', 'User', 'Active'),
(68, 'mike', 'ling@gmail.com', 'mikkee', 'lili', 'aL0UZPeTZ9BTCWXsLy6XvphFsZ0Cf3WWNP7VVQMKEOM=', '09506774836', 'User', 'Active'),
(69, 'jiii', 'jiiiiii', 'park', 'jimin', '8SC7VpjVIMVpG21gOgC/1mLRO/F3oEVx+dEMB0XfoqU=', '09753137977', 'User', 'Active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `houses`
--
ALTER TABLE `houses`
  ADD PRIMARY KEY (`h_id`);

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`is_ID`);

--
-- Indexes for table `occupant`
--
ALTER TABLE `occupant`
  ADD PRIMARY KEY (`o_id`);

--
-- Indexes for table `rent`
--
ALTER TABLE `rent`
  ADD PRIMARY KEY (`t_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `houses`
--
ALTER TABLE `houses`
  MODIFY `h_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
  MODIFY `is_ID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `rent`
--
ALTER TABLE `rent`
  MODIFY `t_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `houses`
--
ALTER TABLE `houses`
  ADD CONSTRAINT `houses_ibfk_1` FOREIGN KEY (`h_id`) REFERENCES `issue` (`is_ID`);

--
-- Constraints for table `occupant`
--
ALTER TABLE `occupant`
  ADD CONSTRAINT `occupant_ibfk_1` FOREIGN KEY (`o_id`) REFERENCES `issue` (`is_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
