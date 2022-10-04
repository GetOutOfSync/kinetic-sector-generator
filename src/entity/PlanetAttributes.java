package entity;

public enum PlanetAttributes {
	PlanetAttributes() {};
	
	/** Creates a quick reference habitability matrix based on planetary attributes.
	 * 
	 * Example: [0, 0, 0] This means the planet almost perfect for life! 
	 * Example: [1, 1, 1] This means the planet has a Dense atmosphere, an above average gravity, and its hot.
	 * The habitability matrix is primarily used for determining life compatibility.
	 * 
	 * @param planet Planet to pull attributes from
	 * @return Completed habitability matrix
	 */
	public static int[] createHabitabilityMatrix(Planet planet) {
		return new int[] {getAtmoNum(planet.getAtmosphere()), getGravNum(planet.getGravity()), getTempNum(planet.getTemprature())};
	}
	
	/**
	 * @param gravity Gravity of the Planet, with Earth being 1.0
	 * @param diameter Diameter of the Planet, with Earth being 1.0
	 * @return Mass of the Planet, with Earth being 1.0. Will automatically set to 0.01 if mass is smaller than that.
	 */
	public static double massCalc(double gravity, double diameter) {
		double mass = gravity * Math.pow(diameter, 2);
		if (mass < 0.01) return 0.01;
		return mass;
	}
	/**
	 * ATMOSPHERE SECTION
	*/
	public static final String ATMO_LABEL_VDENSE = "Very Dense";
	public static final String ATMO_LABEL_DENSE = "Dense";
	public static final String ATMO_LABEL_MODERATE = "Moderate";
	public static final String ATMO_LABEL_THIN = "Thin";
	public static final String ATMO_LABEL_NONE = "None";
	
	/** Function takes an atmosphere label and returns the integer representation of the label.
	 * @param atmo Planetary atmosphere label
	 * @return Integer representation of the label, with 0 being ideal.
	 */
	public static int getAtmoNum(String atmo) {
		switch (atmo) {
			case ATMO_LABEL_VDENSE: return 2;
			case ATMO_LABEL_DENSE: return 1;
			case ATMO_LABEL_MODERATE: return 0;
			case ATMO_LABEL_THIN: return -1;
			case ATMO_LABEL_NONE: return -2;
			default: return -2;
		}
	}
	
	public static final double GRAV_TIER_1 = 0.4;
	public static final double GRAV_TIER_2 = 0.8;
	public static final double GRAV_TIER_3 = 1.2;
	public static final double GRAV_TIER_4 = 1.6;
	public static final double GRAV_TIER_5 = 2.0;
	
	/** Function takes a planet's gravity and returns the integer representation of the label.
	 * @param gravity Planetary gravity.
	 * @return Integer representation of the label, with 0 being ideal.
	 */
	public static int getGravNum(double gravity) {
		if (gravity <= GRAV_TIER_1) return -2;
		else if (gravity <= GRAV_TIER_2) return -1;
		else if (gravity <= GRAV_TIER_3) return 0;
		else if (gravity <= GRAV_TIER_4) return 1;
		else if (gravity <= GRAV_TIER_5) return 2;
		else return 3;
	}
	
	public static final String PLANET_TYPE_DWARF = "Dwarf";
	public static final String PLANET_TYPE_TERRESTRIAL = "Terrestrial";
	public static final String PLANET_TYPE_ASTEROID = "Asteroid Belt";
	public static final String PLANET_TYPE_GAS_GIANT = "Gas Giant";
	public static final String PLANET_TYPE_COMET = "Comet";
	
	public static final String ZONE_LABEL_NEAR = "Near";
	public static final String ZONE_LABEL_INNER = "Inner";
	public static final String ZONE_LABEL_HABITABLE = "Habitable";
	public static final String ZONE_LABEL_OUTER = "Outer";
	public static final String ZONE_LABEL_FAR = "Far";
	
	/** Function takes a zone label and returns the integer representation of the label.
	 * @param label Planetary zone label
	 * @return Integer representation of the label, with 0 being ideal.
	 */
	public static int getProxNum (String label) {
		switch (label) {
			case ZONE_LABEL_NEAR: return -2;
			case ZONE_LABEL_INNER: return -1;
			case ZONE_LABEL_HABITABLE: return 0;
			case ZONE_LABEL_OUTER: return 1;
			case ZONE_LABEL_FAR: return 2;
			default: return 2;
		}
	}
	
	public static final String TEMP_LABEL_INFERNO = "Inferno";
	public static final String TEMP_LABEL_HOT = "Hot";
	public static final String TEMP_LABEL_MODERATE = "Moderate";
	public static final String TEMP_LABEL_COLD = "Cold";
	public static final String TEMP_LABEL_FROZEN = "Frozen";
	
	/** Function takes a temperature label and returns the integer representation of the label.
	 * @param temp Planetary temperature label
	 * @return Integer representation of the label, with 0 being ideal.
	 */
	public static int getTempNum(String temp) {
		switch (temp) {
			case TEMP_LABEL_FROZEN: return -2;
			case TEMP_LABEL_COLD: return -1;
			case TEMP_LABEL_MODERATE: return 0;
			case TEMP_LABEL_HOT: return 1;
			case TEMP_LABEL_INFERNO: return 2;
			default: return -2;
		}
	}
	
	public static final String[][] TEMPERATURE_TABLE = {{TEMP_LABEL_HOT, TEMP_LABEL_HOT, TEMP_LABEL_INFERNO, TEMP_LABEL_INFERNO, TEMP_LABEL_INFERNO},
			{TEMP_LABEL_MODERATE, TEMP_LABEL_HOT, TEMP_LABEL_HOT, TEMP_LABEL_HOT, TEMP_LABEL_INFERNO},
			{TEMP_LABEL_COLD, TEMP_LABEL_MODERATE, TEMP_LABEL_MODERATE, TEMP_LABEL_MODERATE, TEMP_LABEL_HOT},
			{TEMP_LABEL_FROZEN, TEMP_LABEL_COLD, TEMP_LABEL_COLD, TEMP_LABEL_COLD, TEMP_LABEL_MODERATE},
			{TEMP_LABEL_FROZEN, TEMP_LABEL_FROZEN, TEMP_LABEL_FROZEN, TEMP_LABEL_COLD, TEMP_LABEL_COLD}};
}
