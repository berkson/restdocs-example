package com.example.sfgrestdocs.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

/**
 * Created by berkson
 * Date: 03/12/2021
 * Time: 20:30
 */
@ControllerAdvice
public class AbstractControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noSuchElement(NoSuchElementException e){
        return e.getMessage();
    }
}
