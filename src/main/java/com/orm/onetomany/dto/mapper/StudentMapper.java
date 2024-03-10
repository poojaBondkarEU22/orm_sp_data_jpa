package com.orm.onetomany.dto.mapper;

import com.orm.onetomany.dto.StudentDTO;
import com.orm.onetomany.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
@DecoratedWith(StudentMapperDecorator.class)
public interface StudentMapper {

    @BeanMapping(builder = @Builder(disableBuilder = true))
    @Mapping(target = "courses" , ignore = true)
    StudentDTO toDTO(Student student);

    @InheritInverseConfiguration(name = "toDTO")
    Student toEntity(StudentDTO studentDTO);
}
