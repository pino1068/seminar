package com.seminar.model.rule;

public class MaxLength implements Rule {

	private final int _length;

	public MaxLength(int length) {
		_length = length;
	}

	@Override
	public boolean applyOn(String what) {
		return what != null && what.length() <= _length;
	}

	@Override
	public String message() {
		return "must have no more than " + _length + " chars";
	}
}
