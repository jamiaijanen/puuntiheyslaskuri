package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.JDBCBalsaItemDao;
import model.BalsaItem;

@WebServlet("/list")
public class DeleteServlet extends HttpServlet {

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		JDBCBalsaItemDao tietokanta = new JDBCBalsaItemDao();
		int poistettavaTuote = Integer.parseInt(id);

		BalsaItem tuote = tietokanta.getItem(poistettavaTuote);
		tietokanta.removeItem(tuote);

		String json = new Gson().toJson(tuote);

		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().println(json);
	}
}