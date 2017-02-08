package com.seminar.controller.course;

import com.seminar.controller.Method;
import com.seminar.model.entity.CourseRepository;
import com.seminar.route.Context;
import com.seminar.view.Csv;
import com.seminar.view.ResponseWrapper;

public class CsvMethod implements Method {

	@Override
	public void execute(Context context) throws Exception {
		if (context.get("/course/csv")) {
			context.response().setContentType("text/plain");
			context.response().setHeader("Content-Disposition", "attachment;filename=course.csv");

			new ResponseWrapper(context.response()).render(new Csv(new CourseRepository().get()));
		}
	}
}
