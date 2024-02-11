package com.orm.onetomany.exception.instructor;

public class CourseAlreadyAssignToInstructorException extends RuntimeException{

    public CourseAlreadyAssignToInstructorException(String massage) {
        super(massage);
    }
}
