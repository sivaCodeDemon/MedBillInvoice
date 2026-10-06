package com.medbill.backend.invoice;

import java.math.BigDecimal;
import java.util.List;

public record InvoiceResponse(
        String id,
        String invoiceNumber,
        String patientId,
        String organizationId,
        String status,
        BigDecimal subtotal,
        BigDecimal discount,
        BigDecimal tax,
        BigDecimal insuranceAdjustment,
        BigDecimal patientResponsibility,
        BigDecimal grandTotal,
        List<InvoiceItemRequest> items
) {
}
