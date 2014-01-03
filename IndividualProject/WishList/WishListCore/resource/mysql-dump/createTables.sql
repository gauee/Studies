DROP TABLE IF EXISTS `Wish_User_UserGroup`;
DROP TABLE IF EXISTS `Wish_User_List`;
DROP TABLE IF EXISTS `WishItemInList`;
DROP TABLE IF EXISTS `WishUserRole`;
DROP TABLE IF EXISTS `WishUser`;
DROP TABLE IF EXISTS `WishUserGroup`;
DROP TABLE IF EXISTS `WishItem`;
DROP TABLE IF EXISTS `WishList`;
DROP TABLE IF EXISTS `WishItemCategory`;



CREATE TABLE `WishUser` (
  `wu_id` int(11) NOT NULL AUTO_INCREMENT,
  `wu_login` varchar(255) NOT NULL,
  `wu_pass_hash` varchar(255) NOT NULL,
  `wu_name` varchar(255) NOT NULL,
  `wu_surname` varchar(255) DEFAULT NULL,
  `wu_email` varchar(255) NOT NULL,
  `wu_msisdn` varchar(255) DEFAULT NULL, 
  PRIMARY KEY (`wu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WishUserRole` (
  `wur_id` int(11) NOT NULL AUTO_INCREMENT,
  `wur_authority` varchar(255) NOT NULL,
  `wur_wu_id` int(11) NOT NULL,

  PRIMARY KEY (`wur_id`),
  INDEX(wur_wu_id),
  FOREIGN KEY (wur_wu_id)
      REFERENCES WishUser(wu_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WishUserGroup` (
  `wug_id` int(11) NOT NULL AUTO_INCREMENT,
  `wug_name` varchar(255) NOT NULL,
  `wug_created_time` date NOT NULL, 
  PRIMARY KEY (`wug_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `Wish_User_UserGroup` (
  `wuug_wu_id` int(11) NOT NULL,
  `wuug_wug_id` int(11) NOT NULL,
  INDEX(wuug_wu_id),
  INDEX(wuug_wug_id),
  FOREIGN KEY (wuug_wu_id)
      REFERENCES WishUser(wu_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (wuug_wug_id)
      REFERENCES WishUserGroup(wug_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `WishList` (
  `wl_id` int(11) NOT NULL AUTO_INCREMENT,
  `wl_name` varchar(255) NOT NULL,
  `wl_created_time` date NOT NULL, 
  PRIMARY KEY (`wl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Wish_User_List` (
  `wul_wu_id` int(11) NOT NULL,
  `wul_wl_id` int(11) NOT NULL,
  INDEX(wul_wu_id),
  INDEX(wul_wl_id),
  FOREIGN KEY (wul_wu_id)
      REFERENCES WishUser(wu_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (wul_wl_id)
      REFERENCES WishList(wl_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WishItemCategory` (
  `wic_id` int(11) NOT NULL AUTO_INCREMENT,
  `wic_name` varchar(255) NOT NULL,
  PRIMARY KEY (`wic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WishItem` (
  `wi_id` int(11) NOT NULL AUTO_INCREMENT,
  `wi_name` varchar(255) NOT NULL,
  `wi_photo` varchar(255) DEFAULT NULL, 
  `wi_description` varchar(255) DEFAULT NULL,
  `wi_price` DECIMAL(9,2) DEFAULT NULL,
  `wi_wic_id` int(11) NOT NULL,
  PRIMARY KEY (`wi_id`),
  INDEX(wi_wic_id),
  FOREIGN KEY (wi_wic_id)
      REFERENCES WishItemCategory(wic_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `WishItemInList` (
  `wiil_id` int(11) NOT NULL AUTO_INCREMENT,
  `wiil_bought` tinyint(1) DEFAULT FALSE,
  `wiil_last_update` date NOT NULL,

  `wiil_wl_id` int(11) NOT NULL,
  `wiil_wi_id` int(11) NOT NULL,

  PRIMARY KEY (`wiil_id`),
  INDEX(wiil_wl_id),
  INDEX(wiil_wi_id),
  FOREIGN KEY (wiil_wl_id)
      REFERENCES WishList(wl_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  FOREIGN KEY (wiil_wi_id)
      REFERENCES WishItem(wi_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

show variables like "character_set_database";
show variables like "collation_database";
ALTER DATABASE wishListDb CHARACTER SET utf8;
ALTER DATABASE wishListDb COLLATE 'utf8_general_ci';
ALTER TABLE WishUser CONVERT TO CHARACTER SET utf8 collate utf8_general_ci;
SET NAMES utf8mb4;

insert into WishUser (wu_login,wu_pass_hash,wu_name,wu_surname,wu_email,wu_msisdn) 
values('gauee','0e238030db298bbe7fcb89275fe2a789f358b690ca7581479bf1c34d4d0ff49d','Damian','Gałka','galka.damian.91@gmail.com','503109746');

select * from WishUser;