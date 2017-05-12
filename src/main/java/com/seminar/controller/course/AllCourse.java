package com.seminar.controller.course;

import com.Context;
import com.Route;
import com.seminar.controller.Controller;
import com.seminar.model.entity.Course;
import com.seminar.model.mapper.CourseMapper;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;
import com.seminar.view.TableCourse;

public class AllCourse implements Controller {

	public final static Route ROUTE = new Route("/", "/course/?");
	
	@Override
	public boolean handles(String url) {
		return ROUTE.matches(url);
	}
	
	@Override
	public void execute(Context context) throws Exception {
		Iterable<Course> courses = new CourseMapper(context.connection()).findAll();
		
		new ResponseWrapper(context.response()).render(new Layout("courses", new TableCourse(courses)));
	}
}
