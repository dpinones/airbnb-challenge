package com.damian.pinones.airbnbcore.repositories;

import com.damian.pinones.airbnbcore.entities.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends JpaRepository<ListingEntity, Integer> {


}
