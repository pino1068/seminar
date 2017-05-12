package com;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.seminar.controller.Controller;
import com.seminar.view.Layout;
import com.seminar.view.NotFound;
import com.seminar.view.ResponseWrapper;

public class Servlet extends HttpServlet {

	private DataSource _ds;
	
	@Override
	public void init() throws ServletException {
		try {
			_ds = (DataSource)new InitialContext().lookup("java:/comp/env/jdbc/prod");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		for (Controller controller :  new SeminarFactory().create()) {
			if(controller.handles(req.getRequestURI())){
				ConnectionHandler connection = new ConnectionHandler(_ds);
				try {
					connection.setAutoCommit(false);
					resp.setStatus(HttpServletResponse.SC_OK);
					controller.execute(new Context(req, resp, connection.get()));
					connection.commit();
					
					return ;
				} catch (Exception e) {
					resp.setContentType("text/html;charset=UTF-8");
					resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					
					new ResponseWrapper(resp).render(new Layout("error!", new NotFound(ExceptionUtils.getRootCauseStackTrace(e))));
					connection.rollback();
					
					throw new RuntimeException(ExceptionUtils.getRootCause(e));
				} finally {
					resp.getWriter().flush();
					connection.close();
				}
			}
		}
		
		resp.setContentType("text/html;charset=UTF-8");
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
		new ResponseWrapper(resp).render(new Layout("ops!", new NotFound()));
	}
}
