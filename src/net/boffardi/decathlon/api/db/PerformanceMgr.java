package net.boffardi.decathlon.api.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.types.Discipline;

/**
 * Create, instantiates and deletes objects of type "Performance" from the DB layer.
 * It is the only class that can create/manage the PerformanceImpl object.
 * 
 * @author mauro.boffardi
 *
 */
public class PerformanceMgr {
	// definition of the logger
	private static final Logger log = Logger.getLogger(PerformanceMgr.class.getName());
	
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

			 /*
			 iStmt.setString(4, perf.getDiscipline().toString());
			 iStmt.setDouble(5, perf.getSprint().getAsDouble());
			 iStmt.setDouble(6, perf.getLongJump().getAsDouble());
			 iStmt.setDouble(7, perf.getShotPut().getAsDouble());
			 iStmt.setDouble(8, perf.getHighJump().getAsDouble());
			 iStmt.setDouble(9, perf.getFourHundreds().getAsDouble());
			 iStmt.setDouble(10, perf.getHurdles().getAsDouble());
			 iStmt.setDouble(11, perf.getDiscus().getAsDouble());
			 iStmt.setDouble(12, perf.getPoleVault().getAsDouble());
			 iStmt.setDouble(13, perf.getJavelin().getAsDouble());
			 iStmt.setDouble(14, perf.getM1500sprint().getAsDouble());
			 */
			 iStmt.setInt(15, perf.getScore());
			 iStmt.setBoolean(16, perf.isComplete());
			 

			 iStmt.execute();
               
		
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Could not connect to Derby Embedded DB!", e);
			e.printStackTrace();
		}
    	
		return (Performance) perf;
	}
	 
	// TODO IMPLEMENT UPDATE
	public static void updatePerformance(Performance perf) {
	}
}
