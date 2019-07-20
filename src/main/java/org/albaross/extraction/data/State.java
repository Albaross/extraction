package org.albaross.extraction.data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class State implements Iterable<String> {

	private final String[] literals;

	public State(Set<String> input) {
		Objects.requireNonNull(input, "literals must nor be null");
		this.literals = input.toArray(new String[input.size()]);
		Arrays.sort(literals);
	}

	private State(String[] literals) {
		this.literals = literals;
	}

	public boolean contains(State other) {
		int j = 0;
		for (int i = 0; i < literals.length; i++)
			if (literals[i].equals(other.literals[j])) j++;

		return other.literals.length == j;
	}

	@Override
	public Iterator<String> iterator() {
		return Arrays.asList(literals).iterator();
	}

	public State combineWith(State other) {
		if (literals.length != other.literals.length) return null;
		final int len = literals.length;

		for (int i = 0; i < len - 1; i++)
			if (!literals[i].equals(other.literals[i])) return null;

		if (literals[len - 1].equals(other.literals[len - 1])) return null;

		final String[] append = new String[]{literals[len - 1], other.literals[len - 1]};
		Arrays.sort(append);

		final String[] combined = new String[len + 1];
		System.arraycopy(literals, 0, combined, 0, len - 1);
		System.arraycopy(append, 0, combined, len - 1, 2);

		return new State(combined);
	}

	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < literals.length; i++) {
			if (i > 0) builder.append(" ^ ");
			builder.append(literals[i]);
		}
		return builder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		State state = (State) o;
		return Arrays.equals(literals, state.literals);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(literals);
	}
}
