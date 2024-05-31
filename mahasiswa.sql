-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2024 at 02:19 PM
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
-- Database: `mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswaku`
--

CREATE TABLE `mahasiswaku` (
  `id` int(6) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `nim` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswaku`
--

INSERT INTO `mahasiswaku` (`id`, `nama`, `nim`) VALUES
(1, 'Rina Septiani', '22051017'),
(2, 'Ghaida Sandie H S', '22051044'),
(3, 'Destria Al-Desyar', '22051028'),
(4, 'Neneng Yuli Purbayanti', '22051109'),
(11, 'Yolanda Putri Yani', '21051024'),
(12, 'Consinta Margaretha Bhei', '22051027'),
(13, 'Nuri Maladewi', '22051111');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswaku`
--
ALTER TABLE `mahasiswaku`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mahasiswaku`
--
ALTER TABLE `mahasiswaku`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
