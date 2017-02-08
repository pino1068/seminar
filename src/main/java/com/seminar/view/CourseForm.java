package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.form;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.h1;
import static com.github.manliogit.javatags.lang.HtmlHelper.input;
import static com.github.manliogit.javatags.lang.HtmlHelper.label;
import static com.github.manliogit.javatags.lang.HtmlHelper.textarea;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class CourseForm {

	
	public Element build() {
		
		List<String> components = asList("name", "number",  "location", "totalSeats");
		
		List<Element> input = new ArrayList<Element>();
		for (String component : components) {
			input.add(
				div(attr(" class  -> form-group"),                                                                                                      
					label(attr("for -> " + component, "class -> col-sm-2 control-label"), capitalize(component)),                                                           
					div(attr(" class  -> col-sm-10"),                                                                                                   
						input(attr("type -> text", "class -> form-control", "id -> " + component, "name -> " + component, "placeholder -> " + component))      
					)                                                                                                                    
				)
			);
		}
		
		return 
			div(attr(" class  -> container"),                                                                                                                       
				div(attr(" class  -> row"),                                                                                                                         
					div(attr("class -> col-md-6 col-md-offset-3"),                                                                                                
						h1(attr("class  -> page-header text-center"), "Create Course"),                                                                     
						form(attr("class -> form-horizontal", "role -> form", "method -> post", "action -> /"),                                                       
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
