package servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.decimal4j.util.DoubleRounder;

import com.google.gson.Gson;

import database.JDBCBalsaItemDao;
import model.BalsaItem;

@WebServlet("/prosivu")
public class ProServlet extends HttpServlet {

	String tulos = "";
	String korkeus = "";
	String pituus = "";
	String leveys = "";
	String paino = "";
	String grain = "";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		JDBCBalsaItemDao BalsaItemDao = new JDBCBalsaItemDao();

		req.setAttribute("tulos", tulos);
		req.setAttribute("korkeus", korkeus);
		req.setAttribute("pituus", pituus);
		req.setAttribute("leveys", leveys);
		req.setAttribute("paino", paino);
		req.setAttribute("grain", grain);
		tulos = "";
		korkeus = "";
		pituus = "";
		leveys = "";
		paino = "";
		grain = "";

		req.setAttribute("tuotteet", BalsaItemDao.getAllItems());

		req.getRequestDispatcher("/pro.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		DecimalFormat muotoilu = new DecimalFormat("#.##");

		try {
			String luku1 = req.getParameter("korkeus");
			double korkeus1 = Double.parseDouble(luku1);

			String luku2 = req.getParameter("pituus");
			double pituus1 = Double.parseDouble(luku2);

			String luku3 = req.getParameter("leveys");
			double leveys1 = Double.parseDouble(luku3);

			String luku4 = req.getParameter("paino");
			double paino1 = Double.parseDouble(luku4);

			String grain1 = req.getParameter("grain");

			double tiheys1 = (paino1 / 1000) / ((korkeus1 / 1000) * (pituus1 / 1000) * (leveys1 / 1000));

			tulos = muotoilu.format(tiheys1);
			korkeus = req.getParameter("korkeus");
			pituus = req.getParameter("pituus");
			leveys = req.getParameter("leveys");
			paino = req.getParameter("paino");
			grain = req.getParameter("grain");

			JDBCBalsaItemDao tietokanta = new JDBCBalsaItemDao();
			BalsaItem uusiLevy = new BalsaItem(DoubleRounder.round(tiheys1, 2), korkeus1, pituus1, leveys1, paino1,
					grain1);
			tietokanta.addItem(uusiLevy);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		resp.sendRedirect("/prosivu");
	}

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