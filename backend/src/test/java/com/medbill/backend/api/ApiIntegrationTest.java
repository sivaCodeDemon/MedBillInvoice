package com.medbill.backend.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:medbill-api;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.flyway.enabled=false"
})
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldListOrganizations() throws Exception {
        mockMvc.perform(get("/api/v1/organizations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreatePatientAndInvoice() throws Exception {
        mockMvc.perform(post("/api/v1/patients")
                        .contentType("application/json")
                        .content("{\"firstName\":\"Aisha\",\"lastName\":\"Patel\",\"mrn\":\"MRN-1001\",\"organizationId\":\"org-1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(post("/api/v1/invoices")
                        .contentType("application/json")
                        .content("{\"patientId\":\"patient-1\",\"organizationId\":\"org-1\",\"branchId\":\"branch-1\",\"departmentId\":\"dept-1\",\"items\":[{\"description\":\"Consultation\",\"quantity\":1,\"unitPrice\":1000,\"discount\":100,\"tax\":90}],\"status\":\"DRAFT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.invoiceNumber").exists());
    }

    @Test
    @WithMockUser(username = "cashier", roles = "CASHIER")
    void shouldCreatePaymentForExistingInvoice() throws Exception {
        mockMvc.perform(post("/api/v1/payments")
                        .contentType("application/json")
                        .content("{\"invoiceId\":\"inv-1\",\"amount\":500,\"method\":\"CARD\",\"reference\":\"REF-1001\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId").exists());
    }

        @Test
        @WithMockUser(username = "admin", roles = "ADMIN")
        void shouldDeleteInvoice() throws Exception {
                mockMvc.perform(delete("/api/v1/invoices/inv-1"))
                                .andExpect(status().isNoContent());
        }
}
