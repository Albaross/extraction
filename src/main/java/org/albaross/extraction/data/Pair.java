package org.albaross.extraction.data;

import java.util.Objects;

public class Pair {

	private final State state;
	private final String action;

	private final int hash;

	public Pair(State state, String action) {
		this.state = Objects.requireNonNull(state, "state must not be null");
		this.action = Objects.requireNonNull(action, "action must not be null");
		this.hash = Objects.hash(state, action);
	}

	public State getState() {
		return state;
	}

	public String getAction() {
		return action;
	}

	@Override
	public String toString() {
		return "(" + state + " : " + action + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair pair = (Pair) o;
		return hash == pair.hash &&
				Objects.equals(state, pair.state) &&
				Objects.equals(action, pair.action);
	}

	@Override
	public int hashCode() {
		return hash;
	}
}
