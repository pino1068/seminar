package com.seminar.model.rule;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class NotEmpty implements Rule {

	private final String _what;

	public NotEmpty(String what) {
		_what = what;
	}
	
	@Override
	public void validate(Set<String> errors) {
		if(StringUtils.isBlank(_what)){
			errors.add("can't be empty");
		}
	}
}
