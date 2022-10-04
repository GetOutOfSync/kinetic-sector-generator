package entity;

public class Star extends GeneratedOrbit {
	
	private int luminosity;

	public Star(String type, int luminosity) {
		this.type = type;
		this.luminosity = luminosity;
	}
	
	/**
	 * @return This star's Luminosity
	 */
	public int getLuminosity() {
		return luminosity;
	}
}
