package manager;

import system.ChanceResult;
import system.ChanceTable;

public enum SolarRandTables {
	SolarRandTables(){};
	
	public static final ChanceResult[] STAR_NUM_CHANCES = {new ChanceResult(60, "1"), 
															new ChanceResult(35, "2"), 
															new ChanceResult(1, "3"), 
															new ChanceResult(1, "4"), 
															new ChanceResult(1, "5"), 
															new ChanceResult(1, "6"), 
															new ChanceResult(1, "7")};
	public static final ChanceTable STAR_NUM_CHANCE_TABLE = new ChanceTable(STAR_NUM_CHANCES);
}
