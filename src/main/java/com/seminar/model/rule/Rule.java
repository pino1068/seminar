package com.seminar.model.rule;

public interface Rule {

	boolean applyOn(String what);
	String message();
}
