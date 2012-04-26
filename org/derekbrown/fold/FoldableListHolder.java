package org.derekbrown.fold;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a fold left, motivated by Scala's <a href="http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Seq">foldLeft</a>.
 * 
 * @author Derek Brown
 *
 * @param <A> The list type.
 * @param <B> The fold result type.
 */
public class FoldableListHolder<A, B> {

	/**
	 * Represents a function.
	 * 
	 * @author Derek Brown
	 *
	 * @param <A> The element type
	 * @param <B> The return type.
	 */
	public interface Function<A, B> {
		B op(B acc, A val);
	}

	private List<A> list;

	public FoldableListHolder(final List<A> list) {
		this.list = list;
	}
	
	/**
	 * See <a href="http://www.scala-lang.org/api/current/index.html#scala.collection.immutable.Seq">the docs for Scala's foldLeft</a> for the motivation.
	 */
	public B foldLeft(B acc, Function<A, B> function) {
		if (list.isEmpty()) {
			return acc;
		}
		
		A head = list.remove(0);
		acc = function.op(acc,  head);
		
		return foldLeft(acc, function);
	} // foldLeft
	
	public static void main(String[] args) {
		// Fold with addition
		Function<Integer, Integer> functionAdd = new Function<Integer, Integer>() {
			public Integer op(Integer acc, Integer val) {
				return acc + val;
			}
		};
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		
		FoldableListHolder<Integer, Integer> holderAdd = new FoldableListHolder<Integer, Integer>(list);
		Integer result = holderAdd.foldLeft(new Integer(0), functionAdd);
		System.out.println(result);
		
		// Fold with multiplication
		Function<Integer, Integer> functionMultiply = new Function<Integer, Integer>() {
			public Integer op(Integer acc, Integer val) {
				return acc * val;
			}
		};
		
		list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(4);
		
		FoldableListHolder<Integer, Integer> holderMultiply = new FoldableListHolder<Integer, Integer>(list);
		result = holderMultiply.foldLeft(new Integer(1), functionMultiply);
		System.out.println(result);
		
		// Fold with string append
		Function<Character, String> functionAppend = new Function<Character, String>() {
			public String op(String acc, Character val) {
				return acc + val;
			}
		};
		
		List<Character> listChars = new ArrayList<Character>();
		listChars.add('f');
		listChars.add('o');
		listChars.add('o');
		listChars.add('b');
		listChars.add('a');
		listChars.add('r');
		
		FoldableListHolder<Character, String> holderAppend = new FoldableListHolder<Character, String>(listChars);
		String resultString = holderAppend.foldLeft("", functionAppend);
		System.out.println(resultString);
		
	} // main
	
} // FoldableListHolder
