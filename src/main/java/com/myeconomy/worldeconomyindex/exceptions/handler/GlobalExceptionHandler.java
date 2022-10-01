package com.myeconomy.worldeconomyindex.exceptions.handler;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.InvalidInputException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailableException;
import com.myeconomy.worldeconomyindex.exceptions.model.CommonExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataExistingException.class)
    public ResponseEntity<Object> handleDataExistingException(DataExistingException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.already-exist");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoDataAvailableException.class)
    public ResponseEntity<Object> handleNoDataAvailableException(NoDataAvailableException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.not-available");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.invalid-input");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
