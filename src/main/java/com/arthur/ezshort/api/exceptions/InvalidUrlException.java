package com.arthur.ezshort.api.exceptions;

public class InvalidUrlException extends RuntimeException {

    private static final long serialVersionUID = 5013744369305426563L;

    public InvalidUrlException(String message) {
	super(message);
    }

}
