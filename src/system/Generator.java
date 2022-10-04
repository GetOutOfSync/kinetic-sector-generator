package system;

import java.util.SplittableRandom;

/**
 * 
 * @author Tanner Hansen
 *
 */
public class Generator {
	
	private long seed;
	private SplittableRandom rand;

	public Generator() {
		this.rand = new SplittableRandom();
		seed = this.rand.nextLong();
		this.rand = new SplittableRandom(seed);
	}
	
	public Generator(long seed) {
		this.seed = seed;
		this.rand = new SplittableRandom(seed);
	}
	
	/** @return Seed of the generator*/
	public long getSeed() {
		return this.seed;
	}
	
	/** @return Generates new random seed. Does not set the seed for this generator**/
	public long genSeed() {
		return this.rand.nextLong();
	}
	
	/**@return Random number between 0 and max exclusive.
	 * @param max Largest possible number*/
	public int roll(int max) {
		return roll(max, false);
	}
	
	/**@return Random number between 0 and max exclusive or 1 and max inclusive.
	 * @param max Largest possible number
	 * @param inclusive Decides whether to include max as a possible roll*/
	public int roll(int max, boolean inclusive) {
		if (inclusive) return this.rand.nextInt(max) + 1;
		else return this.rand.nextInt(max);
	}
	
	 /** @return Random number between 1 and 100 inclusive
	 */
	public int rollPercent() {
		return this.rand.nextInt(100) + 1;
	}
	
	/**@param times Number of dice to roll
	 * @param max Random number between 1 and max inclusive.
	 * @return Randomly generated number
	 */
	public int rollMany(int times, int max) {
		int total = 0;
		for (int i = 0; i < times; i++) {
			total += roll(max);
		}
		return total + times;
	}
	
	public String rollOnTable(ChanceTable table) {
		return table.getResult(roll(table.getTotalChance(), true));
	}
	
	public boolean check(int min, int max) {
		return roll(max, true) > min;
	}
}