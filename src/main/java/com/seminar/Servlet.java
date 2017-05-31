package com.seminar;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.db.Database;
import com.http.Request;
import com.http.Response;
import com.seminar.controller.CourseController;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {
	
	private Database db;
	private DataSource ds;

	@Override
	public void init() throws ServletException {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/prod");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Request request = new Request(req);
		Response response = new Response(resp);
		db = new Database(ds);
		try {
			db.setAutoCommit(false);
			if(request.routesTo(CourseController.COURSE)){
				new CourseController(db.connect(), request, response).routing();
				db.commit();
			}
			else	
				response.notFound();
			return;
		} catch (Exception e) {
			response.error(e);
			db.rollback();
			throw new RuntimeException(ExceptionUtils.getRootCause(e));
		} finally {
			resp.getWriter().flush();
			db.close();
		}
	}
}
