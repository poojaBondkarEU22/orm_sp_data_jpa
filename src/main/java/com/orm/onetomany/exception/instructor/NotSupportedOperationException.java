package com.orm.onetomany.exception.instructor;

public class NotSupportedOperationException extends RuntimeException{

    public NotSupportedOperationException(String massage) {
        super(massage);
    }
}
