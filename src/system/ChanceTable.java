package system;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import json.FileWorker;

public class ChanceTable {
	
	private ArrayList<ChanceResult> table;
	private int totalChance;
	private String name;
	
	public ChanceTable(String profile, String filename) {
		table = new ArrayList<ChanceResult>();
		
		FileWorker fileWorker = new FileWorker();
		JsonObject json = fileWorker.importRandTable(profile, filename);
		JsonObject jsonTable = json.get("table").getAsJsonObject();
		JsonArray entries = jsonTable.getAsJsonArray("entries");
		
		for (int i = 0; i < entries.size(); i++) {
			JsonObject entry = entries.get(i).getAsJsonObject();
			ChanceResult result = new ChanceResult(entry.get("weight").getAsInt(), entry.get("result").getAsString());
			table.add(result);
		}
		
		this.totalChance = 0;
		for (ChanceResult result : table) {
			this.totalChance += result.getChance();
		}
		
		this.name = json.get("title").getAsString;
	}
	
	public ChanceTable(String filename) {
		this("default", filename);
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
		return this.name	
	}
	
}
