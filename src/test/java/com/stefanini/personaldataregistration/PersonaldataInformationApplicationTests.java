package com.stefanini.personaldataregistration;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.stefanini.personaldataregistration.dto.PersonDTO;
import com.stefanini.personaldataregistration.service.PersonService;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PersonaldataInformationApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService service;
    
    
     
    void whenValidPersonData_thenANewPersonShoulBeCreatedV2() throws Exception {
    	 
    	String body ="{\n" + 
    			"	 \n" + 
    			"	\"gender\":\"1\",\n" + 
    			"	\"email\":\"teste@gmail.com\",\n" + 
    			"	\"nationality\":\"18\",\n" + 
    			"	\"birthdayDate\":\"26/03/2017\",\n" + 
    			"	\"placeOfBirth\":\"3\",\n" + 
    			"	\"document\":\"87724308004\",\n" + 
    			"	\"address\":\"Rua teste\",\n" + 
    			"	\"name\":\"Robson\"\n" + 
    			"}";
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/person/v2.0")
    	        .contentType("application/json")
    	        .content(body))
    	        .andExpect(status().isCreated());
    	
    	PersonDTO person = service.getPersonByDocumentId("87724308004");
    	Assertions.assertEquals("87724308004", person.getDocument());
    }
    
    
    void whenValidPersonData_thenANewPersonShoulBeCreatedV1() throws Exception {
    	 
    	String body ="{\n" + 
    			"	 \n" + 
    			"	\"gender\":\"1\",\n" + 
    			"	\"email\":\"teste@gmail.com\",\n" + 
    			"	\"nationality\":\"18\",\n" + 
    			"	\"birthdayDate\":\"26/03/2017\",\n" + 
    			"	\"placeOfBirth\":\"3\",\n" + 
    			"	\"document\":\"67789822098\",\n" + 
    			"	\"name\":\"Robson\"\n" + 
    			"}";
    	
    	mockMvc.perform(MockMvcRequestBuilders.post("/person/v1.0")
    	        .contentType("application/json")
    	        .content(body))
    	        .andExpect(status().isCreated());
    	
    	PersonDTO person = service.getPersonByDocumentId("67789822098");
    	Assertions.assertEquals("67789822098", person.getDocument());
    }
    
    @Test
    @Order(3)  
    void whenValidIdPerson_thenPersonShouldReturned() throws Exception {
    	 
    	
    	mockMvc.perform(MockMvcRequestBuilders.get("/person/v1.0/1"))
    	        .andExpect(status().isOk());

    	PersonDTO person = service.getPersonById(1);
    	Assertions.assertEquals(1, person.getId());
    	
    }
    
    
    @Test
    @Order(4)  
    void whenValidDocumentIdPerson_thenPersonShouldReturned() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders.get("/person/document/v1.0/67789822098"))
    	        .andExpect(status().isOk());
    	
    	PersonDTO person = service.getPersonByDocumentId("67789822098");
    	Assertions.assertEquals("67789822098", person.getDocument());
    	
    }
    
}
