package com;

import org.apache.commons.lang3.StringUtils;

public class Route {

	private final String _regEx;

	public Route(String regEx) {
		_regEx = regEx;
	}

	public String regEx() {
		return _regEx;
	}
	
	@Override
	public String toString() {
		return _regEx.replaceAll("/\\?", StringUtils.EMPTY);
	}
}
