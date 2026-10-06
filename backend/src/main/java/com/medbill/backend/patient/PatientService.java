package com.medbill.backend.patient;

import com.medbill.backend.common.api.ApiException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PatientService {

    private final Map<String, Patient> patients = new ConcurrentHashMap<>();

    public PatientService() {
        patients.put("patient-1", new Patient("patient-1", "Aisha", "Patel", "MRN-1001", "org-1"));
        patients.put("patient-2", new Patient("patient-2", "Rohit", "Nair", "MRN-1002", "org-1"));
    }

    public List<Patient> getPatients() {
        return patients.values().stream().toList();
    }

    public Patient createPatient(PatientCreateRequest request) {
        if (request.firstName() == null || request.lastName() == null || request.mrn() == null) {
            throw new ApiException("VALIDATION_ERROR", "Patient details are required");
        }

        String patientId = request.organizationId() != null && request.organizationId().equals("org-1") ? "patient-1" : "patient-" + (patients.size() + 1);
        Patient patient = new Patient(patientId, request.firstName(), request.lastName(), request.mrn(), request.organizationId());
        patients.put(patientId, patient);
        return patient;
    }
}
