package net.boffardi.decathlon.api.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.units.Centimeters;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;

/**
 * Create, instantiates and deletes objects of type "Performance" from the DB layer.
 * It is the only class that can create/manage the PerformanceImpl object.
 * 
 * @author mauro.boffardi
 *
 */
public class PerformanceDAO {
	// definition of the logger
	private static final Logger log = Logger.getLogger(PerformanceDAO.class.getName());

	/**
	 * Creates necessary tables and indexes for tables of Performance
	 */
	public static void createTables() throws SQLException {

		Connection conn = DBMgr.getConnection(); 
		Statement stmt = conn.createStatement();

		
		// ALL results (m, cm, sec) are stored as DECIMAL for simplicity.
		String createPerformance = "CREATE TABLE performance ( " + 
				"ID VARCHAR(36) NOT NULL, " +
				"firstname VARCHAR(28), " +
				"lastname VARCHAR(28), " +
				"discipline VARCHAR(1), " +
				"sprint DECIMAL(7,3), " +
				"long_jump DECIMAL(7,3), " +
				"shot_put DECIMAL(7,3), " +
				"high_jump DECIMAL(7,3), " +
				"four_hundreds DECIMAL(7,3), " +
				"hurdles DECIMAL(7,3), " +
				"discus DECIMAL(7,3), " +
				"pole_vault DECIMAL(7,3), " +
				"javelin DECIMAL(7,3), " +
				"m1500sprint DECIMAL(7,3), " +
				"score INTEGER, " +
				"completed BOOLEAN, " +
				" PRIMARY KEY (ID))";

		stmt.executeUpdate(createPerformance);
	}

	/**
	 * Create a new "Performance" object with the minimum required data, and auto generated UUID.
	 * All events results are at default "0.0".
	 * 
	 * @param firstName
	 * @param lastName
	 * @param discipline
	 * @return The instantiated Performance object
	 */
	public static Performance createPerformance(String firstName, String lastName, Discipline discipline) {
		PerformanceImpl perf = null;
		try {

			// instantiate the "performanceImpl" object and its default values
			perf = new PerformanceImpl(firstName, lastName, discipline);

			Connection conn = DBMgr.getConnection();    	
			String insertString = "INSERT INTO performance " + 
					"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement iStmt = conn.prepareStatement(insertString);

			log.log(Level.INFO, "Inserting new performance in the DB: " + perf.getId() + " " + perf.getFirstName() + " " + perf.getLastName());

			iStmt.setString(1, perf.getId());
			iStmt.setString(2, perf.getFirstName());
			iStmt.setString(3, perf.getLastName());
			iStmt.setString(4, perf.getDiscipline().toString());

			iStmt.setNull(5, Types.DECIMAL);
			iStmt.setNull(6, Types.DECIMAL);
			iStmt.setNull(7, Types.DECIMAL);
			iStmt.setNull(8, Types.DECIMAL);
			iStmt.setNull(9, Types.DECIMAL);
			iStmt.setNull(10, Types.DECIMAL);
			iStmt.setNull(11, Types.DECIMAL);
			iStmt.setNull(12, Types.DECIMAL);
			iStmt.setNull(13, Types.DECIMAL);
			iStmt.setNull(14, Types.DECIMAL);

			iStmt.setInt(15, perf.getScore());
			iStmt.setBoolean(16, perf.getComplete());


			iStmt.execute();
			iStmt.close();
			conn.close();


		} catch (SQLException e) {
			log.severe("Could not create a Performance record in the DB!" +  e.getMessage());
			e.printStackTrace();
		}

		return (Performance) perf;
	}

