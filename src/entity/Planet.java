package entity;

import java.util.ArrayList;
import java.util.Arrays;

import terrain.Terrain;

public class Planet extends GeneratedOrbit {
	
	private double mass;
	private double gravity;
	private double diameter;
	
	private String atmosphere;
	private String temperature;
	private String zoneLabel;
	
	//This will hold all of the terrain the planet has.
	private int waterValue;
	private ArrayList<Terrain> terrain;
	private int[] habitabilityMatrix;
	
	private boolean lifeSeeded;
	private int terrainSlots;
	
	public Planet(String type) {
		// TODO Auto-generated constructor stub
		super();
		super.type = type;
		this.zoneLabel = "Far";
		this.lifeSeeded = false;
		this.terrain = new ArrayList<Terrain>();
	}
	
	/**
	 * @return This planet's atmosphere. Can return Very Thin, Thin, Moderate, Dense, and Very Dense
	 */
	public String getAtmosphere() {
		return this.atmosphere;
	}
	
	/**
	 * @return This planet's diameter, with Earth being 1.0
	 */
	public double getDiameter() {
		return diameter;
	}
	
	public int[] getHabitabilityMatrix() {
		return this.habitabilityMatrix;
	}
	
	/**
	 * @return This planet's gravity, with Earth being 1.0
	 */
	public double getGravity() {
		return this.gravity;
	}
	
	/**
	 * @return This planet's mass, with Earth being 1.0
	 */
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * @return Basic information about the planet in a vaguely readable format.
	 * @TODO Improve readability and hook up all of the basic information better.
	 */
	@Override
	public String getStats() {
		String holder = type + " : " + id + "\n"
				+ "-Parent ID: " + this.parentID + "\n"
				+ "-Atmosphere: " + this.atmosphere + "\n"
				+ "-Distance: " + this.zoneLabel + "\n"
				+ "-Diameter: " + this.diameter + "\n"
				+ "-Gravity: " + this.gravity + "\n"
				+ "-Mass: " + this.mass + "\n"
				+ "-Temperature: " + this.temperature + "\n"
				+ "-Habitability Matrix: " + Arrays.toString(this.habitabilityMatrix) + "\n"
				+ "-Terrain Slot Number: " + this.terrainSlots + "\n"
				+ "-Terrain: " + printTerrain() + "\n"
				+ "-Water Value: " + this.waterValue + "\n"
				+ "-Life Seeded: " + this.lifeSeeded;
		for (GeneratedOrbit object : orbitTable) {
			holder += "\n\n";
			holder += "-Moon Zone " + object.getZone() + " - " + object.getStats();
		}
		return holder;
	}
	
	public int getWaterValue() {
		return waterValue;
	}
	
	public int getTerrainSlots() {
		return terrainSlots;
	}
	
	public String printTerrain() {
		String holder = "[";
		for (Terrain terrainSlot : terrain) {
			holder += terrainSlot.type + ", ";
		}
		holder += "]";
		return holder;
	}
	
	/**
	 * @return This planet's temperature. Can return Inferno, Hot, Moderate, Cold, Frozen.
	 */
	public String getTemprature() {
		return this.temperature;
	}
	
	/**
	 * @return The label of how close this planet is to their parent star. Will return "Far" no star is present. If this is a moon, returns the parent's zone label.
	 */
	public String getZoneLabel() {
		return this.zoneLabel;
	}
	
	/**
	 * @return Whether this planet can host life.
	 * @TODO Remove lifeSeeded, as it is a placeholder.
	 */
	public boolean lifeSeeded() {
		return this.lifeSeeded;
	}
	
	/**
	 * @param atmosphere New planetary atmosphere.
	 */
	public void setAtmosphere(String atmosphere) {
		this.atmosphere = atmosphere;
	}
	
	public void setHabitabilityMatrix(int[] matrix) {
		this.habitabilityMatrix = matrix;
	}
	
	/**
	 * @param temp New planetary ambient temperature.
	 */
	public void setTemperature(String temp) {
		this.temperature = temp;
	}
	
	/**
	 * @param lifeSeeded Whether this planet could contain life.
	 * @TODO Remove lifeSeeded, as it is a placeholder.
	 */
	public void setLifeSeeded (boolean lifeSeeded) {
		this.lifeSeeded = lifeSeeded;
	}
	
	/**
	 * @param diameter Diameter of planet, with Earth being 1.0
	 * @param gravity Gravity of planet, with Earth being 1.0
	 * @param mass Mass of planet, with Earth being 1.0
	 */
	public void setSize (double diameter, double gravity, double mass) {
		this.diameter = Math.round(diameter * 100.0) / 100.0;
		this.gravity = Math.round(gravity * 100.0) / 100.0;
		this.mass = Math.round(mass * 100.0) / 100.0;
	}
	
	/**
	 * @param terrainSlots How many terrain slots this planet has in total.
	 */
	public void setTerrainNum(int terrainSlots) {
		this.terrainSlots= terrainSlots;
	}
	
	public void importTerrainSlots(ArrayList<Terrain> terrain) {
		this.terrain = terrain;
		this.waterValue = 0;
		for (Terrain terrainSlot : terrain) {
			this.waterValue += terrainSlot.getWaterValue();
		}
	}
	
	/**
	 * @param label Sets the zone label for calculation for heat and other variables.
	 */
	public void setZoneLabel (String label) {
		this.zoneLabel = label;
	}
}