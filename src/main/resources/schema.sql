- -----------------------------------------------------
-- Schema daus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `daus`;
USE `daus` ;

-- -----------------------------------------------------
-- Table `daus`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daus`.`player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `wins` INT(11) NULL DEFAULT NULL,
  `rate` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
  );

-- -----------------------------------------------------
-- Table `daus`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `daus`.`game` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `dice1` INT(11) NULL DEFAULT NULL,
  `dice2` INT(11) NULL DEFAULT NULL,
  `win` TINYINT(1) NULL DEFAULT NULL,
  `player_id` INT(11) NULL DEFAULT NULL,
   PRIMARY KEY (`id`),

  CONSTRAINT `fk_game_player`
    FOREIGN KEY (`player_id`)
    REFERENCES `daus`.`player` (`id`)
    );
