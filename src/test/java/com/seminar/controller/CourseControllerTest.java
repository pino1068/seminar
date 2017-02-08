package com.seminar.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.seminar.controller.course.CourseController;
import com.seminar.route.Context;

public class CourseControllerTest {

	private FakeResponse _response;
	
	@Before
	public void setUp() {
		_response = new FakeResponse();
	}
	
	@Test
	public void handlesItsOwnRoutes() throws Exception {
		assertTrue(new CourseController().handles("/course"));
		assertTrue(new CourseController().handles("/course/"));
		assertTrue(new CourseController().handles("/course/1"));
		assertTrue(new CourseController().handles("/course/create"));
		
		assertFalse(new CourseController().handles("/"));
		assertFalse(new CourseController().handles("/some/1"));
		assertFalse(new CourseController().handles("/courseany"));
	}
	
	@Test
	public void html() throws Exception {
		new CourseController().execute(new Context(new FakeRequest("/course/html", "GET"), _response));
		
		assertThat(_response.getContentType(), is("text/html;charset=UTF-8"));
		assertThat(_response.content(), containsString("<!DOCTYPE html><html><head><title>Software Engineering</title></head>"));
	}
	
	@Test
	public void raw() throws Exception {
		new CourseController().execute(new Context(new FakeRequest("/course/raw", "GET"), _response));
		
		assertThat(_response.content(), containsString("Software Engineering a123: cool stuff"));
	}
	
	@Test
	public void csv() throws Exception {
		new CourseController().execute(new Context(new FakeRequest("/course/csv", "GET"), _response));
		
		assertThat(_response.getContentType(), is("text/plain"));
		assertThat(_response.header().get("Content-Disposition"), is("attachment;filename=course.csv"));
		assertThat(_response.content(), containsString("\"a123\";\"cool stuff\";\"somewhere\";\"13\";\"19.01.2017\""));
	}
	
	@Test
	public void renderCreationForm() throws Exception {
		new CourseController().execute(new Context(new FakeRequest("/course/create", "GET"), _response));
		
		assertThat(_response.content(), containsString("<form class='form-horizontal' role='form' method='post' action='/'>"));
		assertThat(_response.content(), containsString("<form class='form-horizontal' role='form' method='post' action='/'>"));
	}
}
