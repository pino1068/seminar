package com.seminar.controller.course;

import com.seminar.controller.Method;
import com.seminar.model.entity.CourseRepository;
import com.seminar.route.Context;
import com.seminar.view.Html;
import com.seminar.view.ResponseWrapper;

public class HtmlMethod  implements Method {

	@Override
	public void execute(Context context) throws Exception {
		if(context.get("/course/html")){
			new ResponseWrapper(context.response()).render(new Html(new CourseRepository().get()));
		}
	}
}
