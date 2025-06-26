package com.booking.movieticket.service;

import com.booking.movieticket.model.Movie;
import com.booking.movieticket.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existing = movieRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setTitle(updatedMovie.getTitle());
            existing.setGenre(updatedMovie.getGenre());
            existing.setLanguage(updatedMovie.getLanguage());
            existing.setDuration(updatedMovie.getDuration());
            existing.setDescription(updatedMovie.getDescription());
            return movieRepository.save(existing);
        }
        return null;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
