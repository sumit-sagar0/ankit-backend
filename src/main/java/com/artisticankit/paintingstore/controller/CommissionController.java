package com.artisticankit.paintingstore.controller;

import com.artisticankit.paintingstore.dto.CommissionDTO;
import com.artisticankit.paintingstore.entity.CommissionRequest;
import com.artisticankit.paintingstore.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commissions")
@CrossOrigin(origins = "http://localhost:5173")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @PostMapping
    public ResponseEntity<?> submitCommission(@RequestBody CommissionDTO dto) {
        try {
            CommissionRequest saved = commissionService.submitCommission(dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error submitting commission: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<CommissionRequest>> getAllCommissions() {
        return ResponseEntity.ok(commissionService.getAllCommissions());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            CommissionRequest updated = commissionService.updateStatus(id, status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
