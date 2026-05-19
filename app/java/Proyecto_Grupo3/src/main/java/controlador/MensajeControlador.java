package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexionBBDD.ConexionBBDD;
import dao.MensajeDAO;
import modelo.Mensaje;

/**
 * Servlet implementation class MensajeControlador
 */
@WebServlet("/MensajeControlador")
public class MensajeControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MensajeControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		String opcion = request.getParameter("opcion");
		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();
		MensajeDAO mensajeDAO = new MensajeDAO(conexion);
		
		if(opcion.equalsIgnoreCase("listarMensajes")) {
			String idUsuarioStr = (String)session.getAttribute("idUsuario");
			String idConsultaStr = (String)session.getAttribute("idConsulta");
			ArrayList <Mensaje>	mensajes = mensajeDAO.readMensajesUsuario(Integer.valueOf(idUsuarioStr), Integer.valueOf(idConsultaStr));
			request.setAttribute("mensajes", mensajes);
			request.getRequestDispatcher("/vistas/gestionMensajes.jsp").forward(request, response);
		}
		conexion.cerrarConexion();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		HttpSession session = request.getSession();
		
		String opcion = request.getParameter("opcion");
		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();
		MensajeDAO mensajeDAO = new MensajeDAO(conexion);
		
		if(opcion.equalsIgnoreCase("enviarMensaje")) {
			String descripcion = String.valueOf(request.getParameter("cuerpoMensaje"));
			String asunto = String.valueOf(request.getParameter("asunto"));
			String idConsultaStr = (String)session.getAttribute("idConsulta");
			int idConsulta = Integer.valueOf(idConsultaStr);
			String idUsuarioStr = (String)session.getAttribute("idUsuario");
			int idUsuario = Integer.valueOf(idUsuarioStr);
			mensajeDAO.create(idConsulta, idUsuario, descripcion, asunto);	
			request.getRequestDispatcher("/vistas/gestionMensajes.jsp").forward(request, response);
		}
		conexion.cerrarConexion();
	}

}
