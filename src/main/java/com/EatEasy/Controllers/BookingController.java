package com.EatEasy.Controllers;

import com.EatEasy.Dtos.BookingRequestDto;
import com.EatEasy.Dtos.BookingResponseDto;
import com.EatEasy.Mapper.BookingMapper;
import com.EatEasy.Models.Booking;
import com.EatEasy.Models.Review;
import com.EatEasy.Services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
    private final BookingMapper bookingMapper;
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingMapper bookingMapper, BookingService bookingService) {
        this.bookingMapper = bookingMapper;
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public ResponseEntity<List<BookingResponseDto>> getAllBookings(){
        log.info("getAllBookings");
        return ResponseEntity.ok(
                bookingMapper.toResponse(bookingService.findAll())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingByID(@PathVariable Long id){
        log.info("getBookingByID");
        return ResponseEntity.ok(
                bookingMapper.toResponse(bookingService.findById(id))
        );
    }

    @PostMapping("")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto){
        log.info("createBooking");
        BookingResponseDto bookingResponseDto = bookingMapper.toResponse(bookingService.save(bookingMapper.toModel(bookingRequestDto)));
        return ResponseEntity.ok(bookingResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDto bookingRequestDto){
        log.info("updateBooking");
        BookingResponseDto bookingResponseDto = bookingMapper.toResponse(bookingService.update(id, bookingMapper.toModel(bookingRequestDto)));
        return ResponseEntity.ok(bookingResponseDto);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        log.info("deleteBooking");
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //POR ID DE USER
        @GetMapping("/user")
        public ResponseEntity<List<Booking>> getBookingsByUserId(@PathVariable Long userId) {
            List<Booking> bookings = bookingService.findBookingsByUserId(userId);
            return ResponseEntity.ok(bookings);
        }
}
