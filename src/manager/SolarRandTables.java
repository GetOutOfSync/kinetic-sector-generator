package manager;
import system.ChanceTable;

public enum SolarRandTables {
	SolarRandTables(){};
	
	public static final ChanceTable STAR_NUM_CHANCES = new ChanceTable("default", "solar\\star_num.json");
	public static final ChanceTable STAR_TYPE_CHANCES = new ChanceTable("default", "solar\\star_type.json");
}
