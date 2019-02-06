# Decathlon Web App

## Installation
### Requirements
- A Java JDK, preferrably 1.8
- A working installaton of Apache Tomcat, with default settings

### Setup
The repository contains an Eclipse project. Checkout the code, and use Eclipse >3.0 to export the project into a WAR file (i.e. decathlon.war).
The application uses an embedded Derby JDB Driver, so it is self-contained, no other resources are needed.
Copy the WAR file into the webapps folder, start Tomcat, and open a browser to the location `http://localhost:8080/decatlon`.
At first startup, the DB will be initialized and the tables created.
*Note:* by Derby default the Database files (decathlonDB) are created directly under the main execution directory of Java (or Eclipse). To locate files in a directory of choice, add an absolute path as command line parameter of Tomcat: `-Dderby.system.home=`*<Absolutepath>*.
To re-initialize the data, just stop Tomcat, remove the `decathlonDB` directory, and restart.

## Development notes
### Changelog

- I selected Java / Tomcat as development environment, and Eclipse as IDE. Java 8 and Tomcat 7 were the simpler.
- The data is not complicated nor big, so any file based approach (XML, JSON?) would be sufficent. For better expandibility i selected a SQL database, Derby was one of the simpler, since it is free and can be embedded in Tomcat, not requiring external intergration
- Added derby.jar (embedded version) in WEB-INF/lib of the project.
- Implemented a connection in context.xml, and a custom ServletContextListener (that is run at Tomcat startup) to initialize the DB at Startup
- Created Interface/Impl/Magr pattern. "completed" and "score" attributes are calculated in the getters, but made persistent to facilitate sorted data retrival.
- Shared first version of the code to a repository on GitHub
- Created webapp framework with header and footer
- Added configuration of database location 
- Added UPDATE method for Performance record, went to an extensive redesign of "Seconds" type to provide correct parsing, and relates test cases. Initialized demo data
	
		
### TODO 
- calculate score
- to test cases and review Second.toString()
- Manage performance deletion
- Check string length in DB layer
- Create default view
- Create/edit a new performance
- Show performance list sorted by score
- Manage in progress / finished statues
- Manage logs better
- Distinguish between Male/female races
- Rationalize events in their own table?

### Improvements
- Implement complete set of JUnit tests


### Should probably have done better
- Use a proper ORM model. Not implemented for simplicity. The abstraction layer is not optimal, but i wanted to use a "homemade" approach to show the thinking pattern, instead to use a "pre-made" library. Still, i do not like the need of the "update" method to save the object.
- Use a build tool like Gradle to build the project and include the dependencies
- Use a client side compilation tool like less on node.js to allow better and easier frontend coding
- Better file structure for jsp resources, and better header/footer framework with cleaner includes

