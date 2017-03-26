package com.seminar.model.rule;

import org.apache.commons.lang3.StringUtils;

public class NotEmpty implements Rule {

	@Override
	public boolean applyOn(String what) {
		return !StringUtils.isBlank(what);
	}

	@Override
	public String message() {
		return "can't be empty";
	}
}
