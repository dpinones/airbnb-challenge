package com.damian.pinones.airbnbcore.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "SPECIAL_PRICE")
@Entity
public class SpecialPriceEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SPECIAL_PRICE_ID", nullable = false, unique = true)
    private Integer id;

    private LocalDate date;

    private Double price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "LISTING_ID")
    private ListingEntity listingEntity;

    @JsonIgnore
    public ListingEntity getListingEntity(){
        return listingEntity;
    }
}
