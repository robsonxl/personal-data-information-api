package com.stefanini.personaldataregistration.dto;

import com.stefanini.personaldataregistration.model.Person;

import lombok.Getter;
import lombok.Setter;

public class PersonDTO {
	
	
	public PersonDTO(Person person) {
		this.id = person.getId();
	}
	
	@Getter
	@Setter
	private Long id;

}
