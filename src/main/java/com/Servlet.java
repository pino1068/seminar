package com;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seminar.controller.Controller;
import com.seminar.controller.NotFoundController;
import com.seminar.controller.course.CourseController;
import com.seminar.route.Context;

public class Servlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Controller> controllers = Arrays.<Controller>asList( new CourseController() , new NotFoundController());
		for (Controller controller : controllers) {
			if(controller.handles(req.getRequestURI())){
				try {
					controller.execute(new Context(req, resp));
					return ;
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					resp.getWriter().flush();
				}
			}
		}
	}
}
