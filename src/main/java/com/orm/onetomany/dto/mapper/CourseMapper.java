package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.CourseDTO;
import com.orm.onetomany.entity.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring" )
@DecoratedWith(CourseMapperDecorator.class)
public interface CourseMapper {
    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    @Mapping(target = "instructorId", ignore = true)
   // @Mapping(target = "studentId", ignore = true)
    CourseDTO toDTO(Course course);

    @Mapping(target = "instructor" , source = "instructorDTO")
    @Mapping(target = "reviews" , source = "reviews")
    @Mapping(target = "students" , source = "students")
    Course toEntity(CourseDTO courseDTO);


}

