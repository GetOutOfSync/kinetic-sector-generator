package manager;

import entity.Planet;
import entity.PlanetAttributes;

public class PlanetGen extends Manager {
	
	/*private static final String TERRAIN_TYPE_FLATLAND = "Flatland";
	private static final String TERRAIN_TYPE_MOUNTAINOUS = "Mountainous";
	private static final String TERRAIN_TYPE_LCRATERED = "Lightly Cratered";
	private static final String TERRAIN_TYPE_HCRATERED = "Heavily Cratered";
	private static final String TERRAIN_TYPE_EVOLCANO = "Extinct Volcatio";
	
	private static final String[] TERRAIN_TABLE_NO_ATMO = {TERRAIN_TYPE_FLATLAND, TERRAIN_TYPE_MOUNTAINOUS, TERRAIN_TYPE_LCRATERED, TERRAIN_TYPE_HCRATERED, TERRAIN_TYPE_EVOLCANO};
	private static final String[] TERRAIN_TABLE_INFERNO = {TERRAIN_TYPE_FLATLAND, };*/
	
	private Planet workObject;

	public PlanetGen() {
		super();
	}

	public PlanetGen(long seed) {
		super(seed);
	}
	
	/** Generates a new Planet.
	 * 
	 * **FLOW OF EXECUTION**
	 * 
	 * Set seed for generation.
	 * Generates planetary type
	 * Passes the zoneLabel parameter to the planet.
	 * Generates planetary mass of planet based on planet type.
	 * Runs initPlanet, which finishes off planet generation. For more info, refer to initPlanet documentation.
	 * Generates planetary moons based on mass. Moon generation is handled by the genMoons function, refer to that documentation for more info.
	 * Return completed planet
	 * 
	 * @param seed Generation seed to create the Planet
	 * @param zoneLabel Label of how close the planet is to the Parent Star
	 * @return Completed Planet Object.
	 */
	public Planet newPlanet(long seed, String zoneLabel) {
		setSeed(seed);
		
		int roll = gen.roll(8, true);
		String type = gen.rollOnTable(stor.getChanceTable("planet_type"));
		
		workObject = new Planet(type);
		workObject.setID(this.seed);
		workObject.setZoneLabel(zoneLabel);
		
		genMass(workObject);
		initPlanet(workObject);
		
		genMoons(workObject);
		return workObject;
	}
	
	protected void initManagers() {}
	
	/** Generates a new Planet. Zone defaults to Far, as it is assumed it does not have a Star parent.
	 * 
	 * @return Completed Planet Object.
	 */
	public Planet newPlanet() {
		return newPlanet(gen.genSeed());
	}
	
	/**Generates a new Planet. Zone defaults to Far, as it is assumed it does not have a Star parent.
	 * 
	 * @param seed Generation seed to create the Planet
	 * @return Completed Planet Object.
	 */
	public Planet newPlanet(long seed) {
		return newPlanet(seed, PlanetAttributes.ZONE_LABEL_FAR);
	}
	
	/** Generates the planetary atmosphere label based on planetary mass.
	 * @param planet Planet to generate an atmosphere on
	 */
	private void genAtmosphere(Planet planet) {
		int roll = gen.roll(10, true);
		double planetMass = planet.getMass();
		
		//Tier 1
		if (planetMass <= 0.3) {
			planet.setAtmosphere(gen.rollOnTable(stor.getChanceTable("tier_1_atmo")));
		}
		//Tier 2
		else if (planetMass <= 0.75) {
			planet.setAtmosphere(gen.rollOnTable(stor.getChanceTable("tier_2_atmo")));
		}
		//Tier 3
		else if (planetMass <= 1.25) {
			planet.setAtmosphere(gen.rollOnTable(stor.getChanceTable("tier_3_atmo")));
		}
		//Tier 4
		else if (planetMass <= 10) {
			planet.setAtmosphere(gen.rollOnTable(stor.getChanceTable("tier_4_atmo")));
		}
		//Tier 5
		else {
			planet.setAtmosphere(gen.rollOnTable(stor.getChanceTable("tier_5_atmo")));
		}
		
		planet.setTemperature(PlanetAttributes.TEMPERATURE_TABLE[PlanetAttributes.getProxNum(planet.getZoneLabel()) + 2][PlanetAttributes.getAtmoNum(planet.getAtmosphere()) + 2]);
	}
	
