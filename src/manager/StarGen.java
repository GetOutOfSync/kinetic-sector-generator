package manager;

import entity.Star;
import entity.StarAttributes;

public class StarGen extends Manager {
	
	private Star workObject;
	
	public StarGen() {
		super();
	}

	public StarGen(long seed) {
		super(seed);
	}

	public Star newStar() {
		return newStar(gen.genSeed());
	}
	
	/** Creates a new Star.
	 * 
	 * **FLOW OF EXECUTION**
	 * Sets seed
	 * Generates Star Type
	 * Sets Star Luminosity based on star type
	 * Return Completed Star object.
	 * 
	 * @param seed Seed to be used to generate new star.
	 * @return Completed Star Object
	 */
	public Star newStar(long seed) {
		setSeed(seed);
		
		String type = gen.rollOnTable(SolarRandTables.STAR_TYPE_CHANCES);
		
		workObject = new Star(type, StarAttributes.getLuminosity(type));
		workObject.setID(this.seed);
		
		return workObject;
	}
}
