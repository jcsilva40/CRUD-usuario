package com.stfn2.ggas.core.event;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

public class ObjectCreateEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private String id;

	public ObjectCreateEvent(Object source, HttpServletResponse response, String id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public String getId() {
		return id;
	}
}
