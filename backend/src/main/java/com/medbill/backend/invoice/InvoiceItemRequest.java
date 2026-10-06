package com.medbill.backend.invoice;

import java.math.BigDecimal;

public record InvoiceItemRequest(
        String description,
        BigDecimal quantity,
        BigDecimal unitPrice,
        BigDecimal discount,
        BigDecimal tax,
        BigDecimal insuranceCoverage
) {
}
