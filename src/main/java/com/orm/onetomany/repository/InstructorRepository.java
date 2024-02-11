package com.orm.onetomany.repository;

import com.orm.onetomany.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findInstructorByFirstNameAndLastName(String firstName, String lastName);
}
