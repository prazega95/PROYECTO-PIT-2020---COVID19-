package com.consorcio.entidad;
import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_usu")
	private int codUsuario;
	@Column(name="nom_usu")
	private String nomUsuario;
	@Column(name="ape_usu")
	private String apeUsuario;
	@Column(name="dni")
	private String dniUsuario;
	@Column(name="depa_usu")
	private String depaUsuario;
	@Column(name="prov_usu")
	private String provUsuario;
	@Column(name="dist_usu")
	private String distUsuario;
	@Column(name="dir_usu")
	private String dirUsuario;
	@Column(name="login")
	private String logUsuario;
	@Column(name="password")
	private int passUsuario;
	
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getApeUsuario() {
		return apeUsuario;
	}
	public void setApeUsuario(String apeUsuario) {
		this.apeUsuario = apeUsuario;
	}
	public String getDniUsuario() {
		return dniUsuario;
	}
	public void setDniUsuario(String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}
	public String getDepaUsuario() {
		return depaUsuario;
	}
	public void setDepaUsuario(String depaUsuario) {
		this.depaUsuario = depaUsuario;
	}
	public String getProvUsuario() {
		return provUsuario;
	}
	public void setProvUsuario(String provUsuario) {
		this.provUsuario = provUsuario;
	}
	public String getDistUsuario() {
		return distUsuario;
	}
	public void setDistUsuario(String distUsuario) {
		this.distUsuario = distUsuario;
	}
	public String getDirUsuario() {
		return dirUsuario;
	}
	public void setDirUsuario(String dirUsuario) {
		this.dirUsuario = dirUsuario;
	}
	public String getLogUsuario() {
		return logUsuario;
	}
	public void setLogUsuario(String logUsuario) {
		this.logUsuario = logUsuario;
	}
	public int getPassUsuario() {
		return passUsuario;
	}
	public void setPassUsuario(int passUsuario) {
		this.passUsuario = passUsuario;
	}
	
	
	
	
	
	
	
	
	
}






