package rand;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public final class ChanceTableHelper {

	private ChanceTableHelper() {}
	
	/** Converts a JsonObject in the standard format into a ChanceTable
	 * @param json Formatted JsonObject
	 * @return ChanceTable
	 */
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
	
	/** Converts an entire list of formatted JsonObjects into ChanceTables.
	 * @param jsonTable Table of formatted JsonObjects
	 * @return ArrayList of ChanceTables
	 */
	public static ArrayList<ChanceTable> JsonTableToChanceTable(JsonObject[] jsonTable) {
		ArrayList<ChanceTable> table = new ArrayList<ChanceTable>();
		for (JsonObject json : jsonTable) {
			table.add(JsonToChanceTable(json));
		}
		return table;
	}

}
