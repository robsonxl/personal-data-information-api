package com.stefanini.personaldataregistration.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "database_sequences")
@Getter
@Setter
public class DatabaseSequenceRepository {
	
    @Id
    private String id;
    private long seq;

}
