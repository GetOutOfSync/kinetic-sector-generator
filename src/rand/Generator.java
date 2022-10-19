package rand;

import java.util.Arrays;
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
		return roll(100, true);
	}
	
	/**@param times Number of dice to roll
	 * @param max Random number between 1 and max inclusive.
	 * @return Randomly generated number
	 */
	public int rollMany(int times, int max) {
		int total = 0;
		for (int i = 0; i < times; i++) {
			total += roll(max, true);
		}
		return total;
	}
	
	/**
	 * This method expects a dice roll to be first in its current implementation, so 1d100 or whatever needs to go before any math.
	 * Failure to follow will lead to strange issues I may or may not bother fixing. Can include just a raw number without math, like "5".
	 * @param string String which needs to be processed into a random roll (Example: "2d6+10") Do not include whitespace.
	 * @return Randomly generated number
	 */
	public int parseString(String string) {
		String[] numberArray = string.split("[d+*//-]");
		if (numberArray.length == 1) return getValue(numberArray[0]);
		String operators = string.replaceAll("[^d+*//-]","");
		int holder = 0;
		for (int i = 0; i < operators.length(); i++) {
			switch (operators.charAt(i)) {
				case 'd':
					holder += rollMany(getValue(numberArray[i]), getValue(numberArray[i+1]));
					break;
				case '+':
					holder += getValue(numberArray[i + 1]);
					break;
				case '-':
					holder -= getValue(numberArray[i + 1]);
					break;
				case '*':
					holder *= getValue(numberArray[i + 1]);
					break;
				case '/':
					holder /= getValue(numberArray[i + 1]);
					break;
			}
		}
		return holder;
	}
	
	/*
	 * @return Integer value of a given string. Helper function
	 */
	private int getValue(String string) {
		return Integer.parseInt(string);
	}

	/*
	 * This function takes a ChanceTable object, rolls based on the total weight of the table, and selects a
	 * result based on that roll.
	 * @return
	 */
	public String rollOnTable(ChanceTable table) {
		return table.getResult(roll(table.getTotalChance(), true));
	}
	
	public boolean check(int min, int max) {
		return roll(max, true) > min;
	}
}
