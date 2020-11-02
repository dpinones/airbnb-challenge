package com.damian.pinones.airbnbcore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String name;

    private String email;

    private String password;

    private List<ListingDTO> listingsDTO;



}
