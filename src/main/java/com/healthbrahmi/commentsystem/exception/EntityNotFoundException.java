package com.healthbrahmi.commentsystem.exception;

/**
 * Created by anurag on 11/3/21.
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
