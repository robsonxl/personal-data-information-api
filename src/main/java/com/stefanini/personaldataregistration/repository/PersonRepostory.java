package com.stefanini.personaldataregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stefanini.personaldataregistration.model.Person;

@Repository
public interface PersonRepostory extends MongoRepository<Person, Integer> {

}
