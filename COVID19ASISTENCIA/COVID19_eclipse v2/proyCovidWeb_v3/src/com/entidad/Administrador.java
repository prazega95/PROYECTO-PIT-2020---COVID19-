package com.entidad;

public class Administrador {
	int idAdmin;
	String nomapeAdmin;
	String telAdmin;
	String userAdmin;
	String passAdmin;
	
	public Administrador() {
		
	}

	public Administrador(int idAdmin, String nomapeAdmin, String telAdmin, String userAdmin, String passAdmin) {
		this.idAdmin = idAdmin;
		this.nomapeAdmin = nomapeAdmin;
		this.telAdmin = telAdmin;
		this.userAdmin = userAdmin;
		this.passAdmin = passAdmin;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNomapeAdmin() {
		return nomapeAdmin;
	}

	public void setNomapeAdmin(String nomapeAdmin) {
		this.nomapeAdmin = nomapeAdmin;
	}

	public String getTelAdmin() {
		return telAdmin;
	}

	public void setTelAdmin(String telAdmin) {
		this.telAdmin = telAdmin;
	}

	public String getUserAdmin() {
		return userAdmin;
	}

	public void setUserAdmin(String userAdmin) {
		this.userAdmin = userAdmin;
	}

	public String getPassAdmin() {
		return passAdmin;
	}

	public void setPassAdmin(String passAdmin) {
		this.passAdmin = passAdmin;
	}
	
	
}
