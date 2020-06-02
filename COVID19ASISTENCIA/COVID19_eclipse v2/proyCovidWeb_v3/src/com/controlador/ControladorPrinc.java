package com.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AdministradorDAO;
import com.dao.PacienteDAO;
import com.dao.SintomasDAO;
import com.entidad.Administrador;
import com.entidad.Paciente;
import com.entidad.Sintomas;

/**
 * Servlet implementation class ControladorPrinc
 */
@WebServlet("/ControladorPrinc")
public class ControladorPrinc extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Administrador adm = new Administrador();
	AdministradorDAO adao = new AdministradorDAO();
	Paciente pac = new Paciente();
	PacienteDAO pdao = new PacienteDAO();
	Sintomas sin = new Sintomas();
	SintomasDAO sdao = new SintomasDAO();
	
	int idAdm;
	int idPac;
	int idSin;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorPrinc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		if(menu.equals("Administradores")) {
			
			switch (accion) {
			case "Listar":
				List lista = adao.listar();
				request.setAttribute("administradores", lista);
				break;
			case "Agregar":
				String nomb = request.getParameter("txtnombres");
				String tel = request.getParameter("txtfono");
				String user = request.getParameter("txtuser");
				String pass = request.getParameter("txtpass");
				adm.setNomapeAdmin(nomb);
				adm.setTelAdmin(tel);
				adm.setUserAdmin(user);
				adm.setPassAdmin(pass);
				adao.agregar(adm);
				
				request.getRequestDispatcher("ControladorPrinc?menu=Administradores&accion=Listar").forward(request, response);
				break;
			
			case "Editar":
				idAdm = Integer.parseInt(request.getParameter("id"));
				Administrador admi = adao.listarId(idAdm);
				request.setAttribute("administrador", admi);
				request.getRequestDispatcher("ControladorPrinc?menu=Administradores&accion=Listar").forward(request, response);
				break;	
				
			case "Actualizar":
				String nomb1 = request.getParameter("txtnombres");
				String tel1 = request.getParameter("txtfono");
				String user1 = request.getParameter("txtuser");
				String pass1 = request.getParameter("txtpass");
				adm.setNomapeAdmin(nomb1);
				adm.setTelAdmin(tel1);
				adm.setUserAdmin(user1);
				adm.setPassAdmin(pass1);
				adm.setIdAdmin(idAdm);
				adao.actualizar(adm);
				request.getRequestDispatcher("ControladorPrinc?menu=Administradores&accion=Listar").forward(request, response);
				break;
			case "Eliminar":
				idAdm = Integer.parseInt(request.getParameter("id"));
				adao.eliminar(idAdm);
				request.getRequestDispatcher("ControladorPrinc?menu=Administradores&accion=Listar").forward(request, response);	
				break;

			default:
				throw new AssertionError();
			}
			
			
			
			
		
			request.getRequestDispatcher("Administradores.jsp").forward(request, response);
		}
		
		
		
		
		if(menu.equals("SituacionMedica")) {
			
			switch (accion) {
			case "Listar":
				List lista = sdao.listar();
				request.setAttribute("sintomas", lista);
				break;
			default:
				throw new AssertionError();	
			}
			request.getRequestDispatcher("SituacionMedica.jsp").forward(request, response);
		}
		
		
		
		
		
		if(menu.equals("Pacientes")) {
			
			switch (accion) {
			case "Listar":
				List lista = pdao.listar();
				request.setAttribute("pacientes", lista);
				break;
				
			case "Eliminar":
				idPac = Integer.parseInt(request.getParameter("id"));
				pdao.Eliminar(idPac);
				request.getRequestDispatcher("ControladorPrinc?menu=Pacientes&accion=Listar").forward(request, response);
				break;	

			default:
				throw new AssertionError();
			}
			request.getRequestDispatcher("Pacientes.jsp").forward(request, response);
		}
		
		
		
		/*if(menu.equals("Principal")) {
			request.getRequestDispatcher("Principal.jsp").forward(request, response);
		}*/
		
	}

}
