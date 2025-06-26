package com.booking.movieticket.controller;

import com.booking.movieticket.model.Theatre;
import com.booking.movieticket.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @PostMapping
    public Theatre createTheatre(@RequestBody Theatre theatre) {
        return theatreService.saveTheatre(theatre);
    }

    @GetMapping
    public List<Theatre> getAllTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/{id}")
    public Theatre getTheatreById(@PathVariable Long id) {
        return theatreService.getTheatreById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatre) {
        Theatre updated = theatreService.updateTheatre(id, theatre);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
    }
}
