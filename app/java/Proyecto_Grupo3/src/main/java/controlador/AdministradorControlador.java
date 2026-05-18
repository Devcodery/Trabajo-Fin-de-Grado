package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdministradorControlador
 */
@WebServlet("/AdministradorControlador")
public class AdministradorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministradorControlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String opcion = request.getParameter("opcion");

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

		if(opcion.equalsIgnoreCase("logueado")) {
			request.getRequestDispatcher("/vistas/portalAdministrador.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("logout")) {
			session.invalidate();
			response.sendRedirect("/logout");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
