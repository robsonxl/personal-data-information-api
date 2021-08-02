package com.stefanini.personaldataregistration.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stefanini.personaldataregistration.dto.PersonDTO;
import com.stefanini.personaldataregistration.model.Person;
import com.stefanini.personaldataregistration.repository.PersonRepostory;
import com.stefanini.personaldataregistration.utils.PersonalDataInformationUtils;


@Service
public class PersonService {
	
	@Autowired
	PersonRepostory personRepostory;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	/**
	 * return all personal information from all person
	 * @return
	 */
	public List<PersonDTO> getAllPerson() {
		List<Person> personalInformationList = personRepostory.findAll();
		List<PersonDTO> personalInformationDTOList = personalInformationList
				.stream()
					.map(
							pesonalInformationItem -> new PersonDTO(pesonalInformationItem)
						)		
					.collect(Collectors.toList());
		
		return personalInformationDTOList;
	}
	
	/**
	 * Return specific person by its id 
	 * @param id
	 * @return
	 */
	public PersonDTO getPersonById(Integer id) {
		 Optional<Person> person = personRepostory.findById(id);
		 if(person.isPresent()) {
			 return new PersonDTO(person.get());
		 }else {
			 throw new EmptyResultDataAccessException(1);
		 }
	}

	public void delelePersonById(Integer id) {
		personRepostory.deleteById(id);
		
	}
	
	/**
	 * Save new person 
	 * @param person
	 * @return
	 */	
	@Transactional
	public PersonDTO saveNewPerson(Person person) {
		if(validateIfDocumentExist(person.getDocument(), personRepostory)){
			throw new DuplicateKeyException("This Document alredy registred");
		}
		person.setDateInsert(PersonalDataInformationUtils.getDateNow());
		person.setId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));
		Person personSaved = personRepostory.save(person);
		PersonDTO personDto = new PersonDTO(personSaved);
		return personDto;
	}
	
	/**
	 * update person
	 * @param person
	 * @param id
	 * @return
	 */
	@Transactional
	public PersonDTO updatePerson(Person person, Integer id) {
		Optional<Person> personFound = personRepostory.findById(id);
		if(personFound.isPresent()){
			BeanUtils.copyProperties(person,personFound.get(),"id","dateInsert","dateUpdate");
			personFound.get().setDateUpdate(PersonalDataInformationUtils.getDateNow());
			Person personUpdated = personRepostory.save(personFound.get());
			return new PersonDTO(personUpdated);
		}else{
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	/**
	 * Update email person
	 * @param id
	 * @param email
	 * @return
	 */
	@Transactional
	public PersonDTO updatePersonEmail(Integer id, String email) {
		Optional<Person> personFound = personRepostory.findById(id);
		if(personFound.isPresent()){
			personFound.get().setDateUpdate(PersonalDataInformationUtils.getDateNow());
			personFound.get().setEmail(email);
			Person personUpdated = personRepostory.save(personFound.get());
			return new PersonDTO(personUpdated);
		}else{
			throw new EmptyResultDataAccessException(1);
		}
	}
	
	/**
	 * Verify if document number already exist
	 * @param document
	 * @param personRepostory
	 * @return
	 */
	
	private static boolean validateIfDocumentExist(String document, PersonRepostory personRepostory) {
		Person person = personRepostory.findByDocument(document);
		if(person!=null){
			return true;	
		}
		return false;
	}
}
