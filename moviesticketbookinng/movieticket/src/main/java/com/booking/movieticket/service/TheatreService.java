package com.booking.movieticket.service;

import com.booking.movieticket.model.Theatre;
import com.booking.movieticket.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }

    public Theatre updateTheatre(Long id, Theatre newTheatre) {
        Theatre existing = theatreRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(newTheatre.getName());
            existing.setLocation(newTheatre.getLocation());
            return theatreRepository.save(existing);
        }
        return null;
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}
