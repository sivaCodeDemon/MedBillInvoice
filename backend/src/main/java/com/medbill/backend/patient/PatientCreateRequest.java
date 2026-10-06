package com.medbill.backend.patient;

public record PatientCreateRequest(String firstName, String lastName, String mrn, String organizationId) {
}
