package com.entidad;

public class Paciente {
	int idPac;
	String nomPac;
	String apePac;
	String dniPac;
	String fonoPac;
	String userPac;
	String passPac;
	
	public Paciente() {
		
	}

	public Paciente(int idPac, String nomPac, String apePac, String dniPac, String fonoPac, String userPac,
			String passPac) {
		this.idPac = idPac;
		this.nomPac = nomPac;
		this.apePac = apePac;
		this.dniPac = dniPac;
		this.fonoPac = fonoPac;
		this.userPac = userPac;
		this.passPac = passPac;
	}

	public int getIdPac() {
		return idPac;
	}

	public void setIdPac(int idPac) {
		this.idPac = idPac;
	}

	public String getNomPac() {
		return nomPac;
	}

	public void setNomPac(String nomPac) {
		this.nomPac = nomPac;
	}

	public String getApePac() {
		return apePac;
	}

	public void setApePac(String apePac) {
		this.apePac = apePac;
	}

	public String getDniPac() {
		return dniPac;
	}

	public void setDniPac(String dniPac) {
		this.dniPac = dniPac;
	}

	public String getFonoPac() {
		return fonoPac;
	}

	public void setFonoPac(String fonoPac) {
		this.fonoPac = fonoPac;
	}

	public String getUserPac() {
		return userPac;
	}

	public void setUserPac(String userPac) {
		this.userPac = userPac;
	}

	public String getPassPac() {
		return passPac;
	}

	public void setPassPac(String passPac) {
		this.passPac = passPac;
	}
	
	
	
}
