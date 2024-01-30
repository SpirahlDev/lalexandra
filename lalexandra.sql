-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 15 jan. 2024 à 10:32
-- Version du serveur : 8.0.31
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lalexandra`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int NOT NULL,
  `login_admin` varchar(80) NOT NULL,
  `password_admin` varchar(80) NOT NULL,
  PRIMARY KEY (`id_admin`),
  UNIQUE KEY `login_admin_UNIQUE` (`login_admin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `login_admin`, `password_admin`) VALUES
(1, 'anselmealloue@gmail.com', '$2y$10$H/lQq0yx0M6OqjxUlVjffOZAnuhR.lTaO5juBYSjPEeiIuO2Ipusy');

-- --------------------------------------------------------

--
-- Structure de la table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `id_cart` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_cart`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `cart`
--

INSERT INTO `cart` (`id_cart`) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

-- --------------------------------------------------------

--
-- Structure de la table `cart_content`
--

DROP TABLE IF EXISTS `cart_content`;
CREATE TABLE IF NOT EXISTS `cart_content` (
  `id_cart` int NOT NULL,
  `id_prod` int NOT NULL,
  `unit_prod_price` float DEFAULT NULL,
  `cart_prod_quantity` int DEFAULT NULL,
  PRIMARY KEY (`id_cart`,`id_prod`),
  KEY `id_cart _idx` (`id_cart`),
  KEY `id_product_idx` (`id_prod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `cart_content`
--

INSERT INTO `cart_content` (`id_cart`, `id_prod`, `unit_prod_price`, `cart_prod_quantity`) VALUES
(1, 24, 5700, 1),
(1, 32, 950, 1),
(5, 30, 13000, 1),
(5, 31, 10000, 3);

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name_category` varchar(55) NOT NULL,
  `description_category` varchar(255) DEFAULT NULL,
  `image_category` varchar(255) DEFAULT NULL,
  `system_name` varchar(25) DEFAULT 'all-products',
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id_category`, `name_category`, `description_category`, `image_category`, `system_name`) VALUES
(1, 'Tout nos produits', 'Découvrez toutes notre game de cosmétique', 'statics/images/all-product_illus.webp', 'all-products'),
(2, 'Soins du visage', 'Trouvez des produits idéals pour le soins de votre visage', 'statics/images/soins-visage.png', 'facial-care'),
(3, 'Soins capillaire', 'Rendez vos cheveux éclatants', 'statics/images/soins-cheveux.webp', 'hair-care'),
(4, 'Maquillage', 'Mettez vous en avant avec notre game de makeup', 'statics/images/makeup.jpg', 'makeup'),
(5, 'Parfums', 'Attirez vers vous, avec nos parfum haut de game', 'statics/images/parfum.jpg', 'perfumes');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `name_client` varchar(55) NOT NULL,
  `firstname_client` varchar(55) NOT NULL,
  `mail_client` varchar(80) NOT NULL,
  `phonenumber_client` varchar(25) NOT NULL,
  `password_client` varchar(80) NOT NULL,
  `registration_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_cart` int NOT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `mail_client_UNIQUE` (`mail_client`),
  UNIQUE KEY `phonenumber_client_UNIQUE` (`phonenumber_client`),
  KEY `id_client_cart` (`id_cart`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `name_client`, `firstname_client`, `mail_client`, `phonenumber_client`, `password_client`, `registration_date`, `id_cart`) VALUES
(1, 'Vianney', 'Emmanuel', 'emmanuelvianney1234@gmail.com', '0504680098', '$2a$10$Y/Zanbl628tBHRGbbC8v3ejkQafOSz.xV3hSBmZYTmiaz0OY7X8n2', NULL, 1),
(4, ' Alloue', 'Anselme', 'anselmealloue@gmail.com', '0704680098', '$2a$10$Djj.vhcRhA8iemoo.9NgrOhDo1wi192YfB9vr05HH5W2pgBUdzVge', '2023-12-20 13:45:04', 4),
(5, 'Bodjui', 'Nestor', 'bodjnest@gmail.com', '0304680098', '$2a$10$dLUlqT24t7SL856de4lDaOxcjW3qp5moOFzZ6TH5ZB7pIcLxZCE36', '2023-12-20 13:50:09', 5),
(6, 'Debe', 'Coulibaly', 'debou@gmail.com', '0804680098', '$2a$10$4.SthOpg45bH0Yvf0YU3gOSuthqHcThPqOmoKenfNMvxPoVJWxvn.', '2023-12-20 13:59:17', 6);

-- --------------------------------------------------------

--
-- Structure de la table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
CREATE TABLE IF NOT EXISTS `delivery` (
  `id_delivery` int NOT NULL AUTO_INCREMENT,
  `delivery_date` date DEFAULT NULL,
  `id_order` int NOT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `id_order_idx` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `id_client` int NOT NULL,
  `amount_order` float NOT NULL,
  `date_order` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `status_order` varchar(25) DEFAULT 'WAIT',
  `payment_type` varchar(45) DEFAULT 'AT_DELIVERY',
  `delivery_location` varchar(255) NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `id_client _idx` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `order`
--

INSERT INTO `order` (`id_order`, `id_client`, `amount_order`, `date_order`, `status_order`, `payment_type`, `delivery_location`) VALUES
(1, 5, 233333, '2023-12-22 11:25:36', 'WAIT', 'AT_DELIVERY', 'abobo');

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `id_payment` int NOT NULL AUTO_INCREMENT,
  `id_order` int NOT NULL,
  `payment_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_payment`),
  KEY `id_order_idx` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id_prod` int NOT NULL AUTO_INCREMENT,
  `name_prod` varchar(55) NOT NULL,
  `description_prod` varchar(255) NOT NULL,
  `price_prod` float NOT NULL,
  `imageURI_prod` varchar(255) DEFAULT NULL,
  `quantity_prod` int NOT NULL,
  `id_category` int NOT NULL,
  `add_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_prod`),
  KEY `id_category_idx` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id_prod`, `name_prod`, `description_prod`, `price_prod`, `imageURI_prod`, `quantity_prod`, `id_category`, `add_date`) VALUES
