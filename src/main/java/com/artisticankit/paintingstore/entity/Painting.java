package com.artisticankit.paintingstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * Entity class representing a Painting in the store.
 */
@Entity
@Table(name = "paintings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Painting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(length = 100)
    private String category;

    @Column(name = "availability_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "youtube_url", length = 500)
    private String youtubeUrl;

    @Column(name = "likes_count", nullable = false)
    private int likesCount = 0;

    /**
     * Enum representing the availability status of a painting.
     */
    public enum AvailabilityStatus {
        AVAILABLE,
        SOLD,
        RESERVED
    }
}
