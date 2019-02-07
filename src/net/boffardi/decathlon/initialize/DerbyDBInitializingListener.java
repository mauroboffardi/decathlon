package net.boffardi.decathlon.initialize;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.db.DBMgr;
import net.boffardi.decathlon.api.db.PerformanceMgr;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.units.Centimeters;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;

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
            log.info("Database file are looked up or created in " + System.getProperty("derby.system.home") + " (ENV VAR derby.system.home)");
            connection = DBMgr.getConnection();
            if (!schemaHasBeenInitialized(connection)) {
                initializeSchema(connection);
            }
        } catch (SQLException sqle) {
            log.severe("Could not connect to Derby Embedded DB!" + sqle.getMessage());
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
    	
    	PerformanceMgr.createTables();
    	
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
   
    	try {
    	Performance alice = PerformanceMgr.createPerformance("Alice", "Allison", Discipline.WOMEN);
    	// reference values for 700pts
    	alice.setSprint(new Seconds("11.756"));
    	alice.setLongJump(new Centimeters(651));
    	alice.setShotPut(new Meters(13.53));
    	alice.setHighJump(new Centimeters(188)); 
    	alice.setFourHundreds(new Seconds("52.58"));
    	alice.setHurdles(new Seconds("16.29"));
    	alice.setDiscus(new Meters(41.72));
    	alice.setPoleVault(new Centimeters(429));
    	alice.setJavelin(new Meters(57.45));
    	alice.setM1500sprint(new Seconds("4:36.96"));
    	PerformanceMgr.updatePerformance(alice);
    	
    	
    	Performance bob = PerformanceMgr.createPerformance("Bob", "Burgersson", Discipline.MEN);
    	// reference values for 900pts
    	bob.setSprint(new Seconds("10.827"));
    	bob.setLongJump(new Centimeters(736));
    	bob.setShotPut(new Meters(16.79));
    	bob.setHighJump(new Centimeters(210));
    	bob.setFourHundreds(new Seconds("48.19"));
    	bob.setHurdles(new Seconds("14.59"));
    	bob.setDiscus(new Meters(51.4));
    	bob.setPoleVault(new Centimeters(496));
    	bob.setJavelin(new Meters(70.67));
    	bob.setM1500sprint(new Seconds("4:07.42"));
    	PerformanceMgr.updatePerformance(bob);


    	Performance carl = PerformanceMgr.createPerformance("Carl", "Cristersson", Discipline.MEN);
    	// reference values for 1000pts
    	carl.setSprint(new Seconds("10.395"));
    	carl.setLongJump(new Centimeters(776));
    	carl.setShotPut(new Meters(18.4));
    	carl.setHighJump(new Centimeters(220)); 
    	carl.setFourHundreds(new Seconds("46.17"));
    	carl.setHurdles(new Seconds("13.8"));
    	carl.setDiscus(new Meters(56.17));
    	carl.setPoleVault(new Centimeters(528));
    	carl.setJavelin(new Meters(77.19));
    	carl.setM1500sprint(new Seconds("3:53.79"));
    	PerformanceMgr.updatePerformance(carl);

    	
    	Performance daniel = PerformanceMgr.createPerformance("Daniel", "Danielsson", Discipline.MEN);
    	// reference values for 800pts
    	daniel.setSprint(new Seconds("11.278"));
    	daniel.setLongJump(new Centimeters(694));
    	daniel.setShotPut(new Meters(15.16));
    	daniel.setHighJump(new Centimeters(199));
    	daniel.setFourHundreds(new Seconds("50.32"));
    	daniel.setHurdles(new Seconds("15.419"));
    	daniel.setDiscus(new Meters(46.59));
    	daniel.setPoleVault(new Centimeters(463));
    	daniel.setJavelin(new Meters(64.09));
    	daniel.setM1500sprint(new Seconds("4:21.77"));
    	PerformanceMgr.updatePerformance(daniel);

    	// UNCOMPLETED PERFORMANCES
    	Performance rookie = PerformanceMgr.createPerformance("Rookie", "Rookiesson", Discipline.MEN);
    	rookie.setSprint(new Seconds("12.121"));
    	rookie.setLongJump(new Centimeters(678));
    	rookie.setShotPut(new Meters(14.90));
    	rookie.setHighJump(new Centimeters(187));
    	rookie.setFourHundreds(new Seconds("1:00.128"));
    	PerformanceMgr.updatePerformance(rookie);

    	// UNCOMPLETED PERFORMANCES
    	Performance lagom = PerformanceMgr.createPerformance("lagom", "lagomsson", Discipline.MEN);
    	lagom.setSprint(new Seconds("11.281"));
    	lagom.setLongJump(new Centimeters(698));
    	lagom.setShotPut(new Meters(18.90));
    	lagom.setHighJump(new Centimeters(230));
    	PerformanceMgr.updatePerformance(lagom);

    	
    	} catch (ParseException pe) {
            log.warning("Cannot initialize sample data! Typo in the program ???  " + pe.getMessage());
    	}
    }

    

    @Override
    public void contextDestroyed(ServletContextEvent event) { }
}