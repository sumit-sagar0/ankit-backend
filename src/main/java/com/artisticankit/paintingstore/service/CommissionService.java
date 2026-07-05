package com.artisticankit.paintingstore.service;

import com.artisticankit.paintingstore.dto.CommissionDTO;
import com.artisticankit.paintingstore.entity.CommissionRequest;

import java.util.List;

public interface CommissionService {
    CommissionRequest submitCommission(CommissionDTO dto);
    List<CommissionRequest> getAllCommissions();
    CommissionRequest updateStatus(Long id, String status);
}
