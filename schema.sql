/*
SQLyog Ultimate v11.01 (64 bit)
MySQL - 5.5.28 : Database - sound
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sound` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sound`;

/*Table structure for table `auth` */

DROP TABLE IF EXISTS `auth`;

CREATE TABLE `auth` (
  `id` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `registerDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='google oauth information';

/*Data for the table `auth` */

LOCK TABLES `auth` WRITE;

insert  into `auth`(`id`,`name`,`email`,`registerDate`) values ('100000000000000000000','Daniel Kim','daniel@gmail.com','2015-05-17 17:09:12'),('100000000000000000001','Cody','cody@gmail.com','2015-05-01 17:13:43'),('100000000000000000002','Jone Park','park@gmail.com','2015-05-17 17:10:25'),('100000000000000000003','Agatha','agath@gmail.com','2015-05-17 17:10:25'),('100000000000000000004','Alice','alice@gmail.com','2015-05-17 17:10:25'),('100000000000000000005','Dorothy','doroty@gmail.com','2015-05-17 17:10:25'),('100000000000000000006','Frances','frances@gmail.com','2015-05-17 17:10:25'),('100000000000000000007','Lucy','lucy@gmail.com','2015-05-17 17:10:25'),('100000000000000000008','Judith','judith@gmail.com','2015-05-17 17:10:25'),('100000000000000000009','Niki','niki@gmail.com','2015-05-17 17:10:25'),('100000000000000000010','Emily','emily@gmail.com','2015-05-17 17:10:25'),('100349807179854360863','Hong Fe','bbcyo2@gmail.com','2015-05-17 00:04:27'),('101905441605606981988','Jung Ho','jsjsch0459@gmail.com','2015-05-16 15:54:05'),('104147905720314292336','Kim Han','ghost4425@naver.com','2015-05-16 14:31:37'),('107262701046067494080','김연상','dusgnlfl@gmail.com','2015-06-07 19:13:20'),('110728430166896073789','김성근','tjdrms0121@gmail.com','2015-06-06 21:16:12');

UNLOCK TABLES;

/*Table structure for table `sound` */

DROP TABLE IF EXISTS `sound`;

CREATE TABLE `sound` (
  `pn` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist` varchar(255) NOT NULL,
  PRIMARY KEY (`pn`),
  KEY `id` (`id`),
  CONSTRAINT `sound_ibfk_1` FOREIGN KEY (`id`) REFERENCES `auth` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sound` */

LOCK TABLES `sound` WRITE;

insert  into `sound`(`pn`,`id`,`title`,`artist`) values (1,'110728430166896073789','Come Back Home','에스더 김'),(2,'110728430166896073789','(Bonus Track) Voice Mail (Korean Ver.)','아이유'),(3,'110728430166896073789','Angel','윤미래 With 타이거 JK & 비지'),(4,'110728430166896073789','BAE BAE','BIGBANG'),(5,'110728430166896073789','LOSER','BIGBANG'),(6,'110728430166896073789','Obliviate','아이유');

UNLOCK TABLES;

/*Table structure for table `sound_time_stamp` */

DROP TABLE IF EXISTS `sound_time_stamp`;

CREATE TABLE `sound_time_stamp` (
  `soundPn` bigint(11) NOT NULL,
  `timeStamp` datetime NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `weatherMain` varchar(255) DEFAULT NULL,
  `weatherDescription` varchar(255) DEFAULT NULL,
  KEY `soundPn` (`soundPn`),
  CONSTRAINT `sound_time_stamp_ibfk_1` FOREIGN KEY (`soundPn`) REFERENCES `sound` (`pn`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sound_time_stamp` */

LOCK TABLES `sound_time_stamp` WRITE;

insert  into `sound_time_stamp`(`soundPn`,`timeStamp`,`latitude`,`longitude`,`weatherMain`,`weatherDescription`) values (1,'2015-06-06 21:24:18',37.5743704,126.9980923,'Rain','light rain'),(2,'2015-06-06 21:26:05',37.5743704,126.9980923,'Rain','light rain'),(3,'2015-06-06 21:27:04',37.5743704,126.9980923,'Rain','light rain'),(2,'2015-06-06 21:30:13',37.5743704,126.9980923,'Rain','light rain'),(3,'2015-06-06 21:34:20',37.5743704,126.9980923,'Rain','light rain'),(3,'2015-06-06 21:34:50',37.5743704,126.9980923,'Rain','light rain'),(3,'2015-06-07 00:53:42',37.5743704,126.9980923,'Rain','light rain'),(4,'2015-06-07 00:57:59',37.5743704,126.9980923,'Rain','light rain'),(3,'2015-06-07 01:00:40',37.5743704,126.9980923,'Rain','light rain'),(2,'2015-06-07 01:00:58',37.5743704,126.9980923,'Rain','light rain'),(5,'2015-06-07 01:04:35',37.5743772,126.9980923,'Rain','light rain'),(3,'2015-06-07 01:04:38',37.5743772,126.9980923,'Rain','light rain'),(3,'2015-06-07 01:06:52',37.5743838,126.9980923,'Rain','light rain'),(4,'2015-06-07 01:11:08',37.5743644,126.9980838,'Rain','light rain'),(6,'2015-06-07 01:12:10',37.5743541,126.9981124,'Rain','light rain');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
