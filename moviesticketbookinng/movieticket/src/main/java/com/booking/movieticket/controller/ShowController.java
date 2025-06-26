package com.booking.movieticket.controller;

import com.booking.movieticket.model.Show;
import com.booking.movieticket.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.saveShow(show);
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show show) {
        Show updated = showService.updateShow(id, show);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
    }
}
