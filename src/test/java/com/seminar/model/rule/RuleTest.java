package com.seminar.model.rule;

import static com.seminar.model.rule.Number.OPERATOR.GREATER_THAN;
import static com.seminar.model.rule.Number.OPERATOR.LESS_THAN;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RuleTest {

	private Set<String> _errors;

	@Before
	public void setUp(){
		_errors = new HashSet<String>();
	}
	
	@Test
	public void notEmpty() throws Exception {
		new NotEmpty("").validate(_errors);
		new NotEmpty("otherKey").validate(_errors);
		new NotEmpty("").validate(_errors);
		
		assertThat(_errors, contains("can't be empty"));
	}
	
	@Test
	public void numberThreshold() throws Exception {
		new Number("1", GREATER_THAN, 0).validate(_errors);
		new Number("5",  LESS_THAN, 4).validate(_errors);
		new Number("xxx", LESS_THAN, 5).validate(_errors);
		
		
		assertThat(_errors, contains("must be a number", "must be less than 4"));
	}
	
	@Test
	public void format() throws Exception {
		new Format("", "[0-3][0-9].[0-1][1-2].[1-2][0-9][0-9][0-9]").validate(_errors);
		new Format("01.10.1990", "[0-3][0-9].[0-1][1-2].[1-2][0-9][0-9][0-9]").validate(_errors);
		
		assertThat(_errors, contains("must have format [0-3][0-9].[0-1][1-2].[1-2][0-9][0-9][0-9]"));
	}
}
