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
import com.seminar.controller.NotFoundController;
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

		List<Controller> controllers = asList( new CourseController() , new NotFoundController());
		for (Controller controller : controllers) {
			if(controller.handles(req.getRequestURI())){
				try {
					controller.execute(new Context(req, resp, _repository));
					return ;
				} catch (Exception e) {
					
					
					Throwable rootCause = ExceptionUtils.getRootCause(e);
					String[] rootCauseStackTrace = ExceptionUtils.getRootCauseStackTrace(e);
					
					new ResponseWrapper(resp).render(new Layout("error!", new NotFound(rootCauseStackTrace)));
					
					throw new RuntimeException(rootCause);
				} finally {
					resp.getWriter().flush();
				}
			}
		}
	}
}
