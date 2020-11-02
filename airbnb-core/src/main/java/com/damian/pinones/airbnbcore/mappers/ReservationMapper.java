package com.damian.pinones.airbnbcore.mappers;

import com.damian.pinones.airbnbcore.entities.ListingEntity;
import com.damian.pinones.airbnbcore.entities.vol.ReservationEntity;
import com.damian.pinones.airbnbcore.model.ReservationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    public ReservationDTO getReservationDto(ListingEntity reservationEntity);

    public ReservationEntity getReservationEntity(ReservationDTO reservationDTO);

    public List<ReservationDTO> getReservationsDtos(List<ReservationEntity> reservationsEntity);

    public List<ReservationEntity> getListingsEntities(List<ReservationDTO> reservationsDtos);
}
