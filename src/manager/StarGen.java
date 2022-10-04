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
		
		int roll = gen.roll(100, true);
		String type = StarAttributes.STAR_TYPE_M;
		if (roll == 1) type = StarAttributes.STAR_TYPE_O;
		else if (roll == 2) type = StarAttributes.STAR_TYPE_B;
		else if (roll == 3) type = StarAttributes.STAR_TYPE_A;
		else if (roll <= 6) type = StarAttributes.STAR_TYPE_F;
		else if (roll <= 14) type = StarAttributes.STAR_TYPE_G;
		else if (roll <= 26) type = StarAttributes.STAR_TYPE_K;
		
		workObject = new Star(type, StarAttributes.getLuminosity(type));
		workObject.setID(this.seed);
		
		return workObject;
	}
}
