package com.okta.aycPedidos.excepciones;

public class WebException extends Exception {
	
	private static final long serialVersionUID = -8049947585343993753L;

	public WebException(String msn) {
		super(msn);
	}
	
}
