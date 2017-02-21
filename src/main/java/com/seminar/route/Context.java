package com.seminar.route;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seminar.model.entity.Course;
import com.seminar.util.RouteHelper;

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

	public List<Course> repository() {
		return _repository;
	}
	
	public RouteHelper route(){
		return new RouteHelper(requestUri(), _request.getMethod());
	}
}
