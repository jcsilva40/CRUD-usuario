
package com.stfn2.ggas.core.utils;

public class Pair<A, B> {

	private A first;

	private B second;

	/**
	 * Instantiates a new pair.
	 * 
	 * @param first
	 *            the first
	 * @param second
	 *            the second
	 */
	public Pair(A first, B second) {

		super();
		this.first = first;
		this.second = second;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int hashFirst;
		int hashSecond;
		
		if (first != null) {
			hashFirst = first.hashCode();
		} else {
			hashFirst = 0;
		}
		
		if (second != null) {
			hashSecond = second.hashCode();
		} else {
			hashSecond = 0;
		}

		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {

		if(other instanceof Pair) {
			Pair<?, ?> otherPair = (Pair<?, ?>) other;
			return this.first == otherPair.first || (this.first != null && otherPair.first != null && this.first.equals(otherPair.first))
							&& (this.second == otherPair.second
											|| (this.second != null && otherPair.second != null && this.second.equals(otherPair.second)));
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "(" + first + ", " + second + ")";
	}

	public A getFirst() {

		return first;
	}

	public void setFirst(A first) {

		this.first = first;
	}

	public B getSecond() {

		return second;
	}

	public void setSecond(B second) {

		this.second = second;
	}
}
