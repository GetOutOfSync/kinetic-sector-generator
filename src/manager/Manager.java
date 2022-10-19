package manager;

import java.util.ArrayList;

import rand.Generator;
import rand.TableStore;

public abstract class Manager {
	
	protected long seed;
	protected Generator gen;
	protected TableStore stor;
	protected ArrayList<Manager> childManagers;

	public Manager() {
		this.gen = new Generator();
		this.seed = gen.getSeed();
		this.childManagers = new ArrayList<Manager>();
		initManagers();
	}
	
	public Manager(long seed) {
		setSeed(seed);
	}
	
	/**
	 * @return Current seed used by the generator.
	 */
	public long getSeed() {
		return seed;
	}
	
	/**
	 * This function is used to rebuild the generator with a new seed, and managers will frequently change seeds.
	 * @param seed What seed will the generator use to create random numbers.
	 */
	public void setSeed(long seed) {
		this.seed = seed;
		this.gen = new Generator(seed);
	}
	
	/**
	 * This function is run on all managers to initialize and store child Managers required to work on objects further down.
	 * This is so we can pass shared objects around, regardless of what level we are testing at.
	 */
	protected abstract void initManagers();
	
	/**
	 * This function adds a child manager to the list for passing around.
	 * @param child Manager required to build out lower objects
	 */
	protected void addChildManager(Manager child) {
		this.childManagers.add(child);
	}
	
	/** Gets a list of this managers children and their children as well.
	 * @return Complete list of required managers for this manager.
	 */
	public ArrayList<Manager> getChildManagers() {
		ArrayList<Manager> holder = new ArrayList<>(childManagers);
		if (childManagers != null) {
			for (Manager child : childManagers) {
				holder.addAll(child.getChildManagers());
			}
		}
		return holder;
	}
	
	public void addTableStor (TableStore stor) {
		this.stor = stor;
		if (this.childManagers != null) {
			for (Manager child : this.childManagers) {
				child.addTableStor(stor);
			}
		}
	}
	
}
