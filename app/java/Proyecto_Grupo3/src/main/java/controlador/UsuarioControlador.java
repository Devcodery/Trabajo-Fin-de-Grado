package controlador;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

import modelo.Cliente;
import modelo.Consultor;
import modelo.Usuario;

/**
 * Servlet implementation class UsuarioControlador
 */
@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String opcion = request.getParameter("opcion");

		if(opcion.equalsIgnoreCase("gestion")){
			request.setAttribute("rolPagina", request.getParameter("rolPagina"));
			
			request.getRequestDispatcher("/vistas/gestionUsuarios.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("verusuarios")){

			String rolPagina = request.getParameter("rolPagina");
			request.setAttribute("rolPagina", rolPagina);

			ArrayList<Usuario> usuarios = new ArrayList<>();

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

					if (rolPagina.equalsIgnoreCase(jsonObject.get("rol").getAsString()) && rolPagina.equalsIgnoreCase("cliente")) {
						usuarios.add(new Cliente(jsonObject.get("id_usuario").getAsInt(), 
												jsonObject.get("nombre").getAsString(), 
												jsonObject.get("apellidos").getAsString(), 
												jsonObject.get("direccion").getAsString(), 
												jsonObject.get("correo").getAsString()));

					}else if(rolPagina.equalsIgnoreCase(jsonObject.get("rol").getAsString()) && rolPagina.equalsIgnoreCase("consultor")) {
						usuarios.add(new Consultor(jsonObject.get("id_usuario").getAsInt(), 
												jsonObject.get("nombre").getAsString(), 
												jsonObject.get("apellidos").getAsString(), 
												jsonObject.get("direccion").getAsString(), 
												jsonObject.get("correo").getAsString(),
												jsonObject.get("id_dpto").getAsString(),
												jsonObject.get("id_sede").getAsString()));
					}
				}
				

			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}

			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("/vistas/verUsuarios.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
