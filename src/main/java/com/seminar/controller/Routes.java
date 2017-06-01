package com.seminar.controller;

import com.http.Route;

public class Routes  {

	private Route[] routes;

	public Routes(Route ...routes) {
		this.routes = routes;
	}

	public boolean match(String url) {
		for (Route route : routes) 
			if(route.matches(url)) return true;
		return false;
	}

}
