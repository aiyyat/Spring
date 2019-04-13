CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expense_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `entry_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`),
  KEY `FK_81thrbnb8c08gua7tvqj7xdqk` (`parent_id`),
  CONSTRAINT `FK_81thrbnb8c08gua7tvqj7xdqk` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h84pd2rtr12isnifnj655rkra` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `expense_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `direction` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `expense_type_category_map` (
  `exp_id` bigint(20) NOT NULL,
  `cat_id` bigint(20) NOT NULL,
  KEY `FK_hcw0fpyyfe754pt1htj0hmhnx` (`cat_id`),
  KEY `FK_cvtbrngcfqd9j0miwxey570u4` (`exp_id`),
  CONSTRAINT `FK_cvtbrngcfqd9j0miwxey570u4` FOREIGN KEY (`exp_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_hcw0fpyyfe754pt1htj0hmhnx` FOREIGN KEY (`cat_id`) REFERENCES `expense_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gj2fy3dcix7ph7k8684gka40c` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


CREATE TABLE `expense` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` float NOT NULL,
  `date` datetime DEFAULT NULL,
  `cat_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `currency_id` int(11) DEFAULT NULL,
  `mode` int(11) DEFAULT NULL,
  `mode_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5c1m47ef8insoj7qplyjdjqax` (`cat_id`),
  KEY `FK_5yg1m4mjt320vmpautnh2w05p` (`user_id`),
  KEY `FK_kx71inx2n24smuh4dte1h2xau` (`currency_id`),
  KEY `FK_s7vj81logu6w5vu99p1e5bga6` (`mode_id`),
  CONSTRAINT `FK_5c1m47ef8insoj7qplyjdjqax` FOREIGN KEY (`cat_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_5yg1m4mjt320vmpautnh2w05p` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_kx71inx2n24smuh4dte1h2xau` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_s7vj81logu6w5vu99p1e5bga6` FOREIGN KEY (`mode_id`) REFERENCES `expense_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

INSERT INTO `user` (`id`,`email`,`name`,`password`,`role`) VALUES (1,NULL,'admin','admin','ROLE_ADMIN');

INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (1,'Root',NULL,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (2,'Food',1,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (3,'Travel',1,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (4,'Entertainment',1,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (5,'Payment',1,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (6,'Office',1,8);
INSERT INTO `category` (`id`,`name`,`parent_id`,`expense_type`) VALUES (7,'Others',1,8);
insert into `category`(expense_type,name,parent_id) values (8,'Health',1);
