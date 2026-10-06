package com.medbill.backend.payment;

import java.math.BigDecimal;

public record Payment(
        String paymentId,
        String invoiceId,
        BigDecimal amount,
        String method,
        String reference,
        String status
) {
}
