package com.seminar.controller.course;

import com.Context;
import com.Route;
import com.seminar.controller.Controller;
import com.seminar.model.mapper.CourseMapper;

public class DeleteCourse implements Controller {

	public final static Route ROUTE = new Route("/course/delete/\\d+");
	
	@Override
	public boolean handles(String url) {
		return ROUTE.matches(url);
	}
	
	@Override
	public void execute(Context context) throws Exception {
		String id = ROUTE.getId(context.requestUri());
		System.out.println("deleting id="+id);
		new CourseMapper(context.connection()).delete(id);
		new AllCourse().execute(context);
	}
}
