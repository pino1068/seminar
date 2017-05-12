package com;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.seminar.controller.Controller;
import com.seminar.controller.course.CourseController;

public class SeminarFactory {

	public List<Controller> create(){
		return new ArrayList<Controller>(asList(new CourseController()));
	}
}
