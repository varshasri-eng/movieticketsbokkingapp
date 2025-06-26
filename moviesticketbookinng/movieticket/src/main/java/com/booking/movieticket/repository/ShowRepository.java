package com.booking.movieticket.repository;

import com.booking.movieticket.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
