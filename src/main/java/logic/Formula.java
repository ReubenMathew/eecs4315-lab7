/*
 * Copyright (C)  2021
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package logic;

import java.util.Random;
import java.util.Set;

import model.TransitionSystem;

/**
 * This class represents the CTL formula.
 * 
 * @author Neena Govindhan
 * @author Jonas Laya
 * @author Jessie Leung
 * @author Paul Sison
 * @author Franck van Breugel
 */
public abstract class Formula {
	private static final Random RANDOM = new Random();
	
	/**
	 * Returns a random formula of at most the given depth.
	 * 
	 * @param the maximum depth of the formula
	 * @return a random formula of at most the given depth
	 */
	public static Formula random(int depth) {
		final int BASE_CASES = 2;
		final int INDUCTIVE_CASES = 6;
		
		if (depth == 0) {
			switch (RANDOM.nextInt(BASE_CASES)) {
			case 0 :
				return new True();
			case 1 :
				char letter = (char) ('a' + RANDOM.nextInt('f' - 'a'));
				String name = "" + letter;
				return new AtomicProposition(name);
			default :
				throw new IllegalArgumentException("Illegal argument for switch in base case");
			}
		} else {
			switch (RANDOM.nextInt(BASE_CASES + INDUCTIVE_CASES)) {
			case 0 :
				return new True();
			case 1 :
				char letter = (char) ('a' + RANDOM.nextInt('f' - 'a'));
				String name = "" + letter;
				return new AtomicProposition(name);
			case 2 :
				return new Not(Formula.random(depth - 1));
			case 3: 
				return new And(Formula.random(depth - 1), Formula.random(depth - 1));
			case 4 :
				return new ExistsNext(Formula.random(depth - 1));
			case 5 :
				return new ForAllNext(Formula.random(depth - 1));
			default :
				throw new IllegalArgumentException("Illegal argument for switch in inductive case");
			}
		}
	}
	
	private static final int DEFAULT_DEPTH = 5;
	
	/**
	 * Returns a random formula.
	 * 
	 * @return a random formula 
	 */
	public static Formula random() {
		return Formula.random(Formula.DEFAULT_DEPTH);
	}	

	/**
	 * Returns the hashcode of this formula.
	 * 
	 * @return the hashcode of this formula
	 */
	public abstract int hashCode();
	
	/**
	 * Tests whether this formula is equal to the given object.
	 * 
	 * @param object an object
	 * @return true if this formula is equal to the given object, false otherwise
	 */
	public abstract boolean equals(Object object);
	
	/**
	 * Returns a string representation of this formula.
	 * 
	 * @return a string representation of this formula
	 */
	public abstract String toString();
}
