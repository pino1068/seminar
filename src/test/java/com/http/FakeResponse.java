package com.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.http.Route;

public class FakeResponse implements HttpServletResponse {

	private final FakeWriter _writer = new FakeWriter();
	private String _contentType = "";
	private final Map<String, String> _header = new HashMap<String, String>();
	private int _status;
	private String redirect;
	
	public String content() {
		return _writer.content();
	}

	public Map<String, String> header(){
		return _header;
	}
	
	public Integer status() {
		return _status;
	}
	
	public boolean wentTo(Route r){
		return r.matches(redirect);
	}
	
	@Override
	public void setStatus(int arg0) {
		_status = arg0;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return _writer;
	}

	@Override
	public void setContentType(String arg0) {
		_contentType = arg0;
	}
	
	@Override
	public String getContentType() {
		return _contentType;
	}
	
	@Override
	public void setHeader(String arg0, String arg1) {
		_header.put(arg0, arg1);
	}
	
	public String getHeader(String key){
		return _header.get(key);
	}
	
	@Override
	public void sendRedirect(String redirect) throws IOException {
		this.redirect = redirect;
	}
	
	@Override
	public void flushBuffer() throws IOException {


	}

	@Override
	public int getBufferSize() {

		return 0;
	}

	@Override
	public String getCharacterEncoding() {

		return null;
	}

	@Override
	public Locale getLocale() {

		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {

		return null;
	}

	@Override
	public boolean isCommitted() {

		return false;
	}

	@Override
	public void reset() {


	}

	@Override
	public void resetBuffer() {


	}

	@Override
	public void setBufferSize(int arg0) {


	}

	@Override
	public void setCharacterEncoding(String arg0) {


	}

	@Override
	public void setContentLength(int arg0) {


	}

	@Override
	public void setLocale(Locale arg0) {


	}

	@Override
	public void addCookie(Cookie arg0) {


	}

	@Override
	public void addDateHeader(String arg0, long arg1) {


	}

	@Override
	public void addHeader(String arg0, String arg1) {


	}

	@Override
	public void addIntHeader(String arg0, int arg1) {


	}

	@Override
	public boolean containsHeader(String arg0) {

		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {

		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {

		return null;
	}

	@Override
	public String encodeURL(String arg0) {

		return null;
	}

	@Override
	public String encodeUrl(String arg0) {

		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException {


	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {

	}

	@Override
	public void setDateHeader(String arg0, long arg1) {


	}

	@Override
	public void setIntHeader(String arg0, int arg1) {


	}

	@Override
	public void setStatus(int arg0, String arg1) {


	}
}
