package com.damian.pinones.airbnbcore.Exception;

public class DateCheckoutIsBeforeTodayException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DateCheckoutIsBeforeTodayException(String message) {
        super(message);
    }
}
