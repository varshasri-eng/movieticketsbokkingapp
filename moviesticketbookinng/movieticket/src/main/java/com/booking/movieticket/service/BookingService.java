package com.booking.movieticket.service;

import com.booking.movieticket.model.Booking;
import com.booking.movieticket.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        booking.setTotalPrice(booking.getShow().getPrice() * booking.getNumberOfSeats());
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking updateBooking(Long id, Booking newBooking) {
        Booking existing = bookingRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setNumberOfSeats(newBooking.getNumberOfSeats());
            existing.setUser(newBooking.getUser());
            existing.setShow(newBooking.getShow());
            existing.setTotalPrice(newBooking.getShow().getPrice() * newBooking.getNumberOfSeats());
            return bookingRepository.save(existing);
        }
        return null;
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
