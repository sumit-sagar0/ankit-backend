package com.artisticankit.paintingstore.dto;

public class CommissionDTO {
    private String name;
    private String email;
    private String paintingType;
    private String size;
    private String budget;
    private String deadline;
    private String reference;
    private String message;

    // Getters and Setters
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
}
