package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alejandro Duarte
 */
@Service
public class PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll(int offset, int limit) {
        int page = offset / limit;
        PageRequest pageRequest = new PageRequest(page, limit);
        return repository.findAll(pageRequest).getContent();
    }

    public Integer count() {
        return Math.toIntExact(repository.count());
    }

}
