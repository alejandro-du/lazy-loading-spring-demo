package com.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alejandro Duarte
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByFirstNameIgnoringCaseContainingOrLastNameIgnoringCaseContaining(String firstName, String lastName, Pageable pageable);

    int countByFirstNameIgnoringCaseContainingOrLastNameIgnoringCaseContaining(String firstName, String lastName);

}
