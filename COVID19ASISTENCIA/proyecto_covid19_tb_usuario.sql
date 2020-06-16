CREATE SCHEMA IF NOT EXISTS `proyecto_covid19` DEFAULT CHARACTER SET utf8 ;
/*use proyecto_covid19

/*drop table tb_administrador*/
CREATE TABLE tb_administrador (
  idAdmin int(2) NOT NULL AUTO_INCREMENT,
  nomape_admin varchar(20) DEFAULT NULL,
  fono_admin varchar(9) DEFAULT NULL,
  usuario_admin varchar(20) DEFAULT NULL,
  contra_admin varchar(20) DEFAULT NULL,
  PRIMARY KEY (idAdmin)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*TRUNCATE tb_administrador */


/*drop table tb_usuario*/
CREATE TABLE `tb_usuario` (
  `cod_usuario` int(2) NOT NULL AUTO_INCREMENT,
  `nom_usuario` varchar(40) DEFAULT NULL,
  `ape_usuario` varchar(60) DEFAULT NULL,
  `TipoDoc_usuario` varchar(10) DEFAULT NULL,
  `doc_usuario` varchar(10) DEFAULT NULL,
  `tel_usuario` varchar(9) DEFAULT NULL,
  `login_usuario` varchar(20) DEFAULT NULL,
  `pass_usuario` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cod_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*TRUNCATE tb_usuario */


/*drop table tb_sintomas*/
CREATE TABLE `tb_sintomas` (
  `cod_sintomas` int(2) NOT NULL AUTO_INCREMENT,
  `Departamento` varchar(80) DEFAULT NULL,
  `Provincia` varchar(80) DEFAULT NULL,
  `Distrito` varchar(80) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Latitud` varchar(255) DEFAULT NULL,
  `Longitud` varchar(255) DEFAULT NULL,
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
  `Condicion` varchar(45) DEFAULT NULL,
  `Resultado` varchar(45) DEFAULT NULL,
  `cod_usuario` int(2) DEFAULT NULL,
  PRIMARY KEY (`cod_sintomas`),
  KEY `fk_sintomas_usuario` (`cod_usuario`),
  CONSTRAINT `fk_sintomas_usuario` FOREIGN KEY (`cod_usuario`) REFERENCES `tb_usuario` (`cod_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*TRUNCATE tb_sintomas */





/*drop table tb_noticias*/
CREATE TABLE tb_noticias (
  idnoticia int(2) NOT NULL AUTO_INCREMENT,
  titulo_noticia BLOB   DEFAULT NULL,
  contenido_noticia BLOB   DEFAULT NULL,
  PRIMARY KEY (idnoticia)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*TRUNCATE tb_noticias */



/*PROCEDURE PARA FILTRAR CIFRAS POR DISTRITO*/
delimiter $$
create procedure sp_cifras(in pDepartamento varchar(200))
begin
select Distinct
	Departamento,
    (select count(*) from tb_sintomas where Departamento=pDepartamento and Resultado="Confirmado") as confirmados,
    (select count(*) from tb_sintomas where Departamento=pDepartamento and Resultado="Recuperado") as recuperados,
    (select count(*) from tb_sintomas where Departamento=pDepartamento and Resultado="Fallecido") as fallecidos,
    (select count(*) from tb_sintomas where Departamento=pDepartamento and Resultado="Hospitalizado") as hospitalizados
from tb_sintomas 
where 
	Departamento=pDepartamento;
end
$$
