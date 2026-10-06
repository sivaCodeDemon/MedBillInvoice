package com.medbill.backend.invoice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InvoiceCalculationService {

    public InvoiceTotals calculateTotals(
            BigDecimal amount,
            BigDecimal discount,
            BigDecimal tax,
            BigDecimal insuranceAdjustment,
            BigDecimal patientResponsibility
    ) {
        BigDecimal safeAmount = ensurePositive(amount);
        BigDecimal safeDiscount = ensurePositive(discount);
        BigDecimal safeTax = ensurePositive(tax);
        BigDecimal safeInsuranceAdjustment = ensurePositive(insuranceAdjustment);
        BigDecimal safePatientResponsibility = ensurePositive(patientResponsibility);

        BigDecimal subtotal = safeAmount.subtract(safeDiscount).setScale(2, RoundingMode.HALF_UP);
        BigDecimal grandTotal = subtotal.add(safeTax).subtract(safeInsuranceAdjustment).setScale(2, RoundingMode.HALF_UP);

        return new InvoiceTotals(
                subtotal,
                safeDiscount.setScale(2, RoundingMode.HALF_UP),
                safeTax.setScale(2, RoundingMode.HALF_UP),
                safeInsuranceAdjustment.setScale(2, RoundingMode.HALF_UP),
                safePatientResponsibility.setScale(2, RoundingMode.HALF_UP),
                grandTotal
        );
    }

    private BigDecimal ensurePositive(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
