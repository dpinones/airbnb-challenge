package com.damian.pinones.airbnbcore.Exception;

public class DateCheckinIsAfterDateCheckoutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DateCheckinIsAfterDateCheckoutException(String message) {
        super(message);
    }
}
