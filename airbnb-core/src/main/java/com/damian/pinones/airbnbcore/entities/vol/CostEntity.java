package com.damian.pinones.airbnbcore.entities.vol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CostEntity {

    private Integer nightsCount;
    private Double nightsCost;
    private Double discount;
    private Double cleaningFee;
    private Double total;

    public void calculeTotal(){
        total = nightsCost + discount + cleaningFee;
        total = Math.round(total * 100.0) / 100.0;
    }

}
