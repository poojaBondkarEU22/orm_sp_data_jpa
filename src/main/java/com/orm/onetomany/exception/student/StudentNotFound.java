package com.orm.onetomany.exception.student;

public class StudentNotFound extends RuntimeException {
    public StudentNotFound(String massage){
        super(massage);
    }
}
