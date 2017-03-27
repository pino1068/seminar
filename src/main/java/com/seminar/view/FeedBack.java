package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.span;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.manliogit.javatags.element.Element;
import com.github.manliogit.javatags.element.attribute.Attribute;

public class FeedBack {

	private final Map<String, Component> _map;
	
	public FeedBack(Map<String, Component> map) {
		_map = map;
	}
	
	public FeedBack() {
		this(new HashMap<String, Component>());
	}
	
	public Element message(String component){
		if(_map.containsKey(component)){
			return group(
				errorList(component),
				span(attr("class -> glyphicon form-control-feedback", "aria-hidden ->true").add("class", _map.get(component).icon())),
				span(attr("id -> idHelp"+component, "class -> sr-only"),_map.get(component).value())
			);
		}
		return span();
	}
	
	private Element errorList(String component) {
		List<Element> errors = new ArrayList<Element>();
		for (String message : _map.get(component).messages()) {
			errors.add(
				span(attr("id -> idHelp"+component, "class -> help-block"), message)
			);
		}
		return group(errors);
	}

	public Attribute state(String component){
		if(_map.containsKey(component)){
			return attr().add("class", _map.get(component).state());
		}
		return attr();
	}
	
	public Attribute value(String component) {
		if(_map.containsKey(component)){
			return attr().
					add("value", _map.get(component).userInput()).
					add("aria-describedby", "idHelp" + component);
		}
		return attr();
	}
	
	public String text(String component){
		return _map.containsKey(component) ? _map.get(component).userInput() : "";
	}
}
