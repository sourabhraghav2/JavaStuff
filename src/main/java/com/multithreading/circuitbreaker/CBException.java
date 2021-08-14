package com.multithreading.circuitbreaker;

public class CBException extends RuntimeException {
	public CBException(State state) {
		super(state.name());
	}
}
