package com.seminar.controller.course;

import java.util.HashMap;
import java.util.Map;

import com.Route;
import com.seminar.controller.Controller;
import com.seminar.model.entity.Course;
import com.seminar.model.entity.Time;
import com.seminar.route.Context;
import com.seminar.view.Component;
import com.seminar.view.Component.TYPE;
import com.seminar.view.CourseForm;
import com.seminar.view.FeedBack;
import com.seminar.view.Layout;
import com.seminar.view.ResponseWrapper;

public class Create implements Controller {

	public static final Route ROUTE = new Route("/course/create/?");

	@Override
	public boolean handles(String url) {
		return url.matches(ROUTE.regEx());
	}
	
	@Override
	public void execute(final Context context) throws Exception {
			CourseForm courseForm = new CourseForm();
			if(context.post(ROUTE)){
				try{
					context.repository().add(new Course(context.parameter("name"),
							context.parameter("description"), 
							context.parameter("number"), 
							context.parameter("location"), 
							new Integer(context.parameter("totalSeats")), 
							new Time(context.parameter("start"))
						)
					);
					context.response().sendRedirect(AllCourse.ROUTE.toString());
				} catch (Exception e){
					Map<String, Component> errors = new HashMap<String, Component>(){{
						put("name", new Component(TYPE.SUCCESS,"Provide a valid name!", context.parameter("name")));
						put("start", new Component(TYPE.WARNING, "Provide a valid start!", context.parameter("start")));
						put("location", new Component(TYPE.ERROR,"Provide a valid location!", context.parameter("location")));
					}};
					
					courseForm = new CourseForm(new FeedBack(errors));
				}
			}
			
			Layout layout = new Layout("create course",  courseForm);
			new ResponseWrapper(context.response()).render(layout);
	}
}
