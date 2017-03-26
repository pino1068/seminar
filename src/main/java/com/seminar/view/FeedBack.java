package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.span;

import java.util.HashMap;
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
				span(attr("id -> idHelp"+component, "class -> help-block"), _map.get(component).message()),
				span(attr("id -> idHelp"+component, "class -> help-block"), _map.get(component).message()),
				span(attr("class -> glyphicon form-control-feedback", "aria-hidden ->true").add("class", _map.get(component).icon())),
				span(attr("id -> idHelp"+component, "class -> sr-only"),_map.get(component).value())
			);
		}
		return span();
	}
	
	public Attribute state(String component){
		if(_map.containsKey(component)){
			return attr().add("class", _map.get(component).state());
		}
		return attr();
	}
	
	public Attribute placeholder(String component) {
		if(_map.containsKey(component)){
			return attr().
					add("value", _map.get(component).placeHolder()).
					add("aria-describedby", "idHelp" + component);
		}
		return attr();
	}
}
