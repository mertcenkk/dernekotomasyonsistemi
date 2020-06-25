-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.5.3-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- dernek için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `dernek` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_unicode_ci */;
USE `dernek`;

-- tablo yapısı dökülüyor dernek.beyanname
CREATE TABLE IF NOT EXISTS `beyanname` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icerik` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dernekId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_beyanname_dernek` (`dernekId`),
  CONSTRAINT `FK_beyanname_dernek` FOREIGN KEY (`dernekId`) REFERENCES `dernek` (`dernekId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- dernek.beyanname: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `beyanname` DISABLE KEYS */;
/*!40000 ALTER TABLE `beyanname` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.dernek
CREATE TABLE IF NOT EXISTS `dernek` (
  `dernekId` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kurulusTipi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `amaci` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kurulusTarihi` date NOT NULL DEFAULT current_timestamp(),
  `telNo` int(11) NOT NULL,
  `documenId` int(11) DEFAULT NULL,
  PRIMARY KEY (`dernekId`) USING BTREE,
  UNIQUE KEY `dernekId` (`dernekId`),
  KEY `documentId` (`documenId`),
  CONSTRAINT `documentId` FOREIGN KEY (`documenId`) REFERENCES `document` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- dernek.dernek: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dernek` DISABLE KEYS */;
INSERT INTO `dernek` (`dernekId`, `adi`, `kurulusTipi`, `adresi`, `amaci`, `kurulusTarihi`, `telNo`, `documenId`) VALUES
	(1, 'Malatya', 'Wqwq', 'awewa', 'awdad', '2020-06-25', 21312312, NULL),
	(2, 'Yeni Malatyaspor', 'Klüp ', 'Malatya', 'futbol', '2020-06-25', 123123123, NULL),
	(12, 'Merttttttttttttt', 'kl', 'aaaaaaaaaaaa', 'aaaaaaaaaaaa', '2020-06-24', 3453453, NULL);
/*!40000 ALTER TABLE `dernek` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.document
CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- dernek.document: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`id`, `path`, `name`, `type`) VALUES
	(2, 'C:\\Users\\kmert\\Desktop\\Dernek Otomasyonu\\web\\upload', 'readme.txt', 'text/plain'),
	(4, 'C:\\Users\\kmert\\Desktop\\Dernek Otomasyonu\\web\\upload', 'config.txt', 'text/plain'),
	(6, 'C:\\Users\\kmert\\Desktop\\Dernek Otomasyonu\\web\\upload', 'mariadb-java-client-2.6.0.jar', 'application/octet-stream'),
	(7, 'C:\\Users\\kmert\\Desktop\\Dernek Otomasyonu\\web\\upload', 'googlelogo_color_272x92dp.png', 'image/png');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.kayit
CREATE TABLE IF NOT EXISTS `kayit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dernekId` int(11) NOT NULL,
  `uyeId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `kayit_dernek` (`dernekId`),
  KEY `kayit_uye` (`uyeId`),
  CONSTRAINT `kayit_dernek` FOREIGN KEY (`dernekId`) REFERENCES `dernek` (`dernekId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kayit_uye` FOREIGN KEY (`uyeId`) REFERENCES `uye` (`uyeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dernek ve uye kaydında manyToMany';

-- dernek.kayit: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kayit` DISABLE KEYS */;
INSERT INTO `kayit` (`id`, `dernekId`, `uyeId`) VALUES
	(1, 12, 4),
	(2, 12, 14),
	(3, 1, 4);
/*!40000 ALTER TABLE `kayit` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.malbildirimi
CREATE TABLE IF NOT EXISTS `malbildirimi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nitelik` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `alinmaTarihi` date NOT NULL,
  `bildirimTarihi` date NOT NULL,
  `fiyat` int(11) NOT NULL DEFAULT 0,
  `adres` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dernekId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `mal_dernek` (`dernekId`),
  CONSTRAINT `mal_dernek` FOREIGN KEY (`dernekId`) REFERENCES `dernek` (`dernekId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dernek tablosuyla one-one ilişkisi bulunmaktadir.';

-- dernek.malbildirimi: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `malbildirimi` DISABLE KEYS */;
/*!40000 ALTER TABLE `malbildirimi` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.privilege
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `type_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- dernek.privilege: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` (`id`, `type`, `type_name`) VALUES
	(1, 0, 'admin'),
	(2, 1, 'visitor');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.sube
CREATE TABLE IF NOT EXISTS `sube` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tarih` date NOT NULL DEFAULT current_timestamp(),
  `adres` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `dernekId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `sube_dernek` (`dernekId`),
  CONSTRAINT `sube_dernek` FOREIGN KEY (`dernekId`) REFERENCES `dernek` (`dernekId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dernek tablosunda subeye one-many kullanılacak';

-- dernek.sube: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sube` DISABLE KEYS */;
INSERT INTO `sube` (`id`, `ad`, `tarih`, `adres`, `dernekId`) VALUES
	(1, 'Malatya', '2020-06-25', 'asdas', 2),
	(2, 'Yeşilyurt', '2020-06-25', 'Yeşilyurt', 1),
	(7, 'mert', '2020-06-04', 'klkqq', 0),
	(13, 'mert', '2020-06-24', 'mert', 12);
/*!40000 ALTER TABLE `sube` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.temsilcilik
CREATE TABLE IF NOT EXISTS `temsilcilik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adresi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `telNo` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Temsilcilik kayıt ve kuruluş bölümü için kurulmuştur (dernek ile many to many ilişkisi bulunmaktadır.)';

-- dernek.temsilcilik: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `temsilcilik` DISABLE KEYS */;
/*!40000 ALTER TABLE `temsilcilik` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.temsilcilikdernek
CREATE TABLE IF NOT EXISTS `temsilcilikdernek` (
  `temsilciDernek` int(11) NOT NULL AUTO_INCREMENT,
  `dernekId` int(11) DEFAULT NULL,
  `temId` int(11) DEFAULT NULL,
  PRIMARY KEY (`temsilciDernek`),
  UNIQUE KEY `temsilciDernek` (`temsilciDernek`),
  KEY `temId` (`temId`),
  KEY `derId` (`dernekId`),
  CONSTRAINT `derId` FOREIGN KEY (`dernekId`) REFERENCES `dernek` (`dernekId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `temId` FOREIGN KEY (`temId`) REFERENCES `temsilcilik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='temsilcilik dernek ilişki tablosu ManyToMany';

-- dernek.temsilcilikdernek: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `temsilcilikdernek` DISABLE KEYS */;
/*!40000 ALTER TABLE `temsilcilikdernek` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `privilege_id` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `surname` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_user_privilege` (`privilege_id`),
  CONSTRAINT `FK_user_privilege` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- dernek.user: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `privilege_id`, `username`, `password`, `name`, `surname`, `email`) VALUES
	(1, 1, 'admin', 'admin', 'mert', 'kilic', 'empty@empty.com'),
	(2, 2, 'other', 'other', 'other', 'other', 'other@other.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- tablo yapısı dökülüyor dernek.uye
CREATE TABLE IF NOT EXISTS `uye` (
  `uyeId` int(11) NOT NULL AUTO_INCREMENT,
  `tcNo` int(11) NOT NULL,
  `adiSoyadi` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `telNo` int(11) NOT NULL,
  `meslek` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ogrenimDurumu` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`uyeId`) USING BTREE,
  UNIQUE KEY `uyeId` (`uyeId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='üye kayıt işlemi için tablo oluşturulmuştur';

-- dernek.uye: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `uye` DISABLE KEYS */;
INSERT INTO `uye` (`uyeId`, `tcNo`, `adiSoyadi`, `telNo`, `meslek`, `ogrenimDurumu`) VALUES
	(1, 1231231, 'Wayne Johnson', 1231231, 'Dr', 'asd'),
	(4, 123123, 'ooops', 4534535, 'NULL değil', 'null değil'),
	(7, 0, 'erere', 0, 'NULL', 'NULL'),
	(9, 123123, 'mertwww', 12123, 'qweqwe', 'qweq'),
	(12, 12312, 'wwwwwwwwwww', 123123, 'qweqwe', '123'),
	(13, 12312, '1231', 1231, '1231', '123'),
	(14, 12312, 'asd', 1231, 'qweqwe', 'aaewa'),
	(15, 12312, 'mertwwwson', 12123, 'qweqwe', 'qweq'),
	(18, 12312, 'asd', 12123, 'aweqwe', 'qweq');
/*!40000 ALTER TABLE `uye` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
