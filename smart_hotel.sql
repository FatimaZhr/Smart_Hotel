-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 17 mars 2026 à 18:06
-- Version du serveur : 8.3.0
-- Version de PHP : 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `smart_hotel`
--

-- --------------------------------------------------------

--
-- Structure de la table `iot_devices`
--

DROP TABLE IF EXISTS `iot_devices`;
CREATE TABLE IF NOT EXISTS `iot_devices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `device_type` enum('AC','CURTAIN','DOOR_LOCK','LIGHT','THERMOSTAT','TV') DEFAULT NULL,
  `device_uid` varchar(255) NOT NULL,
  `is_online` bit(1) DEFAULT NULL,
  `last_command` varchar(255) DEFAULT NULL,
  `last_command_at` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjutryy44q08jjknjbejgx9duc` (`device_uid`),
  KEY `FKevfmppu4svotj2hkc1ty43qph` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `mobile_keys`
--

DROP TABLE IF EXISTS `mobile_keys`;
CREATE TABLE IF NOT EXISTS `mobile_keys` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expires_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `last_used_at` datetime(6) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `reservation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKklh1d36ht53bvy7fvs5khkyg1` (`token`),
  UNIQUE KEY `UK68ii65blnusv7l1hm6swl4lm5` (`reservation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` date DEFAULT NULL,
  `check_out_date` date DEFAULT NULL,
  `status` enum('CANCELLED','CHECKED_IN','CHECKED_OUT','CONFIRMED','PENDING') DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `guest_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3kocu7c52008941ed3rvqsflm` (`guest_id`),
  KEY `FKljt6q1tp205b0h26eiegc5mx6` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `base_price` double DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `has_iot` bit(1) DEFAULT NULL,
  `room_number` varchar(255) NOT NULL,
  `status` enum('AVAILABLE','CLEANING','MAINTENANCE','OCCUPIED') DEFAULT NULL,
  `type` enum('DOUBLE','FAMILY','SINGLE','SUITE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7ljglxlj90ln3lbas4kl983m2` (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `sensor_data`
--

DROP TABLE IF EXISTS `sensor_data`;
CREATE TABLE IF NOT EXISTS `sensor_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `recorded_at` datetime(6) DEFAULT NULL,
  `sensor_type` enum('ENERGY','HUMIDITY','MOTION','TEMPERATURE') DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7sgkd744vb46mxqx9fjap6urt` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('GUEST','MANAGER','RECEPTIONIST') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `iot_devices`
--
ALTER TABLE `iot_devices`
  ADD CONSTRAINT `FKevfmppu4svotj2hkc1ty43qph` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);

--
-- Contraintes pour la table `mobile_keys`
--
ALTER TABLE `mobile_keys`
  ADD CONSTRAINT `FK51yrno63qi3ovnpi3if0162nx` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`);

--
-- Contraintes pour la table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `FK3kocu7c52008941ed3rvqsflm` FOREIGN KEY (`guest_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKljt6q1tp205b0h26eiegc5mx6` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);

--
-- Contraintes pour la table `sensor_data`
--
ALTER TABLE `sensor_data`
  ADD CONSTRAINT `FK7sgkd744vb46mxqx9fjap6urt` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
