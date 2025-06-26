package com.booking.movieticket.repository;

import com.booking.movieticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);
}
