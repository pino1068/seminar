package com.seminar.route;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seminar.model.entity.Course;

public class Context {

	private final HttpServletResponse _response;
	private final HttpServletRequest _request;
	private final List<Course> _repository;

	public Context(HttpServletRequest request, HttpServletResponse response, List<Course> repository) {
		_response = response;
		_request = request;
		_repository = repository;
	}
	
	public Context(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, new ArrayList<Course>());
	}
	
	public HttpServletRequest request() {
		return _request;
	}
	
	public HttpServletResponse response() {
		return _response;
	}
	
	public String parameter(String key){
		return _request.getParameter(key);
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
		return _request.getMethod().equals(method) && requestUri().matches(route + "/?");
	}

	public List<Course> repository() {
		return _repository;
	}

}
