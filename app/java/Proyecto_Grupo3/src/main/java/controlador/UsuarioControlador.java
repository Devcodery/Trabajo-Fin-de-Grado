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
			request.setAttribute("rol", request.getParameter("rol"));
			
			request.getRequestDispatcher("/vistas/gestionUsuarios.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("verusuarios")){

			String rol = request.getParameter("rol");
			request.setAttribute("rol", rol);

			ArrayList<Usuario> usuarios = new ArrayList<>();

			HttpClient cliente = HttpClient.newHttpClient();
			
			HttpRequest peticion = HttpRequest.newBuilder()
									.uri(URI.create("/usuarios"))
									.GET()
									.build();


			try{
				HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

				String usuariosJson = respuesta.body();

				JsonArray jsonCompleto = JsonParser.parseString(usuariosJson).getAsJsonArray();

				for(JsonElement jsonE : jsonCompleto){
					JsonObject jsonObject = jsonE.getAsJsonObject();

					if (rol.equalsIgnoreCase(jsonObject.get("5").getAsString()) && rol.equalsIgnoreCase("cliente")) {
						usuarios.add(new Cliente(jsonObject.get("0").getAsInt(), 
												jsonObject.get("1").getAsString(), 
												jsonObject.get("2").getAsString(), 
												jsonObject.get("6").getAsString(), 
												jsonObject.get("3").getAsString()));

					}else if(rol.equalsIgnoreCase(jsonObject.get("5").getAsString()) && rol.equalsIgnoreCase("consultor")) {
						usuarios.add(new Consultor(jsonObject.get("0").getAsInt(), 
												jsonObject.get("1").getAsString(), 
												jsonObject.get("2").getAsString(), 
												jsonObject.get("6").getAsString(), 
												jsonObject.get("3").getAsString(),
												jsonObject.get("7").getAsString(),
												jsonObject.get("8").getAsString()));
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
