package com.medbill.backend;

import com.medbill.backend.invoice.InvoiceCalculationService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceCalculationServiceTest {

    @Test
    void shouldCalculateInvoiceTotalsCorrectly() {
        InvoiceCalculationService service = new InvoiceCalculationService();

        var totals = service.calculateTotals(
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(90),
                BigDecimal.valueOf(80),
                BigDecimal.valueOf(30)
        );

        assertEquals(new BigDecimal("900.00"), totals.subtotal());
        assertEquals(new BigDecimal("100.00"), totals.discount());
        assertEquals(new BigDecimal("90.00"), totals.tax());
        assertEquals(new BigDecimal("80.00"), totals.insuranceAdjustment());
        assertEquals(new BigDecimal("30.00"), totals.patientResponsibility());
    }
}
