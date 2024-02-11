package com.orm.onetomany.exception.instructor;

public class InstructorNotFoundException extends RuntimeException{

    public InstructorNotFoundException(String massage) {
        super(massage);
    }
}
