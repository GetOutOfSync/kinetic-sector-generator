package system;

import java.util.ArrayList;

public class ChanceTable {
	
	private ChanceResult[] table;
	private int totalChance;
	
	public ChanceTable(ChanceResult[] table) {
		this.table = table;
		this.totalChance = 0;
		for (ChanceResult result : table) {
			this.totalChance += result.getChance();
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
}