package com.github.cloudfilemanager.exception.handler;

import com.github.cloudfilemanager.exception.custom.FileAlreadyExistsException;
import com.github.cloudfilemanager.exception.custom.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleAuthenticationException(AuthenticationException e) {
        log.error("Authentication error: {}", e.getMessage(), e);

        final HttpStatus status = HttpStatus.UNAUTHORIZED;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());

        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ProblemDetail> handleExpiredJwtException(ExpiredJwtException e) {
        log.error("Expired JWT error: {}", e.getMessage(), e);

        final HttpStatus status = HttpStatus.UNAUTHORIZED;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());

        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ProblemDetail> handleUnauthorizedException(UnauthorizedException e) {
        log.error("Unauthorized: {}", e.getMessage(), e);

        final HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());

        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleFileAlreadyExistsException(FileAlreadyExistsException e) {
        log.error("File already exist: {}", e.getMessage(), e);

        final HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());

        return ResponseEntity.status(status).body(problemDetail);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericException(Exception e) {
        log.error("Unexpected error: {}", e.getMessage(), e);

        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "An unexpected error occurred.");

        return ResponseEntity.status(status).body(problemDetail);
    }

}
