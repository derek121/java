package org.derekbrown.misc;

/**
 * Simulate a lazy/infinite list.
 * 
 * @author Derek Brown
 *
 */
public class LazyGenerator {
	private int next;
	
	/**
	 * @param next The initial value.
	 */
	public LazyGenerator(final int next) {
		this.next = next;
	}

	/**
	 * @return The next value.
	 */
	public int head() {
		return next;
	}
	
	/**
	 * @return The tail of this simulated list.
	 */
	public LazyGenerator tail() {
		next++;
		return this;
	}
}
