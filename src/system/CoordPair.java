package system;

public class CoordPair {
	
	private int x;
	private int y;

	public CoordPair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Calculates the distance between this point and a given point.
	 * 
	 * @param pair Target coordinate pair (x, y)
	 * @return Distance between the points
	 */
	public double getDistance(CoordPair pair) {
		return getDistance(pair.getX(), pair.getY());
	}
	
	/** Calculates the distance between this point and a given point.
	 * 
	 * @param targX Target X coordinate
	 * @param targY Target Y coordinate
	 * @return Distance between the points
	 */
	public double getDistance (int targX, int targY) {
		return Math.hypot(targX - x, targY - y);
	}
	
	/** Checks if a given coordinate pair is equal to this coordinate pair.
	 * 
	 * @param pair Given coordinate pair
	 * @return If pairs are equal
	 */
	public boolean isEqual (CoordPair pair) {
		return isEqual(pair.getX(), pair.getY());
	}
	
	/** Checks if a given coordinate pair is equal to this coordinate pair.
	 * 
	 * @param x Given x coordinate
	 * @param y Given y coordinate
	 * @return If pairs are equal
	 */
	public boolean isEqual (int x, int y) {
		return (x == this.x) && (y == this.y);
	}
	
	/**
	 * @return This pair's x value.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return This pair's y value.
	 */
	public int getY() {
		return y;
	}
}
