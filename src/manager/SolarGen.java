package manager;
import entity.PlanetAttributes;
import entity.SolarSystem;
import entity.Star;
import rand.ChanceTable;

/** This class handles the setup of SolarSystem objects. Below is the general flow of execution for various conceptual functions.
 * 
 * @Generate_System
 * 
 * 
 * 
 * @Import_System TODO
 * 
 * @author Tanner Hansen
 */

public class SolarGen extends Manager {
	
	private PlanetGen planetManager;
	private StarGen starManager;
	
	public SolarGen() {
		super();
	}
	
	public SolarGen(long seed) {
		super(seed);
	}
	
	protected void initManagers() {
		starManager = new StarGen(gen.genSeed());
		addChildManager(starManager);
		planetManager = new PlanetGen(gen.genSeed());
		addChildManager(planetManager);
	}
	
	/** Determines the zone label given to objects in orbit based on a star's luminosity and planetary zone.
	 * @param luminosity Host star's luminosity
	 * @param zone Orbit zone
	 * @return Zone label
	 */
	private String calcZoneLabel (int luminosity, int zone) {
		if (zone <= luminosity / 2) {
			return PlanetAttributes.ZONE_LABEL_NEAR;
		}
		else if (zone < luminosity) {
			return PlanetAttributes.ZONE_LABEL_INNER;
		}
		else if (zone == luminosity) {
			return PlanetAttributes.ZONE_LABEL_HABITABLE;
		}
		else if (zone <= luminosity * 1.5) {
			return PlanetAttributes.ZONE_LABEL_OUTER;
		}
		else {
			return PlanetAttributes.ZONE_LABEL_FAR;
		}
	}
	
	/**The newSystem function creates a new System based on a given seed, or generates a seed for generation if one is not given.  
	 * 
	 **FLOW OF EXECUTION**
	 * 
	 * Set seed for generation.
	 * Generate the number of stars to create for the system, and pass off star creation to StarManager.
	 * Determine system luminosity by adding all system star luminosity together.
	 * TODO Determine primary star of system based on luminosity. Largest wins.
	 * Generate total possible orbits, based on total system luminosity.
	 * Generate total system objects, based on total possible orbits.
	 * Generate which orbits objects will have, and verify the orbits are unoccupied.
	 * Pass off planet creation to PlanetManager, and give object orbits to primary star.
	 * Return completed system.
	 * 
	 * @param seed Seed to be used to generate new system.
	 * @return Completed SolarSystem Object.
	 */
	public SolarSystem newSystem(long seed) {
		setSeed(seed);
		SolarSystem workObject = new SolarSystem();
		workObject.setID(seed);
		
		//Creates the Solar System star. starNum is hardcoded for now just for testing.
		int starNum = Integer.parseInt(gen.rollOnTable(stor.getChanceTable("star_num")));
		
		for (int i = 0; i < starNum; i++) {
			workObject.addStar(starManager.newStar(gen.genSeed()));
		}
		Star primaryStar = workObject.getPrimaryStar();
		
		int possOrbits = gen.roll(6, true) + workObject.getLuminosity();
		int numOrbits = gen.roll(possOrbits, true);
		for (int i = 0; i < numOrbits; i++) {
			boolean orbitCreated = false;
			do {
				int zone = gen.roll(possOrbits, true);
				if (!primaryStar.orbitExists(zone)) {
					primaryStar.addOrbit(planetManager.newPlanet(gen.genSeed(), calcZoneLabel(workObject.getLuminosity(), zone)), zone);
					orbitCreated = true;
				}
			} while (!orbitCreated);
		}
		
		return workObject;
	}
	
	public SolarSystem newSystem() {
		return newSystem(gen.genSeed());
	}
}
