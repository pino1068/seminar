package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.h1;
import static com.github.manliogit.javatags.lang.HtmlHelper.table;
import static com.github.manliogit.javatags.lang.HtmlHelper.tbody;
import static com.github.manliogit.javatags.lang.HtmlHelper.td;
import static com.github.manliogit.javatags.lang.HtmlHelper.text;
import static com.github.manliogit.javatags.lang.HtmlHelper.th;
import static com.github.manliogit.javatags.lang.HtmlHelper.thead;
import static com.github.manliogit.javatags.lang.HtmlHelper.tr;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.seminar.model.entity.Course;

public class TableCourse implements Html{

	private final Iterable<Course> _courseList;

	private final List<String> _header = asList("name", "number", "location", "totalSeats", "start");
	
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
				th(attr("scope -> row"),text(course.getName())),
				td(text(course.getNumber())),
				td(text(course.getLocation())),
				td(text(course.getTime().toString())),
				td(text(course.getLocation()))
			);
	}	
	
	@Override
	public Element build() {
		return 
			div(attr(" class  -> container"),                                                                                                                       
				div(attr(" class  -> row"),                                                                                                                         
					div(attr("class -> col-md-6 col-md-offset-3"),
						h1(attr("class  -> page-header text-center"), "Course List"), 
						table(attr("class -> table table-striped"),
							header(),
							body()
						)
					)
				)
			);
	}
}