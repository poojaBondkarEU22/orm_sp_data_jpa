package com.orm.onetomany.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_instructor")
@Getter
@Setter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany( mappedBy = "instructor" ,
                cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,CascadeType.PERSIST})
    private Set<Course> courses;


    public void assignCourseToInstructor(Course newCourse) {
        if(courses == null) {
            courses = new HashSet<>();
        }
        courses.add(newCourse);
        newCourse.setInstructor(this);
    }

    public void removeCourseFromInstructor(Course course) {
        courses.remove(course);
        course.setInstructor(null);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
