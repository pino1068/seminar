package com.seminar.route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Context {

	private final HttpServletResponse _response;
	private final HttpServletRequest _request;

	public Context(HttpServletRequest request, HttpServletResponse response) {
		_response = response;
		_request = request;
	}
	
	public Context(HttpServletResponse response) {
		this(null, response);
	}
	
	public HttpServletRequest request() {
		return _request;
	}
	
	public HttpServletResponse response() {
		return _response;
	}
	
	public String requestUri() {
		return _request.getRequestURI();
	}

	public boolean post(String route) {
		return matches("POST", route);
	}

	public boolean get(String route) {
		return matches("GET", route);
	}
	
	private boolean matches(String method, String route){
		return _request.getMethod().equals(method) && requestUri().equals(route);
	}

}
