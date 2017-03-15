package com.seminar.model.rule;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class Number implements Rule {

	private final String _what;
	private final Integer _b;
	private final OPERATOR _op;

	public enum OPERATOR {LESS_THAN, GREATER_THAN}
	
	public Number(String what, OPERATOR op,  Integer b) {
		_what = what;
		_op = op;
		_b = b;
	}

	@Override
	public void validate(Set<String> errors) {
		if(!StringUtils.isNumeric(_what) || _b.equals(null)){
			errors.add("must be a number");
		} else {
			Integer number = new Integer(_what);
			String message = "";
			if(_op.equals(OPERATOR.LESS_THAN) && (_b < number)){
				message = "must be less than " + _b;
			}
			if(_op.equals(OPERATOR.GREATER_THAN) && (number < _b)){
				message = "must be greater than " + _b;
			}
			if(!message.isEmpty()){
				errors.add(message);
			}
		}
	}
}
