package com.seminar.model.rule;

import java.util.Set;

public class Format implements Rule {

	private final String _what;
	private final String _regEx;

	public Format(String what, String regEx) {
		_what = what;
		_regEx = regEx;
	}

	@Override
	public void validate(Set<String> errors) {
		if(!_what.matches(_regEx)){
			errors.add("must have format " + _regEx);
		}
	}
}
