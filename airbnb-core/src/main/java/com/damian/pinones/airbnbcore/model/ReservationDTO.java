package com.damian.pinones.airbnbcore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationDTO {

    private LocalDate checkin;

    private LocalDate checkout;

}
