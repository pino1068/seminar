package com.seminar.controller;
import javax.servlet.http.HttpServletResponse;

import com.seminar.route.Context;
import com.seminar.view.Layout;
import com.seminar.view.NotFound;
import com.seminar.view.ResponseWrapper;

public class NotFoundController implements Controller {

	@Override
	public boolean handles(String url) {
		return true;
	}

	@Override
	public void execute(Context context) throws Exception {
		
		context.response().setContentType("text/html;charset=UTF-8");
		context.response().setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		new ResponseWrapper(context.response()).render(new Layout("ops!", new NotFound().build()));
	}
}
