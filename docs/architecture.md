# Architecture

## System overview

```mermaid
flowchart LR
    UI[React Frontend] --> API[Spring Boot API]
    API --> DB[(PostgreSQL)]
    API --> JWT[JWT / RBAC]
    API --> REPORTS[Reporting Service]
    REPORTS --> PDF[PDF / Excel / CSV]
```

## Module layout

- auth
- organization
- patient
- invoice
- payment
- insurance
- claim
- report
- audit
- notification
- approval

## Principles

- Domain-driven modular monolith
- DTO-based API exposure
- Backend validation and financial calculations
- Flyway for schema evolution
- PostgreSQL for transactional operations
