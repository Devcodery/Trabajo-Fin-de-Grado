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
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import conexionBBDD.ConexionBBDD;
import dao.ConsultaDAO;
import dao.ServicioDAO;
import modelo.Cliente;
import modelo.Consulta;
import modelo.Consultor;
import modelo.Usuario;

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
		ServicioDAO servicioDAO = new ServicioDAO(conexion);
		
		if(opcion.equalsIgnoreCase("gestionConsultasCliente")) {
			int idUsuario = (int) session.getAttribute("idUsuario");
			ArrayList<Consulta> consultas = consultaDAO.readConsultaCliente(idUsuario);

			request.setAttribute("listaConsultas", consultas);

			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("gestionConsultasConsultor")) {
			int idUsuario = (int) session.getAttribute("idUsuario");
			ArrayList<Consulta> consultas = consultaDAO.readConsultaConsultor(idUsuario);

			request.setAttribute("listaConsultas", consultas);

			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("gestionConsultasAdmin")) {
			String idClienteStr = request.getParameter("idCliente");
			String idConsultorStr = request.getParameter("idConsultor");
			String estado = request.getParameter("estado");
			String idServicioStr = request.getParameter("idServicio");
			String fechaInicioStr = request.getParameter("fechaInicio");
			String fechaFinStr = request.getParameter("fechaFin");

			
			Integer idCliente = (idClienteStr != null && !idClienteStr.isEmpty()) ? Integer.valueOf(idClienteStr) : null;
			Integer idConsultor = (idConsultorStr != null && !idConsultorStr.isEmpty()) ? Integer.valueOf(idConsultorStr) : null;
			Integer idServicio = (idServicioStr != null && !idServicioStr.isEmpty()) ? Integer.valueOf(idServicioStr) : null;
			Date fechaInicio = (fechaInicioStr != null && !fechaInicioStr.isEmpty()) ? Date.valueOf(fechaInicioStr) : null;
			Date fechaFin = (fechaFinStr != null && !fechaFinStr.isEmpty()) ? Date.valueOf(fechaFinStr) : null;

			
			ArrayList<Consulta> consultasFiltradas = consultaDAO.filtrar(idCliente, idConsultor, estado, idServicio, fechaInicio, fechaFin);
			request.setAttribute("listaConsultas", consultasFiltradas);
			request.setAttribute("listaServicios", servicioDAO.readAll());

			ArrayList<Usuario> listaClientes = new ArrayList<>();
			ArrayList<Usuario> listaConsultores = new ArrayList<>();

			String cookies = request.getHeader("Cookie");
			HttpClient cliente = HttpClient.newHttpClient();
			
			HttpRequest peticion = HttpRequest.newBuilder()
									.uri(URI.create("http://10.0.0.103:8383/usuarios"))
									.header("Cookie", cookies != null ? cookies : "")
									.GET()
									.build();


			try{
				HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

				String usuariosJson = respuesta.body();

				JsonArray jsonCompleto = JsonParser.parseString(usuariosJson).getAsJsonArray();

				for(JsonElement jsonE : jsonCompleto){
					JsonObject jsonObject = jsonE.getAsJsonObject();

					if (jsonObject.get("rol").getAsString().equalsIgnoreCase("cliente")) {
						listaClientes.add(new Cliente(jsonObject.get("id_usuario").getAsInt(), 
												jsonObject.get("nombre").getAsString(), 
												jsonObject.get("apellidos").getAsString(), 
												jsonObject.get("direccion").getAsString(), 
												jsonObject.get("correo").getAsString()));

					}else if(jsonObject.get("rol").getAsString().equalsIgnoreCase("consultor")) {
						listaConsultores.add(new Consultor(jsonObject.get("id_usuario").getAsInt(), 
												jsonObject.get("nombre").getAsString(), 
												jsonObject.get("apellidos").getAsString(), 
												jsonObject.get("direccion").getAsString(), 
												jsonObject.get("correo").getAsString(),
												jsonObject.get("id_dpto").getAsInt(),
												jsonObject.get("id_sede").getAsInt()));
					}
				}
				

			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}

			request.setAttribute("listaClientes", listaClientes);
			request.setAttribute("listaConsultores", listaConsultores);

			request.getRequestDispatcher("/vistas/gestionDeConsultas.jsp").forward(request, response);

		} else if(opcion.equalsIgnoreCase("verConsulta")) {
			int idConsulta = Integer.valueOf(request.getParameter("idConsulta"));
			session.setAttribute("idConsulta", idConsulta);

			Consulta consulta = consultaDAO.read(idConsulta);
			request.setAttribute("consulta", consulta);

			request.getRequestDispatcher("/vistas/verConsulta.jsp").forward(request, response);			
		}else if(opcion.equalsIgnoreCase("verMensajes")){
			response.sendRedirect(request.getContextPath() + "/MensajeControlador?opcion=verMensajes");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
