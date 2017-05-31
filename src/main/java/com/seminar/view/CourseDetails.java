package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.form;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.input;
import static com.github.manliogit.javatags.lang.HtmlHelper.label;
import static com.github.manliogit.javatags.lang.HtmlHelper.textarea;
import static com.seminar.model.entity.Course.LOCATION;
import static com.seminar.model.entity.Course.NAME;
import static com.seminar.model.entity.Course.START;
import static com.seminar.model.entity.Course.TOTAL_SEATS;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.seminar.controller.course.Create;
import com.seminar.model.entity.Course;

public class CourseDetails implements Html{

	private Course course;

	public CourseDetails(Course c){
		this.course = c;
	}
	
	@Override
	public Element build() {
		List<Element> input = new ArrayList<Element>();
		input.add(newInput(NAME, course.getName()));
		input.add(newInput(START, course.getTime()));
		input.add(newInput(LOCATION, course.getLocation()));
		input.add(newInput(TOTAL_SEATS, course.getTotalSeats()));
		
		return 
			form(attr("class -> form-horizontal", "method -> post", "action -> " + Create.ROUTE),                                                       
				group(input),
				div(attr(" class  -> form-group"),                                                                                                      
					label(attr("for -> description", "class -> col-sm-2 control-label"), "Description"),                                                           
					div(attr(" class  -> col-sm-10"),
						textarea(attr("class -> form-control", "id -> description", "name -> description", "placeholder -> description"), course.getDescription())
					)                                                                                                                    
				),
				div(attr(" class  -> form-group"),                                                                                                      
					div(attr(" class  -> col-sm-10 col-sm-offset-2"),                                                                                   
						input(attr("id -> submit", "name -> submit", "type -> submit",  "value -> Send", "class -> btn btn-primary"))                                  
					)                                                                                                                    
				)                                                                                                                        
			);                                                                                                                           
	}

	private Element newInput(String name, Object value) {
		return div(attr("class -> form-group") ,                                                                                                      
			label(attr("class -> col-sm-2 control-label").add("for", name), capitalize(name)),                                                           
			div(attr(" class  -> col-sm-10"),                                                                                                   
				input(attr("class -> form-control", "type -> text").
						add("name", name).
						add("id", name).
						add("value", value.toString())
					)
			)                                                                                                                   
		);
	}
}