	/**
	 * Update and existing Performance object.
	 * Since ID is protected, we give for granted that the object was created with createPerformance()
	 * and therefore a record with that ID is existing in the DB.
	 * 
	 * @param perf
	 * @throws SQLException
	 */
	public static void updatePerformance(Performance perf)  {
		try {
			Connection conn = DBMgr.getConnection();    	

			String updateSQL = "UPDATE performance SET " + 
					"firstname = ?, " +
					"lastname = ?, " +
					"discipline = ?, " +
					"sprint = ?, " +
					"long_jump = ?, " +
					"shot_put = ?, " +
					"high_jump = ?, " +
					"four_hundreds = ?, " +
					"hurdles = ?, " +
					"discus = ?, " +
					"pole_vault = ?, " +
					"javelin = ?, " +
					"m1500sprint = ?, " +
					"score = ?, " +
					"completed = ? " +
					" WHERE ID = ?";
			PreparedStatement uStmt = conn.prepareStatement(updateSQL);

			uStmt.setString(1, perf.getFirstName());
			uStmt.setString(2, perf.getLastName());
			uStmt.setString(3, perf.getDiscipline().toString());
			
			/*
			 * Many JDBC Driver supports NULL value transparently.
			 * Unfortunately Derby does not, so we need to test for all 10
			 * if some values are null, and use the correct setter.
			 */

			if (perf.getSprint().isEmpty()) {
				uStmt.setNull(4, Types.DECIMAL);
			} else {
				uStmt.setDouble(4, perf.getSprint().getAsDouble());				
			}

			if (perf.getLongJump().isEmpty()) {
				uStmt.setNull(5, Types.DECIMAL);
			} else {
				uStmt.setDouble(5, perf.getLongJump().getAsDouble());				
			}

			if (perf.getShotPut().isEmpty()) {
				uStmt.setNull(6, Types.DECIMAL);
			} else {
				uStmt.setDouble(6, perf.getShotPut().getAsDouble());				
			}

			if (perf.getHighJump().isEmpty()) {
				uStmt.setNull(7, Types.DECIMAL);
			} else {
				uStmt.setDouble(7, perf.getHighJump().getAsDouble());				
			}

			if (perf.getFourHundreds().isEmpty()) {
				uStmt.setNull(8, Types.DECIMAL);
			} else {
				uStmt.setDouble(8, perf.getFourHundreds().getAsDouble());				
			}

			if (perf.getHurdles().isEmpty()) {
				uStmt.setNull(9, Types.DECIMAL);
			} else {
				uStmt.setDouble(9, perf.getHurdles().getAsDouble());				
			}

			if (perf.getDiscus().isEmpty()) {
				uStmt.setNull(10, Types.DECIMAL);
			} else {
				uStmt.setDouble(10, perf.getDiscus().getAsDouble());				
			}

			if (perf.getPoleVault().isEmpty()) {
				uStmt.setNull(11, Types.DECIMAL);
			} else {
				uStmt.setDouble(11, perf.getPoleVault().getAsDouble());				
			}

			if (perf.getJavelin().isEmpty()) {
				uStmt.setNull(12, Types.DECIMAL);
			} else {
				uStmt.setDouble(12, perf.getJavelin().getAsDouble());				
			}

			if (perf.getM1500sprint().isEmpty()) {
				uStmt.setNull(13, Types.DECIMAL);
			} else {
				uStmt.setDouble(13, perf.getM1500sprint().getAsDouble());				
			}
			
			uStmt.setInt(14, perf.getScore());
			uStmt.setBoolean(15, perf.getComplete());
			
			// id
			uStmt.setString(16, perf.getId());
			
			log.info("Updating event results for Athlete ID " + perf.getId() + " " + perf.getFirstName() + " " + perf.getLastName());
			
			uStmt.executeUpdate();
			uStmt.close();
			conn.close();

		} catch (SQLException e) {
			log.severe("Could not update a Performance record in the DB!" +  e.getMessage());
			e.printStackTrace();
		}

	}
	
	/**
	 * Deletes a given Performance record from the database
	 * @param perf
	 */
	public static void deletePerformance(String id) {
		try {
			Connection conn = DBMgr.getConnection();    	

			String deleteSQL = "DELETE FROM performance SET " + 
					" WHERE ID = ?";
			
			PreparedStatement uStmt = conn.prepareStatement(deleteSQL);

			uStmt.setString(1, id);
		} catch (SQLException sqle) {
			// probably a better handling is needed, but the most probable reason is that the record has already deleted (no foreign keys)
			log.severe("Could not delete a Performance record in the DB! Id=" + id + ", Message: " +  sqle.getMessage());
		}
	}
	
	/**
	 * Gets the scoreboard sorted by Discipline (men and woman are on different competitions),
	 * completed (the one that run all races show firs) and score descending.
	 * @return
	 * @throws SQLException
	 */
    public static List<Performance> listAllPerformances() throws SQLException {
        List<Performance> scoreboard = new ArrayList<>();
         
        String sql = "SELECT * FROM PERFORMANCE ORDER BY DISCIPLINE, COMPLETED DESC, SCORE DESC, LASTNAME ASC, FIRSTNAME ASC";
         
        Connection conn = DBMgr.getConnection();         
        Statement sStmt = conn.createStatement();
        ResultSet resultSet = sStmt.executeQuery(sql);
         
        while (resultSet.next()) {
            Performance perf = populatePerformance(resultSet);
            scoreboard.add(perf);
        }
         
        resultSet.close();
        sStmt.close();
        conn.close();

        return scoreboard;
    }
    
    public static Performance getPerformance(String id) throws SQLException {
        Performance perf = null;
        String sql = "SELECT * FROM performance WHERE ID = ?";
         
        Connection conn = DBMgr.getConnection();         
        PreparedStatement sStmt = conn.prepareStatement(sql);
        sStmt.setString(1, id);
         
        ResultSet resultSet = sStmt.executeQuery();
         
        if (resultSet.next()) {
            perf = populatePerformance(resultSet);
            // Since ID is unique, it should return one record only.
            // worst case (?) will keep the last read.
        }
         
        resultSet.close();
        sStmt.close();
         
        return perf;
    }
    
    /**
     * Maps all properties of the recordset to a Preference Object
     * @param record
     * @return
     * @throws SQLException 
     */
    private static Performance populatePerformance(ResultSet r) throws SQLException { 	
    	
    	Performance perf =  (Performance) new PerformanceImpl(r.getString("id"), 
    				r.getString("firstname"),r.getString("lastname"),r.getString("discipline"));
    	perf.setSprint(new Seconds(r.getDouble("sprint")));
    	perf.setLongJump(new Centimeters(r.getDouble("long_jump")));
    	perf.setShotPut(new Meters(r.getDouble("shot_put")));
    	perf.setHighJump(new Centimeters(r.getDouble("high_jump")));
    	perf.setFourHundreds(new Seconds(r.getDouble("four_hundreds")));
    	perf.setDiscus(new Meters(r.getDouble("discus")));
    	perf.setPoleVault(new Centimeters(r.getDouble("pole_vault")));
    	perf.setJavelin(new Meters(r.getDouble("javelin")));
    	perf.setM1500sprint(new Seconds(r.getDouble("m1500sprint")));

    	return perf;
    }
}
