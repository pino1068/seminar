package com.seminar.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

public class FakeWriter extends PrintWriter {

	public FakeWriter() {
		super(new StringWriter());
	}

	@Override
	public void write(String s) {
		super.write(s);
	}
	
	public String content() {
		return out.toString();
	}
}
