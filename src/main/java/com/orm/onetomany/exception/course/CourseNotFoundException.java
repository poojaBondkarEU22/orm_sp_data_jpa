package com.orm.onetomany.exception.course;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String massage){
        super(massage);
    }
}
