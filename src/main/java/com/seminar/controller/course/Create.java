package com.seminar.controller.course;

import static com.seminar.util.RouteHelper.*;

import com.seminar.controller.Action;
import com.seminar.model.entity.Course;
import com.seminar.model.entity.Time;
import com.seminar.route.Context;
import com.seminar.view.CourseForm;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;

public class Create implements Action {

	public static final String ROUTE = "/course/create";

	@Override
	public void execute(Context context) throws Exception {

		if(match(context.requestUri(), ROUTE)){
			CourseForm courseForm = new CourseForm();
			if(context.route().post(ROUTE)){
				context.repository().add(new Course(context.parameter("name"),
						context.parameter("description"), 
						context.parameter("number"), 
						context.parameter("location"), 
						new Integer(context.parameter("totalSeats")), 
						new Time(context.parameter("start"))
					)
				);
				context.response().sendRedirect(AllCourse.ROUTE);
			}
			
			Layout layout = new Layout("create course",  courseForm);
			new ResponseWrapper(context.response()).render(layout);
		}
	}
}
