package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.StudentDTO;
import com.orm.onetomany.entity.Course;
import com.orm.onetomany.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class StudentMapperDecorator implements StudentMapper{

    @Autowired
    @Qualifier("delegate")
    private StudentMapper delegate;

    @Override
    public StudentDTO toDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());

        if (student.getCourses() != null) {
            studentDTO.setCourseIds(student.getCourses().stream().map(Course::getId).toList());
        }
        return studentDTO;
    }


}
