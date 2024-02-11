package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.dto.InstructorDTO;
import com.orm.onetomany.entity.Instructor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring"  /*uses = {CourseMapper.class}*/)
@DecoratedWith(InstructorMapperDecorator.class)
public interface InstructorMapper {

    @BeanMapping(builder = @Builder(disableBuilder = true))
    InstructorDTO toDTO(Instructor instructor);


   /* -- one of the way to handle circular references
   @AfterMapping
    default void mapCourses(@MappingTarget InstructorDTO instructorDTO, Instructor instructor) {
        if (instructor != null && instructor.getCourses() != null) {
            instructorDTO.setCourses(instructor.getCourses().stream()
                    .map(CourseMapper.INSTANCE::toDTO)
                    .collect(Collectors.toSet()));
        }
    }*/


    @InheritInverseConfiguration(name = "toDTO")
    Instructor toEntity(InstructorDTO instructorDTO);


}




