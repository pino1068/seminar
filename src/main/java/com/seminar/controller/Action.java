package com.seminar.controller;

import com.seminar.route.Context;

public interface Action {

	void execute(Context context) throws Exception;
}
