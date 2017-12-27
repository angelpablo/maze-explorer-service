package com.angel.web.service.maze.explorer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionHandlingController {
    @ExceptionHandler(ExplorerSessionException.class)
    public ResponseEntity<String> handleWrongSession() {
        ResponseEntity<String> response = new ResponseEntity<>("Bad Session, bad boy.", HttpStatus.BAD_REQUEST);
        return response;
    }

}
