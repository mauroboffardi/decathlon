package net.boffardi.decathlon.api.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing the DB connection
 * @author mauro.boffardi
 *
 */
public class DBMgr {

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:derby:decathlonDB;create=true");
	}
	

}
