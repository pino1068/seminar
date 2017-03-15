package com;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.seminar.controller.Controller;
import com.seminar.controller.course.CourseController;
import com.seminar.model.entity.Course;
import com.seminar.route.Context;
import com.seminar.view.Layout;
import com.seminar.view.NotFound;
import com.seminar.view.ResponseWrapper;

public class Servlet extends HttpServlet {

	private List<Course> _repository;
	
	@Override
	public void init() throws ServletException {
		_repository = new ArrayList<Course>();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		for (Controller controller :  asList( new CourseController())) {
			if(controller.handles(req.getRequestURI())){
				try {
					resp.setStatus(HttpServletResponse.SC_OK);
					controller.execute(new Context(req, resp, _repository));
					return ;
				} catch (Exception e) {
					resp.setContentType("text/html;charset=UTF-8");
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					
					new ResponseWrapper(resp).render(new Layout("error!", new NotFound(ExceptionUtils.getRootCauseStackTrace(e))));
					throw new RuntimeException(ExceptionUtils.getRootCause(e));
				} finally {
					resp.getWriter().flush();
				}
			}
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		new ResponseWrapper(resp).render(new Layout("ops!", new NotFound()));
	}
}
