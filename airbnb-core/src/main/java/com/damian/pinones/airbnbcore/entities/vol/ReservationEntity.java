package com.damian.pinones.airbnbcore.entities.vol;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationEntity {

    private LocalDate checkin;

    private LocalDate checkout;

}
