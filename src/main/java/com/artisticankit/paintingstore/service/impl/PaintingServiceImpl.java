package com.artisticankit.paintingstore.service.impl;

import com.artisticankit.paintingstore.entity.Painting;
import com.artisticankit.paintingstore.repository.PaintingRepository;
import com.artisticankit.paintingstore.service.PaintingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the PaintingService interface.
 * Handles all business logic for painting operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepository paintingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Painting> getAllPaintings() {
        log.info("Fetching all paintings");
        return paintingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Painting> getPaintingById(Long id) {
        log.info("Fetching painting with id: {}", id);
        return paintingRepository.findById(id);
    }

    @Override
    public Painting createPainting(Painting painting) {
        log.info("Creating new painting: {}", painting.getTitle());
        if (painting.getAvailabilityStatus() == null) {
            painting.setAvailabilityStatus(Painting.AvailabilityStatus.AVAILABLE);
        }
        return paintingRepository.save(painting);
    }

    @Override
    public Painting updatePainting(Long id, Painting paintingDetails) {
        log.info("Updating painting with id: {}", id);
        Painting existingPainting = paintingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Painting not found with id: " + id));

        existingPainting.setTitle(paintingDetails.getTitle());
        existingPainting.setDescription(paintingDetails.getDescription());
        existingPainting.setPrice(paintingDetails.getPrice());
        existingPainting.setImageUrl(paintingDetails.getImageUrl());
        existingPainting.setCategory(paintingDetails.getCategory());
        existingPainting.setAvailabilityStatus(paintingDetails.getAvailabilityStatus());

        return paintingRepository.save(existingPainting);
    }

    @Override
    public void deletePainting(Long id) {
        log.info("Deleting painting with id: {}", id);
        if (!paintingRepository.existsById(id)) {
            throw new EntityNotFoundException("Painting not found with id: " + id);
        }
        paintingRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Painting> getPaintingsByCategory(String category) {
        log.info("Fetching paintings by category: {}", category);
        return paintingRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Painting> getPaintingsByStatus(Painting.AvailabilityStatus status) {
        log.info("Fetching paintings by status: {}", status);
        return paintingRepository.findByAvailabilityStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Painting> searchPaintingsByTitle(String keyword) {
        log.info("Searching paintings by title keyword: {}", keyword);
        return paintingRepository.findByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public Painting incrementLikes(Long id) {
        log.info("Incrementing likes for painting id: {}", id);
        Painting existingPainting = paintingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Painting not found with id: " + id));
        
        existingPainting.setLikesCount(existingPainting.getLikesCount() + 1);
        return paintingRepository.save(existingPainting);
    }
}
