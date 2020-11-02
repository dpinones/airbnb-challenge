package com.damian.pinones.airbnbcore.Helper;

import com.damian.pinones.airbnbcore.entities.ListingEntity;
import com.damian.pinones.airbnbcore.entities.SpecialPriceEntity;
import com.damian.pinones.airbnbcore.entities.vol.CostEntity;
import com.damian.pinones.airbnbcore.entities.vol.ReservationEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculeCostReservation {

    public static CostEntity run(ListingEntity listingEntity, ReservationEntity reservationEntity){

        ValidateReservation.run(reservationEntity);

        CostEntity costEntity = new CostEntity();

        List<SpecialPriceEntity> specialPricesEntity = listingEntity.getSpecialPrices();
        List<LocalDate> daysReservation = daysReservation(reservationEntity.getCheckin(), reservationEntity.getCheckout());

        int daysBetween = (int) ChronoUnit.DAYS.between(reservationEntity.getCheckin(), reservationEntity.getCheckout());
        costEntity.setNightsCount(daysBetween);


        double total = getTotal(listingEntity, specialPricesEntity, daysReservation, daysBetween);
        costEntity.setNightsCost(total);

        costEntity.setDiscount(listingEntity.getWeeklyDiscount());
        costEntity.setCleaningFee(listingEntity.getCleaningFee());
        costEntity.calculeTotal();
        return costEntity;
    }

    private static double getTotal(ListingEntity listingEntity, List<SpecialPriceEntity> specialPricesEntity, List<LocalDate> daysReservation, int daysBetween) {
        double total = 0;
        for (int i = 0; i < daysBetween; i++) {
            total += listingEntity.getBasePrice();
        }

        for (LocalDate day: daysReservation) {
            for (SpecialPriceEntity specialPrice: specialPricesEntity) {
                if (day.equals(specialPrice.getDate())){
                    total -= listingEntity.getBasePrice();
                    total += specialPrice.getPrice();
                }
            }
        }
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }

    private static List<LocalDate> daysReservation(LocalDate dateCheckin, LocalDate dateCheckout) {
        List<LocalDate> daysReservation = new ArrayList<>();
        LocalDate acum = dateCheckin;
        long daysBetween = ChronoUnit.DAYS.between(dateCheckin, dateCheckout);
        for (int i = 0; i < daysBetween; i++) {
            daysReservation.add(acum);
            acum = acum.plusDays(1);
        }
        return daysReservation;
    }
}
