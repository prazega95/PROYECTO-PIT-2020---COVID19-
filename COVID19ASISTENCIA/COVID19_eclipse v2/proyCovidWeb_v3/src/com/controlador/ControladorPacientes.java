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
@WebServlet("/ControladorPacientes")
public class ControladorPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	Administrador adm = new Administrador();
	AdministradorDAO adao = new AdministradorDAO();
	Paciente pac = new Paciente();
	PacienteDAO pdao = new PacienteDAO();
	Sintomas sin = new Sintomas();
	SintomasDAO sdao = new SintomasDAO();


	int idPac;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorPacientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		if(menu.equals("Paciente")) {
			
			switch (accion) {
			case "Listar":
				List lista = pdao.listar();
				request.setAttribute("pacientes", lista);
				break;
			case "Agregar":
				String nombPac = request.getParameter("txtnombres");
				String apePac = request.getParameter("txtapellido");
				String docPac = request.getParameter("txtdocumento");
				String telPac = request.getParameter("txtfono");
				String userPac = request.getParameter("txtuser");
				String passPac = request.getParameter("txtpass");
				pac.setNomPac(nombPac);
				pac.setApePac(apePac);
				pac.setDniPac(docPac);
				pac.setFonoPac(telPac);
				pac.setUserPac(userPac);
				pac.setPassPac(passPac);
				pdao.agregar(pac);
				
				request.getRequestDispatcher("ControladorPacientes?menu=Paciente&accion=Listar").forward(request, response);
				break;
			
			case "Editar":
				idPac = Integer.parseInt(request.getParameter("id"));
				Paciente paci = pdao.listarId(idPac);
				request.setAttribute("paciente", paci);
				request.getRequestDispatcher("ControladorPacientes?menu=Paciente&accion=Listar").forward(request, response);
				break;	
				
			case "Actualizar":
				String nombPac1 = request.getParameter("txtnombres");
				String apePac1 = request.getParameter("txtapellido");
				String docPac1 = request.getParameter("txtdocumento");
				String telPac1 = request.getParameter("txtfono");
				String userPac1 = request.getParameter("txtuser");
				String passPac1 = request.getParameter("txtpass");
				pac.setNomPac(nombPac1);
				pac.setApePac(apePac1);
				pac.setDniPac(docPac1);
				pac.setFonoPac(telPac1);
				pac.setUserPac(userPac1);
				pac.setPassPac(passPac1);
				pac.setIdPac(idPac);
				pdao.actualizar(pac);
				request.getRequestDispatcher("ControladorPacientes?menu=Paciente&accion=Listar").forward(request, response);
				break;
			case "Eliminar":
				idPac = Integer.parseInt(request.getParameter("id"));
				pdao.Eliminar(idPac);
				request.getRequestDispatcher("ControladorPacientes?menu=Paciente&accion=Listar").forward(request, response);	
				break;

			default:
				throw new AssertionError();
			}
			
			request.getRequestDispatcher("Pacientes.jsp").forward(request, response);
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
				request.getRequestDispatcher("ControladorPacientes?menu=Paciente&accion=Listar").forward(request, response);
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
