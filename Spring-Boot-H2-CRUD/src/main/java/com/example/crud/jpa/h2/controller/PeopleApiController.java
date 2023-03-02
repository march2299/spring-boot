package com.example.crud.jpa.h2.controller;

import java.util.List;

import com.example.crud.jpa.h2.model.Person;
import com.example.crud.jpa.h2.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/people")
public class PeopleApiController {

    private static final String REQUEST_NO_BODY = "Request does not contain a body";

    @Autowired
    PeopleService peopleService;

    @GetMapping("/get/persons")
    public List<Person> getAllPeople() {
        return peopleService.findAllPeople();
    }

    @GetMapping("/get/person/{id}")
    public Person getPerson(@PathVariable long id) {
        return peopleService.findById(id);
    }

    @PostMapping("/add/person")
    public String addPerson(@RequestBody Person person) {
        if(person != null) {
            peopleService.insert(person);
            return "Added a person";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @PostMapping("/add/persons")
    public String addPeople(@RequestBody List<Person> people) {
        if(people != null && !people.isEmpty()) {
            peopleService.insertAll(people);
            return String.format("Added %d people.", people.size());
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @DeleteMapping("/delete/person/{id}")
    public String deletePerson(@PathVariable("id") long id) {
        if(id > 0) {
            if(peopleService.delete(id)) {
                return "Deleted the person.";
            } else {
                return "Cannot delete the person.";
            }
        }
        return "The id is invalid for the person.";
    }

    @DeleteMapping("/delete/persons")
    public String deletePeople(@RequestBody List<Person> ids) {
        if(!ids.isEmpty()) {
            if(peopleService.deleteAll(ids)) {
                return "Deleted the person.";
            } else {
                return "Cannot delete the person.";
            }
        }
        return "The request should contain a list of people to be deleted.";
    }

    @PutMapping("/update/person")
    public String updatePerson(@RequestBody Person person) {
        if(person != null) {
            peopleService.update(person);
            return "Updated person.";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @PutMapping("/update/persons")
    public String updatePeople(@RequestBody List<Person> people) {
        if(people != null) {
            peopleService.updateAll(people);
            return "Updated people.";
        } else {
            return REQUEST_NO_BODY;
        }
    }
}