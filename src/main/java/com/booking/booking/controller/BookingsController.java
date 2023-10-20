package com.booking.booking.controller;
import com.booking.booking.service.BookingsService;
import com.booking.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{bookings}")

public class BookingsController {
    private BookingsService bookingsService;

    public  BookingsController(BookingsService bookingsService) {
        this.bookingsService = bookingsService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllBookingss() {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", bookingsService.getAllBookings(), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }
    }

    @GetMapping("/{bookingsId}")
    public ResponseEntity<ApiResponse> getBookingsbyId(@PathVariable String bookingsId) {
        try {
            return ResponseEntity.ok(new ApiResponse(true, "Success", bookingsService.getBookingsById(bookingsId), null));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ApiResponse(false, "An error occurred.", null, e.getMessage()));
        }

    }
}