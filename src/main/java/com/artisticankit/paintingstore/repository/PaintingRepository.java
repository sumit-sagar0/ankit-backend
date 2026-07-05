package com.artisticankit.paintingstore.repository;

import com.artisticankit.paintingstore.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Painting entity.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

    /**
     * Find all paintings by category (case-insensitive).
     */
    List<Painting> findByCategoryIgnoreCase(String category);

    /**
     * Find all paintings by availability status.
     */
    List<Painting> findByAvailabilityStatus(Painting.AvailabilityStatus status);

    /**
     * Find paintings whose title contains the given keyword (case-insensitive).
     */
    List<Painting> findByTitleContainingIgnoreCase(String keyword);
}
