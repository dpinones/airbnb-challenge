package com.damian.pinones.airbnbcore.mappers;

import com.damian.pinones.airbnbcore.entities.vol.CostEntity;
import com.damian.pinones.airbnbcore.model.CostDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CostMapper {

    public CostDTO getCostDto(CostEntity costEntity);

    public CostEntity getCostEntity(CostDTO costDTO);

    public List<CostDTO> getCostsDtos(List<CostEntity> costsEntity);

    public List<CostEntity> getCostsEntities(List<CostDTO> costsDtos);
}
