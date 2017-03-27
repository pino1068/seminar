package com.seminar.model.entity;

import static com.seminar.model.rule.Number.OPERATOR.GREATER_THAN;
import static com.seminar.model.rule.Number.OPERATOR.LESS_THAN;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.seminar.model.rule.MaxLength;
import com.seminar.model.rule.NotEmpty;
import com.seminar.model.rule.Number;
import com.seminar.model.rule.Rule;
import com.seminar.model.rule.TimeFormat;

public class Course implements Entity {

	private final String _name;
	private final String _description;
	private final Integer _id;
	private final String _location;
	private final Integer _totalSeats;
	private final Time _start;

	private final List<Student> _students;
	
	public Course(Integer id, String name, String description, String location, Integer totalSeats, Time start) {
		_name = name;
		_start = start; 
		_location = location;
		_totalSeats = totalSeats;
		_id = id ;
		_description = description;
		_students = new ArrayList<Student>();
	}
	
	public Course(Map<String, String> params) {
		this(
			Integer.valueOf(params.get("id")), 
			params.get("name"), 
			params.get("description"), 
			params.get("location"), 
			Integer.valueOf(params.get("totalSeats")), 
			new Time(params.get("start"))
		 );
	}

	public static MultiValuedMap<String, Rule> rules(){
		 return new ArrayListValuedHashMap<String, Rule>(){{
			put("id", new NotEmpty());
			putAll("name", asList(new NotEmpty(), new MaxLength(15)));
			put("start", new TimeFormat(Time.FORMAT));
			putAll("location", asList(new NotEmpty(), new MaxLength(20)));
			putAll("totalSeats", asList(new Number(GREATER_THAN, 0), new Number(LESS_THAN, 100), new MaxLength(3)));
		}};
	}
	
	public String getName() {
		return _name;
	}
	
	public String getDescription() {
		return _description;
	}
	
	public String getId() {
		return _id.toString();
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
