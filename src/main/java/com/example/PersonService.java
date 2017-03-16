package com.example;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Alejandro Duarte
 */
@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> searchByFirstNameOrLastName(String firstName, String lastName, int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        return repository.findByFirstNameIgnoringCaseContainingOrLastNameIgnoringCaseContaining(firstName, lastName, pageRequest).getContent();
    }

    public Integer searchByFirstNameOrLastName(String firstName, String lastName) {
        return Math.toIntExact(repository.countByFirstNameIgnoringCaseContainingOrLastNameIgnoringCaseContaining(firstName, lastName));
    }

}
