package com.seminar.controller.course;

import static java.util.Arrays.asList;

import com.Context;
import com.Route;
import com.seminar.controller.Controller;
import com.seminar.model.entity.Course;
import com.seminar.model.mapper.CourseMapper;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;
import com.seminar.view.TableCourse;

public class ShowCourse implements Controller {

	public final static Route ROUTE = new Route("/course/show/\\d+");
	
	@Override
	public boolean handles(String url) {
		return ROUTE.matches(url);
	}
	
	@Override
	public void execute(Context context) throws Exception {
		String id = ROUTE.getId(context.requestUri());
		Course course = new CourseMapper(context.connection()).get(id);
		
		new ResponseWrapper(context.response()).render(
				new Layout("courses", new TableCourse(asList(course)))
		);
	}
}
