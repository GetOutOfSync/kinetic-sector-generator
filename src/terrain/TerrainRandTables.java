package terrain;

import system.ChanceResult;
import system.ChanceTable;

public enum TerrainRandTables {
	TerrainRandTables(){};
	
	private static final ChanceResult[] TERRAIN_NO_ATMO_TABLE = {new ChanceResult(1, "Flatlands"),
															new ChanceResult(1, "Mountains"),
															new ChanceResult(2, "Craters"),
															new ChanceResult(1, "Extinct Volcano")};
	public static final ChanceTable TERRAIN_NO_ATMO = new ChanceTable(TERRAIN_NO_ATMO_TABLE);
	private static final ChanceResult[] TERRAIN_INFERNO_TEMP_TABLE = {new ChanceResult(1, "Flatlands"),
																new ChanceResult(1, "Volcanic Flatlands"),
																new ChanceResult(1, "Mountains"),
																new ChanceResult(1, "Volcano"),
																new ChanceResult(1, "Volcanic Lake")};
	public static final ChanceTable TERRAIN_INFERNO_TEMP = new ChanceTable(TERRAIN_INFERNO_TEMP_TABLE);
	private static final ChanceResult[] TERRAIN_HOT_TEMP_TABLE = {new ChanceResult(2, "Flatlands"),
															new ChanceResult(1, "Mountains"),
															new ChanceResult(1, "Volcano"),
															new ChanceResult(1, "Pseudo Aquatic")};
	public static final ChanceTable TERRAIN_HOT_TEMP = new ChanceTable(TERRAIN_HOT_TEMP_TABLE);
	private static final ChanceResult[] TERRAIN_MODERATE_TEMP_TABLE = {new ChanceResult(1, "Flatlands"),
																	new ChanceResult(1, "Mountains"),
																	new ChanceResult(1, "Volcano"),
																	new ChanceResult(2, "Pseudo Aquatic"),
																	new ChanceResult(1, "Aquatic")};
	public static final ChanceTable TERRAIN_MODERATE_TEMP = new ChanceTable(TERRAIN_MODERATE_TEMP_TABLE);
	private static final ChanceResult[] TERRAIN_COLD_TEMP_TABLE = {new ChanceResult(1, "Flatlands"),
																new ChanceResult(1, "Mountains"),
																new ChanceResult(1, "Cryovolcanic"),
																new ChanceResult(1, "Pseudo Aquatic"),
																new ChanceResult(1, "Frozen Ocean")};
	public static final ChanceTable TERRAIN_COLD_TEMP = new ChanceTable(TERRAIN_COLD_TEMP_TABLE);
	private static final ChanceResult[] TERRAIN_FROZEN_TEMP_TABLE = {new ChanceResult(1, "Flatlands"),
																new ChanceResult(1, "Mountains"),
																new ChanceResult(1, "Cryovolcanic"),
																new ChanceResult(1, "Hardened Ice"),
																new ChanceResult(1, "Frozen Ocean")};
	public static final ChanceTable TERRAIN_FROZEN_TEMP = new ChanceTable(TERRAIN_FROZEN_TEMP_TABLE);
}
