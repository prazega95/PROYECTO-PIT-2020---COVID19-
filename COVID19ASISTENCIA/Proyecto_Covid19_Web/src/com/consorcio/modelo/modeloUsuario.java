package com.consorcio.modelo;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import com.consorcio.entidad.Usuario;

public class modeloUsuario {
	EntityManagerFactory fabrica=Persistence.
			createEntityManagerFactory("PE");
	public void addUsuario(Usuario usu){
		EntityManager manager=fabrica.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(usu);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			manager.close();
			fabrica.close();
		}
	}
	public void updateUsuario(Usuario usu){
		EntityManager manager=fabrica.createEntityManager();
		try {
			manager.getTransaction().begin();
			manager.merge(usu);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			manager.close();
			fabrica.close();
		}
	}	
	public void deleteUsuario(int cod){
		EntityManager manager=fabrica.createEntityManager();
		try {
			Usuario usu=manager.find(Usuario.class, cod);
			manager.getTransaction().begin();
			manager.remove(usu);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			manager.close();
			fabrica.close();
		}
	}	
	
	//listar todos los usuarios
	public List<Usuario> lista(){
		EntityManager manager=fabrica.createEntityManager();
		List<Usuario> data=null;
		TypedQuery<Usuario> resul=null;
		try {
			String hql="select u from Usuario u";
			resul=manager.createQuery(hql,Usuario.class);
			data=resul.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			manager.close();
			fabrica.close();
		}
		return data;
	}
}

