package com.github.cloudfilemanager.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileAlreadyExistsException extends RuntimeException {

    public FileAlreadyExistsException(String fileName) {
        super("File " + fileName + " already exists.");
    }

}