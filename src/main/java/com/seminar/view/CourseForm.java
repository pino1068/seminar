package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.form;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.h1;
import static com.github.manliogit.javatags.lang.HtmlHelper.input;
import static com.github.manliogit.javatags.lang.HtmlHelper.label;
import static com.github.manliogit.javatags.lang.HtmlHelper.textarea;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.manliogit.javatags.element.Element;
import com.seminar.controller.course.Create;

public class CourseForm implements Html{

	private final FeedBack _feedBack;

	private final Map<String, String> _components = new LinkedHashMap<String, String>(){{
		put("name", "text");
		put("start", "time");
		put("location", "text");
		put("number", "text");
		put("totalSeats", "text");
	}};
	
	public CourseForm() {
		this(new FeedBack());
	}
	
	public CourseForm(FeedBack feedBack){
		_feedBack = feedBack;
	}
	
	@Override
	public Element build() {
		
		List<Element> input = new ArrayList<Element>();
		for (Entry<String, String> pair: _components.entrySet()) {
			String component = pair.getKey();
			String type = pair.getValue();
			
			input.add(
					div(attr("class -> form-group").add(_feedBack.state(component)) ,                                                                                                      
						label(attr("class -> col-sm-2 control-label").add("for", component), capitalize(component)),                                                           
						div(attr(" class  -> col-sm-10"),                                                                                                   
							input(attr("class -> form-control").
									add(_feedBack.placeholder(component)).
									add("type", type).
									add("name", component).
									add("id", component) 
								),
							_feedBack.message(component)
						)                                                                                                                   
					)
				);
		}
		
		return 
			div(attr(" class  -> container"),                                                                                                                       
				div(attr(" class  -> row"),                                                                                                                         
					div(attr("class -> col-md-6 col-md-offset-3"),                                                                                                
						h1(attr("class  -> page-header text-center"), "Create Course"),                                                                     
						form(attr("class -> form-horizontal", "method -> post", "action -> " + Create.ROUTE),                                                       
							group(input),
							div(attr(" class  -> form-group"),                                                                                                      
								label(attr("for -> description", "class -> col-sm-2 control-label"), "Description"),                                                           
								div(attr(" class  -> col-sm-10"),
									textarea(attr("class -> form-control", "id -> description", "name -> description", "placeholder -> description"))
								)                                                                                                                    
							),
							div(attr(" class  -> form-group"),                                                                                                      
								div(attr(" class  -> col-sm-10 col-sm-offset-2"),                                                                                   
									input(attr("id -> submit", "name -> submit", "type -> submit",  "value -> Send", "class -> btn btn-primary"))                                  
								)                                                                                                                    
							)                                                                                                                        
						)                                                                                                                           
					)                                                                                                                                
				)                                                                                                                                    
			);
	}
}
