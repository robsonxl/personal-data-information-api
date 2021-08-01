package com.stefanini.personaldataregistration.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stefanini.personaldataregistration.dto.PersonDTO;
import com.stefanini.personaldataregistration.event.NewResourceEvent;
import com.stefanini.personaldataregistration.form.PersonForm;
import com.stefanini.personaldataregistration.service.PersonService;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<PersonDTO>getAllPerson(){
		return personService.getAllPerson();
	}

	@GetMapping("/{id}")
	public PersonDTO getPersonById(final @PathVariable Integer id) {
		return personService.getPersonById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delelePersonById(final @PathVariable Integer id){
		personService.delelePersonById(id);
	}
	
	@PostMapping
	public ResponseEntity<PersonDTO> saveNewPerson(@Valid @RequestBody PersonForm personForm, HttpServletResponse response) {
		PersonDTO newPerson = personService.saveNewPerson(personForm.mapToPerson(personForm));
		publisher.publishEvent(new NewResourceEvent(this, response, newPerson.getId())); 
		return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody @Valid PersonForm personForm, @PathVariable Integer id){
		PersonDTO personUpdated = personService.updatePerson(personForm.mapToPerson(personForm), id);
		return ResponseEntity.ok().body(personUpdated);
	}
	
	@PatchMapping("{id}/status")
	public ResponseEntity<PersonDTO> updateEmail(@PathVariable Integer id, @RequestBody String newEmail){
		PersonDTO personUpdated = personService.updateEmail(id, newEmail);
		return ResponseEntity.ok().body(personUpdated);
	}
	
}
