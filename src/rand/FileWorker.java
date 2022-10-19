package rand;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.*;

public class FileWorker {
	private Gson worker;

	public FileWorker() {
		worker = new GsonBuilder().create();
	}
	
	public JsonObject importRandTable(String profile, String filename) {
		String primaryPath = System.getProperty("user.dir") + "\\config\\" + profile + "\\" + filename;
		String defaultPath = System.getProperty("user.dir") + "\\config\\default\\" + filename;
		if (checkPath(primaryPath)) return importObjectAsJSON(primaryPath);
		else return importObjectAsJSON(defaultPath);
	}
	
	public JsonObject importObjectAsJSON(String path) {
		return importObjectAsJSON(Paths.get(path));
	}
	
	public JsonObject importObjectAsJSON(Path path) {
		if (Files.isReadable(path)) {
			FileReader reader;
			try {
				reader = new FileReader(path.toString());
				JsonObject holder = worker.fromJson(reader, JsonElement.class).getAsJsonObject();
				reader.close();
				return holder;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public JsonObject[] recursiveImport(String root) {
		try {
			return Files.walk(Paths.get(root))
					.filter(Files::isRegularFile)
					.map(file -> importObjectAsJSON(file))
					.toArray(JsonObject[]::new);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getObjectTitle(JsonObject json) {
		return json.get("title").getAsString();
	}
	
	private boolean checkPath(String path) {
		return Files.isReadable(Paths.get(path));
	}
}
