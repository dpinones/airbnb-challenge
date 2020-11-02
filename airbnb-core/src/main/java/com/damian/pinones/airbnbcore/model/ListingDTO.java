package com.damian.pinones.airbnbcore.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "Listing")
public class ListingDTO extends RepresentationModel<ListingDTO> {

    @ApiModelProperty(notes = "Unique identifier of the listing", example = "1", position = 0)
    private Integer id;

    @ApiModelProperty(notes = "Name of the listing.", example = "Black Raven", position = 1)
    private String name;

    @ApiModelProperty(notes = "Slug of the listing.", example = "Morbi porttitor", position = 2)
    private String slug;

    @ApiModelProperty(notes = "Description of the listing.", example = "Morbi porttitor lorem id ligula. Suspendisse ornare consequ", position = 3)
    private String description;

    @ApiModelProperty(notes = "Adults of the listing.", example = "3", position = 4)
    private Integer adults;

    @ApiModelProperty(notes = "Children of the listing.", example = "2", position = 5)
    private Integer children;

    @ApiModelProperty(notes = "Is Pets Allowed of the listing.", example = "true", position = 6)
    private Boolean isPetsAllowed;

    @ApiModelProperty(notes = "Base Price of the listing.", example = "195.62", position = 7)
    private Double basePrice;

    @ApiModelProperty(notes = "cleaning Fee of the listing.", example = "95.82", position = 8)
    private Double cleaningFee;

    @ApiModelProperty(notes = "Image Url of the listing.", example = "http://dummyimage.com/126x173.bmp/cc0000/ffffff", position = 9)
    private String imageUrl;

    @ApiModelProperty(notes = "Weekly Discount of the listing.", example = "0.77", position = 10)
    private Double weeklyDiscount;

    @ApiModelProperty(notes = "MonthlyDiscount of the listing.", example = "0.61", position = 11)
    private Double monthlyDiscount;

    private List<SpecialPriceDTO> SpecialPricesDTO;

}
