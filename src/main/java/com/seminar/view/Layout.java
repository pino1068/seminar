package com.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.attr;
import static com.github.manliogit.javatags.lang.HtmlHelper.body;
import static com.github.manliogit.javatags.lang.HtmlHelper.head;
import static com.github.manliogit.javatags.lang.HtmlHelper.html5;
import static com.github.manliogit.javatags.lang.HtmlHelper.link;
import static com.github.manliogit.javatags.lang.HtmlHelper.meta;
import static com.github.manliogit.javatags.lang.HtmlHelper.script;
import static com.github.manliogit.javatags.lang.HtmlHelper.title;

import com.github.manliogit.javatags.element.Element;

public class Layout implements View {

	private final String _title;
	private final Element _bodyContent;

	public Layout(String title, Element bodyContent) {
		_title = title;
		_bodyContent = bodyContent;
	}

	@Override
	public String render() {
		return 
				html5(
					 head(
						 meta(attr("charset -> utf-8")),
						 meta(attr("http-equiv -> X-UA-Compatible", "content -> IE=edge")),
						 meta(attr("name -> viewport", "content -> width=device-width, initial-scale=1")),
						 title(_title),
						 link(attr("rel -> stylesheet", "href -> /css/bootstrap.min.css")),
						 link(attr("rel -> stylesheet", "href -> /css/app.css"))
					 ),
					 body(
						 _bodyContent,
						 script(attr("src -> /js/jquery.min.js")),
						 script(attr("src -> /js/bootstrap.min.js"))
				     )
				).
			render();
	}
}
