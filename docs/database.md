# Database design

## Core entities

```mermaid
erDiagram
    ORGANIZATION ||--o{ BRANCH : has
    BRANCH ||--o{ DEPARTMENT : contains
    ORGANIZATION ||--o{ PATIENT : serves
    PATIENT ||--o{ INVOICE : owns
    INVOICE ||--o{ INVOICE_ITEM : items
    INVOICE ||--o{ PAYMENT : settles
    PATIENT ||--o{ CLAIM : has
    ORGANIZATION ||--o{ USERS : manages
```

## Key constraints

- UUID primary keys
- Foreign keys for organization isolation
- Unique invoice numbers and MRNs
- Check constraints for financial validity
- Indexes for invoice number, patient, due dates, status fields
