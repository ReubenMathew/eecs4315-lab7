package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * A transition system.
 * 
 * @author Franck van Breugel
 */
public final class TransitionSystem {
	private int states;
	private Set<Transition> transitions;
	private Map<Integer, Set<String>> labelling;

	/**
	 * Initializes this transition system with the given number of states,
	 * transitions, and labelling.
	 * 
	 * @param states the number of states of this transition system
	 * @pre. states > 0
	 * @param transitions the transitions of this transition system
	 * @pre. transitions != null
	 * @param labelling the labelling of this transition system
	 * @pre. labelling != null
	 */
	private TransitionSystem(int states, Set<Transition> transitions, Map<Integer, Set<String>> labelling) {
		super();
		this.states = states;
		this.transitions = transitions;
		this.labelling = labelling;
	}

	/**
	 * Randomness.
	 */
	private static Random random = new Random(System.currentTimeMillis());
	
	/**
	 * Returns a random transition system with the given number of states.
	 * Each state has at least one outgoing transition.
	 * 
	 * @param states the number of states of the transition system
	 * @pre. states > 0
	 * @return a random transition system
	 */
	public static TransitionSystem random(int states) {
		Set<Transition> transitions = new HashSet<Transition>();
		double probability = 2 * Math.log(states) / states;
		for (int source = 0; source < states; source++) {
			boolean hasOutgoingTransition = false;
			for (int target = 0; target < states; target++) {
				if (random.nextDouble() < probability) {
					transitions.add(new Transition(source, target));
					hasOutgoingTransition = true;
				}
			}
			if (!hasOutgoingTransition) {
				transitions.add(new Transition(source, source));
			}
		}

		Map<Integer, Set<String>> labelling = new HashMap<Integer, Set<String>>();
		for (int s = 0; s < states; s++) {
			int number = random.nextInt('f' - 'a');
			Set<String> labels = new TreeSet<String>();
			for (int n = 0; n < number; n++) {
				char letter = (char) ('a' + random.nextInt('f' - 'a'));
				labels.add("" + letter);
			}
			labelling.put(s, labels);
		}
		
		return new TransitionSystem(states, transitions, labelling);
	}
	
	@Override
	public boolean equals(Object object) {
		if (object != null && object instanceof TransitionSystem) {
			TransitionSystem other = (TransitionSystem) object;
			return this.states == other.states && this.transitions.equals(other.transitions) && this.labelling.equals(other.labelling);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 37;
		return this.states + PRIME * (this.transitions.hashCode() + PRIME * this.labelling.hashCode());
	}
	
	@Override
	public String toString() {
		StringBuffer toString = new StringBuffer();
		toString.append("<");
		toString.append(this.transitions);
		toString.append(", ");
		toString.append(this.labelling);
		toString.append(">");
		return toString.toString();
	}
	
	/**
	 * Returns the labels of this transition system.
	 * 
	 * @return the labels of this transition system.
	 */
	private List<String> getLabels() {
		Set<String> labels = new HashSet<String>();
		for (Set<String> value : this.labelling.values()) {
			labels.addAll(value);
		}
		return new ArrayList<String>(labels);
	}
	
	/**
	 * Returns a dot representation of this transition system.
	 * 
	 * @return a dot representation of this transition system
	 */
	public String toDot() {
		StringBuffer toDot = new StringBuffer();
		toDot.append("digraph system {\n");
		toDot.append("  node [colorscheme=\"set312\" style=wedged]\n");
		
		for (Transition transition : this.transitions) {
			toDot.append("  " + transition.toDot() + "\n");
		}
		
		List<String> labels = this.getLabels();
		for (int s = 0; s < this.states; s++) {
			Set<String> label = this.labelling.get(s);
			if (!label.isEmpty()) {
				StringBuffer colours = new StringBuffer();
				for (String l : label) {
					colours.append(":" + (1 + labels.indexOf(l)));
				}
				colours.deleteCharAt(0); // remove first :
				
				if (label.size() == 1) {
					toDot.append(String.format("  %d [style=filled fillcolor=%s]\n", s, colours.toString()));
				} else {
					toDot.append(String.format("  %d [fillcolor=\"%s\"]\n", s, colours.toString()));
				}
			}
		}
		toDot.append("}\n");
		return toDot.toString();
	}
}
