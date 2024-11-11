package com.stfn2.ggas.core.interfaces;

public interface BaseFilter {
	default String getName() {
		return this.getClass().toString();
	}
}
