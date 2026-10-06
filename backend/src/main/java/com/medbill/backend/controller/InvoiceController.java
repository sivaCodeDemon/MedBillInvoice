package com.medbill.backend.controller;

import com.medbill.backend.invoice.InvoiceCalculationService;
import com.medbill.backend.invoice.InvoiceCreateRequest;
import com.medbill.backend.invoice.InvoiceResponse;
import com.medbill.backend.invoice.InvoiceService;
import com.medbill.backend.invoice.InvoiceTotals;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Invoices", description = "Invoice lifecycle management")
public class InvoiceController {

    private final InvoiceCalculationService invoiceCalculationService;
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceCalculationService invoiceCalculationService, InvoiceService invoiceService) {
        this.invoiceCalculationService = invoiceCalculationService;
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public ResponseEntity<Map<String, Object>> listInvoices() {
        List<InvoiceResponse> invoices = invoiceService.getInvoices();
        InvoiceTotals totals = invoiceCalculationService.calculateTotals(
                invoices.stream().map(InvoiceResponse::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add),
                invoices.stream().map(InvoiceResponse::discount).reduce(BigDecimal.ZERO, BigDecimal::add),
                invoices.stream().map(InvoiceResponse::tax).reduce(BigDecimal.ZERO, BigDecimal::add),
                invoices.stream().map(InvoiceResponse::insuranceAdjustment).reduce(BigDecimal.ZERO, BigDecimal::add),
                invoices.stream().map(InvoiceResponse::patientResponsibility).reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        return ResponseEntity.ok(Map.of(
                "content", invoices,
                "page", Map.of("number", 0, "size", 20, "totalElements", invoices.size()),
                "summary", totals
        ));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> getInvoice(@PathVariable String id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @PostMapping("/invoices")
    public ResponseEntity<InvoiceResponse> createInvoice(@RequestBody InvoiceCreateRequest request) {
        return ResponseEntity.ok(invoiceService.createInvoice(request));
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<InvoiceResponse> updateInvoice(@PathVariable String id, @RequestBody InvoiceCreateRequest request) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, request));
    }

    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/reports/revenue")
    public ResponseEntity<Map<String, Object>> revenueReport(@RequestParam(required = false) String period) {
        return ResponseEntity.ok(Map.of(
                "period", period == null ? "monthly" : period,
                "revenue", BigDecimal.valueOf(124000.00),
                "taxCollected", BigDecimal.valueOf(9300.00),
                "discounts", BigDecimal.valueOf(5600.00),
                "refunds", BigDecimal.valueOf(2200.00),
                "collectionRate", 89.4
        ));
    }
}
