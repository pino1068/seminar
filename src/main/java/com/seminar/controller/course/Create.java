package com.seminar.controller.course;

import static com.seminar.model.Meta.signatureOf;

import java.util.HashMap;
import java.util.Map;

import com.Route;
import com.seminar.controller.Controller;
import com.seminar.model.entity.Course;
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
			if(context.post()){
				Course course = new Course(context.parameter("name"),
						context.parameter("description"), 
						context.parameter("number"), 
						context.parameter("location"), 
						context.parameter("totalSeats"), 
						context.parameter("start")
				);
				
				if(course.isValid()){
					context.repository().add(course);
					context.response().sendRedirect(AllCourse.ROUTE.toString());
				} else {
					Map<String, Component> errors = new HashMap<String, Component>();
					for (String component : signatureOf(course)) {
						errors.put(component, componentType(component, course, context));
					} 
					
					courseForm = new CourseForm(new FeedBack(errors));
				}
			}
			
			Layout layout = new Layout("create course",  courseForm);
			new ResponseWrapper(context.response()).render(layout);
	}
	
	private Component componentType(String label, Course course, Context context){
		return course.isBrokenOn(label) 
				? new Component(TYPE.ERROR, "Provide a valid " + label + "!", context.parameter(label))
				:  new Component(TYPE.SUCCESS, "", context.parameter(label));
		
	}
}
