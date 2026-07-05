package com.artisticankit.paintingstore.controller;

import com.artisticankit.paintingstore.entity.Painting;
import com.artisticankit.paintingstore.service.PaintingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller exposing CRUD endpoints for Painting resources.
 * Base URL: /api/paintings
 */
@RestController
@RequestMapping("/api/paintings")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class PaintingController {

    private final PaintingService paintingService;

    // ─────────────────────────────────────────────
    // GET /api/paintings  →  Retrieve all paintings
    // ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<Painting>> getAllPaintings() {
        log.info("GET /api/paintings - Fetching all paintings");
        List<Painting> paintings = paintingService.getAllPaintings();
        return ResponseEntity.ok(paintings);
    }

    // ─────────────────────────────────────────────────────
    // GET /api/paintings/{id}  →  Retrieve painting by ID
    // ─────────────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<Painting> getPaintingById(@PathVariable Long id) {
        log.info("GET /api/paintings/{} - Fetching painting by id", id);
        return paintingService.getPaintingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────────────────────
    // GET /api/paintings/category/{category}  →  By category
    // ─────────────────────────────────────────────────────────
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Painting>> getPaintingsByCategory(@PathVariable String category) {
        log.info("GET /api/paintings/category/{} - Fetching paintings by category", category);
        List<Painting> paintings = paintingService.getPaintingsByCategory(category);
        return ResponseEntity.ok(paintings);
    }

    // ─────────────────────────────────────────────────────────────
    // GET /api/paintings/status/{status}  →  By availability status
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Painting>> getPaintingsByStatus(
            @PathVariable Painting.AvailabilityStatus status) {
        log.info("GET /api/paintings/status/{} - Fetching paintings by status", status);
        List<Painting> paintings = paintingService.getPaintingsByStatus(status);
        return ResponseEntity.ok(paintings);
    }

    // ─────────────────────────────────────────────────────────────────────
    // GET /api/paintings/search?keyword=...  →  Search paintings by title
    // ─────────────────────────────────────────────────────────────────────
    @GetMapping("/search")
    public ResponseEntity<List<Painting>> searchPaintings(
            @RequestParam String keyword) {
        log.info("GET /api/paintings/search?keyword={} - Searching paintings", keyword);
        List<Painting> paintings = paintingService.searchPaintingsByTitle(keyword);
        return ResponseEntity.ok(paintings);
    }

    // ──────────────────────────────────────────────
    // POST /api/paintings  →  Create a new painting
    // ──────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<Painting> createPainting(@RequestBody Painting painting) {
        log.info("POST /api/paintings - Creating painting: {}", painting.getTitle());
        Painting created = paintingService.createPainting(painting);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ──────────────────────────────────────────────────
    // PUT /api/paintings/{id}  →  Update existing painting
    // ──────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<Painting> updatePainting(
            @PathVariable Long id,
            @RequestBody Painting painting) {
        log.info("PUT /api/paintings/{} - Updating painting", id);
        try {
            Painting updated = paintingService.updatePainting(id, painting);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ────────────────────────────────────────────────
    // DELETE /api/paintings/{id}  →  Delete a painting
    // ────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainting(@PathVariable Long id) {
        log.info("DELETE /api/paintings/{} - Deleting painting", id);
        try {
            paintingService.deletePainting(id);
            return ResponseEntity.noContent().build();
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ────────────────────────────────────────────────
    // PUT /api/paintings/{id}/like  →  Increment likes
    // ────────────────────────────────────────────────
    @PutMapping("/{id}/like")
    public ResponseEntity<Painting> likePainting(@PathVariable Long id) {
        log.info("PUT /api/paintings/{}/like - Incrementing like count", id);
        try {
            Painting updated = paintingService.incrementLikes(id);
            return ResponseEntity.ok(updated);
        } catch (jakarta.persistence.EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
