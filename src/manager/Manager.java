package manager;

import system.Generator;

public abstract class Manager {
	
	protected long seed;
	protected Generator gen;

	public Manager() {
		this.gen = new Generator();
		this.seed = gen.getSeed();
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
	
}
