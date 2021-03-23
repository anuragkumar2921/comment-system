package com.healthbrahmi.commentsystem.exception;

/**
 * Created by anurag on 11/3/21.
 */
public class NotAllowedException extends RuntimeException {
    public NotAllowedException(String message) {
        super(message);
    }
}
