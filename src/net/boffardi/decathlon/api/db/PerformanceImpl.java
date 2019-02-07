package net.boffardi.decathlon.api.db;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import net.boffardi.decathlon.api.Performance;
import net.boffardi.decathlon.api.Utils;
import net.boffardi.decathlon.api.types.Discipline;
import net.boffardi.decathlon.api.types.ScoreParameters;
import net.boffardi.decathlon.api.types.units.Centimeters;
import net.boffardi.decathlon.api.types.units.EventResult;
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
	private Centimeters longJump = new Centimeters(); 
	private Meters shotPut = new Meters(); 
	private Centimeters highJump = new Centimeters(); 
	private Seconds fourHundreds = new Seconds();
	private Seconds hurdles = new Seconds(); 
	private Meters discus = new Meters();  
	private Centimeters poleVault = new Centimeters();  
	private Meters javelin = new Meters(); 
	private Seconds m1500sprint = new Seconds(); 
	
	// definition of the logger
	private static final Logger log = Logger.getLogger(PerformanceImpl.class.getName());

	
	/**
	 * Constructor for a new performance with all defaults.
	 * ID is auto generated.
	 * 
	 * qualifier should be "protect" to make sure 
	 * 
	 * @param firstName
	 * @param lastName
	 * @param discipline
	 */
	public PerformanceImpl (String firstName, String lastName, Discipline discipline) {
		UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.firstName = firstName;
        this.lastName = lastName;
        setDiscipline(discipline);
	}
	
	/**
	 * Constructor for loading an existing performance from the DB.
	 * ID is passed, and Discipline is parsed from String.
	 */
	public PerformanceImpl (String uuid, String firstName, String lastName, String disciplineString) {
        this.id = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        setDiscipline(Utils.getDiscipline(disciplineString));
	}
	
	
	/**
	 * Calculates the score points according to https://en.wikipedia.org/wiki/Decathlon 
	 * 
	 * @see net.boffardi.decathlon.api.db.internal.Performance#getScore()
	 */
	@Override
	public Integer getScore() {
		Integer score;
		
		score = calculatePoints((EventResult)sprint, ScoreParameters.Sprint);
		score = score + calculatePoints((EventResult)longJump, ScoreParameters.LongJump);
		score = score + calculatePoints((EventResult)shotPut, ScoreParameters.ShotPut);
		score = score + calculatePoints((EventResult)highJump, ScoreParameters.HighJump);
		score = score + calculatePoints((EventResult)fourHundreds, ScoreParameters.FourHundreds);
		score = score + calculatePoints((EventResult)hurdles, ScoreParameters.Hurdles);
		score = score + calculatePoints((EventResult)discus, ScoreParameters.Discus);
		score = score + calculatePoints((EventResult)poleVault, ScoreParameters.PoleVault);
		score = score + calculatePoints((EventResult)javelin, ScoreParameters.Javelin);
		score = score + calculatePoints((EventResult)m1500sprint, ScoreParameters.M1500Sprint);
		
		return score;
	}
	
	/*
	 * Probably a bit overkill.
	 * The method uses the interface to handle all 3 types of results. 
	 * The first part of the equation is different, but then the rest is the same for all. 
	 * There is probably more instantiating and GarbageCollecting than strictly necessary,
	 * but in this context i preferred readabilty.
	 */
	
	private Integer calculatePoints(EventResult er, Map<String,Double> param) {
		log.info("calculatePoints: er=" + er + " param.get(B)=" + param.get("B"));
		if (er.isEmpty()) return 0;
		
		Double PB;
		// for Time events, the first part of the equation is "B" - time
		// for Distance events , the first part of the equation is distance - "B"
		if (er.isTimeBased()) {
			PB = param.get("B") - er.getAsDouble();
		} else {
			 PB = er.getAsDouble() - param.get("B");
		}
		
		log.info("calculatePoints: PB=" + PB);
		
		Double PBC = Math.pow(PB, param.get("C"));

		log.info("calculatePoints: PBC =" + PBC + " -- param.get(C)=" + param.get("C"));

		Double APBC= param.get("A") * PBC;
		log.info("calculatePoints: points =" + APBC + " -- param.get(A)=" + param.get("A"));

		Long rounded = Math.round(APBC);
		log.info("calculatePoints: rounding " + APBC + "to " + rounded);

		return (Integer) rounded.intValue();
	}


	@Override
	/**
	 * returns "true" if a value has been provided for all 10 events.
	 */
	public Boolean getComplete() {
		Boolean complete = Utils.notEmpty(sprint,longJump,shotPut,highJump,fourHundreds,hurdles,discus,poleVault,javelin,m1500sprint);			
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
	public Centimeters getLongJump() {
		return longJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setLongJump(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setLongJump(Centimeters longJump) {
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
	public Centimeters getHighJump() {
		return highJump;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setHighJump(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setHighJump(Centimeters highJump) {
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
	public Centimeters getPoleVault() {
		return poleVault;
	}

	/* (non-Javadoc)
	 * @see net.boffardi.decathlon.api.db.internal.Performance#setPoleVault(net.boffardi.decathlon.api.units.Meters)
	 */
	@Override
	public void setPoleVault(Centimeters poleVault) {
		this.poleVault = poleVault;
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
