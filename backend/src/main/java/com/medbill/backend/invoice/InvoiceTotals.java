package com.medbill.backend.invoice;

import java.math.BigDecimal;

public record InvoiceTotals(
        BigDecimal subtotal,
        BigDecimal discount,
        BigDecimal tax,
        BigDecimal insuranceAdjustment,
        BigDecimal patientResponsibility,
        BigDecimal grandTotal
) {
}
