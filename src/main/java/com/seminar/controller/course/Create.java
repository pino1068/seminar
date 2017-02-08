package com.seminar.controller.course;

import com.seminar.controller.Method;
import com.seminar.route.Context;
import com.seminar.view.CourseForm;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;

public class Create implements Method {

	@Override
	public void execute(Context context) throws Exception {
		if(context.get("/course/create")){
			Layout layout = new Layout("create course",  new CourseForm().build());
			new ResponseWrapper(context.response()).render(layout);
		}
	}
}
