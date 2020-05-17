package utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class Constantes {
public static void mensaje(String titulo,String msj,Severity facesMessage){
		
		FacesContext.getCurrentInstance().addMessage(
				null, 
				new FacesMessage(
								facesMessage,
								titulo,
								msj
								)
			);
	}
public static void url(String direccion) throws IOException{
	FacesContext.getCurrentInstance().getExternalContext().redirect(direccion);	
}

}
