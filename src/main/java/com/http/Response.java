package com.http;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.seminar.view.Html;
import com.seminar.view.Layout;
import com.seminar.view.NotFound;

public class Response {

	private final HttpServletResponse _response;

	public Response(HttpServletResponse response) {
		_response = response;
		_response.setContentType("text/html;charset=UTF-8");
		_response.setStatus(HttpServletResponse.SC_OK);//be positive!
	}
	
	public void render(Html view){
		try {
			_response.getWriter().write(view.build().render());
			_response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void notFound() {
		_response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		render(new Layout("ops!", new NotFound()));
	}

	public void error(Exception e) {
		_response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		render(new Layout("error!", new NotFound(ExceptionUtils.getRootCauseStackTrace(e))));
	}

	public void sendRedirectTo(Route routing) {
		try {
			_response.sendRedirect(routing.toString());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
