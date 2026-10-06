package com.medbill.backend.patient;

public record Patient(String id, String firstName, String lastName, String mrn, String organizationId) {
}
