package com.medbill.backend.organization;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrganizationService {

    private final Map<String, Organization> organizations = new ConcurrentHashMap<>();

    public OrganizationService() {
        organizations.put("org-1", new Organization("org-1", "City General Hospital", "CGH", "ACTIVE"));
        organizations.put("org-2", new Organization("org-2", "Sunrise Women & Child Care", "SWC", "ACTIVE"));
    }

    public List<Organization> getOrganizations() {
        return organizations.values().stream().toList();
    }

    public Organization createOrganization(String name, String code) {
        String id = "org-" + (organizations.size() + 1);
        Organization organization = new Organization(id, name, code, "ACTIVE");
        organizations.put(id, organization);
        return organization;
    }
}
