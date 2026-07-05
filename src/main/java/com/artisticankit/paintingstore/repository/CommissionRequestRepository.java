package com.artisticankit.paintingstore.repository;

import com.artisticankit.paintingstore.entity.CommissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommissionRequestRepository extends JpaRepository<CommissionRequest, Long> {
    List<CommissionRequest> findAllByOrderByCreatedAtDesc();
}
