package net.boffardi.decathlon.api.db;

import java.util.UUID;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.Utils;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.units.Meters;
import net.boffardi.decathlon.api.types.units.Seconds;

/**
 *  Object that represents the Performance of an Athlete.
 *  It is the internal Implementaion of the public "Performance" interface.
 * 
 * @author mauro.boffardi
 *
 */
public class PerformanceImpl implements Performance {
	private String id;
	private String firstName;
	private String lastName;
	private Discipline discipline;
	private Seconds sprint = new Seconds();  
	private Meters longJump = new Meters(); 
	private Meters shotPut = new Meters(); 
	private Meters highJump = new Meters(); 
	private Seconds fourHundreds = new Seconds();
	private Seconds hurdles = new Seconds(); 
	private Meters discus = new Meters();  
	private Meters poleVault = new Meters();  
	private Meters javelin = new Meters(); 
	private Seconds m1500sprint = new Seconds(); 
	private Integer score = 0;
	
	/**
	 * Constructor for a new performance with all defaults.
	 * ID is auto generated.
	 * @param firstName
	 * @param lastName
	 * @param discipline
	 */
	protected PerformanceImpl (String firstName, String lastName, Discipline discipline) {
		UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.firstName = firstName;
        this.lastName = lastName;
        setDiscipline(discipline);
	}

	
	
	// @TODO Calculate the scores here
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getScore()
	 */
	@Override
	public Integer getScore() {
		return score;
	}

	@Override
	/**
	 * returns "true" if a value has been provided for all 10 events.
	 */
	public Boolean isComplete() {
		Boolean complete = Utils.notNull(sprint,longJump,shotPut,highJump,fourHundreds,hurdles,discus,poleVault,javelin,m1500sprint);
			
		return complete;
	}
	

	

	/*
	 * Plain Getters and setters
	 */


	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getDiscipline()
	 */
	@Override
	public Discipline getDiscipline() {
		return discipline;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setDiscipline(net.boffardi.decathlon.api.types.Discipline)
	 */
	@Override
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
	
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getSprint()
	 */
	@Override
	public Seconds getSprint() {
		return sprint;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setSprint(net.boffardi.decathlon.api.units.Seconds)
	 */
	@Override
	public void setSprint(Seconds sprint) {
		this.sprint = sprint;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getLongJump()
	 */
	@Override
	public Meters getLongJump() {
		return longJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setLongJump(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setLongJump(Meters longJump) {
		this.longJump = longJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getShotPut()
	 */
	@Override
	public Meters getShotPut() {
		return shotPut;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setShotPut(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setShotPut(Meters shotPut) {
		this.shotPut = shotPut;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getHighJump()
	 */
	@Override
	public Meters getHighJump() {
		return highJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setHighJump(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setHighJump(Meters highJump) {
		this.highJump = highJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getFourHundreds()
	 */
	@Override
	public Seconds getFourHundreds() {
		return fourHundreds;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setFourHundreds(net.boffardi.decathlon.api.units.Seconds)
	 */
	@Override
	public void setFourHundreds(Seconds fourHundreds) {
		this.fourHundreds = fourHundreds;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getHurdles()
	 */
	@Override
	public Seconds getHurdles() {
		return hurdles;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setHurdles(net.boffardi.decathlon.api.units.Seconds)
	 */
	@Override
	public void setHurdles(Seconds hurdles) {
		this.hurdles = hurdles;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getDiscus()
	 */
	@Override
	public Meters getDiscus() {
		return discus;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setDiscus(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setDiscus(Meters discus) {
		this.discus = discus;
	}
	
	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getPoleVault()
	 */
	@Override
	public Meters getPoleVault() {
		return discus;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setPoleVault(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setPoleVault(Meters discus) {
		this.discus = discus;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getJavelin()
	 */
	@Override
	public Meters getJavelin() {
		return javelin;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setJavelin(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setJavelin(Meters javelin) {
		this.javelin = javelin;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getM1500sprint()
	 */
	@Override
	public Seconds getM1500sprint() {
		return m1500sprint;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setM1500sprint(net.boffardi.decathlon.api.units.Seconds)
	 */
	@Override
	public void setM1500sprint(Seconds m1500sprint) {
		this.m1500sprint = m1500sprint;
	}


	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getId()
	 */
	@Override
	public String getId() {
		return id;
	}


}
