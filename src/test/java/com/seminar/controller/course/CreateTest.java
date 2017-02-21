package com.seminar.controller.course;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.seminar.controller.FakeRequest;
import com.seminar.controller.FakeResponse;
import com.seminar.route.Context;

public class CreateTest {

	private FakeResponse _response;
	
	@Before
	public void setUp() {
		_response = new FakeResponse();
	}
	
	@Test
	public void createNewCourse() throws Exception {
		Map<String, String> parameters = new HashMap<String, String>(){{
			put("name", "courseName");
			put("description", "desc");
			put("number", "123");
			put("location", "loc");
			put("totalSeats", "15");
			put("start", "10.10.2017");
		}};
		
		Context context = new Context(new FakeRequest(Create.ROUTE, "POST", parameters), _response);
		new Create().execute(context);
		
		assertThat(context.repository().get(0).getName(), is("courseName"));
		assertThat(_response.status(), is(HttpServletResponse.SC_FOUND));
		assertThat(_response.getHeader("Location"), is(AllCourse.ROUTE));
	}
}
