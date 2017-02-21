package com.seminar.controller.course;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.seminar.controller.FakeRequest;
import com.seminar.controller.FakeResponse;
import com.seminar.controller.course.CourseController;
import com.seminar.controller.course.Create;
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
		assertTrue(new CourseController().handles("/course/create"));
		assertTrue(new CourseController().handles("/course/create/"));
		
		assertFalse(new CourseController().handles("/"));
		assertFalse(new CourseController().handles("/some/1"));
		assertFalse(new CourseController().handles("/courseany"));
	}
	
	@Test
	public void renderCreationForm() throws Exception {
		new CourseController().execute(new Context(new FakeRequest("/course/create", "GET"), _response));
		
		assertThat(_response.content(), containsString("<form class='form-horizontal' role='form' method='post' action='"+Create.ROUTE+"'>"));
	}
}
