package com.stefanini.personaldataregistration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "person")
@AllArgsConstructor
@Getter
@Setter
public class Person {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
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