	/** Generates planetary mass based on planetary type.
	 * @param planet Planet to generate mass for.
	 */
	private void genMass (Planet planet) {
		double diameter = 0.0;
		double gravity = 0.0;
		double mass = 0.0;
		switch (planet.getType()) {
			case PlanetAttributes.PLANET_TYPE_DWARF:
				diameter = gen.rollMany(6, 10) * 0.01;
				gravity = gen.rollPercent() / 30.0;
				mass = PlanetAttributes.massCalc(gravity, diameter);
				planet.setSize(diameter, gravity, mass);
				break;
			case PlanetAttributes.PLANET_TYPE_TERRESTRIAL:
				diameter = (gen.rollPercent() + 40.0) / 70.0;
				gravity = gen.rollMany(2, 10) / 10.0;
				mass = PlanetAttributes.massCalc(gravity, diameter);
				planet.setSize(diameter, gravity, mass);
				break;
			case PlanetAttributes.PLANET_TYPE_GAS_GIANT:
				diameter = (gen.roll(10) + 15.0) / 2.0;
				gravity = gen.rollPercent() * 0.05;
				mass = PlanetAttributes.massCalc(gravity, diameter);
				planet.setSize(diameter, gravity, mass);
				break;
		}
	}
	
	/** Helper function which finalizes generation. This function exists primarily because normal planetary generation and moon generation start radically different,
	 * still need to generate very similar things. This function makes sure both moons and planets run shared functions.
	 * @param planet Planet to initialize
	 */
	private void initPlanet(Planet planet) {
		genAtmosphere(planet);
		planet.setHabitabilityMatrix(PlanetAttributes.createHabitabilityMatrix(planet));
		//genTerrain(planet);
	}
	
	/**
	 * MOON SECTION
	 * 
	 * This section of functions is all about handling the generation of Planetary moons.
	 */
	
	
	/** This helper function generates the moons of a given planet. Start of the moon function rabbit hole.
	 * 
	 * **FLOW OF EXECUTION**
	 * Generates number of moons based on planetary mass
	 * Creates moons, with a limit on moon mass based on planetary mass.
	 * Places the moon in the planet's orbit, starting from zone 1 and incrementing by one.
	 *  
	 * @TODO Implement planetary ring generation.
	 * 
	 * @param planet Parent planet moons will orbit
	 */
	private void genMoons (Planet planet) {
		double planetMass = planet.getMass();
		int roll = gen.roll(10, true);
		
		//Tier 0
		if (planetMass <= 0.5);
		//Tier 1
		else if (planetMass <= 2.0) {
			createMoonOrbits(planet, gen.parseString(gen.rollOnTable(stor.getChanceTable("tier_1_moon"))));
		}
		//Tier 2
		else if (planetMass <= 15.0) {
			createMoonOrbits(planet, gen.parseString(gen.rollOnTable(stor.getChanceTable("tier_2_moon"))));
		}
		//Tier 3
		else if (planetMass <= 25) {
			createMoonOrbits(planet, gen.parseString(gen.rollOnTable(stor.getChanceTable("tier_3_moon"))));// (3/10) 1d5 rings
		}
		//Tier 4
		else if (planetMass <= 130) {
			createMoonOrbits(planet, gen.parseString(gen.rollOnTable(stor.getChanceTable("tier_4_moon")))); //1d10 rings
		}
		//Tier 5
		else {
			createMoonOrbits(planet, gen.parseString(gen.rollOnTable(stor.getChanceTable("tier_5_moon"))));//1d10 rings
		}
	}
	
	/** This will create moons and add them to planet's orbit table.
	 * 
	 * @param planet Parent planet
	 * @param num Number of moons to generate
	 */
	private void createMoonOrbits(Planet planet, int num) {
		for (int i = 1; i <= num; i++) {
			planet.addOrbit(newMoon(planet, gen.genSeed()), i);
		}
	}
	
	/** Generates a new planet using moon rules. Takes a Parent planet to verify the moon is not a significant size compared to the parent.
	 * @param parent Parent planet.
	 * @return New moon
	 */
	public Planet newMoon(Planet parent) {
		return newMoon(parent, gen.genSeed());
	}
	
	/** Generates a new planet using moon rules. Takes a Parent planet to verify the moon is not a significant size compared to the parent.
	 * @param parent Parent planet
	 * @param seed Seed which will be used to attempt to generate a moon. If the moon is too massive for the given parent moon, the seed will change.
	 * @return New moon
	 */
	public Planet newMoon(Planet parent, long seed) {
		setSeed(seed);
		Planet moon = null;
		do {
			//Sets the Moon Type
			String type = PlanetAttributes.PLANET_TYPE_DWARF;
			int roll = gen.roll(10);
			if (roll <= 3) type = PlanetAttributes.PLANET_TYPE_TERRESTRIAL;
			
			moon = new Planet(type);
			//Keep all generation stuff before the seed gets reset to make sure everything remains the same
			genMass(moon);
			moon.setID(gen.getSeed());
			//Generates the next seed just in case the moon is too big.
			setSeed(gen.genSeed());
		} while (parent.getMass() < moon.getMass() * 1.5);
		
		moon.setZoneLabel(parent.getZoneLabel());
		initPlanet(moon);
		
		return moon;
	}
}
