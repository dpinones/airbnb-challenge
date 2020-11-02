package com.damian.pinones.airbnbcore.controllers;

import com.damian.pinones.airbnbcore.model.UserDTO;
import com.damian.pinones.airbnbcore.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@Api(tags = "User API")
@RequestMapping("/users")
public class UserControllerRest {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){

        List<UserDTO> users = userService.findAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){

        Optional<UserDTO> userDTOOptional = userService.getUserById(id);
        UserDTO userDTO = null;

        try {

            userDTO = userDTOOptional.orElseThrow(NoSuchElementException::new);
//            hatoeasTeamHelper.generateSelfLink(teamDTO);

        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){

        Integer id = userService.saveUser(userDTO);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        userDTO = userService.updateUser(userDTO);

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {

        userService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }


}
