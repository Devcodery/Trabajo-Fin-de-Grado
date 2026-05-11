package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String opcion = request.getParameter("opcion");
		int idUsuario = Integer.valueOf(request.getParameter("idUsuario"));
		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();
		ConsultaDAO consultaDAO = new ConsultaDAO(conexion);
		
		if(opcion.equalsIgnoreCase("gestionConsultas")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
