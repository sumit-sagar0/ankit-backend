package com.artisticankit.paintingstore.service.impl;

import com.artisticankit.paintingstore.dto.CommissionDTO;
import com.artisticankit.paintingstore.entity.CommissionRequest;
import com.artisticankit.paintingstore.repository.CommissionRequestRepository;
import com.artisticankit.paintingstore.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRequestRepository repository;

    @Override
    public CommissionRequest submitCommission(CommissionDTO dto) {
        CommissionRequest request = new CommissionRequest();
        request.setName(dto.getName());
        request.setEmail(dto.getEmail());
        request.setPaintingType(dto.getPaintingType());
        request.setSize(dto.getSize());
        request.setBudget(dto.getBudget());
        request.setDeadline(dto.getDeadline());
        request.setReference(dto.getReference());
        request.setMessage(dto.getMessage());

        CommissionRequest saved = repository.save(request);

        // Mocking the Email sending process since SMTP is not configured yet
        sendMockEmail(saved);

        return saved;
    }

    @Override
    public List<CommissionRequest> getAllCommissions() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public CommissionRequest updateStatus(Long id, String status) {
        Optional<CommissionRequest> opt = repository.findById(id);
        if (opt.isPresent()) {
            CommissionRequest req = opt.get();
            req.setStatus(status);
            return repository.save(req);
        }
        throw new RuntimeException("Commission request not found with id: " + id);
    }

    @Override
    public void deleteCommission(Long id) {
        CommissionRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission request not found with id: " + id));
        repository.delete(request);
    }

    private void sendMockEmail(CommissionRequest request) {
        System.out.println("==================================================");
        System.out.println("📧 SIMULATED EMAIL DISPATCH (No SMTP configured)");
        System.out.println("==================================================");
        System.out.println("To: artisticankit0@gmail.com");
        System.out.println("Subject: New Commission Request from " + request.getName());
        System.out.println("--------------------------------------------------");
        System.out.println("Hello Ankit,");
        System.out.println("You have received a new commission request from your Studio Gallery!\n");
        System.out.println("🧑‍🎨 Client Name  : " + request.getName());
        System.out.println("✉️ Client Email : " + request.getEmail());
        System.out.println("🎨 Painting Type: " + request.getPaintingType());
        if (request.getSize() != null && !request.getSize().isEmpty()) System.out.println("📏 Canvas Size  : " + request.getSize());
        if (request.getBudget() != null && !request.getBudget().isEmpty()) System.out.println("💰 Budget       : ₹" + request.getBudget());
        if (request.getDeadline() != null && !request.getDeadline().isEmpty()) System.out.println("⏱️ Deadline     : " + request.getDeadline());
        if (request.getReference() != null && !request.getReference().isEmpty()) System.out.println("🔗 Reference    : " + request.getReference());
        if (request.getMessage() != null && !request.getMessage().isEmpty()) System.out.println("📝 Message      : " + request.getMessage());
        System.out.println("\nLog into your Admin Panel to accept or reject this request.");
        System.out.println("==================================================");
    }
}
