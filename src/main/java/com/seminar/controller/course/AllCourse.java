package com.seminar.controller.course;

import com.seminar.controller.Action;
import com.seminar.route.Context;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;
import com.seminar.view.TableCourse;

public class AllCourse implements Action {

	public final static String ROUTE = "/course";
	
	@Override
	public void execute(Context context) throws Exception {
		if(context.route().get(ROUTE)){
			new ResponseWrapper(context.response()).render(new Layout("courses", new TableCourse(context.repository())));
		}
	}

}
