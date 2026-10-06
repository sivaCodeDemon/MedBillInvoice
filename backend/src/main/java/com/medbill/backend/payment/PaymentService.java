package com.medbill.backend.payment;

import com.medbill.backend.common.api.ApiException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PaymentService {

    private final Map<String, Payment> payments = new ConcurrentHashMap<>();

    public PaymentService() {
        payments.put("pay-1", new Payment("pay-1", "inv-1", new BigDecimal("500.00"), "CARD", "REF-1001", "POSTED"));
    }

    public List<Payment> getPayments() {
        return payments.values().stream().toList();
    }

    public Payment createPayment(PaymentCreateRequest request) {
        if (request == null || request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApiException("VALIDATION_ERROR", "Payment amount must be greater than zero");
        }
        if (request.invoiceId() == null || request.invoiceId().isBlank()) {
            throw new ApiException("VALIDATION_ERROR", "Invoice id is required");
        }

        String paymentId = "pay-" + (payments.size() + 1);
        Payment payment = new Payment(paymentId, request.invoiceId(), request.amount(), request.method(), request.reference(), "POSTED");
        payments.put(paymentId, payment);
        return payment;
    }
}
