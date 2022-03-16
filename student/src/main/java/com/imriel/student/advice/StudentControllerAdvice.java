package com.imriel.student.advice;

import com.imriel.student.customexception.*;
import com.imriel.student.exceptionresponse.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class StudentControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<Object> handleEmptyInput() {
        ExceptionResponse response = new ExceptionResponse(
                "empty Input 😔",
                LocalDateTime.now(),
                "name should not be null");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdInvalidException.class)
    public ResponseEntity<Object> handleIdInvalid() {
        ExceptionResponse response = new ExceptionResponse(
                "record doesn't exist with this id 😔",
                LocalDateTime.now(),
                "please try with another id");

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(StudentNotExistException.class)
    public ResponseEntity<Object> handleStudentNotExistException() {
        ExceptionResponse response = new ExceptionResponse(
                "record doesn't exist 😔",
                LocalDateTime.now(),
                "please add record after that we will able to fetch the record");

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(NameInvalidException.class)
    public ResponseEntity<Object> handleNameInvalidException() {
        ExceptionResponse response = new ExceptionResponse(
                "record doesn't exist with this name 😔",
                LocalDateTime.now(),
                "please try with another name");

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(NullIdException.class)
    public ResponseEntity<Object> handleNullIdException() {
        ExceptionResponse response = new ExceptionResponse(
                "id missing 😔",
                LocalDateTime.now(),
                "The given id must not be null !");

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(InternalError.class)
    public ResponseEntity<Object> handleInternalError() {
        ExceptionResponse response = new ExceptionResponse(
                "something went wrong 😔",
                LocalDateTime.now(),
                "please reach out to support team");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFindByCharacterException.class)
    public ResponseEntity<Object> handleNotFindByCharacterException(NotFindByCharacterException e, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse(
                "Oops.. doesn't find any record by this character 😔",
                LocalDateTime.now(),
                "please try with another character");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
