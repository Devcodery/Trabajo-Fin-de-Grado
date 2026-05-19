package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



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

		HttpSession session = request.getSession();

		if(opcion.equalsIgnoreCase("logueado")) {
			request.getRequestDispatcher("/vistas/portalConsultor.jsp").forward(request, response);
		}else if(opcion.equalsIgnoreCase("verconsultas")) {
			response.sendRedirect(request.getContextPath() + "/ConsultaControlador?opcion=gestionConsultasConsultor");
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
