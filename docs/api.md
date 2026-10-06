# API documentation

## Base path

`/api/v1`

## Key endpoints

- `POST /auth/login`
- `POST /auth/refresh`
- `GET /dashboard`
- `GET /invoices`
- `GET /invoices/{id}`
- `GET /reports/revenue`

## Error format

```json
{
  "timestamp": "2026-10-05T00:00:00Z",
  "status": 400,
  "code": "VALIDATION_ERROR",
  "message": "Validation failed",
  "path": "/api/v1/invoices",
  "errors": [],
  "traceId": "..."
}
```
