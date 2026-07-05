package com.artisticankit.paintingstore.controller;

import com.artisticankit.paintingstore.entity.Painting;
import com.artisticankit.paintingstore.repository.PaintingRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.JSONObject;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // In production, restrict this
public class OrderController {

    @Value("${razorpay.key.id}")
    private String razorpayId;

    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    @Autowired
    private PaintingRepository paintingRepository;

    // Create Order
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            int amount = (int) data.get("amount"); // Amount in INR
            Long paintingId = Long.valueOf(data.get("paintingId").toString());

            // Initialize Razorpay Client
            RazorpayClient razorpay = new RazorpayClient(razorpayId, razorpaySecret);

            // Create Order Request
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // Amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "txn_" + System.currentTimeMillis());
            
            JSONObject notes = new JSONObject();
            notes.put("paintingId", paintingId);
            orderRequest.put("notes", notes);

            // Generate Order
            Order order = razorpay.orders.create(orderRequest);

            return ResponseEntity.ok(order.toString());
        } catch (RazorpayException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Error creating Razorpay Order\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Server error\"}");
        }
    }

    // Verify Payment Signature
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> payload) {
        try {
            String razorpayOrderId = payload.get("razorpay_order_id");
            String razorpayPaymentId = payload.get("razorpay_payment_id");
            String razorpaySignature = payload.get("razorpay_signature");
            Long paintingId = Long.valueOf(payload.get("paintingId"));

            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", razorpayOrderId);
            options.put("razorpay_payment_id", razorpayPaymentId);
            options.put("razorpay_signature", razorpaySignature);

            boolean isValid = Utils.verifyPaymentSignature(options, razorpaySecret);

            if (isValid) {
                // Payment is successful, mark painting as SOLD
                Optional<Painting> paintingOpt = paintingRepository.findById(paintingId);
                if (paintingOpt.isPresent()) {
                    Painting painting = paintingOpt.get();
                    painting.setStatus("SOLD");
                    paintingRepository.save(painting);
                }
                return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Payment verified\"}");
            } else {
                return ResponseEntity.status(400).body("{\"status\": \"failed\", \"message\": \"Invalid signature\"}");
            }

        } catch (RazorpayException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Razorpay signature verification failed\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"error\": \"Server error during verification\"}");
        }
    }
}
