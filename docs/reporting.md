# Reporting strategy

## Reporting engine

The reporting layer is designed around a server-side service that can generate:

- PDF
- Excel
- CSV
- HTML print previews

## Report types

- Revenue summary
- Aging and receivables
- Invoice history
- Claims
- Tax and insurance reports

## Integration approach

Use service-oriented report builders that accept filters and produce export artifacts securely from the backend. The current scaffold exposes the reporting API entry point and a foundation for extension.