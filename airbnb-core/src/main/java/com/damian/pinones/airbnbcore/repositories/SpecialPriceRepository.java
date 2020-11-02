package com.damian.pinones.airbnbcore.repositories;

import com.damian.pinones.airbnbcore.entities.SpecialPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialPriceRepository extends JpaRepository<SpecialPriceEntity, Integer> {


}
