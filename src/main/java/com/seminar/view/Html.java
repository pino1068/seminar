package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.body;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.head;
import static com.github.manliogit.javatags.lang.HtmlHelper.html5;
import static com.github.manliogit.javatags.lang.HtmlHelper.li;
import static com.github.manliogit.javatags.lang.HtmlHelper.text;
import static com.github.manliogit.javatags.lang.HtmlHelper.title;
import static com.github.manliogit.javatags.lang.HtmlHelper.ul;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.seminar.model.entity.Course;
import com.seminar.model.entity.Student;

public class Html implements View {

	private final Course _course;

	public Html(Course course) {
		_course = course;
	}

	
	@Override
	public String render() {
		
		List<Element> items = new ArrayList<Element>();
		
		for (Student student : _course.getStudents()) {
			items.add(li(text(student.getFullName())));
		}
		
		return html5(
				 head(
				   title(_course.getName())
				 ),
				 body(
				   div(_course.getName() + ":"),
				   ul(
				     li(text(_course.getDescription())),
				     li(text(_course.getLocation())),
				     li(text(_course.getSeatsLeft().toString())),
				     li(text(_course.getTime().toString()))
				   ),
				   div("partecipanti:"),
				   ul(group(items))
			     )
			   ).render();
	}

}
