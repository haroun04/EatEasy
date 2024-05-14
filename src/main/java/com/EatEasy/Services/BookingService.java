package com.EatEasy.Services;
import com.EatEasy.Models.Booking;
import com.EatEasy.Models.Review;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();

    Booking findById(Long id);

    Booking save(Booking booking);

    void deleteById(Long id);

    Booking update(Long id, Booking booking);

    List<Booking> findBookingsByUserId(Long userId);

}
