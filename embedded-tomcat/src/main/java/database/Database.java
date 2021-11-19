package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	private static final String URL = "jdbc:sqlite:/sqlite/balsa.sqlite";

	public Connection connect() {
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}