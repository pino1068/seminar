package com.seminar.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private final String _name;
	private final String _description;
	private final String _number;
	private final String _location;
	private final int _totalSeats;
	private final Time _start;

	private final List<Student> _students;
	
	public Course(String name, String description, String number, String location, int totalSeats, Time start) {
		_name = name;
		_description = description;
		_number = number;
		_location = location;
		_totalSeats = totalSeats;
		_start = start;
		_students = new ArrayList<Student>();
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public String getNumber() {
		return _number;
	}
	
	public String getLocation() {
		return _location;
	}
	
	public Integer getSeatsLeft(){
		return _totalSeats - _students.size();
	}
	
	public void enroll(Student student) {
		if(_students.size()  < getSeatsLeft()) {
			_students.add(student); 
		} else {
			throw new RuntimeException("Student: " + student.getFullName() + " can't be enrolled. Seats terminated");
		}
	}

	public Iterable<Student> getStudents() {
		return _students;
	}

	public Time getTime() {
		return _start;
	}
}
