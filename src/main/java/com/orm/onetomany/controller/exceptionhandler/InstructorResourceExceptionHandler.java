package com.orm.onetomany.controller.exceptionhandler;

import com.orm.onetomany.exception.instructor.CourseAlreadyAssignToInstructorException;
import com.orm.onetomany.exception.instructor.CourseBelongToDifferentInstructorException;
import com.orm.onetomany.exception.instructor.InstructorNotFoundException;
import com.orm.onetomany.exception.instructor.NotSupportedOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InstructorResourceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<InstructorErrorResponse> handleCourseAlreadyAssignException(CourseAlreadyAssignToInstructorException courseAlreadyAssignToInstructorException) {

        InstructorErrorResponse errorResponse = new InstructorErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                courseAlreadyAssignToInstructorException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InstructorErrorResponse> handleCourseBelongsToDifferentInstructor(CourseBelongToDifferentInstructorException courseBelongToDifferentInstructorException) {

        InstructorErrorResponse errorResponse = new InstructorErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                courseBelongToDifferentInstructorException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<InstructorErrorResponse> handleNotSupportException(NotSupportedOperationException notSupportedOperationException) {

        InstructorErrorResponse errorResponse = new InstructorErrorResponse(
                HttpStatus.BAD_REQUEST.value() ,
                notSupportedOperationException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InstructorErrorResponse> handleInstructorNotFound(InstructorNotFoundException instructorNotFoundException) {

        InstructorErrorResponse errorResponse = new InstructorErrorResponse(
                HttpStatus.NOT_FOUND.value() ,
                instructorNotFoundException.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
