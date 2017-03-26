package com.seminar.model.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.seminar.model.EntityModel;

public class CourseTest {

	private Course _course; 

	@Before
	public void setUp(){
		_course = new Course("name", "description", 1, "somewhere", 1, new Time("20.09.2016"));
	}
	
	@Test
	public void enrollingAStudentDecreaseSeatsLeft() {
		_course.enroll(new Student("a", "b"));
		
		assertThat(_course.getSeatsLeft(), is(0));
	}

	@Test
	public void cantEnrollStudentOverTotal() {
		try {
			_course.enroll(new Student("a", "b"));
			_course.enroll(new Student("c", "d"));
	        
			fail("Expected an RuntimeException to be thrown");
	    } catch (RuntimeException rte) {
	        assertThat(rte.getMessage(), is("Student: c d can't be enrolled. Seats terminated"));
	    }
	}
	
	@Test
	public void courseHasStartDate() throws Exception {
		assertThat(_course.getTime(), is(new Time("20.09.2016")));
	}
	
	@Test
	public void courseNameStartLocationTotalSeatsAreMandatory() throws Exception {
		
		EntityModel invalidCourse = new EntityModel(Course.rules(), new HashMap<String, String>());
		
		assertThat(invalidCourse.isValid(), is(false));
		assertThat(invalidCourse.isBrokenOn("name"), is(true));
		assertThat(invalidCourse.isBrokenOn("start"), is(true));
		assertThat(invalidCourse.isBrokenOn("location"), is(true));
		assertThat(invalidCourse.isBrokenOn("totalSeats"), is(true));
		assertThat(invalidCourse.isBrokenOn("description"), is(false));
		assertThat(invalidCourse.isBrokenOn("number"), is(false));
	}
	
	@Test
	public void validCourse() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>(){{
			put("name", "course");
			put("start", "26.03.2017");
			put("location", "somewhere");
			put("totalSeats", "15");
		}};
		EntityModel validCourse = new EntityModel(Course.rules(), params);
		
		assertThat(validCourse.isValid(), is(true));
	}
}
