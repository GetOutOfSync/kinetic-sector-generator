package rand;

public class ChanceTable {
	
	private ChanceResult[] table;
	private int totalChance;
	private String name;
	
	public ChanceTable(String name, ChanceResult[] table) {
		this.table = table;
		this.name = name;
		this.totalChance = 0;
		
		for (ChanceResult chance : table) {
			this.totalChance += chance.getChance();
		}
	}
	
	public String getResult(int roll) {
		int comp = 0;
		for (ChanceResult chance : table) {
			comp += chance.getChance();
			if (roll <= comp) return chance.getResult();
		}
		return "null";
	}
	
	public int getTotalChance() {
		return this.totalChance;
	}
	
	public String getName() {
		return this.name;	
	}
	
}
