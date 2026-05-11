package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionBBDD.ConexionBBDD;
import dao.ConsultaDAO;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String opcion = request.getParameter("opcion");
		ConexionBBDD conexion = new ConexionBBDD();
		conexion.conectarBDDotenv();		
		ServicioDAO servicioDAO = new ServicioDAO(conexion);
		ConsultaDAO consultaDAO = new ConsultaDAO(conexion);
		
		if(opcion.equalsIgnoreCase("listarServicios")) {
			ArrayList<Servicio> servicios = servicioDAO.readAll();
			request.setAttribute("servicios", servicios);
			request.setAttribute("funcion", "verServicio");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);			
		} else if(opcion.equalsIgnoreCase("crearServicio")){
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("http://consultoriatech.php.es/formularios/formularioServicio.php");
	        requestDispatcher.forward(request, response);			
		} else if(opcion.equalsIgnoreCase("modificarServicio")) {
			ArrayList<Servicio> servicios = servicioDAO.readAll();
			request.setAttribute("servicios", servicios);
			request.setAttribute("funcion", "modificarServicio");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);
		} else if(opcion.equalsIgnoreCase("listarServiciosActivos")) {
			ArrayList<Servicio> serviciosActivos = servicioDAO.readActivos();
			request.setAttribute("serviciosActivos", serviciosActivos);
			request.setAttribute("funcion", "listarServicioActivos");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);
		} else if(opcion.equalsIgnoreCase("listarServiciosInactivos")) {
			ArrayList<Servicio> serviciosInactivos = servicioDAO.readInactivos();
			request.setAttribute("serviciosInactivos", serviciosInactivos);
			request.setAttribute("funcion", "listarServicioInactivos");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);
		} else if(opcion.equalsIgnoreCase("eliminarServicio")) {
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			servicioDAO.borrarServicio(idServicio);
			consultaDAO.borrarServicioConsulta(idServicio);
			ArrayList<Servicio> serviciosInactivos = servicioDAO.readInactivos();
			request.setAttribute("serviciosInactivos", serviciosInactivos);
			request.setAttribute("funcion", "listarServicioInactivos");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);
		} else if(opcion.equalsIgnoreCase("verServicio")) {
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			Servicio servicio = servicioDAO.read(idServicio);
			System.out.println(servicio);
			if(servicio != null) {
				request.setAttribute("servicio", servicio);
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/verServicio.jsp");
	        requestDispatcher.forward(request, response);		
		} else if(opcion.equalsIgnoreCase("modificarServicioPHP")) {
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("http://consultoriatech.php.es/formularios/modificarServicio.php?idServicio="+idServicio);
	        requestDispatcher.forward(request, response);	
		} else if(opcion.equalsIgnoreCase("desactivarServicio")) {
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			servicioDAO.desactivarServicio(idServicio);
			ArrayList<Servicio> serviciosActivos = servicioDAO.readActivos();
			request.setAttribute("serviciosActivos", serviciosActivos);
			request.setAttribute("funcion", "listarServicioActivos");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/servicios.jsp");
	        requestDispatcher.forward(request, response);
		}
		
		conexion.cerrarConexion();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
