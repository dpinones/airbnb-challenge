package com.damian.pinones.airbnbcore.mappers;

import com.damian.pinones.airbnbcore.entities.UserEntity;
import com.damian.pinones.airbnbcore.model.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO getUserDto(UserEntity userEntity);

    public UserEntity getUserEntity(UserDTO userDTO);

    public List<UserDTO> getUserDtos(List<UserEntity> usersEntity);

    public List<UserEntity> getUsersEntities(List<UserDTO> userDtos);
}
