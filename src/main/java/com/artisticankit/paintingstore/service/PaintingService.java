package com.artisticankit.paintingstore.service;

import com.artisticankit.paintingstore.entity.Painting;

import java.util.List;
import java.util.Optional;

/**
 * Service interface defining the contract for all Painting-related business operations.
 */
public interface PaintingService {

    /**
     * Retrieve all paintings.
     * @return list of all paintings
     */
    List<Painting> getAllPaintings();

    /**
     * Retrieve a painting by its ID.
     * @param id the painting ID
     * @return Optional containing the painting if found
     */
    Optional<Painting> getPaintingById(Long id);

    /**
     * Create a new painting.
     * @param painting the painting object to create
     * @return the saved painting
     */
    Painting createPainting(Painting painting);

    /**
     * Update an existing painting.
     * @param id      the ID of the painting to update
     * @param painting the updated painting data
     * @return the updated painting
     */
    Painting updatePainting(Long id, Painting painting);

    /**
     * Delete a painting by its ID.
     * @param id the painting ID
     */
    void deletePainting(Long id);

    /**
     * Find paintings by category.
     * @param category the category name
     * @return list of paintings in the given category
     */
    List<Painting> getPaintingsByCategory(String category);

    /**
     * Find paintings by availability status.
     * @param status the availability status
     * @return list of paintings with the given status
     */
    List<Painting> getPaintingsByStatus(Painting.AvailabilityStatus status);

    /**
     * Search paintings by title keyword.
     * @param keyword the search keyword
     * @return list of paintings whose title matches the keyword
     */
    List<Painting> searchPaintingsByTitle(String keyword);

    /**
     * Increment the likes count for a specific painting.
     * @param id the painting ID
     * @return the updated painting
     */
    Painting incrementLikes(Long id);
}
