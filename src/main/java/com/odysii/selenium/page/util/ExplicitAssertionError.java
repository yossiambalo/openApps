package com.odysii.selenium.page.util;

public class ExplicitAssertionError extends AssertionError {
    private static final long serialVersionUID = 1L;
    public ExplicitAssertionError (String msg) {
        super(msg);
    }
}