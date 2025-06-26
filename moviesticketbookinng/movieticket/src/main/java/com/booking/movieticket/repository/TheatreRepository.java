package com.booking.movieticket.repository;

import com.booking.movieticket.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
