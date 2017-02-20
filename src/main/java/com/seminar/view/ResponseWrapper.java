package com.seminar.view;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseWrapper {

	private final HttpServletResponse _response;

	public ResponseWrapper(HttpServletResponse response) {
		_response = response;
	}
	
	public void render(Html view){
		try {
			_response.getWriter().write(view.build().render());
			_response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
