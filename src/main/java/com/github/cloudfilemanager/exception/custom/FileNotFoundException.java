package com.github.cloudfilemanager.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String fileName) {
        super("File " + fileName + " not found.");
    }

}