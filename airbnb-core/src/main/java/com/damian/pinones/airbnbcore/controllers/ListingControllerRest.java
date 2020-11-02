package com.damian.pinones.airbnbcore.controllers;

import com.damian.pinones.airbnbcore.Helper.HatoeasListingHelper;
import com.damian.pinones.airbnbcore.model.*;
import com.damian.pinones.airbnbcore.services.ListingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/listings")
@Api(tags = "Listing API")
public class ListingControllerRest {

    @Autowired
    private ListingService listingService;

    @Autowired
    private HatoeasListingHelper hatoeasListingHelper;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }

    @ApiOperation(notes="Retrieve all listings",value="Get listings")
    @GetMapping
    public ResponseEntity<CollectionModel<ListingDTO>> getListings(){

        List<ListingDTO> listings = listingService.findAllListings();
        CollectionModel<ListingDTO> listingsModel =  hatoeasListingHelper.generateLinksSelfList(listings);
        listings.forEach(team -> {
            try {
                hatoeasListingHelper.generateSelfLink(team);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return ResponseEntity.ok(listingsModel);
    }

    @ApiOperation(notes="Retrieve one listing by id",value="Get listing by id")
    @GetMapping("/{id}")
    public ResponseEntity<ListingDTO> getListingById(
            @ApiParam(example = "1",value = "Identifier for Listing",allowableValues = "1,2,3,4",required = true)
            @PathVariable Integer id){

        Optional<ListingDTO> listingDTOOptional = listingService.getListingById(id);
        ListingDTO listingDTO = null;

        try {

            listingDTO = listingDTOOptional.orElseThrow(NoSuchElementException::new);
            hatoeasListingHelper.generateSelfLink(listingDTO);

        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listingDTO);
    }

    @ApiOperation(notes="Save one listing",value="Post listing")
    @PostMapping
    public ResponseEntity<String> saveListing(@RequestBody ListingDTO listingDTO){

        Integer id = listingService.saveListing(listingDTO);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(location.toString());
    }

    @ApiOperation(notes="Update one listing",value="Put listing")
    @PutMapping
    public ResponseEntity<ListingDTO> updateListing(@RequestBody ListingDTO listingDTO) {

        listingDTO = listingService.updateListing(listingDTO);

        return ResponseEntity.ok(listingDTO);
    }

    @ApiOperation(notes="Delete one listing by id",value="Delete listing by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListingById(
            @ApiParam(example = "1",value = "Identifier for Listing",allowableValues = "1,2,3,4",required = true)
            @PathVariable Integer id) {

        listingService.deleteListingById(id);

        return ResponseEntity.ok().build();
    }


    @ApiOperation(notes="Retrieve special price for a listing",value="Get special price for a listing")
    @GetMapping("/{idListing}/special-prices/{idSpecialPrice}")
    public ResponseEntity<SpecialPriceDTO> getSpecialPriceById(
            @ApiParam(example = "1",value = "Identifier for Listing",allowableValues = "1,2,3,4",required = true) @PathVariable Integer idListing,
            @ApiParam(example = "1",value = "Identifier for Special Price",allowableValues = "1,2,3,4",required = true) @PathVariable Integer idSpecialPrice) {

        try {
            Optional<ListingDTO> listingDTOOptional = listingService.getListingById(idListing);
            listingDTOOptional.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        Optional<SpecialPriceDTO> specialPriceDTOOptional = listingService.getSpecialPriceById(idSpecialPrice);
        SpecialPriceDTO specialPriceDTO = null;
        try {
            specialPriceDTO = specialPriceDTOOptional.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specialPriceDTO);
    }

    @ApiOperation(notes="Add special price for a listing",value="Post special price for a listing")
    @PostMapping("/{idListing}/special-prices")
    public ResponseEntity<String> saveSpecialPrice(
            @ApiParam(example = "1",value = "Identifier for Listing",allowableValues = "1,2,3,4",required = true) @PathVariable Integer idListing,
            @RequestBody SpecialPriceDTO specialPriceDTO){


        try {
            Optional<ListingDTO> listingDTOOptional = listingService.getListingById(idListing);
            listingDTOOptional.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        Integer id = listingService.saveSpecialPrice(idListing, specialPriceDTO);

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(location.toString());
    }

    @ApiOperation(notes="Remove special price for a listing",value="Delete special price for a listing")
    @DeleteMapping("/{idListing}/special-prices/{idSpecialPrice}")
    public ResponseEntity<Void> deleteSpecialPriceById(
            @ApiParam(example = "1",value = "Identifier for Listing",allowableValues = "1,2,3,4",required = true) @PathVariable Integer idListing,
            @ApiParam(example = "1",value = "Identifier for Special Price",allowableValues = "1,2,3,4",required = true) @PathVariable Integer idSpecialPrice) {

        try {
            Optional<ListingDTO> listingDTOOptional = listingService.getListingById(idListing);
            listingDTOOptional.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        try {
            Optional<SpecialPriceDTO> specialPriceDTOOptional = listingService.getSpecialPriceById(idSpecialPrice);
            specialPriceDTOOptional.orElseThrow(NoSuchElementException::new);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

        listingService.deleteSpecialPriceById(idSpecialPrice);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(notes="Calculate the cost of a reservation",value="Calculate the cost of a reservation")
    @GetMapping("/{idListing}/special-prices/checkout")
    public ResponseEntity<CostDTO> calculeCostReservation(@PathVariable Integer idListing, @RequestBody ReservationDTO reservationDTO){

        CostDTO costDTO = listingService.calculeReservation(idListing, reservationDTO);
        return ResponseEntity.ok(costDTO);
    }


}
