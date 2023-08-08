package com.assignment.scheduler.exceptions;

import com.assignment.scheduler.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Response> invalidInput(InvalidInputException ex, WebRequest request){
        return ResponseEntity.badRequest().body(new Response(
                HttpStatus.BAD_REQUEST.value(), ex.getMessage()
        ));
    }
}
