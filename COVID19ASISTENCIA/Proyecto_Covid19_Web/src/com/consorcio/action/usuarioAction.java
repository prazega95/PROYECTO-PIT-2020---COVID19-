package com.consorcio.action;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.consorcio.entidad.Usuario;
import com.consorcio.modelo.modeloUsuario;

import utils.Constantes;
@ManagedBean(name="CRUDUsuario")
@ViewScoped
public class usuarioAction {
	//atributo para almacenar todos los ciudadanos
	private List<Usuario> listaUsuario;
	//Atributo para registrar
	private Usuario usuario;
	//Metodo para registrar ciudadanos
	public void registrarUsuario(){
		new modeloUsuario().addUsuario(usuario);
		Constantes.mensaje("Sistema","Registro grabado corre.",
					FacesMessage.SEVERITY_INFO);
		//return "/ui/listaUsuario.jsf";
	}
	//Método para actualizar ciudadano
	public void actualizarUsuario(){
		new modeloUsuario().
			updateUsuario(usuario);
		Constantes.mensaje("Sistema","Registro actualizado corre.",
				FacesMessage.SEVERITY_INFO);
	}
	//Método para eliminar ciudadano
	public void eliminarUsuario(){
		new modeloUsuario().
		deleteUsuario(usuario.getCodUsuario());
		Constantes.mensaje("Sistema","Registro eliminado corre.",
				FacesMessage.SEVERITY_INFO);
	}
	
	
	
	public void nuevoUsuario(){
		usuario = new Usuario();
	}
	
	//Constructor
	public usuarioAction(){
		usuario = new Usuario();
	}
	
	public List<Usuario> getListaUsuario() {
		listaUsuario=new modeloUsuario().lista();
		return listaUsuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}
	
}

