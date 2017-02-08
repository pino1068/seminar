package com.seminar.util;

import static com.seminar.util.Format.component;
import static com.seminar.util.Format.newLine;
import static java.lang.System.getProperty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FormatTest {

	@Test
	public void newLineAppendLineSeparator() throws Exception {
		assertThat(newLine("aaa"), is("aaa" + getProperty("line.separator")));
	}
	
	@Test
	public void lineAppendSeparators() throws Exception {
		assertThat(component("aaa"), is("\"aaa\";"));
	}
}
