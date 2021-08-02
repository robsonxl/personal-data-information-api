package com.stefanini.personaldataregistration.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.validation.constraints.NotNull;

import com.stefanini.personaldataregistration.model.Person;

import br.com.caelum.stella.validation.CPFValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonFormV1 {

	@NotNull
	private String name;
	private String gender;
	private String email;
	@NotNull
	private String birthdayDate;
	private String nationality;
	private String placeOfBirth;
	@NotNull
	private String document;

	public Person mapToPerson(PersonFormV1 personForm) {
		return new Person(
							null, 
							personForm.getName(),
							personForm.getGender(),
							personForm.getEmail(),
							isDateValid(personForm.getBirthdayDate()),
							personForm.getNationality(),
							personForm.getPlaceOfBirth(),
							validateDocument(personForm.getDocument()),
							null,
							null
						);
	}

	/**
	 * validate document number
	 * 
	 * @param document
	 */
	public static String validateDocument(String document) {
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.assertValid(document);
		return document;
	}
	
	/**
	 * validate birthday date
	 * @param strDate
	 * @return
	 */
	public static String isDateValid(String strDate) {
		String dateFormat = "dd/MM/uuuu";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate.parse(strDate, dateTimeFormatter);
			return strDate;
		} catch (DateTimeParseException e) {
			throw new DateTimeParseException("Invalid Birthday date", null, 0);
		}
	}

}
