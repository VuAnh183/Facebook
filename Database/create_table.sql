-- Create Schema 
DROP SCHEMA IF EXISTS `facebook`;

CREATE SCHEMA `facebook`;
USE `facebook`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `facebook_user`;

CREATE TABLE `facebook_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name`varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,student
  `data_created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

