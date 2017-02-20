package com.seminar.util;

import static java.lang.System.getProperty;

public class Format {

	public static String newLine(String token){
		return token + getProperty("line.separator");
	}
	
	public static String component(Object token){
		return "\"" + token + "\";";
	}
	
	public static String component(Integer token){
		return component(token.toString());
	}
	
	public static boolean match(String route, Iterable<String> registeredRoutes){
		for (String registered : registeredRoutes) {
			if(route.matches(registered + "/?")){
				return true;
			}
		}
		
		return false;
	}
}
