package com.damian.pinones.airbnbcore.repositories;

import com.damian.pinones.airbnbcore.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {


}
