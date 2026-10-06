# MedBill Pro

MedBill Pro is a healthcare revenue and invoice management platform built as a modular monorepo with a Java 17 Spring Boot backend and a React + TypeScript frontend.

## Tech stack

- Backend: Java 17, Spring Boot 3.3.x, Spring Security, JPA, JWT, Flyway, PostgreSQL, Actuator, OpenAPI
- Frontend: React 19, TypeScript, Vite, TanStack Query, MUI, Recharts

## Structure

```text
medbill-pro/
├── backend/
├── frontend/
├── docs/
├── README.md
└── .gitignore
```

## Run locally

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

### Database

Use PostgreSQL locally with the default database settings in `backend/src/main/resources/application.yml`.

## Core modules

- Authentication and RBAC
- Organization and branch hierarchy
- Patient billing profiles
- Invoice engine and calculations
- Payments and receipts
- Insurance and claims
- Refunds and credit/debit notes
- Reconciliation and aging reports
- Dashboard analytics and reporting

## Notes

This starter implements the foundation, security configuration, modern UI shell, invoice totals service, and documentation. It is intended to be extended to a full production billing platform in line with the specification.
