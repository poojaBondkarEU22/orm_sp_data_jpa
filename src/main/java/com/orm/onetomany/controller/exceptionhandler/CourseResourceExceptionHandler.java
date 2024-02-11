package com.orm.onetomany.controller.exceptionhandler;

import com.orm.onetomany.exception.course.CourseNotFoundException;
import com.orm.onetomany.exception.course.CourseTitleCannotChangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleCourseNotFound(CourseNotFoundException courseNotFoundException) {

        CourseErrorResponse errorResponse = new CourseErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                courseNotFoundException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CourseErrorResponse> handleCourseTitleChangeException(CourseTitleCannotChangeException courseTitleCannotChangeException) {

        CourseErrorResponse errorResponse = new CourseErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                courseTitleCannotChangeException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
