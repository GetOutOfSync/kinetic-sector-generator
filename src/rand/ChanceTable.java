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
	
	/**
	 * Pulls a result from its table based on what was rolled.
	 * @param roll Number to pull
	 * @return Whatever entry is stored at that location.
	 */
	public String getResult(int roll) {
		int comp = 0;
		for (ChanceResult chance : table) {
			comp += chance.getChance();
			if (roll <= comp) return chance.getResult();
		}
		return "null";
	}
	
	/**
	 * @return Total size of the table numerically, accounting for the weights of each entry.
	 */
	public int getTotalChance() {
		return this.totalChance;
	}
	
	/**
	 * @return Name of the table.
	 */
	public String getName() {
		return this.name;	
	}
	
}
