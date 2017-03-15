package com.seminar.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;

import com.seminar.model.rule.Rule;

public class Validation {

	private final String _label;
	private final String _what;
	private final Class<?> _klass;

	public Validation(String label, String what, Class<?> klass) {
		_label = label;
		_what = what;
		_klass = klass;
	}

	public <T> T collect(MultiValuedMap<String, String> errors, Rule...rules) {
		try {
			Set<String> validations = new HashSet<String>();
			for (Rule rule : rules) {
				rule.validate(validations);
			}
			if(validations.isEmpty()){
				return (T) _klass.getConstructor(String.class).newInstance(_what);
			} else {
				errors.putAll(_label, validations);
				return null;
			}
		} catch (Exception e) {
			return null;
		} 
	}
}
