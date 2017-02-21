package com.seminar.controller.course;

import static com.seminar.util.RouteHelper.match;
import static java.util.Arrays.asList;

import java.util.List;

import com.seminar.controller.Action;
import com.seminar.controller.Controller;
import com.seminar.route.Context;

public class CourseController implements Controller{

	private static final Iterable<String> REGISTERED = asList(
			AllCourse.ROUTE, Create.ROUTE
		);
	
	@Override
	public boolean handles(String url) {
		return match(url, REGISTERED);
	}

	@Override
	public void execute(Context context) throws Exception {

		context.response().setContentType("text/html;charset=UTF-8");

		List<Action> actions = asList(new AllCourse(), new Create());
		for (Action action : actions) {
			action.execute(context);
		}
	}
}
