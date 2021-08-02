package com.stefanini.personaldataregistration.dto;

import com.stefanini.personaldataregistration.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
	
	
	public PersonDTO(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.gender = person.getGender();
		this.email = person.getEmail();
		this.birthdayDate = person.getBirthdayDate();
		this.nationality =  person.getNationality();
		this.placeOfBirth = person.getPlaceOfBirth();
		this.document = person.getDocument();
		this.dateInsert = person.getDateInsert();
		this.dateUpdate = person.getDateUpdate();
	}
	

	private Long id;
	private String name;
	private String gender;
	private String email;
	private String birthdayDate;
	private String nationality;
	private String placeOfBirth;
	private String document;
	private String dateInsert;
	private String dateUpdate;
}


