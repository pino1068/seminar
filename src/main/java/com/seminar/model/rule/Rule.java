package com.seminar.model.rule;

import java.util.Set;

public interface Rule {

	void validate(Set<String> errors);
}
