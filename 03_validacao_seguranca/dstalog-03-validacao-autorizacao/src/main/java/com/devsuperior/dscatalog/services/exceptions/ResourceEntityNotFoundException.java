package com.devsuperior.dscatalog.services.exceptions;

public class ResourceEntityNotFoundException extends RuntimeException {
    private static final long seriaLVersionUID = 1L;

    public ResourceEntityNotFoundException(String msg) {
        super(msg);
    }
}
