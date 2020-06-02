package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entidad.Administrador;
import com.util.Conexion;

public class LoginDAO {
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public Administrador validar(String user, String pass) {
		
		Administrador adm = new Administrador();
		String sql = "Select * from tb_administrador where usuario_admin=? and contra_admin=?";
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			while(rs.next()) {
				adm.setIdAdmin(rs.getInt("idAdmin"));
				adm.setUserAdmin(rs.getString("usuario_admin"));
				adm.setPassAdmin(rs.getString("contra_admin"));
				adm.setNomapeAdmin(rs.getString("nomape_admin"));
			}
		} catch (Exception e) {
			
		}
		
		return adm;
	}
}
