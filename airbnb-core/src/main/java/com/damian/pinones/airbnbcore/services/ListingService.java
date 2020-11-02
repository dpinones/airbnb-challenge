package com.damian.pinones.airbnbcore.services;


import com.damian.pinones.airbnbcore.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ListingService {

    public Optional<ListingDTO> getListingById(Integer id);

    public List<ListingDTO> findAllListings();

    public Integer saveListing(ListingDTO listingDTO);

    public ListingDTO updateListing(ListingDTO listingDTO);

    public void deleteListingById(Integer id);

    public Optional<SpecialPriceDTO> getSpecialPriceById(Integer id);

    public Integer saveSpecialPrice(Integer idListing, SpecialPriceDTO specialPriceDTO);

    public void deleteSpecialPriceById(Integer id);


    CostDTO calculeReservation(Integer idListing, ReservationDTO reservationDTO);
}
