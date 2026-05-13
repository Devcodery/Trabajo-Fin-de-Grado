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
import dao.ConsultaDAO;
import modelo.Consulta;

/**
 * Servlet implementation class ConsultaControlador
 */
@WebServlet("/ConsultaControlador")
public class ConsultaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String opcion = request.getParameter("opcion");

		HttpSession session = request.getSession();

		String rol = (String) session.getAttribute("rol");

		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();
		ConsultaDAO consultaDAO = new ConsultaDAO(conexion);
		
		if(opcion.equalsIgnoreCase("gestionConsultasCliente")) {
			int idUsuario = (int) session.getAttribute("idUsuario");
			ArrayList<Consulta> consultas = consultaDAO.readCliente(idUsuario);

			request.setAttribute("consultasClientes", consultas);
			request.setAttribute("rol", rol);

			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("gestionConsultasConsultor")) {
			int idUsuario = (int) session.getAttribute("idUsuario");
			ArrayList<Consulta> consultas = consultaDAO.readConsultor(idUsuario);

			request.setAttribute("consultasConsultas", consultas);
			request.setAttribute("rol", rol);

			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("gestionConsultasAdmin")) {
			ArrayList<Consulta> consultas = consultaDAO.readAll();

			request.setAttribute("consultasAdmin", consultas);
			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("verConsulta")) {
			int idConsulta = Integer.valueOf(request.getParameter("idConsulta"));

			Consulta consulta = consultaDAO.read(idConsulta);
			request.setAttribute("consulta", consulta);
			
			request.getRequestDispatcher("/vistas/verConsulta.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
