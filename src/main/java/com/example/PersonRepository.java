package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alejandro Duarte
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
