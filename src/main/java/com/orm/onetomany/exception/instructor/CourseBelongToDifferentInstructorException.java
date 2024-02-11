package com.orm.onetomany.exception.instructor;

public class CourseBelongToDifferentInstructorException extends RuntimeException{

    public CourseBelongToDifferentInstructorException(String massage) {
        super(massage);
    }
}
