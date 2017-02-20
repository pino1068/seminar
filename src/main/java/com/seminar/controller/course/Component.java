package com.seminar.controller.course;

public class Component {

	private final String _message;
	private final String _placeHolder;
	private final TYPE _type;

	public Component(TYPE type, String message, String placeHolder) {
		_type = type;
		_message = message;
		_placeHolder = placeHolder;
	}
	
	public Component(String message, String placeHolder) {
		this(TYPE.ERROR, message, placeHolder);
	}
	
	public Component() {
		this(TYPE.ERROR, "", "");
	}
	
	public String message() {
		return _message;
	}

	public String placeHolder() {
		return _placeHolder;
	}

	public TYPE type() {
		return _type;
	}
	
	public String value(){
		return _type._name;
	}

	public String state() {
		return _type._state + " has-feedback";
	}
	
	public String icon(){
		return _type._icon;
	}
	
	enum TYPE{
		SUCCESS("(success)", "has-success", "glyphicon-ok"),
		WARNING("(warning)", "has-warning", "glyphicon-warning-sign"),
		ERROR("(error)", "has-error", "glyphicon-remove");
		
		private final String _name;
		private final String _state;
		private final String _icon;
		
		TYPE(String name, String state, String icon){
			_name = name;
			_state = state;
			_icon = icon;
		}
	}
}
