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
	
	public ChanceTable getChanceTable(String name) {
		return chanceTables.stream()
				.filter(table -> name.equals(table.getName()))
				.findAny()
				.orElse(null);
	}
	
	/*
	 * This will load a given profile from the filesystem, named after the given directory. Will load the entire tree below it.
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
