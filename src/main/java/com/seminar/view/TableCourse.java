package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;
import static com.seminar.model.entity.Course.ID;
import static com.seminar.model.entity.Course.LOCATION;
import static com.seminar.model.entity.Course.NAME;
import static com.seminar.model.entity.Course.START;
import static com.seminar.model.entity.Course.TOTAL_SEATS;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.seminar.controller.course.DeleteCourse;
import com.seminar.controller.course.ShowCourse;
import com.seminar.model.entity.Course;

public class TableCourse implements Html{

	private static final String ACTION = "action";

	private final Iterable<Course> _courseList;

	private final List<String> _header = asList(ACTION, NAME, LOCATION, TOTAL_SEATS, START);
	
	public TableCourse(Iterable<Course> courseList) {
		_courseList = courseList;
	}
	
	private Element header(){
		List<Element> list = new ArrayList<Element>();
		for (String component : _header) {
			list.add(th(text(component)));
		}
		
		return thead(
				tr(
					group(list)
				)
			);
	}
	
	private Element body(){
		List<Element> rows = new ArrayList<Element>();
		for (Course course : _courseList) {
			rows.add(row(course));
		}
		
		return 
			tbody(
				rows
			);
	}
	
	private Element row(Course course){
		return tr(
				th(attr("scope -> row"),a(attr(/*" onclick=\"return confirm('Are you sure?')\"",*/" href -> "+DeleteCourse.ROUTE.with(course.getId())), "delete")),
				th(attr("scope -> row"),a(attr("href -> "+ShowCourse.ROUTE.with(course.getId())), course.getName())),
				td(text(course.getLocation())),
				td(text(course.getTotalSeats().toString())),
				td(text(course.getTime().toString()))
			);
	}	
	
	@Override
	public Element build() {
		return 
				table(attr("class -> table table-striped"),
					header(),
					body()
				);
	}
}
