package com.seminar.model.entity;

import static com.seminar.model.rule.Number.OPERATOR.GREATER_THAN;
import static com.seminar.model.rule.Number.OPERATOR.LESS_THAN;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import com.seminar.model.Validation;
import com.seminar.model.rule.Format;
import com.seminar.model.rule.NotEmpty;
import com.seminar.model.rule.Number;

public class Course implements Entity {

	private final String _name;
	private final String _description;
	private final Integer _number;
	private final String _location;
	private final Integer _totalSeats;
	private final Time _start;

	private final List<Student> _students;
	private final MultiValuedMap<String, String> _errors;
	
	public Course(String name, String description, String number, String location, String totalSeats, String start) {
		_errors = new HashSetValuedHashMap<String, String>();
		_name = new Validation("name", name, String.class).collect(_errors, new NotEmpty(name));
		_start = new Validation("start", start, Time.class).collect(_errors, new Format(start, Time.REG_EX)); 
		_location = new Validation("location", location, String.class).collect(_errors, new NotEmpty(location));
		_totalSeats = new Validation("totalSeats", totalSeats, Integer.class).collect(_errors, new Number(totalSeats, GREATER_THAN, 0));
		_number = (Integer) (number.isEmpty() ? 0 : new Validation("number", number,  Integer.class).collect(_errors, new Number(number, LESS_THAN, _totalSeats))) ;
		_description = description;
		_students = new ArrayList<Student>();
	}

	public String getName() {
		return _name;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public String getNumber() {
		return _number.toString();
	}
	
	public String getLocation() {
		return _location;
	}
	
	public Integer getSeatsLeft(){
		return getTotalSeats() - _students.size();
	}
	
	public void enroll(Student student) {
		if(_students.size()  < getSeatsLeft()) {
			_students.add(student); 
		} else {
			throw new RuntimeException("Student: " + student.getFullName() + " can't be enrolled. Seats terminated");
		}
	}
	
	public boolean isValid(){
		return _errors.isEmpty();
	}
	
	public Iterable<Student> getStudents() {
		return _students;
	}

	public Time getTime() {
		return _start;
	}

	public boolean isBrokenOn(String label) {
		return _errors.containsKey(label);
	}

	public Integer getTotalSeats() {
		return _totalSeats;
	}
}
