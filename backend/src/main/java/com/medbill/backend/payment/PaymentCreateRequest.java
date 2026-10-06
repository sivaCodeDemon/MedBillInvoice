package com.medbill.backend.payment;

import java.math.BigDecimal;

public record PaymentCreateRequest(String invoiceId, BigDecimal amount, String method, String reference) {
}
