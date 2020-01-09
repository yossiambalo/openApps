package com.odysii.utils;

public class ExplicitAssertionError extends AssertionError {
    private static final long serialVersionUID = 1L;
    public ExplicitAssertionError (String msg) {
        super(msg);
    }
}