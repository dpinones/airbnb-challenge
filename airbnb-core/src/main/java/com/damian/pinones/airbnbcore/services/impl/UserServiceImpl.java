package com.damian.pinones.airbnbcore.services.impl;

import com.damian.pinones.airbnbcore.entities.UserEntity;
import com.damian.pinones.airbnbcore.mappers.UserMapper;
import com.damian.pinones.airbnbcore.model.UserDTO;
import com.damian.pinones.airbnbcore.repositories.UserRepository;
import com.damian.pinones.airbnbcore.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private UserMapper userMapper;

    public List<UserDTO> findAllUsers() {

        List<UserEntity> usersEntities = userRepository.findAll();
        List<UserDTO> usersDtos = userMapper.getUserDtos(usersEntities);
        return usersDtos;

    }

    public Optional<UserDTO> getUserById(Integer id) {

        Optional<UserEntity> optionUserEntity = userRepository.findById(id);
        UserEntity userEntity = optionUserEntity.get();
        UserDTO userDTO = userMapper.getUserDto(userEntity);
        return Optional.ofNullable(userDTO);

    }

    public Integer saveUser(UserDTO userDTO) {

        UserEntity userEntity = userMapper.getUserEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userEntity.getId();

    }

    public UserDTO updateUser(UserDTO userDTO) {

        UserEntity userEntity = userMapper.getUserEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userMapper.getUserDto(userEntity);

    }

    public void deleteUserById(Integer id) {

        userRepository.deleteById(id);

    }
}
