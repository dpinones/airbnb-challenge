package com.damian.pinones.airbnbcore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CostDTO {

    private Integer nightsCount;
    private Double nightsCost;
    private Double discount;
    private Double cleaningFee;
    private Double total;

}
