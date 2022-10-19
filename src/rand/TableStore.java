package rand;

import java.util.ArrayList;

public class TableStore {
	
	private FileWorker worker;
	private static String profileStart = System.getProperty("user.dir") + "//config";
	
	private ArrayList<ChanceTable> chanceTables;

	public TableStore() {
		this.worker = new FileWorker();
		this.chanceTables = new ArrayList<ChanceTable>();
		loadProfile("default");
	}
	/**Gets a given ChanceTable from storage based on name.
	 * @param name Name of the table to find
	 * @return The ChanceTable associated with the name
	 */
	public ChanceTable getChanceTable(String name) {
		return chanceTables.stream()
				.filter(table -> name.equals(table.getName()))
				.findAny()
				.orElse(null);
	}
	
	/**
	 * This will load a given profile from the filesystem, named after the given directory. Will load the entire tree below it.
	 * Example: submitting "profile" will load all files from "PROJECT_DIR/config/profile". The newest profile will overwrite any
	 * old tables with the same name. The default profile is automatically loaded on object initialization.
	 * @param profile Profile to be loaded. The name of the directory effectively.
	 */
	public void loadProfile(String profile) {
		String profilePath = profileStart + "//" + profile;
		ArrayList<ChanceTable> profileImport = ChanceTableHelper.JsonTableToChanceTable(worker.recursiveImport(profilePath));
		for (int i = 0; i < chanceTables.size(); i++) {
			for (ChanceTable profileElement : profileImport) {
				if (chanceTables.get(i).getName().equals(profileElement.getName())) {
					chanceTables.remove(i);
					i--;
				}
			}
		}
		chanceTables.addAll(profileImport);
	}
}
