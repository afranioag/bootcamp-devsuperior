package com.devsuperior.tarefacrud.services.exceptions;

public class ResourceEntityNotFoundException extends RuntimeException {
    private static final long seriaLVersionUID = 1L;

    public ResourceEntityNotFoundException(String msg) {
        super(msg);
    }
}