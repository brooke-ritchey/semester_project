package edu.uh.tech.cis3368.semesterproject;

import edu.uh.tech.cis3368.semesterproject.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
    Employee findByEmail(String email);
}
