package com.http;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.http.Route;
import com.seminar.controller.CourseController;

public class RouteTest {

	@Test
	public void toStringReplaceAdditionalRegExinfo() throws Exception {
		assertThat(new Route("/course/create/?").toString(), is("/course/create"));
//		new Route("/", "/course/?").matches(""); 
//		new Route("/course/create/?").matches("");
//		new Route("/course/show/\\d+");
//		new Route("/course/delete/\\d+");
	}
}
