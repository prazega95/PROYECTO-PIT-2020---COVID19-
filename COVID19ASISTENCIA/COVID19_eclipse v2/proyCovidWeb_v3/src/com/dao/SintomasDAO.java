package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entidad.Administrador;
import com.entidad.Paciente;
import com.entidad.Sintomas;
import com.util.Conexion;

public class SintomasDAO {
	Conexion cn = new Conexion();
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	int r;
	
	
	public List listar() {
		String sql="Select s.Departamento, s.Provincia, s.Distrito, s.Direccion, s.NumeroFamiliar, s.Profesion,s.Email, u.nom_usuario, u.ape_usuario, s.PrimerSintoma, s.SegundoSintoma, s.TercerSintoma, s.CuartoSintoma, s.QuintoSintoma, s.SextoSintoma from tb_sintomas s inner join tb_usuario u on s.cod_usuario = u.cod_usuario";
		List<Sintomas> lista = new ArrayList<>();
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Sintomas sint = new Sintomas();
				sint.setDepartamento(rs.getString(1));
				sint.setProvincia(rs.getString(2));
				sint.setDistrito(rs.getString(3));
				sint.setDireccion(rs.getString(4));
				sint.setNumFamiliar(rs.getString(5));
				sint.setProfesion(rs.getString(6));
				sint.setEmail(rs.getString(7));
				Paciente paci = new Paciente();
				paci.setNomPac(rs.getString(8));
				paci.setApePac(rs.getString(9));
				/*--------------------------------*/
				//seteo para mostrar en el modal sintomas
				sint.setPriSintoma(rs.getString(10));
				sint.setSegSintoma(rs.getString(11));
				sint.setTerSintoma(rs.getString(12));
				sint.setCuartSintoma(rs.getString(13));
				sint.setQuintSintoma(rs.getString(14));
				sint.setSextSintoma(rs.getString(15));
			
				sint.setPaciente(paci);
				
				lista.add(sint);
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}
	
	
	
	
	
	
	
/*	public List listar2() {
		String sql="Select cod_sintomas,Departamento,Provincia,Distrito,Direccion,NumeroFamiliar,Profesion,PrimerSintoma,SegundoSintoma,TercerSintoma,CuartoSintoma,QuintoSintoma,SextoSintoma,Ninguna,Email,cod_usuario from tb_sintomas";
		List<Sintomas> lista = new ArrayList<>();
		try {
			con = cn.getConexion();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Sintomas sint = new Sintomas();
				sint.setIdSintom(rs.getInt(1));
				sint.setDepartamento(rs.getString(2));
				sint.setProvincia(rs.getString(3));
				sint.setDistrito(rs.getString(4));
				sint.setDireccion(rs.getString(5));
				sint.setNumFamiliar(rs.getString(6));
				sint.setProfesion(rs.getString(7));
				
				sint.setPriSintoma(rs.getString(8));
				sint.setSegSintoma(rs.getString(9));
				sint.setTerSintoma(rs.getString(10));
				sint.setCuartSintoma(rs.getString(11));
				sint.setQuintSintoma(rs.getString(12));
				sint.setSextSintoma(rs.getString(13));
				sint.setNinguna(rs.getString(14));
				
				sint.setEmail(rs.getString(15));
				sint.setCodPaciente(rs.getInt(16));
				lista.add(sint);
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lista;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
