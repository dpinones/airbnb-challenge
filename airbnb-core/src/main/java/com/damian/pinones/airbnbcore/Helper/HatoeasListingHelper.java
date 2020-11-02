package com.damian.pinones.airbnbcore.Helper;

import com.damian.pinones.airbnbcore.controllers.ListingControllerRest;
import com.damian.pinones.airbnbcore.model.ListingDTO;
import lombok.Data;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Data
public class HatoeasListingHelper {

    public  void generateSelfLink(ListingDTO listingDTO){

        Link withSelfRel = linkTo(methodOn(ListingControllerRest.class).getListingById(listingDTO.getId())).withSelfRel();
        listingDTO.add(withSelfRel);

    }

    public  CollectionModel<ListingDTO> generateLinksSelfList(List<ListingDTO> Listings) {

        Link link = linkTo(methodOn(ListingControllerRest.class).getListings()).withSelfRel();
        CollectionModel<ListingDTO> result = CollectionModel.of(Listings, link);
        return result;
    }
}
