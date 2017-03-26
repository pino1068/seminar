package com.seminar.model.rule;

import java.text.SimpleDateFormat;

public class TimeFormat implements Rule {

	private final String _format;

	public TimeFormat(String format) {
		_format = format;
	}

	@Override
	public boolean applyOn(String what) {
		try {
			new SimpleDateFormat(_format).parse(what);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String message() {
		return "must have " + _format + " format";
	}
}
