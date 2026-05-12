package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

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
public class GestionServicioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionServicioControlador() {
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
		ConsultaDAO consultaDAO = new ConsultaDAO(conexion);
		
		if(opcion.equalsIgnoreCase("gestionServicios")) {
			// Redirige a la pagina de gestion de servicios donde se pueden ejecutar varias opciones
			// Entrada a Gestion de Servicios

			request.getRequestDispatcher("/vistas/gestionDeServicios.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("crearServicio")){
			// Redirige a la pagina de creacion de servicio en PHP
			// Crear Servicio

			response.sendRedirect("http://consultoriatech.php.es/formularios/formularioServicio.php");			
		
		} else if(opcion.equalsIgnoreCase("listarServicios")) {
			// Sirve para redirigir a la paginas que necesitan lista servicios para mostrar, como:

			// Servicios
			// Modificar
			// Desactivar
			// Eliminar


			boolean modificar = false;
			modificar = Boolean.valueOf(request.getParameter("modificar"));
			
			if(modificar){
				// Esta seccion aqui nos redigire en caso de que queramos modificar un
				// servicio en especifico
				int idServicio = Integer.valueOf(request.getParameter("idServicio"));
				response.sendRedirect("http://consultoriatech.php.es/formularios/modificarServicio.php?idServicio="+idServicio);
			}
			String categoria = request.getParameter("categoria");
    		Boolean estado = Boolean.valueOf(request.getParameter("estado"));
   		 	String sede = request.getParameter("sede");
			Date fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
			Date fechaFin = Date.valueOf(request.getParameter("fechaFin"));
			
			ArrayList<Servicio> servicios = servicioDAO.filtrar(categoria, estado, sede, fechaInicio, fechaFin);
			System.out.println(servicios);

			request.setAttribute("servicios", servicios);
			
			String funcion = request.getParameter("funcion");

			if(funcion.equalsIgnoreCase("verservicios")){
				request.setAttribute("funcion", "servicio");
				request.getRequestDispatcher("/vistas/servicios.jsp").forward(request, response);
			}else if (funcion.equalsIgnoreCase("modificarservicios")) {
				request.setAttribute("funcion", "modificar");
				request.getRequestDispatcher("/vistas/servicios.jsp").forward(request, response);
			}else if(funcion.equalsIgnoreCase("eliminarservicios")){
				request.setAttribute("funcion", "eliminar");
				request.getRequestDispatcher("/vistas/servicios.jsp").forward(request, response);
			}else if(funcion.equalsIgnoreCase("desactivarservicios")){
				request.setAttribute("funcion", "desactivar");
				request.getRequestDispatcher("/vistas/servicios.jsp").forward(request, response);
			}

			
		} else if(opcion.equalsIgnoreCase("eliminarServicio")) {
			// Sirve para eliminar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			
			servicioDAO.borrarServicio(idServicio);
			consultaDAO.borrarServicioConsulta(idServicio);

			response.sendRedirect(request.getContextPath() + "/ServicioControlador?opcion=listarServicios&funcion=eliminarServicios");
		} else if(opcion.equalsIgnoreCase("desactivarServicio")) {
			// Sirve para desactivar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));

			servicioDAO.cambiarEstadoServicio(idServicio, false);

			response.sendRedirect(request.getContextPath() + "/ServicioControlador?opcion=listarServicios&funcion=desactivarServicios");
		} else if(opcion.equalsIgnoreCase("activarServicio")) {
			// Sirve para desactivar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));

			servicioDAO.cambiarEstadoServicio(idServicio, true);

			response.sendRedirect(request.getContextPath() + "/ServicioControlador?opcion=listarServicios&funcion=desactivarServicios");
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
