package entity;

import java.util.ArrayList;

import entity.Star;
import json.Exclude;

public class SolarSystem extends GeneratedOrbit {
	
	private int totalLuminosity;
	@Exclude
	private ArrayList<Star> starCluster;

	public SolarSystem() {
		// TODO Auto-generated constructor stub
		super();
		totalLuminosity = 0;
		type = "Solar System";
		starCluster = new ArrayList<Star>();
	}
	
	/**
	 * @param star Star to be added to this solar system's star cluster.
	 */
	public void addStar(Star star) {
		starCluster.add(star);
		totalLuminosity += star.getLuminosity();
	}
	
	public Star getPrimaryStar() {
		return starCluster.get(0); 
	}
	
	/**
	 * @return An array containing all planets and their moons in this solar system.
	 */
	public ArrayList<GeneratedOrbit> getAllOrbitObjects () {
		ArrayList<GeneratedOrbit> planets = new ArrayList<GeneratedOrbit>();
		for (GeneratedOrbit planet : getOrbitTable()) {
			planets.addAll(planet.getOrbitTable());
		}
		planets.addAll(getOrbitTable());
		return planets;
	}
	
	/**
	 * @return Total Luminosity of the system, used to calculate total orbits and habitability zones.
	 */
	public int getLuminosity() {
		return totalLuminosity;
	}
	
	public ArrayList<GeneratedOrbit> getOrbitTable() {
		return getPrimaryStar().getOrbitTable();
	}
	
	/**
	 * @return Primary star of the system.
	 */
	public Star getStar() {
		return starCluster.get(0);
	}
	
	/**
	 * @return Entire star cluster table.
	 */
	public ArrayList<Star> getStarCluster() {
		return starCluster;
	}
	
	@Override
	public String getStats() {
		String holder = this.type + " : " + this.id + "\n";
		for (Star star: starCluster) {
			holder += "\n";
			holder += star.getType() + " Type Star - " + star.getID();
		}
		
		for (GeneratedOrbit object : getPrimaryStar().getOrbitTable()) {
			holder += "\n\n";
			holder += "Zone " + object.getZone() + " - " + object.getStats();
		}
		return holder;
	}

}
