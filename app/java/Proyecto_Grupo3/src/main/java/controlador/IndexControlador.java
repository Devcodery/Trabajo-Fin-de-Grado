package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionBBDD.ConexionBBDD;
import dao.ServicioDAO;
import modelo.Servicio;

/**
 * Servlet implementation class IndexControlador
 */
@WebServlet("/IndexControlador")
public class IndexControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ArrayList<Servicio> servicios = new ArrayList<>();

		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();

		ServicioDAO servicioDAO = new ServicioDAO(conexion);

		servicios = servicioDAO.readAll();

		for (int i = 0; i < servicios.size(); i++) {
			if(!servicios.get(i).isEstado()) {
				servicios.remove(i);
			}
		}

		request.setAttribute("servicios", servicios);

		request.getRequestDispatcher("/vistas/index.jsp").forward(request, response);

		conexion.cerrarConexion();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
