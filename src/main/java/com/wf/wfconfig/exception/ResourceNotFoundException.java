package com.wf.wfconfig.exception;

/**
 * @author liudongxu06
 * @since 2019-04-09
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super("Resource not found ->",cause);
    }
}
