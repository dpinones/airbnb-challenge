package com.damian.pinones.airbnbcore.mappers;

import com.damian.pinones.airbnbcore.entities.ListingEntity;
import com.damian.pinones.airbnbcore.model.ListingDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingMapper {

    public ListingDTO getListingDto(ListingEntity listingEntity);

    public ListingEntity getListingEntity(ListingDTO listingDTO);

    public List<ListingDTO> getListingsDtos(List<ListingEntity> listingsEntity);

    public List<ListingEntity> getListingsEntities(List<ListingDTO> listingsDtos);
}
