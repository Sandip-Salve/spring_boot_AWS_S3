package com.app.fileuploadanddownload.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIoException(IOException ex){
        String errorMsg = "File related exception occurred :: "+ ex.getMessage();
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchKeyException.class)
    public ResponseEntity<String> handleNoSuchKeyException(NoSuchKeyException ex){
        String msg = "Error::"+ ex.getMessage();
        return new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
