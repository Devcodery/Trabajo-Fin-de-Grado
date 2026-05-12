package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionBBDD.ConexionBBDD;
import dao.ServicioDAO;
import modelo.Servicio;

/**
 * Servlet implementation class ServicioControlador
 */
@WebServlet("/ServicioControlador")
public class ServicioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicioControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion = request.getParameter("opcion");

		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();		
		
		ServicioDAO servicioDAO = new ServicioDAO(conexion);
		
		if(opcion.equalsIgnoreCase("verServicio")) {
			//Muestra un servicio en especifico
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			
			Servicio servicio = servicioDAO.read(idServicio);
			System.out.println(servicio);

			if(servicio != null) {
				request.setAttribute("servicio", servicio);
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/verServicio.jsp");
	        requestDispatcher.forward(request, response);		
		
		}

		conexion.cerrarConexion();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
