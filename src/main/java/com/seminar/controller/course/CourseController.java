package com.seminar.controller.course;

import static com.seminar.util.Format.match;
import static java.util.Arrays.asList;

import java.util.List;

import com.seminar.controller.Controller;
import com.seminar.controller.Method;
import com.seminar.route.Context;

public class CourseController implements Controller {

	@Override
	public boolean handles(String url) {
		return match("/course", url);
	}

	@Override
	public void execute(Context context) throws Exception {

		context.response().setContentType("text/html;charset=UTF-8");

		List<Method> methods = asList(new Create(), new HtmlMethod(), new RawMethod(), new CsvMethod());
		for (Method method : methods) {
			method.execute(context);
		}
	}
}
