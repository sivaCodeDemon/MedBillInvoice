package com.medbill.backend.invoice;

import com.medbill.backend.common.api.ApiException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InvoiceService {

    private final Map<String, InvoiceResponse> invoices = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    public InvoiceService() {
        invoices.put("inv-1", new InvoiceResponse(
                "inv-1",
                "INV-2026-000001",
                "patient-1",
                "org-1",
                "PAID",
                new BigDecimal("1000.00"),
                new BigDecimal("100.00"),
                new BigDecimal("90.00"),
                new BigDecimal("80.00"),
                new BigDecimal("910.00"),
                new BigDecimal("910.00"),
                List.of(new InvoiceItemRequest("Consultation", BigDecimal.ONE, new BigDecimal("1000.00"), new BigDecimal("100.00"), new BigDecimal("90.00"), new BigDecimal("80.00")))
        ));
    }

    public List<InvoiceResponse> getInvoices() {
        return invoices.values().stream().toList();
    }

    public InvoiceResponse getInvoice(String id) {
        return invoices.get(id);
    }

    public InvoiceResponse createInvoice(InvoiceCreateRequest request) {
        if (request == null || request.items() == null || request.items().isEmpty()) {
            throw new ApiException("VALIDATION_ERROR", "Invoice items are required");
        }

        BigDecimal subtotal = request.items().stream()
                .map(item -> item.unitPrice().multiply(item.quantity() == null ? BigDecimal.ONE : item.quantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal discount = request.items().stream()
                .map(item -> item.discount() == null ? BigDecimal.ZERO : item.discount())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal tax = request.items().stream()
                .map(item -> item.tax() == null ? BigDecimal.ZERO : item.tax())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal insuranceAdjustment = request.items().stream()
                .map(item -> item.insuranceCoverage() == null ? BigDecimal.ZERO : item.insuranceCoverage())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal patientResponsibility = subtotal.subtract(discount).add(tax).subtract(insuranceAdjustment)
                .setScale(2, RoundingMode.HALF_UP);

        InvoiceResponse response = new InvoiceResponse(
                "inv-" + sequence.getAndIncrement(),
                "INV-2026-" + String.format("%06d", sequence.get()),
                request.patientId() == null ? "patient-1" : request.patientId(),
                request.organizationId() == null ? "org-1" : request.organizationId(),
                request.status() == null ? "DRAFT" : request.status(),
                subtotal,
                discount,
                tax,
                insuranceAdjustment,
                patientResponsibility,
                patientResponsibility,
                request.items()
        );

        invoices.put(response.id(), response);
        return response;
    }

    public InvoiceResponse updateInvoice(String id, InvoiceCreateRequest request) {
        InvoiceResponse existing = invoices.get(id);
        if (existing == null) {
            throw new ApiException("NOT_FOUND", "Invoice not found");
        }

        InvoiceResponse updated = new InvoiceResponse(
                existing.id(),
                existing.invoiceNumber(),
                request.patientId() == null ? existing.patientId() : request.patientId(),
                request.organizationId() == null ? existing.organizationId() : request.organizationId(),
                request.status() == null ? existing.status() : request.status(),
                existing.subtotal(),
                existing.discount(),
                existing.tax(),
                existing.insuranceAdjustment(),
                existing.patientResponsibility(),
                existing.grandTotal(),
                request.items() == null ? existing.items() : request.items()
        );
        invoices.put(id, updated);
        return updated;
    }

    public void deleteInvoice(String id) {
        if (invoices.remove(id) == null) {
            throw new ApiException("NOT_FOUND", "Invoice not found");
        }
    }
}