(20, 'Eau micellaire Yves Rocher', 'Élimine le maquillage et les impuretés en douceur, sans agresser la peau.\r\nIngrédients: Eau florale de bleuet, huile de jojoba, glycérine', 5000, 'statics/images/products/Eau-micellaire-Yves-Rocher-5000.png', 20, 2, '2023-12-01 16:49:41'),
(21, 'Gel nettoyant ayes essentiel ', 'Nettoie la peau en profondeur tout en la laissant hydratée et douce\r\nIngrédients: Aloe vera, glycérine, miel', 7000, 'statics/images/products/Gel-nettoyant-ayes-essentiel--7000.png', 33, 2, '2023-12-01 16:49:41'),
(22, 'Lotion tonique Yves Rocher', 'Tonifie la peau et resserre les pores.', 6000, 'statics/images/products/Lotion-tonique-Yves-Rocher-6000 .png', 9, 2, '2023-12-15 19:40:52'),
(24, 'Shampooings', 'Produits capillaires naturels, souvent axés sur l\'hydratation et la nutrition. \r\nLieu de fabrication : États-Unis\r\n\r\nIngrédients : Beurre de karité, huiles végétales, ingrédients naturels.', 5700, 'statics/images/products/Shampooings-5700 .png', 12, 3, '2023-12-15 19:42:44'),
(25, 'Nappy Queen', 'Marque axée sur les besoins spécifiques des cheveux bouclés et crépus.\r\n**Lieu de fabrication** : France\r\n\r\n**Ingrédients** : Produits à base d\'ingrédients naturels.', 2500, 'statics/images/products/Nappy-Queen-2500 .png', 22, 3, '2023-12-15 19:43:44'),
(26, 'Maybelline New York', 'Ingrédients : Varient selon le produit, mais souvent comprennent des pigments, cires, huiles, et agents hydratants.Description : Marque internationale offrant une gamme variée de produits de maquillage adaptés aux besoins quotidiens.\r\nType de produit: Mas', 8000, 'statics/images/products/Maybelline-New-York-8000.png', 33, 4, '2023-12-15 22:36:02'),
(27, 'Mixa', 'La crème pour les mains de Mixa est conçue pour hydrater et protéger la peau des mains. Sa formule nourrissante, enrichie en beurre de karité, aide à apaiser et adoucir la peau sèche. Convient à un usage quotidien pour des mains douces et confortables.\r\nI', 3000, 'statics/images/products/Mixa-3000.png', 12, 3, '2023-12-15 22:56:44'),
(28, 'Déodorant stick à l\'aloe vera Weleda', '**Marque** : Weleda\r\n\r\n**Volume** : 45 ml\r\n\r\n**Fabriqué en** : Allemagne\r\n\r\n**Ingrédients** : Aloe vera, camomille, huile de jojoba\r\n\r\n**Propriétés** : Protection 24 heures contre la transpiration et les odeurs, naturel et sans parfum', 13000, 'statics/images/products/Déodorant-stick-à-l-aloe-vera-Weleda-13000.png', 25, 5, '2023-12-15 23:05:46'),
(29, 'Déodorant stick à l\'aloe vera Dove', 'Le déodorant stick à l\'aloe vera Dove est un déodorant naturel et efficace qui offre une protection 48 heures contre la transpiration et les odeurs. Il est formulé avec de l\'aloe vera, du beurre de karité et de la vitamine E, des ingrédients connus pour l', 15000, 'statics/images/products/Déodorant-stick-à-l-aloe-vera-Dove-15000.png', 22, 5, '2023-12-15 23:08:20'),
(30, 'Déodorant stick à la lavande', ' Notre déodorant stick à la lavande est un choix rafraîchissant pour une protection toute la journée. Infusé de l\'arôme apaisant de la lavande, il contient également du bicarbonate de sodium pour combattre les odeurs, et de l\'huile d\'amande douce pour pre', 13000, 'statics/images/products/Déodorant-stick-à-la-lavande-13000.png', 11, 5, '2023-12-15 23:08:20'),
(31, 'Nutri Skin', 'Nourrit votre peau', 10000, 'statics/images/products/Nutri-Skin-10000.png', 20, 2, '2023-12-20 14:07:10'),
(32, 'Lotion Sivoderm', 'Lutte contre les imperfections', 950, 'statics/images/products/Lotion-Sivoderm-950.png', 22, 2, '2023-12-22 14:14:23');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cart_content`
--
ALTER TABLE `cart_content`
  ADD CONSTRAINT `id_cart ` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id_cart`),
  ADD CONSTRAINT `id_product` FOREIGN KEY (`id_prod`) REFERENCES `product` (`id_prod`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`id_cart`) REFERENCES `cart` (`id_cart`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `delivery`
--
ALTER TABLE `delivery`
  ADD CONSTRAINT `id_order_delivery` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`);

--
-- Contraintes pour la table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `id_client _order` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Contraintes pour la table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `id_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id_order`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `id_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
