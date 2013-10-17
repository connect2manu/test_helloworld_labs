-- Creates a separate MySql user.


-- creating local user with all permissions
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin#123';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' WITH GRANT OPTION;

-- creating user for all domains with all permissions
CREATE USER 'admin'@'%' IDENTIFIED BY 'admin#123';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%' WITH GRANT OPTION;



-- Notification Server Database Schema
-- Version 0.1

DROP SCHEMA IF EXISTS `demo`;
CREATE SCHEMA `demo`;
USE `demo`;

-- creating TRIGGERS table inside demo Schema
DROP TABLE IF EXISTS `NOTIFICATION`;
CREATE  TABLE `NOTIFICATION` (
  `MACADDRESS` BIGINT(20) NOT NULL ,
  `MESSAGE` TIMESTAMP NULL ,
  `REMINDER` TIMESTAMP NULL ,
  PRIMARY KEY (`MACADDRESS`) ,
  UNIQUE INDEX `idNotification_UNIQUE` (`MACADDRESS` ASC) 
)
ENGINE = INNODB
;
