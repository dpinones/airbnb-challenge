package com.damian.pinones.airbnbcore.Helper;

import com.damian.pinones.airbnbcore.Exception.DateCheckinIsAfterDateCheckoutException;
import com.damian.pinones.airbnbcore.Exception.DateCheckinIsBeforeTodayException;
import com.damian.pinones.airbnbcore.Exception.DateCheckoutIsBeforeTodayException;
import com.damian.pinones.airbnbcore.Exception.DifferenceDaysException;
import com.damian.pinones.airbnbcore.entities.vol.ReservationEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidateReservation {

    public static void run(ReservationEntity reservationEntity){

        LocalDate dateCheckin = reservationEntity.getCheckin();
        LocalDate dateCheckout = reservationEntity.getCheckout();

        LocalDate today = LocalDate.now();
        if(dateCheckin.isBefore(today)){
            throw new DateCheckinIsBeforeTodayException(String.format("Date checkin %tD is before today %tD", dateCheckin, today));
        }

        if(dateCheckout.isBefore(today)){
            throw new DateCheckoutIsBeforeTodayException(String.format("Date checkout %tD is before today %tD", dateCheckout, today));
        }

        if(dateCheckin.isAfter(dateCheckout)){
            throw new DateCheckinIsAfterDateCheckoutException(String.format("Date checkin %tD is after date checkout %tD", dateCheckin, dateCheckout));
        }

        long daysBetween = ChronoUnit.DAYS.between(dateCheckin, dateCheckout);
        if(daysBetween > 28){
            throw new DifferenceDaysException(String.format("Difference of days %d exceeds 28 days allowed", daysBetween));
        }
    }
}
