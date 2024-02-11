package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.InstructorDTO;
import com.orm.onetomany.entity.Instructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.stream.Collectors;

@Log4j2
public abstract class InstructorMapperDecorator implements InstructorMapper{

    @Autowired
    @Qualifier("delegate")
    private InstructorMapper delegate;

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public InstructorDTO toDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getFirstName());
        instructorDTO.setLastName(instructor.getLastName());
        instructorDTO.setEmail(instructor.getEmail());

        if(instructor.getCourses() != null) {
            instructorDTO.setCourses(instructor.getCourses().stream().map(courseMapper::toDTO).collect(Collectors.toSet()));
        }

        return instructorDTO;
    }
}
