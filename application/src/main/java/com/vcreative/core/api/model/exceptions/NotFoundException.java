package com.vcreative.core.api.model.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("The resource you requested does not exist");
    }

    public NotFoundException(final String message) {
        super(message);
    }
}
