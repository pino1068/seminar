package com.seminar.model.entity;

public class CourseRepository {

	public Course get(){
		Course course = new Course("Software Engineering", "cool stuff", "a123", "somewhere", 15, new Time("19.01.2017"));
		course.enroll(new Student("xxx", "yyy"));
		course.enroll(new Student("zzz", "ttt"));
		
		return course;
	}
}
