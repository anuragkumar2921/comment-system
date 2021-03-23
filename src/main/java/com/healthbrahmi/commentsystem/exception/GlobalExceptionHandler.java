package com.healthbrahmi.commentsystem.exception;

import com.healthbrahmi.commentsystem.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by anurag on 11/3/21.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String ENTITY_NOT_FOUND = "ENTITY_NOT_FOUND";
    private static final String NOT_ALLOWED = "NOT_ALLOWED";

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("EntityNotFoundException: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ENTITY_NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<ErrorResponseDto> handleNotAllowedException(NotAllowedException ex) {
        log.error("NotAllowedException: {}", ex.getMessage(), ex);
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(NOT_ALLOWED, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }
}
