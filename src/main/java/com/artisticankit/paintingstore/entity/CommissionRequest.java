package com.artisticankit.paintingstore.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commission_requests")
public class CommissionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "painting_type", nullable = false)
    private String paintingType;

    private String size;
    private String budget;
    private String deadline;

    @Column(length = 1000)
    private String reference;

    @Column(length = 2000)
    private String message;

    @Column(nullable = false)
    private String status = "PENDING"; // PENDING, ACCEPTED, COMPLETED, REJECTED

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPaintingType() { return paintingType; }
    public void setPaintingType(String paintingType) { this.paintingType = paintingType; }
    
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    
    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }
    
    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
    
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
