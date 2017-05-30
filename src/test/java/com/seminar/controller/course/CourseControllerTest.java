package com.seminar.controller.course;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.Context;
import com.Db;
import com.FakeRequest;
import com.FakeResponse;

public class CourseControllerTest {

	private FakeResponse _response;

	@Before
	public void setUp() {
		_response = new FakeResponse();
	}
	
	@After
	public void tearDown(){
		Db.close();
	}
	@Test
	public void casso() throws Exception {
//		System.out.println("/course/show/1".matches(ShowCourse.ROUTE));
	}
	@Test
	public void handlesItsOwnRoutes() throws Exception {
		assertTrue(new CourseController().handles("/course"));
		assertTrue(new CourseController().handles("/course/"));
		assertTrue(new CourseController().handles("/course/create"));
		assertTrue(new CourseController().handles("/course/create/"));
		assertTrue(new CourseController().handles("/"));
		assertTrue(new CourseController().handles("/course/show/1"));

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
		
		Context context = new Context(new FakeRequest(Create.ROUTE, "POST", parameters), _response, Db.connection());
		new CourseController().execute(context);
		
		assertThat(_response.status(), is(HttpServletResponse.SC_FOUND));
		assertThat(_response.getHeader("Location"), is(AllCourse.ROUTE.toString()));
		assertThat(_response.content(), containsString("courseName"));
	}
	
	@Test
	public void renderLink() throws Exception {
		assertThat(ShowCourse.ROUTE.with("1"), is("/course/show/1"));
		assertThat(ShowCourse.ROUTE.getId("/course/show/1"), is("1"));
	}
	
	@Test
	public void renderCourseList() throws Exception {
		new CourseController().execute(new Context(new FakeRequest(AllCourse.ROUTE, "GET"), _response, Db.connection()));
		
		assertThat(_response.content(), containsString("<thead>"));
	}
	
	@Test
	public void renderCreationForm() throws Exception {
		new CourseController().execute(new Context(new FakeRequest(Create.ROUTE, "GET"), _response));
		
		assertThat(_response.content(), containsString("<form class='form-horizontal' method='post' action='" + Create.ROUTE + "'>"));
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
