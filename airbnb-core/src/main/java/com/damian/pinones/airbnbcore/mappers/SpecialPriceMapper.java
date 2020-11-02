package com.damian.pinones.airbnbcore.mappers;

import com.damian.pinones.airbnbcore.entities.SpecialPriceEntity;
import com.damian.pinones.airbnbcore.model.SpecialPriceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialPriceMapper {

    public SpecialPriceDTO getSpecialPriceDto(SpecialPriceEntity specialPriceEntity);

    public SpecialPriceEntity getSpecialPriceEntity(SpecialPriceDTO SpecialPriceDTO);

    public List<SpecialPriceDTO> getSpecialPricesDtos(List<SpecialPriceEntity> specialPricesEntity);

    public List<SpecialPriceEntity> getSpecialPricesEntities(List<SpecialPriceDTO> specialPriceDtos);
}
