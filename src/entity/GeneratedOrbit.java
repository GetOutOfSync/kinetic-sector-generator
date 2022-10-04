package entity;

import java.util.ArrayList;

public abstract class GeneratedOrbit extends TaggedObject {
	
	protected long parentID;
	protected int orbitZone;
	protected ArrayList<GeneratedOrbit> orbitTable;

	public GeneratedOrbit() {
		super();
		this.orbitZone = 0;
		this.orbitTable = new ArrayList<GeneratedOrbit>();
	}
	
	public int getZone() {
		return this.orbitZone;
	}
	
	public void setOrbitZone(int orbitZone) {
		this.orbitZone = orbitZone;
	}
	
	/**
	 * @param object The object which will orbit the current object
	 * @param zone What zone the object will orbit in.
	 */
	public void addOrbit (GeneratedOrbit object, int zone) {
		object.setParentID(this.id);
		object.setOrbitZone(zone);
		
		for (int i = 0; i < orbitTable.size(); i++) {
			if (zone <= orbitTable.get(i).orbitZone) {
				orbitTable.add(i, object);
				return;
			}
		}
		orbitTable.add(object);
	}
	
	/**
	 * @param zone What zone you want to see the orbiting object
	 * @return The orbiting object occupying the zone. Returns null if no object is orbiting in that zone.
	 */
	public TaggedObject findOrbit (int zone) {
		for (GeneratedOrbit object : orbitTable) {
			if (zone == object.getZone()) return object;
		}
		
		return null;
	}
	
	/**
	 * @param zone Which zone to check
	 * @return True if the orbit is occupied by an object, False if no object exists.
	 */
	public boolean orbitExists (int zone) {
		for (GeneratedOrbit object : orbitTable) {
			if (zone == object.getZone()) return true;
		}
		return false;
	}
	
	public long getParentID() {
		return this.parentID;
	}
	
	public void setParentID (long parentID) {
		this.parentID = parentID;
	}
	
	public String getStats() {
		return this.type + " : " + getID() + "\n";
	}
	
	public ArrayList<GeneratedOrbit> getOrbitTable() {
		return this.orbitTable;
	}
}
