package com.orm.onetomany.exception.course;

public class CourseTitleCannotChangeException extends RuntimeException {

    public CourseTitleCannotChangeException(String massage){
        super(massage);
    }

}
