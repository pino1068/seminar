package com;

import org.apache.commons.lang3.StringUtils;

public class Route {

	private final String[] _regEx;

	public Route(String...regEx) {
		_regEx = regEx;
	}

	public boolean matches(String url){
		for (String regEx : _regEx) {
			if(url.matches(regEx)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return _regEx[0].replaceAll("/\\?", StringUtils.EMPTY);
	}
}
