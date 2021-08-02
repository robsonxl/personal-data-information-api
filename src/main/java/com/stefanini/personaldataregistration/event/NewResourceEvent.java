package com.stefanini.personaldataregistration.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewResourceEvent  extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	public NewResourceEvent(Object source, HttpServletResponse response, Long id) { 
		super(source);
		this.setResponse(response);
		this.setId(id);
	}
	
	private HttpServletResponse response;
	private Long id;

}
