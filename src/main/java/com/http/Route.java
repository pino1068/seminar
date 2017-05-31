package com.http;

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
	
	public String with(Object item){
		return _regEx[0].replaceAll("\\\\d", "").replaceAll("\\+", item.toString());
	}
	
	@Override
	public String toString() {
		return _regEx[0].replaceAll("/\\?", StringUtils.EMPTY);
	}

	public String getId(String uri) {
		String nonNumber = _regEx[0].split("\\\\d+")[0];
		String[] id = uri.split(nonNumber);
		if(id.length>1)
			return id[1];
		return null;
	}
}
