package com.seminar.controller.course;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.db.Db;
import com.http.FakeRequest;
import com.http.FakeResponse;
import com.http.Request;
import com.http.Response;
import com.seminar.controller.CourseController;

public class CourseControllerTest {

	private FakeResponse _response;
	private Response response;

	@Before
	public void setUp() {
		_response = new FakeResponse();
		response = new Response(_response);
	}
	
	@After
	public void tearDown(){
		Db.close();
	}
	
	@Test
	public void createNewCourse() throws Exception {
		@SuppressWarnings("serial")
		Map<String, String> parameters = new HashMap<String, String>(){{
			put("name", "courseName");
			put("description", "desc");
			put("id", "12");
			put("location", "loc");
			put("totalSeats", "15");
			put("start", "10.10.2017");
		}};
		Request request = new Request(new FakeRequest(CourseController.CREATE, "POST", parameters));
		new CourseController(Db.connection(),request, response).create();
		
		assertThat(_response.status(), is(HttpServletResponse.SC_OK));
		assertThat(_response.wentTo(CourseController.LIST), is(true));
	}
	
	@Test
	public void renderLink() throws Exception {
		assertThat(CourseController.SHOW.with("1"), is("/course/show/1"));
		assertThat(CourseController.SHOW.getId("/course/show/1"), is("1"));
	}
	
	@Test
	public void renderCourseList() throws Exception {
		Request request = new Request(new FakeRequest(CourseController.CREATE, "GET"));
		new CourseController(Db.connection(),request, response)
		.list();
		
		assertThat(_response.content(), containsString("<thead>"));
	}
	
	@Test
	public void renderCreationForm() throws Exception {
		Request request = new Request(new FakeRequest(CourseController.CREATE, "GET"));
		new CourseController(Db.connection(),request, response)
		.create();;
		
		assertThat(_response.content(), containsString("<form class='form-horizontal' method='post' action='" + CourseController.CREATE + "'>"));
	}
	
	@Test
	public void wrongCreationGivesFeedbackToUser() throws Exception {
		@SuppressWarnings("serial")
		Map<String, String> parameters = new HashMap<String, String>(){{
			put("name", "");
			put("description", "desc");
			put("id", "0");
			put("location", "loc");
			put("totalSeats", "");
			put("start", "wrong format");
		}};
		
		Request request = new Request(new FakeRequest(CourseController.CREATE, "POST", parameters));
		new CourseController(Db.connection(),request, response)
		.create();
		
		assertThat(_response.content(), containsString("can't be empty"));
		assertThat(_response.content(), containsString("must be a number"));
		assertThat(_response.content(), containsString("must have dd.MM.yyyy format"));
	}
}
