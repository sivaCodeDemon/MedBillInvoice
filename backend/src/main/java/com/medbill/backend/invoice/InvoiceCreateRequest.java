package com.medbill.backend.invoice;

import java.util.List;

public record InvoiceCreateRequest(
        String patientId,
        String organizationId,
        String branchId,
        String departmentId,
        List<InvoiceItemRequest> items,
        String status
) {
}
