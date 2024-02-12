package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.entity.Course;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Log4j2
public abstract class CourseMapperDecorator implements CourseMapper{

    @Autowired
    @Qualifier("delegate")
    private CourseMapper delegate;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public CourseDTO toDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        if(course.getInstructor() != null) {
            if(course.getInstructor().getId() != null) {
                courseDTO.setInstructorId(course.getInstructor().getId());
            }
        }
        if(course.getReviews() != null) {
            courseDTO.setReviews(course.getReviews().stream().map(reviewMapper::toDTO).toList());
        }
        return courseDTO;
    }
}
