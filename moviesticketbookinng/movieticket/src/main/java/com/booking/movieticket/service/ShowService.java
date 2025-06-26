package com.booking.movieticket.service;

import com.booking.movieticket.model.Show;
import com.booking.movieticket.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }

    public Show updateShow(Long id, Show newShow) {
        Show existing = showRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setDate(newShow.getDate());
            existing.setTime(newShow.getTime());
            existing.setPrice(newShow.getPrice());
            existing.setMovie(newShow.getMovie());
            existing.setTheatre(newShow.getTheatre());
            return showRepository.save(existing);
        }
        return null;
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}
