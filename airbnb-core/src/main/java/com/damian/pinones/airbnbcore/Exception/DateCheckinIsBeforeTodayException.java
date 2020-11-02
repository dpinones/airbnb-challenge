package com.damian.pinones.airbnbcore.Exception;

public class DateCheckinIsBeforeTodayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DateCheckinIsBeforeTodayException(String message) {
        super(message);
    }
}
