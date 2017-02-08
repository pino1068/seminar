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
	
	public static boolean match(String route, String url){
		try {
			String[] routeComponents = route.split("/");
			String[] urlComponents = url.split("/");
			
			return  routeComponents[1].equals(urlComponents[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
}
