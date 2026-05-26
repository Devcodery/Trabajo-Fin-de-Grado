package controlador;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import conexionBBDD.ConexionBBDD;
import dao.ConsultaDAO;
import dao.MensajeDAO;
import dao.ServicioDAO;
import modelo.Consulta;
import modelo.Sede;
import modelo.Servicio;

/**
 * Servlet implementation class ServicioControlador
 */
@WebServlet("/GestionServicioControlador")
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
		MensajeDAO mensajeDAO = new MensajeDAO(conexion);
		
		if(opcion.equalsIgnoreCase("gestionServicios")) {
			// Redirige a la pagina de gestion de servicios donde se pueden ejecutar varias opciones
			// Entrada a Gestion de Servicios

			request.getRequestDispatcher("/vistas/gestionDeServicios.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("crearServicio")){
			// Redirige a la pagina de creacion de servicio en PHP
			// Crear Servicio

			response.sendRedirect("/formularios/formularioServicio.php");			
		
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
				response.sendRedirect("/formularios/modificarServicio.php?idServicio="+idServicio);
			}
			
    		Boolean estado = null;
   		 	String sede = request.getParameter("sede");
   		 	Date fechaInicio = null;
   		 	Date fechaFin = null;
   		 	
   		 	if(request.getParameter("fechaInicio") != null) {
   		 		fechaInicio = Date.valueOf(request.getParameter("fechaInicio"));
   		 	}
   		 	
   		 	if(request.getParameter("fechaFin") != null) {
   		 		fechaFin = Date.valueOf(request.getParameter("fechaFin"));
   		 	} 
   		 	
   		 	if(request.getParameter("estado") != null) {
				if(request.getParameter("estado").equals("activo")){
					estado = true;
				}else if(request.getParameter("estado").equals("inactivo")){
					estado = false;
				}
   		 	}
			
			ArrayList<Servicio> servicios = servicioDAO.filtrar(estado, sede, fechaInicio, fechaFin);
			request.setAttribute("servicios", servicios);

			HttpClient cliente = HttpClient.newHttpClient();
			ArrayList<Sede> sedes = new ArrayList<>();
			HttpRequest peticion = HttpRequest.newBuilder()
									.uri(URI.create("http://info.empresa.dam.es:8055/items/sedes"))
									.GET()
									.build();
			
			try {
				HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());
				String respuestaString = respuesta.body();
				
				JsonObject jsonCompleto = JsonParser.parseString(respuestaString).getAsJsonObject();
				
				JsonArray jsonArray = jsonCompleto.getAsJsonArray("data");
				
				for(JsonElement j : jsonArray) {
					JsonObject jsonObj = j.getAsJsonObject();
					
					sedes.add(new Sede(jsonObj.get("id").getAsInt(), 
										jsonObj.get("nombre").getAsString(), 
										jsonObj.get("direccion").getAsString(), 
										jsonObj.get("ciudad").getAsString()));
				}
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("sedes", sedes);
			String funcion = request.getParameter("funcion");

			if(funcion.equalsIgnoreCase("servicio")) {
				request.setAttribute("funcion", "servicio");
				request.getRequestDispatcher("/vistas/mostrarServicios.jsp").forward(request, response);
			}else if (funcion.equalsIgnoreCase("modificar")) {
				request.setAttribute("funcion", "modificar");
				request.getRequestDispatcher("/vistas/mostrarServicios.jsp").forward(request, response);
			}else if(funcion.equalsIgnoreCase("eliminar")){
				request.setAttribute("funcion", "eliminar");
				request.getRequestDispatcher("/vistas/mostrarServicios.jsp").forward(request, response);
			}else if(funcion.equalsIgnoreCase("desactivar")){
				request.setAttribute("funcion", "desactivar");
				request.getRequestDispatcher("/vistas/mostrarServicios.jsp").forward(request, response);
			}

			
		} else if(opcion.equalsIgnoreCase("eliminarServicio")) {
			// Sirve para eliminar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));
			
			ArrayList<Consulta> consultas = consultaDAO.readConsultaServicio(idServicio);

			for(Consulta c : consultas) {
				mensajeDAO.borrarMensajesConsulta(c.getIdConsulta());
			}
			
			consultaDAO.borrarServicioConsulta(idServicio);
			servicioDAO.borrarServicio(idServicio);

			response.sendRedirect(request.getContextPath() + "/GestionServicioControlador?opcion=listarServicios&funcion=eliminar");
		} else if(opcion.equalsIgnoreCase("desactivarServicio")) {
			// Sirve para desactivar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));

			servicioDAO.cambiarEstadoServicio(idServicio, false);

			response.sendRedirect(request.getContextPath() + "/GestionServicioControlador?opcion=listarServicios&funcion=desactivar");
		} else if(opcion.equalsIgnoreCase("activarServicio")) {
			// Sirve para desactivar el servicio y volver a llamar el doGet para redirigir a la misma ubicacion
			int idServicio = Integer.valueOf(request.getParameter("idServicio"));

			servicioDAO.cambiarEstadoServicio(idServicio, true);

			response.sendRedirect(request.getContextPath() + "/GestionServicioControlador?opcion=listarServicios&funcion=desactivar");
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
