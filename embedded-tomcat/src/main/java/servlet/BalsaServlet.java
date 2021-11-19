package servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/balsalaskuri")
public class BalsaServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String tulos = "";
		String toinenTulos = "";
		DecimalFormat muotoilu = new DecimalFormat("#.##");

		try {
			String korkeus = req.getParameter("korkeus");
			double luku1 = Double.parseDouble(korkeus) / 1000.0;

			String pituus = req.getParameter("pituus");
			double luku2 = Double.parseDouble(pituus) / 1000.0;

			String leveys = req.getParameter("leveys");
			double luku3 = Double.parseDouble(leveys) / 1000.0;

			String paino = req.getParameter("paino");
			double luku4 = Double.parseDouble(paino) / 1000.0;

			tulos = muotoilu.format(luku4 / (luku1 * luku2 * luku3) / 1.00);
			toinenTulos = muotoilu.format((luku4 / (luku1 * luku2 * luku3)) * 0.062428);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		req.setAttribute("tulos", tulos.replace(",", "."));
		req.setAttribute("toinenTulos", toinenTulos.replace(",", "."));
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}