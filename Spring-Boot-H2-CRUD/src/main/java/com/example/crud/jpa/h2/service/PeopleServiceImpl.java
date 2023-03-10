package com.example.crud.jpa.h2.service;

import java.util.List;
import java.util.Optional;

import com.example.crud.jpa.h2.model.Person;
import com.example.crud.jpa.h2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PersonRepository repository;

    @Override
    public List<Person> findAllPeople() {
        return (List<Person>)repository.findAll();
    }

    @Override
    public Person findById(long id) {
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Person> findByIds(List<Long> ids) {
        return (List<Person>)repository.findAllById(ids);
    }

    @Override
    public Person insert(Person p) {
        return repository.save(p);
    }

    @Override
    public List<Person> insertAll(List<Person> p) {
        return (List<Person>)repository.saveAll(p);
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll(List<Person> ids) {

        try {
            repository.deleteAll(ids);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Person p) {

        try {
            repository.save(p);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAll(List<Person> p) {
        try {
            repository.saveAll(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
