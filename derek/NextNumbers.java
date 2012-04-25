package derek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find the next N numbers after a starting point made up of the same digits (not including negative sign).
 * 
 * @author Derek Brown
 *
 */
public class NextNumbers {
	
	private boolean debug;
	
	public NextNumbers(final boolean debug) {
		this.debug = debug;
	}
	
	/**
	 * Find the numbers.
	 * 
	 * @param start The starting number.
	 * @param howMany How many matches to find
	 * 
	 * @return List of the matching numbers, of size less than or equal to howMany.
	 */
	public List<Integer> find(final int start, final int howMany) {
		debug("Seeking first " + howMany + " number" + ((howMany != 1) ? "s" : "") + " after " + start);
		
		List<Integer> results = new ArrayList<Integer>();
		int countFound = 0;
		
		// The characters of the target number, sorted for comparison later
		char[] startChars = Integer.toString(Math.abs(start)).toCharArray();
		Arrays.sort(startChars);
		
		// Start looking at the next value
		for (int i = start + 1; ; i++) {
			// The characters of the current number
			char[] chars = Integer.toString(Math.abs(i)).toCharArray();
			
			if (chars.length > startChars.length) {
				// i has more digits than start, so quitting since we'll never have a match now
				break;
			}
			
			if (chars.length < startChars.length) {
				// i has fewer digits than start, so continuing until reaching a positive number 
				// with the same number of digits
				continue;
			}
			
			// So let's check if the digits are the same
			Arrays.sort(chars);
			if (Arrays.equals(startChars, chars)) {
				// They're the same!
				results.add(i);
				
				if (++countFound >= howMany) {
					break;
				}
			}
			
		} // for...
		
		return results;
		
	} // find

	private void debug(final Object o) {
		if (this.debug) {
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
		boolean debug = false;
		NextNumbers app = new NextNumbers(debug);
		
		List<Integer> result;
		int start;
		
		start = -49816;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
		start = -213;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
		start = 743386;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
		// Will end before finding 3 matches
		start = -1000;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
		start = -987;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
		start = 987;
		result = app.find(start, 3);
		System.out.println(start + ": " + result);
		
	} // main
	
}
