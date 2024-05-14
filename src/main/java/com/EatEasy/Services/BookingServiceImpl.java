package com.EatEasy.Services;

import com.EatEasy.Models.Booking;
import com.EatEasy.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
            return bookingRepository.findById(id).orElseThrow();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking update(Long id, Booking model) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setNumberDiners(model.getNumberDiners());
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public List<Booking> findBookingsByUserId(Long userId) {
        return bookingRepository.findByUser_Id(userId);
    }
}
