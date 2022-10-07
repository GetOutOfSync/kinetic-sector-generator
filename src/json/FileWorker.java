package json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;


public class FileWorker {
	private Gson worker;

	public FileWorker() {
		worker = new GsonBuilder().setExclusionStrategies(new AnnotationExclusionStrategy()).create();
	}
	
	public String objectToJson(Object object) {
		return worker.toJson(object);
	}
	
	public void exportObject(Object object, String fileLocation) {
		FileWriter writer;
		try {
			writer = new FileWriter(fileLocation);
			worker.toJson(object, writer);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(fileLocation + " not accessable. Double check your location");
			e1.printStackTrace();
		}
	}
	
	public String importConfig(String profile, String filename) {
		String primaryPath = System.getProperty("user.dir") + "\\config\\" + profile + "\\" + filename;
		String defaultPath = System.getProperty("user.dir") + "\\config\\default\\" + filename;
		if (checkPath(primaryPath)) return importObject(primaryPath);
		else return importObject(defaultPath);
	}
	
	public JsonObject importRandTable(String profile, String filename) {
		String primaryPath = System.getProperty("user.dir") + "\\config\\" + profile + "\\" + filename;
		String defaultPath = System.getProperty("user.dir") + "\\config\\default\\" + filename;
		if (checkPath(primaryPath)) return importObjectAsJSON(primaryPath);
		else return importObjectAsJSON(defaultPath);
	}
	
	public JsonObject importObjectAsJSON(String path) {
		if (checkPath(path)) {
			FileReader reader;
			try {
				reader = new FileReader(path);
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
	
	public String importObject(String path) {
		if (checkPath(path)) {
			FileReader reader;
			try {
				reader = new FileReader(path);
				JsonElement holder = worker.fromJson(reader, JsonElement.class);
				reader.close();
				return worker.toJson(holder);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "null";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "null";
			}
			
		}
		else return "null";
	}
	
	private boolean checkPath(String path) {
		return Files.isReadable(Paths.get(path));
	}
}
