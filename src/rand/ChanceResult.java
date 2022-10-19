package rand;

public class ChanceResult {
	
	private int chance;
	private String result;
	
	public ChanceResult(int chance, String result) {
		this.chance = chance;
		this.result = result;
	}
	
	public int getChance() {
		return chance;
	}
	
	public String getResult() {
		return result;
	}
}
