package com.fransica.cardscheme.exception;

import java.io.Serializable;

public class JwtInvalidException extends Throwable implements Serializable{
    private static final long serialVersionUID = 1L;

    public JwtInvalidException(String message) {
        super(message);
    }

    public JwtInvalidException(String message, Exception e) {
        super(message, e);
    }
}

