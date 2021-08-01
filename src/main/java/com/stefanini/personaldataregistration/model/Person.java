package com.stefanini.personaldataregistration.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document(collection = "person")
@Getter
public class Person {

	private String name;
	private String gender;
	private String email;
	private String birthdayDate;
	private String nationality;
	private String placeOfBirth;
	private String document;
	private String documentType;

}
