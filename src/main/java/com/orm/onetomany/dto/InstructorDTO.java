package com.orm.onetomany.dto;

import com.orm.onetomany.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<CourseDTO> courses;

}
