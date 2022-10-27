package com.example.demo.core.exception;

import java.text.MessageFormat;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(Long id) {
        super(MessageFormat.format("Movie with id:{0} not found", id));
    }
}
