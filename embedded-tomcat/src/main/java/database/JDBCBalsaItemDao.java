package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.BalsaItem;

public class JDBCBalsaItemDao implements BalsaItemDao {
	List<BalsaItem> uusiLevy = new ArrayList<BalsaItem>();

	public List<BalsaItem> getAllItems() {
		try {
			String URL = "jdbc:sqlite:/sqlite/balsa.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection yhteys = DriverManager.getConnection(URL);
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Balsalevy");
			ResultSet tulokset = kysely.executeQuery();

			while (tulokset.next()) {
				int id = tulokset.getInt("id");
				double tiheys = tulokset.getDouble("tiheys");
				double korkeus = tulokset.getDouble("korkeus");
				double pituus = tulokset.getDouble("pituus");
				double leveys = tulokset.getDouble("leveys");
				double paino = tulokset.getDouble("paino");
				String grain = tulokset.getString("grain");

				BalsaItem rivi = new BalsaItem(id, tiheys, korkeus, pituus, leveys, paino, grain);

				if (!uusiLevy.contains(rivi)) {
					uusiLevy.add(rivi);
				}
			}
			tulokset.close();
			kysely.close();
			yhteys.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return uusiLevy;
	}

	@Override
	public BalsaItem getItem(int id) {
		try {
			String URL = "jdbc:sqlite:/sqlite/balsa.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection yhteys = DriverManager.getConnection(URL);
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Balsalevy");
			ResultSet tulokset = kysely.executeQuery();

			while (tulokset.next()) {
				int haettavaId = tulokset.getInt("id");

				if (haettavaId == id) {
					double tiheys = tulokset.getDouble("tiheys");
					double korkeus = tulokset.getDouble("korkeus");
					double pituus = tulokset.getDouble("pituus");
					double leveys = tulokset.getDouble("leveys");
					double paino = tulokset.getDouble("paino");
					String grain = tulokset.getString("grain");
					BalsaItem rivi = new BalsaItem(haettavaId, tiheys, korkeus, pituus, leveys, paino, grain);

					tulokset.close();
					kysely.close();
					yhteys.close();

					return rivi;
				}
			}

		} catch (Exception ex) {
			return null;
		}
		return null;
	}

	@Override
	public boolean addItem(BalsaItem uusiTuote) {
		int id = 1;
		for (BalsaItem idGenerointi : this.getAllItems()) {
			if (idGenerointi.getId() >= id) {
				id++;
			}
		}

		try {
			String URL = "jdbc:sqlite:/sqlite/balsa.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection yhteys = DriverManager.getConnection(URL);
			PreparedStatement insertKysely = yhteys
					.prepareStatement("INSERT INTO Balsalevy VALUES(?, ?, ?, ?, ?, ?, ?)");
			insertKysely.setInt(1, id);
			insertKysely.setDouble(2, uusiTuote.getTiheys());
			insertKysely.setDouble(3, uusiTuote.getKorkeus());
			insertKysely.setDouble(4, uusiTuote.getLeveys());
			insertKysely.setDouble(5, uusiTuote.getPaino());
			insertKysely.setDouble(6, uusiTuote.getPituus());
			insertKysely.setString(7, uusiTuote.getGrain());

			insertKysely.executeUpdate();

			insertKysely.close();
			yhteys.close();

		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean removeItem(BalsaItem item) {
		int poistettavaTuote = item.getId();
		int summa = 0;
		try {
			String URL = "jdbc:sqlite:/sqlite/balsa.sqlite";
			Class.forName("org.sqlite.JDBC");
			Connection yhteys = DriverManager.getConnection(URL);
			PreparedStatement Kysely = yhteys.prepareStatement("SELECT * FROM Balsalevy");
			ResultSet Tulokset = Kysely.executeQuery();

			while (Tulokset.next()) {
				int tuoteID = Tulokset.getInt("id");

				if (tuoteID == poistettavaTuote) {
					summa = summa + 1;
				}
			}

			Tulokset.close();
			Kysely.close();
			yhteys.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		if (summa <= 0) {
			return false;
		} else {
			Database database = new Database();
			Connection connection = database.connect();

			try {
				PreparedStatement removeKysely = connection.prepareStatement("DELETE FROM Balsalevy WHERE id = (?)");
				removeKysely.setInt(1, poistettavaTuote);
				removeKysely.executeUpdate();
				uusiLevy.removeAll(uusiLevy);

				removeKysely.close();
				connection.close();

			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
			return true;
		}
	}
}