package system;

public class LocationPair {
	
	private CoordPair coords;
	private long id;

	public LocationPair(int x, int y, long id) {
		this.coords = new CoordPair(x, y);
		this.id = id;
	}
	
	public LocationPair(CoordPair coords, long id) {
		this.coords = coords;
		this.id = id;
	}
	
	public boolean checkCoords (CoordPair pair) {
		return coords.isEqual(pair);
	}
	
	public boolean checkCoords(int x, int y) {
		return coords.isEqual(x, y);
	}
	
	public long getID () {
		return id;
	}
}
