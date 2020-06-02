CREATE SCHEMA IF NOT EXISTS `proyecto_covid19` DEFAULT CHARACTER SET utf8 ;
USE `proyecto_covid19` ;


CREATE TABLE tb_administrador (
  idAdmin int(2) NOT NULL AUTO_INCREMENT,
  nomape_admin varchar(20) DEFAULT NULL,
  fono_admin varchar(9) DEFAULT NULL,
  usuario_admin varchar(20) DEFAULT NULL,
  contra_admin varchar(20) DEFAULT NULL,
  PRIMARY KEY (idAdmin)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;



CREATE TABLE `tb_usuario` (
  `cod_usuario` int(2) NOT NULL AUTO_INCREMENT,
  `nom_usuario` varchar(20) DEFAULT NULL,
  `ape_usuario` varchar(20) DEFAULT NULL,
  `doc_usuario` varchar(8) DEFAULT NULL,
  `tel_usuario` varchar(9) DEFAULT NULL,
  `login_usuario` varchar(10) DEFAULT NULL,
  `pass_usuario` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
--



CREATE TABLE `tb_sintomas` (
  `cod_sintomas` int(2) NOT NULL AUTO_INCREMENT,
  `Departamento` varchar(45) DEFAULT NULL,
  `Provincia` varchar(45) DEFAULT NULL,
  `Distrito` varchar(45) DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `NumeroFamiliar` varchar(45) DEFAULT NULL,
  `Profesion` varchar(45) DEFAULT NULL,
  `PrimerSintoma` varchar(45) DEFAULT NULL,
  `SegundoSintoma` varchar(45) DEFAULT NULL,
  `TercerSintoma` varchar(45) DEFAULT NULL,
  `CuartoSintoma` varchar(45) DEFAULT NULL,
  `QuintoSintoma` varchar(45) DEFAULT NULL,
  `SextoSintoma` varchar(45) DEFAULT NULL,
  `Ninguna` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `cod_usuario` int(2) DEFAULT NULL,
  PRIMARY KEY (`cod_sintomas`),
  KEY `fk_sintomas_usuario` (`cod_usuario`),
  CONSTRAINT `fk_sintomas_usuario` FOREIGN KEY (`cod_usuario`) REFERENCES `tb_usuario` (`cod_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

