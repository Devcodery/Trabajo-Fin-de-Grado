package controlador;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
import dao.ServicioDAO;
import modelo.Sede;
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

			HttpClient cliente = HttpClient.newHttpClient();
			Sede sede = null;
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
					
					if(jsonObj.get("id").getAsInt() == servicio.getSede()) {
						sede = new Sede(jsonObj.get("id").getAsInt(), 
										jsonObj.get("nombre").getAsString(), 
										jsonObj.get("direccion").getAsString(), 
										jsonObj.get("ciudad").getAsString());
					}
				}
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}

			if(servicio != null) {
				request.setAttribute("servicio", servicio);
				request.setAttribute("sede", sede);
			}

			request.getRequestDispatcher("/vistas/verServicio.jsp").forward(request, response);	
		
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
