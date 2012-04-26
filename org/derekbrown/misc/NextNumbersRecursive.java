package org.derekbrown.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Find the next N numbers after a starting point made up of the same digits (not including negative sign),
 * in a tail recursive manner (useful if the compiler/JVM did tail-call optimization).
 * 
 * @author Derek Brown
 *
 */
public class NextNumbersRecursive {
	
	final private boolean debug;
	final private int start;
	
	/** The characters of the target number, sorted for comparison later. */
	final private char[] startChars;
	
	public NextNumbersRecursive(final boolean debug, final int start) {
		this.debug = debug;
		this.start = start;
		
		this.startChars = Integer.toString(Math.abs(this.start)).toCharArray();
		Arrays.sort(this.startChars);
	}
	
	/**
	 * Find the numbers.
	 * 
	 * @param start The starting number.
	 * @param howMany How many matches to find
	 * 
	 * @return List of the matching numbers, of size less than or equal to howMany.
	 */
	public List<Integer> find(final int howMany) {
		debug("Seeking first " + howMany + " number" + ((howMany != 1) ? "s" : "") + " after " + start);
		
		List<Integer> accumulator = new ArrayList<Integer>();
		LazyGenerator generator = new LazyGenerator(start + 1);
		
		return find2(generator, howMany, accumulator);
	}
	
	private List<Integer> find2(final LazyGenerator generator, final int howMany,
			final List<Integer> accumulator) {
		
		int curr = generator.head();
		
		// The characters of the current number
		char[] chars = Integer.toString(Math.abs(curr)).toCharArray();

		if (chars.length > startChars.length) {
			// i has more digits than start, so quitting since we'll never have a match now
			return accumulator;
		}

		if (chars.length < startChars.length) {
			// i has fewer digits than start, so continuing until reaching a positive number 
			// with the same number of digits
			return find2(generator.tail(), howMany, accumulator);
		}

		// So let's check if the digits are the same
		Arrays.sort(chars);
		if (Arrays.equals(startChars, chars)) {
			// They're the same!
			accumulator.add(curr);

			if (accumulator.size() >= howMany) {
				return accumulator;
			}
		}
		
		return find2(generator.tail(), howMany, accumulator);
		
	} // find2

	private void debug(final Object o) {
		if (this.debug) {
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		boolean debug = false;
		
		List<Integer> result;
		int start;
		
		start = -49816;
		NextNumbersRecursive app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		start = -213;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		start = 743386;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		// Will end before finding 3 matches
		start = -1000;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		start = -987;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		start = 987;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
		// A large start value that will cause a stack overflow if not using tail recursion.
		// Some of the previous values here could cause it, too.
		// (See comment at end of find2()).
		start = 1234567890;
		app = new NextNumbersRecursive(debug, start);
		result = app.find(3);
		System.out.println(start + ": " + result);
		
	} // main
	
}
