package com.stefanini.personaldataregistration.event;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class NewResourceEventListener implements ApplicationListener<NewResourceEvent>{

	@Override
	public void onApplicationEvent(NewResourceEvent newResourceEvent) {
		HttpServletResponse response = newResourceEvent.getResponse();
		Long id = newResourceEvent.getId();
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(
				 id).toUri();
		 response.setHeader("Location",uri.toASCIIString());
	}
}
