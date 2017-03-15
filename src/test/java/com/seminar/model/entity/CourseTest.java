package com.seminar.model.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class CourseTest {

	private Course _course; 

	@Before
	public void setUp(){
		_course = new Course("name", "description", "1", "somewhere", "1", "20.09.2016");
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
		Course invalidCourse = new Course("", "desc", "2", "", "", "");
		
		assertThat(invalidCourse.isValid(), is(false));
		assertThat(invalidCourse.isBrokenOn("name"), is(true));
		assertThat(invalidCourse.isBrokenOn("start"), is(true));
		assertThat(invalidCourse.isBrokenOn("location"), is(true));
		assertThat(invalidCourse.isBrokenOn("totalSeats"), is(true));
		assertThat(invalidCourse.isBrokenOn("description"), is(false));
		assertThat(invalidCourse.isBrokenOn("number"), is(false));
		assertThat(_course.isValid(), is(true));
	}
	
	@Test
	public void numberIsZeroWhenNotDefined() throws Exception {
		Course course = new Course("name", "description", "", "somewhere", "1", "20.09.2016");
		
		assertThat(course.isValid(), is(true));
		assertThat(course.getNumber(), is("0"));
	}
	
	@Test
	public void numberMustBeBelowTotalSeats() throws Exception {
		Course course = new Course("name", "description", "5", "somewhere", "1", "20.09.2016");
		
		assertThat(course.isValid(), is(false));
		assertThat(course.isBrokenOn("number"), is(true));
	}
	
	@Test
	public void timeHasExpectedFormat() throws Exception {
		Course course = new Course("name", "description", "0", "somewhere", "1", "90.19.2016");
		Course otherCourse = new Course("name", "description", "0", "somewhere", "1", "90/19/whatever");
		
		assertThat(course.isValid(), is(false));
		assertThat(otherCourse.isValid(), is(false));
		assertThat(course.isBrokenOn("start"), is(true));
		assertThat(otherCourse.isBrokenOn("start"), is(true));
	}
}
