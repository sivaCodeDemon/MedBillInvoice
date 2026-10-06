package com.medbill.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Dashboard", description = "Revenue and operational metrics")
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> dashboard() {
        return ResponseEntity.ok(Map.of(
                "revenue", BigDecimal.valueOf(124000.00),
                "outstanding", BigDecimal.valueOf(210000.00),
                "paidPercent", 89,
                "claims", 142,
                "recentInvoices", List.of(
                        Map.of("invoiceNumber", "INV-2026-000001", "patient", "Rahul Sharma", "amount", BigDecimal.valueOf(14500.00), "status", "PAID"),
                        Map.of("invoiceNumber", "INV-2026-000002", "patient", "Ananya Verma", "amount", BigDecimal.valueOf(9800.00), "status", "PARTIALLY_PAID")
                ),
                "recentPayments", List.of(
                        Map.of("paymentId", "PAY-1001", "amount", BigDecimal.valueOf(5400.00), "method", "CARD"),
                        Map.of("paymentId", "PAY-1002", "amount", BigDecimal.valueOf(2100.00), "method", "UPI")
                )
        ));
    }
}
