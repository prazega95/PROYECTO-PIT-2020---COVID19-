package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entidad.Administrador;
import com.entidad.Paciente;
import com.util.Conexion;

public class PacienteDAO {
	
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int r;
	
	//Operaciones CRUD
	
	//Listar
	public List listar() {
		String sql = "select * from tb_usuario";
		List<Paciente> lista = new ArrayList<>();
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Paciente pac = new Paciente();
				pac.setIdPac(rs.getInt(1));
				pac.setNomPac(rs.getString(2));
				pac.setApePac(rs.getString(3));
				pac.setDniPac(rs.getString(4));
				pac.setFonoPac(rs.getString(5));
				pac.setUserPac(rs.getString(6));
				pac.setPassPac(rs.getString(7));
				lista.add(pac);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	public int agregar(Paciente pac) {
		String sql="insert into tb_usuario(nom_usuario,ape_usuario,doc_usuario,tel_usuario,login_usuario,pass_usuario)values(?,?,?,?,?,?)";
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, pac.getNomPac());
			ps.setString(2, pac.getApePac());
			ps.setString(3, pac.getDniPac());
			ps.setString(4, pac.getFonoPac());
			ps.setString(5, pac.getUserPac());
			ps.setString(6, pac.getPassPac());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return r;
	}
	
	
	
	
	
	public Paciente listarId(int id) {
		Paciente pac = new Paciente();
		String sql = "Select * from tb_usuario where cod_usuario=" + id;
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				pac.setNomPac(rs.getString(2));
				pac.setApePac(rs.getString(3));
				pac.setDniPac(rs.getString(4));
				pac.setFonoPac(rs.getString(5));
				pac.setUserPac(rs.getString(6));
				pac.setPassPac(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pac;
	}
	
	
	public int actualizar(Paciente pac) {
		String sql="update tb_usuario set nom_usuario=?,ape_usuario=?,doc_usuario=?,tel_usuario=?,login_usuario=?,pass_usuario=? where cod_usuario=?";
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			ps.setString(1, pac.getNomPac());
			ps.setString(2, pac.getApePac());
			ps.setString(3, pac.getDniPac());
			ps.setString(4, pac.getFonoPac());
			ps.setString(5, pac.getUserPac());
			ps.setString(6, pac.getPassPac());
			ps.setInt(7, pac.getIdPac());
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return r;
	}
	
	
	
	
	
	//Eliminar
	public void Eliminar(int id) {
		String sql = "delete from tb_usuario where cod_usuario="+ id;
			try {
				con = cn.getConexion();
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
					
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
