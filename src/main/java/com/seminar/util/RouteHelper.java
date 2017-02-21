package com.seminar.util;

import static java.util.Arrays.*;

public class RouteHelper {
	
	private final String _requestUri;
	private final String _method;

	public RouteHelper(String requestUri, String method) {
		_requestUri = requestUri;
		_method = method;
	}
	
	public static boolean match(String route, Iterable<String> registeredRoutes){
		for (String registered : registeredRoutes) {
			if(route.matches(registered + "/?")){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean match(String route, String registeredRoute){
		return match(route, asList(registeredRoute));
	}
	
	public boolean post(String route) {
		return matches("POST", route);
	}

	public boolean get(String route) {
		return matches("GET", route);
	}
	
	private boolean matches(String method, String route){
		return _method.equals(method) && _requestUri.matches(route + "/?");
	}
}
