package com.seminar.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.seminar.route.Context;

public class NotFoundControllerTest {

	@Test
	public void renderPage() throws Exception {
		FakeResponse response = new FakeResponse();

		new NotFoundController().execute(new Context(new FakeRequest("/xxx", "GET"), response));
		
		assertThat(response.getContentType(), is("text/html;charset=UTF-8"));
		assertThat(response.status(), is(HttpServletResponse.SC_NOT_FOUND));
	}
}
