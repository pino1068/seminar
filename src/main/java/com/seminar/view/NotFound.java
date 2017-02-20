package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.a;
import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.br;
import static com.github.manliogit.javatags.lang.HtmlHelper.div;
import static com.github.manliogit.javatags.lang.HtmlHelper.group;
import static com.github.manliogit.javatags.lang.HtmlHelper.h1;
import static com.github.manliogit.javatags.lang.HtmlHelper.h2;
import static com.github.manliogit.javatags.lang.HtmlHelper.h4;
import static com.github.manliogit.javatags.lang.HtmlHelper.i;
import static com.github.manliogit.javatags.lang.HtmlHelper.text;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class NotFound implements Html{

	private final String[] _rootCauseStackTrace;

	public NotFound(String[] rootCauseStackTrace) {
		_rootCauseStackTrace = rootCauseStackTrace;
	}
	
	public NotFound() {
		this(new String[]{});
	}

	@Override
	public Element build() {
		return
			div(attr("class -> container"),
				    div(attr("class -> row"),
			    		div(attr("class -> col-md-6 col-md-offset-3"),
						    div(attr("class -> error-template"),
							    h1("Oops!"),
							    h2("404 Not Found"),
							    div(attr("class -> error-details"),
									text("Sorry, an error has occured, Requested page not found!"),
									br()
							    ),
							    div(attr("class -> error-actions"),
									a(attr("href -> /", "class -> btn btn-primary"),
									    i(attr("class -> icon-home icon-white")), 
									    text("Take Me Home") 
								    ),
									a(attr("href -> mailto:me@null-byte.info", "class -> btn btn-default"),
									    i(attr("class -> icon-envelope")), 
									    text("Contact Support") 
								    )
							    )
							)
					    )
				    ),
				   errorPanel()
				);
	}

	private Element errorPanel() {
		if(_rootCauseStackTrace.length > 0){
			List<Element> out = new ArrayList<Element>();
			for (String line : _rootCauseStackTrace) {
				out.add(div(text(line)));
			}
			return
				div(attr("class -> row"),
					div(attr("class -> panel panel-danger"),
						div(attr("class -> panel-heading"),h4(attr("class -> panel-title"), "Error!")),
						div(attr("class -> panel-body"), group(out))
					)
				);
		}
		return div();
	}
}
