package com.EatEasy.Repository;

import com.EatEasy.Models.Booking;
import com.EatEasy.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByUuid(UUID uuid);
    List<Booking> findByUser_Id(Long userId);

}
