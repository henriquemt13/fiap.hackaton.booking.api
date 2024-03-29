package com.hackaton.booking.api.filter;

import com.hackaton.booking.api.exceptions.BadRequestException;
import com.hackaton.booking.api.exceptions.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity handleBadRequestException(BadRequestException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex,
          WebRequest request) {
        log.error(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .body(ex.getMessage());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleContraintViolationException(ConstraintViolationException ex,
          WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(e -> errors.add(
              String.format("%s", e.getMessage())));
        log.error(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
          WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getFieldErrors().forEach(e -> errors.add(
              String.format("%s: %s (%s)", e.getField(), e.getRejectedValue(),
                    e.getDefaultMessage())));
        log.error(errors.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
