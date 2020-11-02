package com.damian.pinones.airbnbcore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "LISTING")
@Entity
public class ListingEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "LISTING_ID", nullable = false, unique = true)
    private Integer id;

    private String name;

    private String slug;

    private String description;

    private Integer adults;

    private Integer children;

    private Boolean isPetsAllowed;

    private Double basePrice;

    private Double cleaningFee;

    private String imageUrl;

    private Double weeklyDiscount;

    private Double monthlyDiscount;

    @OneToMany
    @JoinColumn(name = "LISTING_ID")
    private List<SpecialPriceEntity> specialPrices;



}
