package com.seminar.controller;

import com.Context;

public interface Controller {
	boolean handles(String url);
	void execute(Context context) throws Exception;
}
