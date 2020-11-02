package com.damian.pinones.airbnbcore.services.impl;

import com.damian.pinones.airbnbcore.Exception.DateCheckinIsAfterDateCheckoutException;
import com.damian.pinones.airbnbcore.Exception.DateCheckinIsBeforeTodayException;
import com.damian.pinones.airbnbcore.Exception.DateCheckoutIsBeforeTodayException;
import com.damian.pinones.airbnbcore.Exception.DifferenceDaysException;
import com.damian.pinones.airbnbcore.Helper.CalculeCostReservation;
import com.damian.pinones.airbnbcore.entities.ListingEntity;
import com.damian.pinones.airbnbcore.entities.SpecialPriceEntity;
import com.damian.pinones.airbnbcore.entities.UserEntity;
import com.damian.pinones.airbnbcore.entities.vol.CostEntity;
import com.damian.pinones.airbnbcore.entities.vol.ReservationEntity;
import com.damian.pinones.airbnbcore.mappers.*;
import com.damian.pinones.airbnbcore.model.*;
import com.damian.pinones.airbnbcore.repositories.ListingRepository;
import com.damian.pinones.airbnbcore.repositories.SpecialPriceRepository;
import com.damian.pinones.airbnbcore.repositories.UserRepository;
import com.damian.pinones.airbnbcore.services.ListingService;
import com.damian.pinones.airbnbcore.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@RequiredArgsConstructor
@Service
public class ListingServiceImpl implements ListingService {


    @NonNull
    private ListingRepository listingRepository;

    @NonNull
    private ListingMapper listingMapper;

    @NonNull
    private SpecialPriceRepository specialPriceRepository;

    @NonNull
    private SpecialPriceMapper specialPriceMapper;

    @NonNull
    private CostMapper costMapper;

    @NonNull
    private ReservationMapper reservationMapper;


    public List<ListingDTO> findAllListings() {
        List<ListingEntity> listingsEntities = listingRepository.findAll();
        List<ListingDTO> listingsDTO = new ArrayList<>();

        listingsEntities.forEach(listingEntity -> {
            List<SpecialPriceEntity> specialPrices = listingEntity.getSpecialPrices();
            ListingDTO listingDTO = listingMapper.getListingDto(listingEntity);
            listingDTO.setSpecialPricesDTO(specialPriceMapper.getSpecialPricesDtos(specialPrices));
            listingsDTO.add(listingDTO);
        });

        return listingsDTO;
    }

    public Optional<ListingDTO> getListingById(Integer id) {

        Optional<ListingEntity> optionListingEntity = listingRepository.findById(id);

        ListingEntity listingEntity = optionListingEntity.get();
        List<SpecialPriceEntity> specialPrices = listingEntity.getSpecialPrices();
        ListingDTO listingDTO = listingMapper.getListingDto(listingEntity);
        listingDTO.setSpecialPricesDTO(specialPriceMapper.getSpecialPricesDtos(specialPrices));
        return Optional.ofNullable(listingDTO);
    }

    public Integer saveListing(ListingDTO listingDTO) {
        ListingEntity listingEntity = listingMapper.getListingEntity(listingDTO);
        listingEntity = listingRepository.save(listingEntity);
        return listingEntity.getId();
    }

    public ListingDTO updateListing(ListingDTO listingDTO) {
        ListingEntity listingEntity = listingMapper.getListingEntity(listingDTO);
        listingEntity = listingRepository.save(listingEntity);
        return listingMapper.getListingDto(listingEntity);
    }

    public void deleteListingById(Integer id) {
        listingRepository.deleteById(id);
    }

    public Optional<SpecialPriceDTO> getSpecialPriceById(Integer id) {
        Optional<SpecialPriceEntity> optionSpecialPriceEntity = specialPriceRepository.findById(id);
        SpecialPriceDTO SpecialPriceDTO = specialPriceMapper.getSpecialPriceDto(optionSpecialPriceEntity.get());
        return Optional.ofNullable(SpecialPriceDTO);
    }

    public Integer saveSpecialPrice(Integer idListing, SpecialPriceDTO specialPriceDTO) {

        Optional<ListingEntity> optionListingEntity = listingRepository.findById(idListing);
        ListingEntity listingEntity = null;
        try {
            listingEntity = optionListingEntity.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }

        SpecialPriceEntity specialPriceEntity = specialPriceMapper.getSpecialPriceEntity(specialPriceDTO);
        specialPriceEntity.setListingEntity(listingEntity);
        specialPriceEntity = specialPriceRepository.save(specialPriceEntity);
        return specialPriceEntity.getId();
    }

    public void deleteSpecialPriceById(Integer id) {
        specialPriceRepository.deleteById(id);
    }

    public CostDTO calculeReservation(Integer idListing, ReservationDTO reservationDTO) {

        Optional<ListingEntity> optionListingEntity = listingRepository.findById(idListing);
        ListingEntity listingEntity = null;
        try {
            listingEntity = optionListingEntity.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }

        ReservationEntity reservationEntity = reservationMapper.getReservationEntity(reservationDTO);
        CostEntity costEntity = CalculeCostReservation.run(listingEntity, reservationEntity);

        CostDTO costDto = costMapper.getCostDto(costEntity);
        return  costDto;
    }
}
