package system;

import java.util.ArrayList;
import entity.GeneratedOrbit;
import entity.Planet;
import entity.PlanetAttributes;
import entity.SolarSystem;
import entity.TaggedObject;
import json.FileWorker;
import manager.*;

public class Main {
	
	public static final int TESTS = 100000;

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FileWorker worker = new FileWorker();
		SolarGen solarManager = new SolarGen();
		SolarSystem solar = solarManager.newSystem();
		worker.exportObject(solar, "C:\\Users\\Gaming\\Desktop\\Test\\solarv2.json");
		System.out.println(worker.importObject("C:\\Users\\Gaming\\Desktop\\Test\\solarv2.json"));
	}

	public static void planetTest() {
		// TODO Auto-generated method stub
		PlanetGen planetManager = new PlanetGen();
		Planet test  = planetManager.newPlanet();
		TextOutput.println(test.getStats());
	}
	
	public static void solarTest() {
		SolarGen solarManager = new SolarGen();
		SolarSystem solar = solarManager.newSystem();
		TextOutput.println(solar.getStats());
	}
	
	public static int lifeTest() {
		SolarGen solarManager = new SolarGen();
		SolarSystem solar = solarManager.newSystem();
		ArrayList<GeneratedOrbit> planets = solar.getAllOrbitObjects();
		LifeManager lifeManager = new LifeManager();
		TerrainManager terrainManager = new TerrainManager();
		int count = 0;
		for (int i = 0; i < planets.size(); i++) {
			terrainManager.generateTerrain((Planet) planets.get(i));
			if (lifeManager.lifeCheck(planets.get(i))) count++;
		}
		TextOutput.println(solar.getStats());
		System.out.println("Total Life Seeds: " + count);
		
		return count;
	}
	
	@SuppressWarnings("unused")
	public static void averageLifeSeed () {
		int total = 0;
		SolarGen solarManager = new SolarGen();
		LifeManager lifeManager = new LifeManager();
		
		for (int i = 0; i < TESTS; i++) {
			SolarSystem system = solarManager.newSystem();
			TerrainManager terrainManager = new TerrainManager();
			for (TaggedObject object : system.getAllOrbitObjects()) {
				terrainManager.generateTerrain((Planet) object);
				if (lifeManager.lifeCheck(object)) total++;
			}
		}
		
		//System.out.println(systems.get(13).getStats());
		
		System.out.println("\n");
		System.out.println("Total Runs: " + TESTS + "\nTotal Life Seeds: " + total + "\nAverage Life Seeds: " + ((double)total / (double)TESTS));
	}
	
	public static void terrainTest() {
		PlanetGen planetManager = new PlanetGen();
		Planet planet = planetManager.newPlanet();
		planet.setTemperature(PlanetAttributes.TEMP_LABEL_MODERATE);
		
		TerrainManager terrainManager = new TerrainManager();
		terrainManager.generateTerrain(planet);
		System.out.println(planet.getStats());
	}
}
