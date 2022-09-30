package com.myeconomy.worldeconomyindex.exceptions.handler;

import com.myeconomy.worldeconomyindex.exceptions.DataExistingException;
import com.myeconomy.worldeconomyindex.exceptions.NoDataAvailable;
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

    @ExceptionHandler(NoDataAvailable.class)
    public ResponseEntity<Object> handleNoDataAvailable(NoDataAvailable e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.not-available");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
