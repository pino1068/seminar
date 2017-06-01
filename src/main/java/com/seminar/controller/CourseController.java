package com.seminar.controller;

import java.sql.Connection;

import com.http.Request;
import com.http.Response;
import com.http.Route;
import com.model.EntityModel;
import com.seminar.model.entity.Course;
import com.seminar.model.mapper.CourseMapper;
import com.seminar.view.FeedBack;
import com.seminar.view.Layout;
import com.seminar.view.course.CourseDetails;
import com.seminar.view.course.CourseForm;
import com.seminar.view.course.TableCourse;

public class CourseController  {
	public final static Route
			LIST 	= new Route("/", "/course/?"),
			CREATE 	= new Route("/course/create/?"), 
			SHOW 	= new Route("/course/show/\\d+"), 
			DELETE 	= new Route("/course/delete/\\d+");
	public final static Routes ROUTING = 	new Routes(LIST,CREATE, SHOW,DELETE);

	private CourseMapper courses;
	private Response resp;
	private Request req;
	
	public CourseController(Connection conn, Request req, Response resp) {
		this.req = req;
		this.resp = resp;
		this.courses = new CourseMapper(conn);
	}

	public void execute() {
		if 		(req.routesTo(LIST)) 	list();
		else if (req.routesTo(CREATE))	create();
		else if (req.routesTo(SHOW)) 	show();
		else if (req.routesTo(DELETE))	delete();
		else	resp.notFound();
	}
	
	public void list() {
		resp.render(new Layout("courses", new TableCourse(courses.all())));
	}

	public void create() {
		CourseForm courseForm = new CourseForm();
		if (req.post()) {
			EntityModel entity = new EntityModel(Course.rules(),req.params());
			if (entity.isValid()) {
				courses.insert(entity.<Course> create(Course.class));
				resp.sendRedirectTo(LIST);
				return;
			} else {
				courseForm = new CourseForm(new FeedBack(entity.errors(Course.class)));
			}
		}
		resp.render(new Layout("create course", courseForm));
	}

	public void show() {
		Course course = courses.get(req.id(SHOW));
		resp.render(new Layout("courses", new CourseDetails(course)));
	}

	public void delete() {
		courses.delete(req.id(DELETE));
		list();
	}
}
