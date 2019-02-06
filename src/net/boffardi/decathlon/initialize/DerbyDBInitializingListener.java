package net.boffardi.decathlon.initialize;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.boffardi.decathlon.api.db.DBMgr;
import net.boffardi.decathlon.api.db.PerformanceMgr;
import net.boffardi.decathlon.api.types.Discipline;

/**
 * Our custom InitailizingListener is declared as a ServletContextListener in 
 * WEB-INF/web.xml. 
 * ServletContextListeners are executed at startup.
 * In this case we use it to load the derby driver, and check that everything is allright.
 * If connection to the DB is successful, its content will be checked; 
 * if it is a new empty DB it will be initialized with table creation and (optionally)
 * Sample test data.
 * 
 * @author mauro.boffardi
 *
 */
public class DerbyDBInitializingListener implements ServletContextListener {
    private static final Logger log = Logger
            .getLogger(DerbyDBInitializingListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Servlet Content Initialized");

        try {
            log.info("Loading Derby DB Driver...");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            initializeDatabase();
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE, "Could not load Derby Embedded Driver!", e);
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Fatal Database Error!", sqle);
        }
    }

    /**
     * connects to the DB and check if the DB contains existing data or it is new.
     * 
     * @throws SQLException
     */
    private void initializeDatabase() throws SQLException {
        Connection connection = null;
        try {
            log.info("Starting up Derby DB...");
            connection = DBMgr.getConnection();
            if (!schemaHasBeenInitialized(connection)) {
                initializeSchema(connection);
            }
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "Could not connect to Derby Embedded DB!", sqle);
            throw sqle;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * Queries the Table list and verify if the "performance" table exists.
     * 
     * @param con
     * @return true if the DB already contains a "performence" table
     * @throws SQLException
     */
    private boolean schemaHasBeenInitialized(Connection con) throws SQLException {
        final DatabaseMetaData metaData = con.getMetaData();
        final ResultSet tablesResultSet =
                metaData.getTables(
                        null, null, null,
                        new String[] { "TABLE" });

        try {
            while (tablesResultSet.next()) {
                final String tableName = tablesResultSet.getString("TABLE_NAME");
                if (tableName != null
                        && "performance".equalsIgnoreCase(tableName)) {
                	log.info("Datablase tables are already present. Good.");
                    return true;
                }
            }
        } finally {
            if (tablesResultSet != null) {
                tablesResultSet.close();
            }
        }
        return false;
    }

    /**
     * Execute CREATE TABLE statements to create application tables
     * 
     * @param con
     * @throws SQLException
     */
    private void initializeSchema(Connection con) throws SQLException {
        // Execute whatever SQL is necessary to
        // create the schema tables and seed data
    	log.info("Initializing decathlonDB...");
    	
    	Statement stmt = con.createStatement();
    	
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
    	
    	// Comment here to start the application with an empty table.
    	initializeDemoData(con);
    }
    
    /**
     * Loads test data in the performance table.
     * 
     * @param con
     * @throws SQLException
     */
    private void initializeDemoData(Connection con) throws SQLException {
   
    	PerformanceMgr.createPerformance("Alice", "Allison", Discipline.WOMEN);
    	PerformanceMgr.createPerformance("Bob", "Burgersson", Discipline.MEN);
    	PerformanceMgr.createPerformance("Carl", "Cristersson", Discipline.MEN);
    	PerformanceMgr.createPerformance("Daniel", "Danielsson", Discipline.MEN);
    }

    

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}