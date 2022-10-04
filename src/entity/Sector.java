package entity;

import java.util.ArrayList;

import system.CoordPair;
import system.LocationPair;

public class Sector {
	
	private ArrayList<SolarSystem> systemList = new ArrayList<SolarSystem>();
	private ArrayList<LocationPair> starMap = new ArrayList<LocationPair>();
	
	public Sector() {
		// TODO Auto-generated constructor stub
	}
	
	public void importSolarSystem(SolarSystem system, CoordPair coords) {
		systemList.add(system);
		starMap.add(new LocationPair(coords, system.getID()));
	}
	
	public SolarSystem getSystem(long id) {
		for (SolarSystem system : systemList) {
			if (system.getID() == id) return system;
		}
		return new SolarSystem();
	}
	
	public SolarSystem getSystem(CoordPair coords) {
		long id = 0;
		for (LocationPair location : starMap) {
			if (location.checkCoords(coords)) {
				id = location.getID();
				break;
			}
		}
		
		return getSystem(id);
	}
	
	public SolarSystem getSystem(int x, int y) {
		return getSystem(new CoordPair(x, y));
	}

}
