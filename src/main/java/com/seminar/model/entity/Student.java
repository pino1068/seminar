package com.seminar.model.entity;

public class Student implements Entity {

	private final String _firstName;
	private final String _lastName;

	public Student(String firstName, String lastName){
		_firstName = firstName;
		_lastName = lastName;
		
	}
	
	public String getFullName(){
		return getFirstName() + " " + getLastName();
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}
}
