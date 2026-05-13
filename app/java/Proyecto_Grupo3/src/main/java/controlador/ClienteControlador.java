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
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class ClienteControlador
 */
@WebServlet("/ClienteControlador")
public class ClienteControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String opcion = request.getParameter("opcion");

		HttpSession session = request.getSession();
		
		if(request.getParameter("idUsuario") != null) {
			session.setAttribute("idUsuario", Integer.valueOf(request.getParameter("idUsuario")));

		}
		int idUsuario = (int) session.getAttribute("idUsuario");
		
		if(opcion.equalsIgnoreCase("python")) {

			request.getRequestDispatcher("/vistas/portalCliente.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("verConsultas")) {
			String cookies = request.getHeader("Cookie");
			HttpClient cliente = HttpClient.newHttpClient();
			
			HttpRequest peticion = HttpRequest.newBuilder()
									.uri(URI.create("http://10.0.0.103:8383/usuario/" + idUsuario))
									.header("Cookie", cookies != null ? cookies : "")
									.GET()
									.build();

			String rol = "";

			try{
				HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

				String usuarioJson = respuesta.body();
				
				System.out.println(usuarioJson);

				JsonObject jsonCompleto = JsonParser.parseString(usuarioJson).getAsJsonObject();

				rol = jsonCompleto.get("rol").getAsString();
				System.out.println("ROL: " + rol);
			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}

			session.setAttribute("rol", rol);

			response.sendRedirect(request.getContextPath() + "/ConsultaControlador?opcion=gestionConsultasCliente");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
