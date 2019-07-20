package org.albaross.extraction.data;

import java.util.Objects;

public class Rule {

	private final State state;
	private final String action;
	private final float weight;

	public Rule(State state, String action, float weight) {
		this.state = Objects.requireNonNull(state, "state must not be null");
		this.action = Objects.requireNonNull(action, "action must not be null");
		this.weight = weight;
	}

	public State getState() {
		return state;
	}

	public String getAction() {
		return action;
	}

	public float getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return state + " => " + action + " [" + weight + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Rule rule = (Rule) o;
		return Float.compare(rule.weight, weight) == 0 &&
				Objects.equals(state, rule.state) &&
				Objects.equals(action, rule.action);
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, action, weight);
	}
}
