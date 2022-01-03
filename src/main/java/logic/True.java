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

/**
 * This class represents the CTL formula true.
 * 
 * @author Neena Govindhan
 * @author Jonas Laya
 * @author Jessie Leung
 * @author Paul Sison
 * @author Franck van Breugel
 */
public class True extends Formula {

	/**
	 * Initializes this CTL formula as true.
	 */
	public True() {}
	
	@Override
	public int hashCode() {
		return 1;
	}
	
	@Override
	public boolean equals(Object object) {
		return object instanceof True;
	}
	
	@Override
	public String toString() {
		return "true";
	}
}