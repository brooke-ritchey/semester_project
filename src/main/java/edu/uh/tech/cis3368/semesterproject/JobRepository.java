package edu.uh.tech.cis3368.semesterproject;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Integer> {
    Job findAllByStage(String stage);
}
