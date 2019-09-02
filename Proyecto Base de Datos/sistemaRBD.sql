-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`hotel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`hotel` ;

CREATE TABLE IF NOT EXISTS `mydb`.`hotel` (
  `idHotel` INT auto_increment,
  `nombre` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(20) NOT NULL,
  `estado` boolean NOT NULL,
  PRIMARY KEY (`idHotel`),
  UNIQUE INDEX `idHotel_UNIQUE` (`idHotel` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`empleado` ;

CREATE TABLE IF NOT EXISTS `mydb`.`empleado` (
  `idEmpleado` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(40) NOT NULL,
  `apellido` VARCHAR(40) NOT NULL,
  `direccion` VARCHAR(40) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `estado` boolean NOT NULL,
  `sueldo` FLOAT NOT NULL,
  `turno` CHAR(1) NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  `idHotel` INT NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  UNIQUE INDEX `idEmpleado_UNIQUE` (`idEmpleado` ASC) VISIBLE,
  INDEX `idHotel_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `idHotel`
    FOREIGN KEY (`idHotel`)
    REFERENCES `mydb`.`hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cliente` ;

CREATE TABLE IF NOT EXISTS `mydb`.`cliente` (
  `idCliente` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(20) NOT NULL,
  `apellido` VARCHAR(40) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `id_UNIQUE` (`idCliente` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `idUsuario` INT auto_increment,
  `usuario` VARCHAR(15) NOT NULL,
  `contrasena` VARCHAR(15) NOT NULL,
  `estado` boolean NOT NULL,
  `tipo` VARCHAR(25) NOT NULL,
  `idCliente` VARCHAR(15) NULL,
  `idEmpleado` VARCHAR(15) NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `idEmpleado_idx` (`idEmpleado` ASC) VISIBLE,
  CONSTRAINT `idClienteU`
    FOREIGN KEY (`idCliente`)
    REFERENCES `mydb`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idEmpleado`
    FOREIGN KEY (`idEmpleado`)
    REFERENCES `mydb`.`empleado` (`idEmpleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipoHabitacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tipoHabitacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tipoHabitacion` (
  `idTipo` INT auto_increment,
  `nombre` VARCHAR(15) NOT NULL,
  `precio` FLOAT NOT NULL,
  `estado` boolean NOT NULL,
  UNIQUE INDEX `idTipo_UNIQUE` (`idTipo` ASC) VISIBLE,
  PRIMARY KEY (`idTipo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`habitacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`habitacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`habitacion` (
  `idHabitacion` INT auto_increment,
  `numeroHabitacion` VARCHAR(5) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `numeroDeCama` INT NOT NULL,
  `estado` boolean NOT NULL,
  `idHotel` INT NOT NULL,
  `idTipoH` INT NOT NULL,
  PRIMARY KEY (`idHabitacion`),
  UNIQUE INDEX `idHabitacion_UNIQUE` (`idHabitacion` ASC) VISIBLE,
  INDEX `idHotel_idx` (`idHotel` ASC) VISIBLE,
  CONSTRAINT `idHotelh`
    FOREIGN KEY (`idHotel`)
    REFERENCES `mydb`.`hotel` (`idHotel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTipoH`
    FOREIGN KEY (`idTipoH`)
    REFERENCES `mydb`.`tipoHabitacion` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tipoPago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tipoPago` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tipoPago` (
  `idTipo` INT auto_increment,
  `tipo` VARCHAR(15) NOT NULL,
  `Descripcion` VARCHAR(45) NOT NULL,
  `estado` boolean NOT NULL,
  PRIMARY KEY (`idTipo`),
  UNIQUE INDEX `idTipo_UNIQUE` (`idTipo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`reserva`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reserva` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reservacion` (
  `idReserva` INT auto_increment,
  `fechaInicio` DATE NOT NULL,
  `fechaFin` DATE NOT NULL,
  `detalle` VARCHAR(45) NOT NULL,
  `estado` boolean NOT NULL,
  `total` float NOT NULL,
  `idCliente` VARCHAR(20) NOT NULL,
  `idHabitacion` INT NOT NULL,
  `idTipoP` INT NOT NULL,
  PRIMARY KEY (`idReserva`),
  UNIQUE INDEX `idReserva_UNIQUE` (`idReserva` ASC) VISIBLE,
  INDEX `idCliente_idx` (`idCliente` ASC) VISIBLE,
  INDEX `idHabitacion_idx` (`idHabitacion` ASC) VISIBLE,
  INDEX `idTipoP_idx` (`idTipoP` ASC) VISIBLE,
  CONSTRAINT `idClienteR`
    FOREIGN KEY (`idCliente`)
    REFERENCES `mydb`.`cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idHabitacionR`
    FOREIGN KEY (`idHabitacion`)
    REFERENCES `mydb`.`habitacion` (`idHabitacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idTipoPR`
    FOREIGN KEY (`idTipoP`)
    REFERENCES `mydb`.`tipoPago` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `mydb`.`log` (
  `idCodigo` INT auto_increment,
  `fecha` varchar(80) NOT NULL,
  `detalle` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`idCodigo`))
ENGINE = InnoDB;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
