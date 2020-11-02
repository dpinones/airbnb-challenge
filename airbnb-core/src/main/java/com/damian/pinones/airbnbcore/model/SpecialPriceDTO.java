package com.damian.pinones.airbnbcore.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@ApiModel(description = "Special price")
public class SpecialPriceDTO {

    @ApiModelProperty(notes = "Unique identifier of the special price.", example = "1", position = 0)
    private String id;

    @ApiModelProperty(notes = "Date of the special price.", example = "2020-11-04", required = true, position = 1)
    private LocalDate date;

    @ApiModelProperty(notes = "Price of the special price.", example = "5.8", required = true, position = 2)
    private Double price;

}
