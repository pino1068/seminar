package com.seminar.controller.course;

import com.seminar.controller.Method;
import com.seminar.model.entity.CourseRepository;
import com.seminar.route.Context;
import com.seminar.view.Raw;
import com.seminar.view.ResponseWrapper;

public class RawMethod implements Method {

	@Override
	public void execute(Context context) throws Exception {
		if (context.get("/course/raw")) {
			new ResponseWrapper(context.response()).render(new Raw(new CourseRepository().get()));
		}
	}
}
