package manager;
import system.ChanceTable;

public enum PlanetRandTables {
	PlanetRandTables(){};
	
	public static final ChanceTable PLANET_TYPE = new ChanceTable("planet//planet_type.json");
	
	public static final ChanceTable MOON_TYPE = new ChanceTable("planet//moon_tables//moon_type.json");
	
	public static final ChanceTable TIER_1_MOON = new ChanceTable("planet//moon_tables//tier_1_moon.json");
	public static final ChanceTable TIER_2_MOON = new ChanceTable("planet//moon_tables//tier_2_moon.json");
	public static final ChanceTable TIER_3_MOON = new ChanceTable("planet//moon_tables//tier_3_moon.json");
	public static final ChanceTable TIER_4_MOON = new ChanceTable("planet//moon_tables//tier_4_moon.json");
	public static final ChanceTable TIER_5_MOON = new ChanceTable("planet//moon_tables//tier_5_moon.json");
	
	
	public static final ChanceTable TIER_1_ATMO = new ChanceTable("planet//atmo_tables//tier_1_atmo.json");
	public static final ChanceTable TIER_2_ATMO = new ChanceTable("planet//atmo_tables//tier_2_atmo.json");
	public static final ChanceTable TIER_3_ATMO = new ChanceTable("planet//atmo_tables//tier_3_atmo.json");
	public static final ChanceTable TIER_4_ATMO = new ChanceTable("planet//atmo_tables//tier_4_atmo.json");
	public static final ChanceTable TIER_5_ATMO = new ChanceTable("planet//atmo_tables//tier_5_atmo.json");
}
