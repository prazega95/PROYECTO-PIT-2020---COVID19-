package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	Connection con;
	public Conexion() {
		try {
			
		//    Class.forName("com.mysql.cj.jdbc.Driver");
		//	con = DriverManager.getConnection("jdbc:mysql://node225420-env-3574683.j.layershift.co.uk/proyecto_covid19?serverTimezone=UTC","root","TGArlk31649");
			
		   Class.forName("com.mysql.jdbc.Driver");
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_covid19?serverTimezone=UTC","root","");
		    
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
	}
	
	public Connection getConexion() {
		return con;
	}
}
