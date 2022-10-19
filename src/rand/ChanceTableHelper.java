package rand;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public final class ChanceTableHelper {

	private ChanceTableHelper() {}
	
	public static ChanceTable JsonToChanceTable(JsonObject json) {
		JsonObject jsonTable = json.get("table").getAsJsonObject();
		JsonArray table = jsonTable.getAsJsonArray("entries");
		
		String title = json.get("title").getAsString();
		ArrayList<ChanceResult> results = new ArrayList<ChanceResult>();
		
		for (int i = 0; i < table.size(); i++) {
			JsonObject entry = table.get(i).getAsJsonObject();
			ChanceResult result = new ChanceResult(entry.get("weight").getAsInt(), entry.get("result").getAsString());
			results.add(result);
		}
		ChanceResult[] completedResults = new ChanceResult[results.size()];
		return new ChanceTable(title, results.toArray(completedResults));
	}
	
	public static ArrayList<ChanceTable> JsonTableToChanceTable(JsonObject[] jsonTable) {
		ArrayList<ChanceTable> table = new ArrayList<ChanceTable>();
		for (JsonObject json : jsonTable) {
			table.add(JsonToChanceTable(json));
		}
		return table;
	}

}
