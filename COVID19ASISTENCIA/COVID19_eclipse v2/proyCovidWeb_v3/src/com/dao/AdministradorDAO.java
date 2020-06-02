package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entidad.Administrador;
import com.util.Conexion;

public class AdministradorDAO {
	
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int r;
	
	//Operaciones CRUD
	public List listar() {
		String sql ="Select * from tb_administrador";
		List<Administrador> lista = new ArrayList<>();
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Administrador adm = new Administrador();
				adm.setIdAdmin(rs.getInt(1));
				adm.setNomapeAdmin(rs.getString(2));
				adm.setTelAdmin(rs.getString(3));
				adm.setUserAdmin(rs.getString(4));
				adm.setPassAdmin(rs.getString(5));
				lista.add(adm);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lista;
	}
	
	public int agregar(Administrador adm) {
		String sql="insert into tb_administrador(nomape_admin,fono_admin,usuario_admin,contra_admin)values(?,?,?,?)";
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, adm.getNomapeAdmin());
			ps.setString(2, adm.getTelAdmin());
			ps.setString(3, adm.getUserAdmin());
			ps.setString(4, adm.getPassAdmin());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return r;
	}
	
	public Administrador listarId(int id) {
		Administrador adm = new Administrador();
		String sql = "Select * from tb_administrador where idAdmin=" + id;
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				adm.setNomapeAdmin(rs.getString(2));
				adm.setTelAdmin(rs.getString(3));
				adm.setUserAdmin(rs.getString(4));
				adm.setPassAdmin(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return adm;
	}
	
	public int actualizar(Administrador adm) {
		String sql="update tb_administrador set nomape_admin=?,fono_admin=?,usuario_admin=?,contra_admin=? where idAdmin=?";
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, adm.getNomapeAdmin());
			ps.setString(2, adm.getTelAdmin());
			ps.setString(3, adm.getUserAdmin());
			ps.setString(4, adm.getPassAdmin());
			ps.setInt(5, adm.getIdAdmin());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return r;
	}
	
	public void eliminar(int id) {
		String sql="delete from tb_administrador where idAdmin=" + id;
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
