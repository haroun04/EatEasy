package com.EatEasy.Repository;

import com.EatEasy.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByUuid(UUID uuid);
    Owner findByEmail(String email);
    List<Owner> findByNameContainingIgnoreCase(String name);
    List<Owner> findByRestaurants_Name(String restaurantName);
}
