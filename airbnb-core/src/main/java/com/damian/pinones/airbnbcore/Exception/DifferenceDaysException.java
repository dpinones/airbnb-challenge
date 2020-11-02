package com.damian.pinones.airbnbcore.Exception;

public class DifferenceDaysException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DifferenceDaysException(String message) {
        super(message);
    }
}
