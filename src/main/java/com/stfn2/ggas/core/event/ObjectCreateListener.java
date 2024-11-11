package com.stfn2.ggas.core.event;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class ObjectCreateListener implements ApplicationListener<ObjectCreateEvent> {

	@Override
	public void onApplicationEvent(ObjectCreateEvent event) {
		HttpServletResponse response = event.getResponse();
		String id = event.getId();

		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, String id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
