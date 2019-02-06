package net.boffardi.decathlon.api;

import net.boffardi.decathlon.api.db.PerformanceImpl;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;
/**
 * Public interface for the Performance object.
 * "performances" are meant to be created and administered by the PerformanceMgr class
 * @author mauro.boffardi
 * @see PerformanceImpl
 */
public interface Performance {

	Integer getScore();

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	Discipline getDiscipline();

	void setDiscipline(Discipline discipline);

	Seconds getSprint();

	void setSprint(Seconds sprint);

	Meters getLongJump();

	void setLongJump(Meters longJump);

	Meters getShotPut();

	void setShotPut(Meters shotPut);

	Meters getHighJump();

	void setHighJump(Meters highJump);

	Seconds getFourHundreds();

	void setFourHundreds(Seconds fourHundreds);

	Seconds getHurdles();

	void setHurdles(Seconds hurdles);

	Meters getDiscus();

	void setDiscus(Meters discus);

	Meters getPoleVault();

	void setPoleVault(Meters discus);
	
	Meters getJavelin();

	void setJavelin(Meters javelin);

	Seconds getM1500sprint();

	void setM1500sprint(Seconds m1500sprint);

	Boolean isComplete();

	String getId();

}