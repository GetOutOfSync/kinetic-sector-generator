package terrain;

import entity.TaggedObject;

public abstract class Terrain extends TaggedObject {
	/**
	 * NOTABLE TERRAIN ATTRIBUTES
	 * 
	 * Water Value
	 * Mineral Value
	 * Food Value
	 * Tags?
	 */

	protected int waterValue;
	protected int mineralValue;
	protected int foodValue;
	
	public Terrain(String type) {
		this.type = type;
		tags.add(type);
		this.foodValue = 0;
		this.mineralValue = 0;
		this.waterValue = 0;
	}
	
	public int getFoodValue() {
		return this.foodValue;
	}
	
	protected void setFoodValue(int foodValue) {
		this.foodValue = foodValue;
	}
	
	public int getMineralValue() {
		return this.mineralValue;
	}
	
	protected void setMineralValue(int mineralValue) {
		this.mineralValue = mineralValue;
	}
	
	public int getWaterValue() {
		return this.waterValue;
	}
	
	protected void setWaterValue(int waterValue) {
		this.waterValue = waterValue;
	}
}
