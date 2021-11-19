package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kirjautuminen")
public class KirjautuminenServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("/kirjautuminen.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		int oikeaSalasana = 22;
		String syote = req.getParameter("salasana");
		int syotettySalasana = Integer.parseInt(syote);

		if (syotettySalasana == oikeaSalasana) {
			req.getRequestDispatcher("/pro.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/kirjautuminen.jsp").forward(req, resp);
		}
	}
}