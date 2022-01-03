package model;

/**
 * A transition of a transition system.
 * 
 * @author Franck van Breugel
 */
public final class Transition {
	int source;
	int target;

	/**
	 * Initializes this transition with the given source and target state.
	 * 
	 * @param source the source state of this transition
	 * @param target the target state of this transition
	 */
	public Transition(int source, int target) {
		this.source = source;
		this.target = target;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof Transition) {
			Transition other = (Transition) object;
			return this.source == other.source && this.target == other.target;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		return this.source + PRIME * this.target;
	}
	
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.source, this.target);
	}
	
	/**
	 * Returns a dot representation of this transition.
	 * 
	 * @return a dot representation of this transition
	 */
	public String toDot() {
		return String.format("%d -> %d", this.source, this.target);
	}
}
