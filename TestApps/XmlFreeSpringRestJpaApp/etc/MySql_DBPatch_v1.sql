CREATE DATABASE  IF NOT EXISTS `test`;
USE `test`;

DROP TABLE IF EXISTS `test`.`customer`;
CREATE TABLE `test`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL,
  `accountnumber` INT(10) NOT NULL,
  `email` VARCHAR(50) NULL,
  `imageloc` VARCHAR(100) NULL,
  `lastupdated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `accountnumber_UNIQUE` (`accountnumber` ASC))
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to hold all customers data.';