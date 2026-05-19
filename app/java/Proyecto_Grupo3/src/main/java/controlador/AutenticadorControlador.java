package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AutenticadorControlador
 */
@WebServlet("/AutenticadorControlador")
public class AutenticadorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticadorControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession();

		String userIdHeader = request.getHeader("X-User-Id");
		String userNameHeader = request.getHeader("X-Username");
		String roleHeader = request.getHeader("X-Role");

		if (userIdHeader == null || userIdHeader.isBlank()) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Usuario no autenticado");
			return;
		}
		
		if (session.getAttribute("idUsuario") == null || session.getAttribute("idUsuario").equals("")) {
			session.setAttribute("idUsuario", userIdHeader);
			session.setAttribute("nombreUsuario", userNameHeader);
			session.setAttribute("rol", roleHeader);
		}

		if(roleHeader.equalsIgnoreCase("admin")) {
			response.sendRedirect(request.getContextPath() + "/AdministradorControlador?opcion=logueado");
		}else if(roleHeader.equalsIgnoreCase("cliente")) {
			response.sendRedirect(request.getContextPath() + "/ClienteControlador?opcion=logueado");
		}else if(roleHeader.equalsIgnoreCase("consultor")) {
			response.sendRedirect(request.getContextPath() + "/ConsultorControlador?opcion=logueado");
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Rol de usuario no reconocido");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
