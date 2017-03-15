package com.seminar.model.entity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.seminar.model.entity.Student;

public class StudentTest {

	@Test
	public void getFullName() throws Exception {
		assertThat(new Student("name", "lastName").getFullName(), is("name lastName"));
	}
}
