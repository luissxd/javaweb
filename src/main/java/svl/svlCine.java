package svl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import bean.Cine;

public class svlCine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public svlCine() {
		super();
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		dao.CineDAO CineDAO = new dao.CineDAO();

		String id = request.getParameter("id");

		if (id == null) {
			List<Cine> lstCine = CineDAO.getVerCinesList();
			session.setAttribute("id", lstCine == null ? null : "3");
			session.setAttribute("lstCine", lstCine);
		} else {
			Cine Cine = CineDAO.getCineList(id);
			session.setAttribute("id", Cine == null ? null : "4");
			session.setAttribute("Cine", Cine);
			session.setAttribute("lstCineTarifa", CineDAO.getCineTarifasList(id));
			session.setAttribute("lstCinePelicula", CineDAO.getCinePeliculasList(id));

		}

		response.sendRedirect("index.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}