package com.seminar.controller.course;

import static java.util.Arrays.asList;

import java.util.List;

import com.Context;
import com.Route;
import com.seminar.controller.Controller;

public class CourseController implements Controller {

	private static final List<Controller> ACTIONS 	= asList(new AllCourse(), new Create(), new ShowCourse(), new DeleteCourse());
	private static final Iterable<Route> REGISTERED = asList(AllCourse.ROUTE, Create.ROUTE, ShowCourse.ROUTE, DeleteCourse.ROUTE);
	
	@Override
	public boolean handles(String url) {
		for (Route registered : REGISTERED) {
			if(registered.matches(url)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute(Context context) throws Exception {
		context.response().setContentType("text/html;charset=UTF-8");

		for (Controller action : ACTIONS) {
			if(action.handles(context.requestUri())){
				action.execute(context);
			}
		}
	}
}
