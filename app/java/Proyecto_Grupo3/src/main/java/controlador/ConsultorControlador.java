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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class ConsultorControlador
 */
@WebServlet("/ConsultorControlador")
public class ConsultorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultorControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String opcion = request.getParameter("opcion");
		int idUsuario = Integer.valueOf(request.getParameter("idUsuario"));
		
		if(opcion.equalsIgnoreCase("python")) {
			request.setAttribute("id", idUsuario);

			request.getRequestDispatcher("/vistas/portalCliente.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("verconsultas")) {
			HttpClient cliente = HttpClient.newHttpClient();
			
			HttpRequest peticion = HttpRequest.newBuilder()
									.uri(URI.create("/usuario/" + idUsuario))
									.GET()
									.build();

			String rol = "";

			try{
				HttpResponse<String> respuesta = cliente.send(peticion, HttpResponse.BodyHandlers.ofString());

				String usuarioJson = respuesta.body();

				JsonObject jsonCompleto = JsonParser.parseString(usuarioJson).getAsJsonObject();

				rol = jsonCompleto.get("rol").getAsString();

			}catch (IOException e) {
				e.printStackTrace();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}


			request.setAttribute("rol", rol);

			request.getRequestDispatcher("/vistas/consultas.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
