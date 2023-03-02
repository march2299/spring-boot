package com.example.crud.jpa.h2.repository;

import com.example.crud.jpa.h2.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
