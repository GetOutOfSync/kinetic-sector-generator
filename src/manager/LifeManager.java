package manager;

import entity.Planet;
import entity.TaggedObject;

public class LifeManager extends Manager {
	
	public static final double MIN_WATER_VALUE = 0.50;
	
	public LifeManager() {
		super();
	}

	public LifeManager(long seed) {
		super(seed);
	}
	
	protected void initManagers(){};
	
	/** This function is still heavily in development, but conceptually this function will take a planet and read its attributes and terrain slots
	 * to determine if life can be supported on the planet, and then decides whether life has taken root.
	 * 
	 * @param planet Planet which will possibly host life.
	 * @return If life has taken root on the planet.
	 */
	public boolean lifeCheck (TaggedObject object) {
		/** MAJOR CHECKS
		 * 
		 * Atmosphere
		 * Gravity
		 * Temperature
		 * Liquid - TODO
		 * 
		 */
		
		Planet planet = (Planet) object;
		
		int score = 0;
		
		int[] matrix = planet.getHabitabilityMatrix();
		for (int i = 0; i < matrix.length; i++) {
			score += Math.abs(matrix[i]);
		}
		
		int size = planet.getTerrainSlots();
		if (size == 0) size = 1;
		
		planet.setLifeSeeded(((double)planet.getWaterValue() / (double)planet.getTerrainSlots()) > MIN_WATER_VALUE);
		
		return planet.lifeSeeded();
	}
	
	/** Quick and dirty function for a printer function somewhere else. Runs the life check and returns true or false as a string.
	 * @param planet Planet which will possibly host life.
	 * @return If life has taken root on the planet.
	 */
	public String printLifeCheck (Planet planet) {
		if (lifeCheck(planet)) return "True";
		else return "False";
	}
}
