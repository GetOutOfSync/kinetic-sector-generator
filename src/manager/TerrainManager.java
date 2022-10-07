/*package manager;

import java.util.ArrayList;

import entity.Planet;
import entity.PlanetAttributes;
import system.ChanceTable;
import terrain.*;

public class TerrainManager extends Manager {
	
	private static final int TERRAIN_SLOT_CONST = 20;
	
	public TerrainManager() {}
	
	public void generateTerrain(Planet planet) {
		generateTerrain(planet, gen.genSeed());
	}
	
	//THIS WILL NEED A HEAVY REWRITE TO BETTER ALIGN 
	public void generateTerrain(Planet planet, long seed) {
		if (planet.getType().equals("Gas Giant")) return;
		setSeed(seed);
		int terrainSlots = (int) Math.round(planet.getDiameter() * TERRAIN_SLOT_CONST);
		planet.setTerrainNum(terrainSlots);
		
		if (planet.getAtmosphere().equals("None")) {
			createTerrainTable(TerrainRandTables.TERRAIN_NO_ATMO, planet);
			return;
		}
		
		switch(planet.getTemprature()) {
			case PlanetAttributes.TEMP_LABEL_INFERNO:
				createTerrainTable(TerrainRandTables.TERRAIN_INFERNO_TEMP, planet);
				break;
			case PlanetAttributes.TEMP_LABEL_HOT:
				createTerrainTable(TerrainRandTables.TERRAIN_HOT_TEMP, planet);
				break;
			case PlanetAttributes.TEMP_LABEL_MODERATE:
				createTerrainTable(TerrainRandTables.TERRAIN_MODERATE_TEMP, planet);
				break;
			case PlanetAttributes.TEMP_LABEL_COLD:
				createTerrainTable(TerrainRandTables.TERRAIN_COLD_TEMP, planet);
				break;
			case PlanetAttributes.TEMP_LABEL_FROZEN:
				createTerrainTable(TerrainRandTables.TERRAIN_FROZEN_TEMP, planet);
				break;
		}
	}
	
	private void createTerrainTable(ChanceTable table, Planet planet) {
		int terrainSlots = planet.getTerrainSlots();
		 ArrayList<Terrain> holder = new ArrayList<Terrain>();
		for (int i = 0; i < terrainSlots; i++) {
			holder.add(convtStrToTerrain(gen.rollOnTable(table)));
		}
		planet.importTerrainSlots(holder);
	}
	
	private Terrain convtStrToTerrain(String string) {
		switch(string) {
			case "Aquatic": return new Aquatic();
			case "Craters": return new Craters();
			case "Cryovolcanic": return new Cryovolcanic();
			case "Extinct Volcano": return new ExtinctVolcano();
			case "Flatlands": return new Flatlands();
			case "Frozen Ocean": return new FrozenOcean();
			case "Hardened Ice": return new HardenedIce();
			case "Hydrogen Lake": return new HydrogenLake();
			case "Mountains": return new Mountains();
			case "Pseudo Aquatic": return new PseudoAquatic();
			case "Volcanic Flatland": return new VolcanicFlatland();
			case "Volcanic Lake": return new VolcanicLake();
			case "Volcano": return new Volcano();
			default : return new Flatlands();
		}
	}
}*/
