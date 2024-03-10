package com.orm.onetomany.controller.exceptionhandler;

import com.orm.onetomany.exception.course.CourseTitleCannotChangeException;
import com.orm.onetomany.exception.student.StudentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleStudentNotFound(StudentNotFound studentNotFound) {

        StudentErrorResponse errorResponse = new StudentErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                studentNotFound.getMessage());

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
