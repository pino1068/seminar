package com.seminar.model.entity;

import static com.seminar.model.rule.Number.OPERATOR.GREATER_THAN;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.seminar.model.rule.NotEmpty;
import com.seminar.model.rule.Number;
import com.seminar.model.rule.Rule;
import com.seminar.model.rule.TimeFormat;

public class Course implements Entity {

	private final String _name;
	private final String _description;
	private final Integer _number;
	private final String _location;
	private final Integer _totalSeats;
	private final Time _start;

	private final List<Student> _students;
	
	public Course(String name, String description, Integer number, String location, Integer totalSeats, Time start) {
		_name = name;
		_start = start; 
		_location = location;
		_totalSeats = totalSeats;
		_number = number ;
		_description = description;
		_students = new ArrayList<Student>();
	}
	
	public Course(Map<String, String> params) {
		this(
			params.get("name"), 
			params.get("description"), 
			Integer.valueOf(params.get("number")), 
			params.get("location"), 
			Integer.valueOf(params.get("totalSeats")), 
			new Time(params.get("start"))
		 );
	}

	public static MultiValuedMap<String, Rule> rules(){
		 return new ArrayListValuedHashMap<String, Rule>(){{
			put("name", new NotEmpty());
			put("start", new TimeFormat(Time.FORMAT));
			put("location", new NotEmpty());
			put("totalSeats", new Number(GREATER_THAN, 0));
		}};
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
	
	public Iterable<Student> getStudents() {
		return _students;
	}

	public Time getTime() {
		return _start;
	}

	public Integer getTotalSeats() {
		return _totalSeats;
	}
}
