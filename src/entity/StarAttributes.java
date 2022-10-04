package entity;

public enum StarAttributes {
	StarAttributes(){};
	
	public static String STAR_TYPE_O = "O";
	public static String STAR_TYPE_B = "B";
	public static String STAR_TYPE_A = "A";
	public static String STAR_TYPE_F = "F";
	public static String STAR_TYPE_G = "G";
	public static String STAR_TYPE_K = "K";
	public static String STAR_TYPE_M = "M";
	
	
	public static int getLuminosity (String type) {
		switch (type) {
			case "O": return 7;
			case "B": return 6;
			case "A": return 5;
			case "F": return 4;
			case "G": return 3;
			case "K": return 2;
			case "M": return 1;
			default: return -1;
		}
	}
}
