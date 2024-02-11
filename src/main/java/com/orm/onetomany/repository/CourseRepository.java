package com.orm.onetomany.repository;

import com.orm.onetomany.entity.Course;
import com.orm.onetomany.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByInstructor(Instructor instructor);

    Optional<Course> findCourseByTitle(String courseTitle);
}
