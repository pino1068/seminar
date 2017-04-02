package com.seminar.controller.course;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.seminar.controller.FakeRequest;
import com.seminar.controller.FakeResponse;
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
		assertTrue(new CourseController().handles("/"));

		assertFalse(new CourseController().handles("/some/1"));
		assertFalse(new CourseController().handles("/courseany"));
	}
	
	@Test
	public void createNewCourse() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>(){{
			put("name", "courseName");
			put("description", "desc");
			put("id", "12");
			put("location", "loc");
			put("totalSeats", "15");
			put("start", "10.10.2017");
		}};
		
		Context context = new Context(new FakeRequest(Create.ROUTE, "POST", parameters), _response);
		new CourseController().execute(context);
		
		assertThat(_response.status(), is(HttpServletResponse.SC_FOUND));
		assertThat(_response.getHeader("Location"), is(AllCourse.ROUTE.toString()));
		assertThat(context.repository().get(0).getName(), is("courseName"));
	}
	
	@Test
	public void renderCreationForm() throws Exception {
		new CourseController().execute(new Context(new FakeRequest(Create.ROUTE, "GET"), _response));
		
		assertThat(_response.content(), containsString("<form class='form-horizontal' method='post' action='" + Create.ROUTE + "'>"));
	}
	
	@Test
	public void renderCourseList() throws Exception {
		new CourseController().execute(new Context(new FakeRequest(AllCourse.ROUTE, "GET"), _response));
		
		assertThat(_response.content(), containsString("<thead>"));
	}
	
	@Test
	public void wrongCreationGivesFeedbackToUser() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>(){{
			put("name", "");
			put("description", "desc");
			put("id", "0");
			put("location", "loc");
			put("totalSeats", "");
			put("start", "wrong format");
		}};
		
		new CourseController().execute(new Context(new FakeRequest(Create.ROUTE, "POST", parameters), _response));
		
		assertThat(_response.content(), containsString("can't be empty"));
		assertThat(_response.content(), containsString("must be a number"));
		assertThat(_response.content(), containsString("must have dd.MM.yyyy format"));
	}
}
