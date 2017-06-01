package com.http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.seminar.controller.Routes;

public class Request {

	private HttpServletRequest request;

	public Request(HttpServletRequest request) {
		this.request = request;
	}
	
	public String get(String key){
		return request.getParameter(key);
	}
	
	public String uri() {
		return request.getRequestURI();
	}
	
	public boolean post() {
		return matches("POST");
	}

	public boolean get() {
		return matches("GET");
	}
	
	public Map<String, String> params(){
		Map<String, String> convert = new HashMap<String, String>();
		for (Object key : request.getParameterMap().keySet()) {
			convert.put(key.toString(), request.getParameter(key.toString()));
		}
		return convert;
	}
	
	private boolean matches(String method){
		return request.getMethod().equals(method);
	}

	public boolean routesTo(Routes routes) {
		return routes.match(uri());
	}

	public boolean routesTo(Route route) {
		return route.matches(uri());
	}

	public String id(Route route) {
		return route.getId(uri());
	}
}
