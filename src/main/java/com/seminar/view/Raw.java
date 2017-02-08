package com.seminar.view;

import static com.seminar.util.Format.newLine;

import com.seminar.model.entity.Course;
import com.seminar.model.entity.Student;

public class Raw implements View {

	private Course _course;
	
	public Raw(Course course) {
		_course = course;
	}
	
	@Override
	public String render() {
		String studentList = "";
		for (Student student : _course.getStudents()) {
			studentList += newLine(student.getFullName());
		}
		return newLine(_course.getName() + " " + _course.getNumber() + ": " + _course.getDescription()) +
			   newLine("location: " + _course.getLocation()) +
			   newLine("seats left: " + _course.getSeatsLeft()) +
			   newLine("start: " + _course.getTime()) +
			   newLine("") +
			   newLine("Enrollment:") +
			   studentList;
	}

}
