package rand;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.google.gson.*;

public class FileWorker {
	private Gson worker;

	public FileWorker() {
		worker = new GsonBuilder().create();
	}
	
	/** Imports a json file from the provided path
	 * @param path Path of the file
	 * @return A JsonObject for further work
	 */
	public JsonObject importObjectAsJSON(String path) {
		return importObjectAsJSON(Paths.get(path));
	}
	
	/** Imports a json file from the provided path
	 * @param path Path of the file
	 * @return A JsonObject for further work
	 */
	public JsonObject importObjectAsJSON(Path path) {
		if (Files.isReadable(path) && getExtension(path.toString()).equals("json")) {
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
	
	/**
	 * 
	 * @param root The root folder to start importing json files from
	 * @return
	 */
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
	
	/**
	 * Helper function to quickly pull the title from a formatted JsonObject
	 * @param json JsonObject to pull.
	 * @return Title of formatted json.
	 */
	public static String getObjectTitle(JsonObject json) {
		return json.get("title").getAsString();
	}
	
	/**
	 * Helper function so File Worker can verify that it is pulling the right files.
	 * @param filename Filename of the file to extract
	 * @return Whatever extension the file is, without the period
	 */
	private String getExtension(String filename) {
	    for (int i = filename.length() - 1; i >= 0; i--) {
	    	if(filename.charAt(i) == '.') {
	    		filename = filename.substring(i + 1);
	    		return filename;
	    	}
	    }
	    return filename;
	}
}
