package edu.uh.tech.cis3368.semesterproject;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
    Customer findByFirstNameAndLastNameAndAddress(String first, String last, String address);
}
