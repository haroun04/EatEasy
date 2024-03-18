package com.EatEasy.Repository;

import com.EatEasy.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByUuid(UUID uuid);
}
