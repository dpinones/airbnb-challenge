package com.damian.pinones.airbnbcore.services;


import com.damian.pinones.airbnbcore.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public Optional<UserDTO> getUserById(Integer id);

//    public List<UserDTO> findAllUsers(UserDTO pageable);

    public List<UserDTO> findAllUsers();

    public Integer saveUser(UserDTO userDTO);

    public UserDTO updateUser(UserDTO userDTO);

    public void deleteUserById(Integer id);

}
